package com.config.loader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    public List<Model> models = Lists.newArrayList();
}
