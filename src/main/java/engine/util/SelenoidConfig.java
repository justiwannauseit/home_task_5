package engine.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SelenoidConfig {

    private static SelenoidConfig INSTANCE;
    private final static Scanner scanner = new Scanner(System.in);

    private static String browserName;
    private static String browserVersion;
    private static String port;
    private static String directoryPath;

    private SelenoidConfig() {
    }

    public static SelenoidConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SelenoidConfig();
            INSTANCE.initBrowserName();
            INSTANCE.initBrowserVersion();
            INSTANCE.initPort();
            INSTANCE.initDirectoryPath();
            scanner.close();
        }
        return INSTANCE;
    }

    private void initBrowserName() {
        System.out.println("Выберете браузер, введя только цифру соответсвующего браузера:");
        System.out.println("1 - chrome");
        System.out.println("2 - opera");
        System.out.println("3 - firefox");
        System.out.println("4 - chrome-mobile");
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                SelenoidConfig.browserName = "chrome";
                break;
            case 2:
                SelenoidConfig.browserName = "opera";
                break;
            case 3:
                SelenoidConfig.browserName = "firefox";
                break;
            case 4:
                SelenoidConfig.browserName = "chrome-mobile";
                break;
            default:
                throw new NoSuchElementException("Ваше значение " + choose + " недопустимо");
        }
    }

    public void initBrowserVersion() {
        System.out.println("Введите вресию браузера. Например: 101.0");
        SelenoidConfig.browserVersion = scanner.next();
    }

    public void initPort() {
        System.out.println("Введите порт для селеноида...");
        System.out.println("Так вы сможете обращаться к селеноиду по адресу: \"http://localhost:{ваш порт}/wd/hub\"");
        SelenoidConfig.port = scanner.next();
    }

    public void initDirectoryPath() {
        System.out.println("Последний шаг...");
        System.out.println("Укажите путь для создания директории selenoid по шаблону:");
        System.out.println("C:/Users/username/selenoid");
        SelenoidConfig.directoryPath = scanner.next();

    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public String getPort() {
        return port;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

}
