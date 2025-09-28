package org.thinkingstudio.randomtitlerework.fabric;

import net.fabricmc.api.ClientModInitializer;

import org.thinkingstudio.randomtitlerework.RandomTitleReworkMod;

public final class RandomTitleReworkModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RandomTitleReworkMod.init();
    }
}
