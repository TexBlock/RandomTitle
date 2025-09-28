package org.thinkingstudio.randomtitlerework.forge;

import net.minecraftforge.fml.ModList;

public class RTRModPlatformImpl {
    public static int getModListSize() {
        return ModList.get().getMods().size();
    }
}
