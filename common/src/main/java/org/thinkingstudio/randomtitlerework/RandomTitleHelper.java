package org.thinkingstudio.randomtitlerework;

import org.thinkingstudio.randomtitlerework.config.RTRModConfigHelper;
import org.thinkingstudio.randomtitlerework.config.RandomTitleSource;
import org.thinkingstudio.randomtitlerework.hitokoto.Hitokoto;
import org.thinkingstudio.randomtitlerework.hitokoto.HitokotoClient;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RandomTitleHelper {
    private static String getTitleFromList() {
        String title = "";

        try {
            List<String> titles = RTRModConfigHelper.getConfig().titleList;
            title = titles.get(new Random().nextInt(titles.size()));
        } catch (Throwable e) {
            RandomTitleReworkMod.LOGGER.error("Failed to get title from config!", e);
        }

        return title;
    }

    private static String getTitleFromHitokoto() {
        RandomTitleReworkMod.LOGGER.info("Getting title from Hitokoto API.");

        String title;

        HitokotoClient hitokotoClient = new HitokotoClient();
        try {
            Hitokoto hitokoto = hitokotoClient.getRandomHitokoto();
            String type = hitokoto.getType();
            title = hitokoto.getHitokoto() + "   —— ";
            switch (type) {
                case "e":
                    title += hitokoto.getCreator() + " 原创";
                    break;
                case "f":
                    title += "来自网络";
                    break;
                default:
                    title += hitokoto.getFrom();
            }
        } catch (IOException e) {
            RandomTitleReworkMod.LOGGER.error("Failed to get title from API! {}",e.getMessage(), e);
            return getTitleFromList();
        }
        return title;
    }

    public static String getRandomTitle() {
        RandomTitleSource titleSource = RTRModConfigHelper.getConfig().randomTitleSource;

        if (titleSource == RandomTitleSource.HITOKOTO) {
            return getTitleFromHitokoto();
        } else if (titleSource == RandomTitleSource.LIST) {
            return getTitleFromList();
        } else if (titleSource == RandomTitleSource.BOTH) {
            boolean use = new Random().nextBoolean();
            if (use) {
                return getTitleFromList();
            } else {
                return getTitleFromHitokoto();
            }
        }

        return getTitleFromHitokoto();
    }
}
