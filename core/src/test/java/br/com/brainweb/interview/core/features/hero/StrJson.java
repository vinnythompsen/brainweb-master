package br.com.brainweb.interview.core.features.hero;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StrJson {
    public static String getJson(Object obj){
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            json = objectMapper.writeValueAsString(obj);
        }catch (JsonProcessingException e){
            e.getMessage();
        }
        return  json;
    }
}
