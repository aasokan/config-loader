package com.config.loader.modules;

import com.config.loader.Config;
import com.config.loader.parser.Parser;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by aasokan on 10/7/15.
 */
public class ConfigLoader {
    private final Parser parser;

    @Inject
    public ConfigLoader(@Parser.Read final Parser parser) {
        this.parser = parser;
    }

    public Config loadConfig(final File inputFile) throws IOException {
        return this.parser.readValue(inputFile, Config.class);
    }

    public List<Config> loadAllConfigs(final File inputFile) throws IOException {
        final Iterable<Object> configDocuments = this.parser.readAll(inputFile);
        List<Config> configs = Lists.newArrayList();
        configDocuments.forEach(config -> configs.add((Config) config));
        return configs;
    }
}
