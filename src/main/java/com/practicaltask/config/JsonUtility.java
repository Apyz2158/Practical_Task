package com.practicaltask.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtility {

    public static String toJsonString(Object obj) {
        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static <I> I toObject(String jsonString, Class<I> cls) {
        I result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
