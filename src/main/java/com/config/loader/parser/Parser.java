package com.config.loader.parser;

import com.google.inject.BindingAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Parser Interface
 */
public interface Parser {
    public <T> T readValue(File src, Class<T> valueType) throws IOException;

    public Iterable<Object> readAll(File src) throws IOException;

    public String getStringRepresentation(final Object object) throws IOException;

    @BindingAnnotation
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    public @interface Read {
    }

    @BindingAnnotation
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    public @interface Write {
    }
}
