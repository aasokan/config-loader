package com.config.loader.modules;

import com.config.loader.model.Config;
import com.config.loader.parser.Parser;
import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;

/**
 * Created by aasokan on 10/7/15.
 */
public class ConfigLoader {
    private final Parser parser;

    @Inject
    public ConfigLoader(final Parser parser) {
        this.parser = parser;
    }

    public Config loadConfig(final File inputFile) throws IOException {
        return this.parser.readValue(inputFile, Config.class);
    }
}
