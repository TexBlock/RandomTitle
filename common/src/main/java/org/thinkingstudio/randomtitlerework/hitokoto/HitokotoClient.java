package org.thinkingstudio.randomtitlerework.hitokoto;

import com.google.gson.Gson;
import com.mojang.authlib.minecraft.client.ObjectMapper;
import org.thinkingstudio.randomtitlerework.RandomTitleReworkMod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HitokotoClient {
    private static final String API_URL = "https://v1.hitokoto.cn";
    private final ObjectMapper objectMapper;

    public HitokotoClient() {
        this.objectMapper = new ObjectMapper(new Gson());
    }

    /**
     * 获取随机一言
     * @return Hitokoto对象
     * @throws IOException 网络或解析异常
     */
    public Hitokoto getRandomHitokoto() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Java Hitokoto Client");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            RandomTitleReworkMod.LOGGER.info("Hitokoto Response String: " + response);
            return objectMapper.readValue(response.toString(), Hitokoto.class);
        } finally {
            connection.disconnect();
        }
    }
}

