package org.thinkingstudio.randomtitlerework.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

public class RTRModConfigHelper {
    private static RTRModConfigs modConfigs;

    public static RTRModConfigs getConfig() {
        if (modConfigs == null) {
            AutoConfig.register(RTRModConfigs.class, Toml4jConfigSerializer::new);
            modConfigs = AutoConfig.getConfigHolder(RTRModConfigs.class).getConfig();
        }
        return modConfigs;
    }

    public static Screen setConfigScreen(Screen screen) {
        return AutoConfig.getConfigScreen(RTRModConfigs.class, screen).get();
    }
}
