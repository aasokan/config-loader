package com.config.loader.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Parser Module
 */
public class ParserModule extends AbstractModule {

    @Provides
    @Singleton
    Yaml providesYaml() {
        return new Yaml();
    }

    @Provides
    @Singleton
    ObjectMapper providesObjectMapper() {
        return new ObjectMapper();
    }

    @Override
    protected void configure() {
        bind(Parser.class)
                .annotatedWith(Parser.Read.class)
                .to(YamlParser.class)
                .asEagerSingleton();
        bind(Parser.class)
                .annotatedWith(Parser.Write.class)
                .to(JsonParser.class)
                .asEagerSingleton();
    }

    public static class YamlParser implements Parser {
        private final Yaml yaml;

        @Inject
        public YamlParser(final Yaml yaml) {
            this.yaml = yaml;
        }

        @Override
        public <T> T readValue(final File src, final Class<T> valueType) throws IOException {
            return yaml.loadAs(new FileReader(src), valueType);
        }

        @Override
        public String getStringRepresentation(Object object) throws IOException {
            return yaml.dump(object);
        }
    }

    public static class JsonParser implements Parser {

        private final ObjectMapper objectMapper;

        @Inject
        public JsonParser(final ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public <T> T readValue(File src, Class<T> valueType) throws IOException {
            return this.objectMapper.readValue(src, valueType);
        }

        @Override
        public String getStringRepresentation(Object object) throws IOException {
            return objectMapper.writeValueAsString(object);
        }
    }
}
