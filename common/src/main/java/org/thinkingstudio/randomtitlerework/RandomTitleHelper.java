package org.thinkingstudio.randomtitlerework;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.thinkingstudio.randomtitlerework.config.RTRModConfigHelper;
import org.thinkingstudio.randomtitlerework.config.RandomTitleSource;

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
        String response;

        try {
            response = EntityUtils.toString(HttpClients.createDefault().execute(new HttpGet("https://v1.hitokoto.cn/")).getEntity());
            RandomTitleReworkMod.LOGGER.info("Hitokoto Response String: " + response);
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            String from = json.get("from").getAsString();
            String sentence = json.get("hitokoto").getAsString();
            String type = json.get("type").getAsString();
            title = sentence + "   —— ";

            switch (type) {
                case "e":
                    title += json.get("creator").getAsString() + " 原创";
                    break;
                case "f":
                    title += "来自网络";
                    break;
                default:
                    title += from;
            }

        } catch (Throwable e) {
            RandomTitleReworkMod.LOGGER.error("Failed to get title from API! {}",e.getMessage(), e);
            return getTitleFromList();
        }
        return title;
    }

    public static String getRandomTitle() {
        RandomTitleSource titleSource = RTRModConfigHelper.getConfig().randomTitleSource;

        switch (titleSource) {
            case HITOKOTO -> getTitleFromHitokoto();
            case LIST -> getTitleFromList();
            case BOTH -> {
                boolean use = new Random().nextBoolean();
                if (use) {
                    return getTitleFromList();
                } else {
                    return getTitleFromHitokoto();
                }
            }
        }

        return getTitleFromList();
    }
}
