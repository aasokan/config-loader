package com.config.loader;

import com.config.loader.model.Config;
import com.config.loader.modules.ConfigLoader;
import com.config.loader.modules.ConfigLoaderModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

/**
 * Main class for testing out loaders
 */
public class ConfigLoaderApplication {

    private static class Options {
        @Option(required = true, aliases = "-i", name = "--input", usage = "input file path")
        private File inputFile;
    }

    public static void main(final String args[]) {
        final Options arguments = new Options();
        final CmdLineParser parser = new CmdLineParser(arguments);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            System.exit(1);
        }

        final Injector injector = Guice.createInjector(new ConfigLoaderModule());

        final ConfigLoader configLoader = injector.getInstance(ConfigLoader.class);
        Config config = null;

        try {
            config = configLoader.loadConfig(arguments.inputFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Assert Config
        if (config != null) {
            System.out.println(config.toString());
        }

        config.printModelNames();

    }
}
