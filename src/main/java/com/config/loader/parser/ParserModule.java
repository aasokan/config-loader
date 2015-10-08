package com.config.loader.parser;

import com.config.loader.Config;
import com.config.loader.experts.AExpert;
import com.config.loader.experts.BExpert;
import com.config.loader.model.AModel;
import com.config.loader.model.BModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

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
        final Constructor constructor = new Constructor(Config.class);

        // Tags for Experts
        constructor.addTypeDescription(new TypeDescription(AExpert.class, "!aExpert"));
        constructor.addTypeDescription(new TypeDescription(BExpert.class, "!bExpert"));

        // Tags for Models
        constructor.addTypeDescription(new TypeDescription(AModel.class, "!aModel"));
        constructor.addTypeDescription(new TypeDescription(BModel.class, "!bModel"));

        return new Yaml(constructor);
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
        public Iterable<Object> readAll(File src) throws IOException {
            return yaml.loadAll(new FileReader(src));
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
        public Iterable<Object> readAll(File src) throws IOException {
            return null;
        }

        @Override
        public String getStringRepresentation(Object object) throws IOException {
            return objectMapper.writeValueAsString(object);
        }
    }
}
