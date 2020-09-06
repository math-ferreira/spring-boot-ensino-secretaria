package com.springboot.boaspraticas.apisecretaria.api.response;

import java.util.HashMap;
import java.util.Map;

public class CustomResponse {

    public static Object wrapper(Object object) {
        Map<String, Object> usersMap = new HashMap<String, Object>();
        usersMap.put("data", object);
        return usersMap;
    }
}
