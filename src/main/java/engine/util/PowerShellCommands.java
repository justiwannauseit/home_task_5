package engine.util;

public class PowerShellCommands {

    private final static String commandDockerPs = "docker ps";
    private final static String commandDockerStop = "docker stop $(docker ps -a -q)";
    private final static String commandDockerRm = "docker rm $(docker ps -a -q)";

    private PowerShellCommands() {
    }

    public static String dockerPs() {
        return commandDockerPs;
    }

    public static String dockerStop() {
        return commandDockerStop;
    }

    public static String dockerRm() {
        return commandDockerRm;
    }

    public static String dockerRun(final String directoryPath, final String port) {
        return "docker run -d -p " + port +
                ":4444 --net=selenoid_1 -v " +
                directoryPath.replaceAll("C:/", "/c/") +
                ":/etc/selenoid:ro -v //var/run/docker.sock:/var/run/docker.sock aerokube/selenoid " +
                "-limit=5 -capture-driver-logs -max-timeout=0h20m0s -container-network=selenoid_1\n";
    }

    public static String dockerPull(final String browserName, String browserVersion) {
        return String.format("docker pull selenoid/%s:%s", browserName, browserVersion);
    }

}
