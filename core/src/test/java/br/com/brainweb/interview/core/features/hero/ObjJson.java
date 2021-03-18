package br.com.brainweb.interview.core.features.hero;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjJson <T>{
    public T  getObject(String str, T obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (T) objectMapper.readValue(str, obj.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
