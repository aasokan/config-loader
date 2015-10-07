package com.config.loader.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by aasokan on 10/6/15.
 */
public class ParserModule extends AbstractModule {

    @Provides
    @Singleton
    ObjectMapper providesObjectMapper() {
        return new ObjectMapper();
    }

    @Override
    protected void configure() {
        bind(Parser.class).to(YamlParser.class).asEagerSingleton();
    }

    public static class JsonParser extends Parser {
        @Inject
        public JsonParser(final ObjectMapper objectMapper) {
            super(objectMapper);
        }
    }

    public static class YamlParser extends Parser {
        public YamlParser() {
            super(new ObjectMapper(new YAMLFactory()));
        }
    }
}
