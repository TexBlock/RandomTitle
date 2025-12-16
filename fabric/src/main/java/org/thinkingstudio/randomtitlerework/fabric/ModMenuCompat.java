package org.thinkingstudio.randomtitlerework.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.thinkingstudio.randomtitlerework.config.ConfigHelper;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ConfigHelper::setConfigScreen;
    }
}
