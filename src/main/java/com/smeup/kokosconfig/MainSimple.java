package com.smeup.kokosconfig;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

public class MainSimple {

    public static void main(String[] args) {
        final Properties properties = new Properties();
        properties.setProperty("port", "80");
        properties.setProperty("rpgDirs", "c:/temp");
        properties.setProperty("env.rpgDirs", "c:/temp, c:/temp/myEnv");
        KokosConfiguration.registerKokosConfigurationProvider(env ->  {
            final KokosConfiguration kokosConfiguration = new KokosConfiguration();
            kokosConfiguration.setPort(Integer.parseInt(properties.getProperty("port")));
            final String rpgDirs = properties.getProperty(env + ".rpgDirs", properties.getProperty("rpgDirs"));
            kokosConfiguration.setRpgDirs(Arrays.stream(rpgDirs.split(",")).
                    map(s -> Paths.get(s.trim())).collect(Collectors.toList()));
            return kokosConfiguration;
        });
        System.out.println(KokosConfiguration.getInstance().getPort());
        System.out.println(KokosConfiguration.getInstance("env").getPort());
        System.out.println(KokosConfiguration.getInstance().getRpgDirs());
        System.out.println(KokosConfiguration.getInstance("env").getRpgDirs());

    }
}
