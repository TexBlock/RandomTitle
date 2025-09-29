package org.thinkingstudio.randomtitlerework.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import org.thinkingstudio.randomtitlerework.RandomTitleReworkMod;
import org.thinkingstudio.randomtitlerework.config.RTRModConfigHelper;

@Mod(value = RandomTitleReworkMod.MOD_ID, dist = Dist.CLIENT)
public final class RandomTitleReworkModNeoForge {
    public RandomTitleReworkModNeoForge(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (container, screen) -> RTRModConfigHelper.setConfigScreen(screen));
    }
}
