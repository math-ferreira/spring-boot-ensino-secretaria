package com.springboot.boaspraticas.apisecretaria.api.response;

import java.util.HashMap;
import java.util.Map;

public class CustomResponse {

    /**
     * Realiza a transição de qualquer tipo de objetos para um tipo Map<Objetc,
     * Object>, no qual é retornado como JSON
     * 
     * @param objects representa uam lista indefinida de objetos. Esses valores
     *                informados como parametros devem seguir a ordem: key, value,
     *                key, value ... para a representação como objeto JSON
     * @return Objeto Map contendo todos os parametros de entrada separados por
     *         chave e valor
     */
    public static Map<Object, Object> wrapper(Object... objects) {
        Map<Object, Object> map = new HashMap<>();
        int cont = 0;
        Object key = null, value = null;
        for (Object object : objects) {
            if (cont % 2 == 0) {
                key = object;
            } else {
                value = object;
                map.put(key, value);
            }
            cont++;
        }
        return map;
    }

}
