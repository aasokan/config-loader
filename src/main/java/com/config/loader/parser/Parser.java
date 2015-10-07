package com.config.loader.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Parser Interface
 */
public abstract class Parser {
    private final ObjectMapper objectMapper;

    public Parser(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T readValue(File src, Class<T> valueType) throws IOException {
        return this.objectMapper.readValue(src, valueType);
    }
}
