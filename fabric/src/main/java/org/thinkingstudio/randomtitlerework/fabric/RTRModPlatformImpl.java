package org.thinkingstudio.randomtitlerework.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class RTRModPlatformImpl {
    public static int getModListSize() {
        return FabricLoader.getInstance().getAllMods().size();
    }
}
