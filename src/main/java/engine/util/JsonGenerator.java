package engine.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class JsonGenerator {

    private static String json = "{\n" +
            "    \"{browser}\": {\n" +
            "        \"default\": \"{version}\",\n" +
            "        \"versions\": {\n" +
            "            \"{version}\": {\n" +
            "                \"image\": \"selenoid/{browser}:{version}1\",\n" +
            "                \"port\": \"4444\",\n" +
            "                \"path\": \"/\"\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";

    private JsonGenerator() {
    }

    public static void createFile(String path, String browser, String version) {
        createFileByJsonBody(path + "/browsers.json", generateJsonBody(browser, version));
    }

    private static void createFileByJsonBody(String path, String body) {
        try {
            FileUtils.writeStringToFile(new File(path), body);
        } catch (IOException exception) {
            System.out.println("Некорректный путь! Возможно вы неправильно указали имя пользователя или не соответсвовали шаблону");
            exception.printStackTrace();
            System.exit(0);
        }
    }

    private static String generateJsonBody(String browser, String version) {
        return json
                .replaceAll("\\{browser}", browser)
                .replaceAll("\\{version}", version);
    }

}
