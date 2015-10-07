package com.config.loader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Joiner;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by aasokan on 10/7/15.
 */
public class ModelRepresentation {

    private final static Joiner CLASS_JOINER = Joiner.on('.');
    private final static String BASE_MODEL_PACKAGE = "com.config.loader.model";

    @Valid
    @NotNull
    @JsonProperty
    public String name;

    @JsonProperty
    public JsonNode value = null;

    public Model getModel(final ObjectCodec objectCodec) {
        final String fullClassName = CLASS_JOINER.join(BASE_MODEL_PACKAGE, name);
        try {
            final Class modelClass = ClassLoader.getSystemClassLoader().loadClass(fullClassName);
            if (value != null) {
                return (Model) objectCodec.treeToValue(value, modelClass);
            }
            return (Model) modelClass.newInstance();
        } catch (ClassNotFoundException e) {
            // Ignore this
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
