package com.smeup.kokosconfig;


import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class KokosConfiguration {

    private static Function<String, KokosConfiguration> KOKOS_CONFIGURATION_PROVIDER;

    private int  port;

    private List<Path> rpgDirs;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Path> getRpgDirs() {
        return rpgDirs;
    }

    public void setRpgDirs(List<Path> rpgDirs) {
        this.rpgDirs = rpgDirs;
    }

    public static void registerKokosConfigurationProvider(final Function<String, KokosConfiguration> kokosConfigurationProvider) {
        if (KOKOS_CONFIGURATION_PROVIDER != null) {
            throw new IllegalArgumentException("Kokos configuration provider already registered");
        } else {
            KOKOS_CONFIGURATION_PROVIDER = kokosConfigurationProvider;
        }
    }

    public static KokosConfiguration getInstance(final String env) {
        Objects.requireNonNull(KOKOS_CONFIGURATION_PROVIDER, "You need register kokos configuration supplier");
        return KOKOS_CONFIGURATION_PROVIDER.apply(env);
    }

    public static KokosConfiguration getInstance() {
        return getInstance(null);
    }
}
