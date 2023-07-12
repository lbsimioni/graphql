package br.com.simioni.graphql.listener.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    public static <T> T fromJson(final String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

}
