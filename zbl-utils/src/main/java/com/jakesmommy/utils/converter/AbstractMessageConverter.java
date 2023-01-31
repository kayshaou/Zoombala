package com.jakesmommy.utils.converter;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AbstractMessageConverter<T> {
    //  json to object
    Object serialize(Class clz, String jsonString) throws JsonProcessingException;
    // object to Json
    String deserialize(Object obj) throws JsonProcessingException;
}
