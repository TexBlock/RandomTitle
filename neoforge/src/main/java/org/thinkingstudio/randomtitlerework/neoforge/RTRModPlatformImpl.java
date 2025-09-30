package org.thinkingstudio.randomtitlerework.neoforge;

import net.neoforged.fml.loading.FMLLoader;

public class RTRModPlatformImpl {
    public static int getModListSize() {
        return FMLLoader.getLoadingModList().getModFiles().size();
    }
}
