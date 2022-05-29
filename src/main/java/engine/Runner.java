package engine;

import com.profesorfalken.jpowershell.PowerShell;
import engine.util.JsonGenerator;
import engine.util.PowerShellCommands;
import engine.util.SelenoidConfig;
import engine.util.StartScreen;

import java.util.Scanner;

public class Runner {

    private static Runner INSTANCE;
    private final static Scanner scanner = new Scanner(System.in);


    private Runner() {
    }

    public static Runner getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Runner();
        }
        return INSTANCE;
    }

    public void run() {
        StartScreen.createStartArea();
        System.out.println("(ВАЖНО!) Перед началом работы запустите Docker Quickstart Terminal");
        System.out.println("Введите цифру нужной команды:");
        System.out.println("1 - если вы хотите развернуть selenoid в docker контейнере");
        System.out.println("2 - если вы хотите удалить все images");
        String choose = scanner.next();
        switch (choose) {
            case "1":
                createSelenoidContainer();
                break;
            case "2":
                deleteAllContainers();
                break;
            default:
                System.exit(0);
        }
    }

    private void createSelenoidContainer() {
        SelenoidConfig config = SelenoidConfig.getInstance();
        JsonGenerator.createFile(config.getDirectoryPath(), config.getBrowserName(), config.getBrowserVersion());
        System.out.println(PowerShell.executeSingleCommand(PowerShellCommands.dockerRun(config.getDirectoryPath(), config.getPort())).getCommandOutput());
        System.out.println(PowerShell.executeSingleCommand(PowerShellCommands.dockerPs()).getCommandOutput());
        System.out.println(PowerShell.executeSingleCommand(PowerShellCommands.dockerPull(config.getBrowserName(), config.getBrowserVersion())).getCommandOutput());
        System.exit(0);
    }

    private void deleteAllContainers() {
        PowerShell.executeSingleCommand(PowerShellCommands.dockerStop());
        PowerShell.executeSingleCommand(PowerShellCommands.dockerRm());
        System.out.println("Удаленние docker images прошло успешно");
        System.exit(0);
    }

}

