package org.thinkingstudio.randomtitlerework.hitokoto;

import com.google.gson.Gson;
import com.mojang.authlib.minecraft.client.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.thinkingstudio.randomtitlerework.RandomTitleReworkMod;

import java.io.IOException;

public class HitokotoClient {
    private static final String API_URL = "https://v1.hitokoto.cn/";
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
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String response = EntityUtils.toString(httpClient.execute(new HttpGet(API_URL)).getEntity());
            RandomTitleReworkMod.LOGGER.info("Hitokoto Response String: " + response);
            return objectMapper.readValue(response, Hitokoto.class);
        }
    }
}

