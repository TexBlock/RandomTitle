package org.thinkingstudio.randomtitlerework;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class RTRModPlatform {
    @ExpectPlatform
    public static int getModListSize() {
        throw new AssertionError();
    }
}
