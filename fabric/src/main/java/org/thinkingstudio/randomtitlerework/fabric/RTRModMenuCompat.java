package org.thinkingstudio.randomtitlerework.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.thinkingstudio.randomtitlerework.config.RTRModConfigHelper;

public class RTRModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return RTRModConfigHelper::setConfigScreen;
    }
}
