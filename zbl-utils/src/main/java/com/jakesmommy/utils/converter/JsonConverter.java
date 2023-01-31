package com.jakesmommy.utils.converter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("jsonConverter")
@Slf4j
public class JsonConverter implements AbstractMessageConverter {
    private static ObjectMapper objectMapper;

    @PostConstruct
    public void init(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override //  json to object
    public Object serialize(Class clz, String jsonString) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, clz);
    }

    @Override // object to Json
    public String deserialize(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
