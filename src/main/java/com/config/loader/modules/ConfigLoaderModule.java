package com.config.loader.modules;

import com.config.loader.parser.ParserModule;
import com.google.inject.AbstractModule;

/**
 * Config Loader Dagger Module
 */
public class ConfigLoaderModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ParserModule());

        bind(ConfigLoader.class).asEagerSingleton();
    }
}
