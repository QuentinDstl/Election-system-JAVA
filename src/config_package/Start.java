package config_package;

public class Start {
    public static void start(String ... args) {
        String xlsxName = "";
        switch (args.length) {
            case 2:
                // load the xslx file
                xlsxName = args[1];
            case 1:
                // load the config and the default xlsx if its havent been load yet
                Config.initConfig(args[0],xlsxName);
                break;
            default:
                // load the default file
                Config.initConfig();
                break;
        }
    }
}
