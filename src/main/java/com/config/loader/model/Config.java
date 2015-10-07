package com.config.loader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Config File
 */
public class Config {

    @NotNull
    @JsonProperty
    public String experiment = StringUtils.EMPTY;

    @NotNull
    @JsonProperty
    @JsonDeserialize(using = ModelDeserializer.class)
    List<Model> models = Lists.newArrayList();


    public void printModelNames() {
        models.forEach(m -> System.out.println(m.getModelName()));
    }


}
