package br.com.simioni.graphql.publisher.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    public static String toJson(final Object object) {
        return new Gson().toJson(object);
    }

}
