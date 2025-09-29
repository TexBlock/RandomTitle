package org.thinkingstudio.randomtitlerework.neoforge;

import net.neoforged.fml.ModList;

public class RTRModPlatformImpl {
    public static int getModListSize() {
        return ModList.get().getMods().size();
    }
}
