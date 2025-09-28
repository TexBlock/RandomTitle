package org.thinkingstudio.randomtitlerework.forge;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.loading.FMLLoader;
import org.thinkingstudio.randomtitlerework.RandomTitleReworkMod;
import org.thinkingstudio.randomtitlerework.config.RTRModConfigHelper;

@Mod(RandomTitleReworkMod.MOD_ID)
public final class RandomTitleReworkModForge {
    public RandomTitleReworkModForge() {
        if (FMLLoader.getDist().isClient()) {
            ModContainer modContainer = ModList.get().getModContainerById(RandomTitleReworkMod.MOD_ID).orElseThrow();
            modContainer.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));
            modContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> RTRModConfigHelper.setConfigScreen(screen)));
        } else if (FMLLoader.getDist().isDedicatedServer()) {
            throw new RuntimeException("This mod is a client-only mod. Currently, it is being run on a DedicatedServer, which is not supported.");
        }
    }
}
