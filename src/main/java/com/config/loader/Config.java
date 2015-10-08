package com.config.loader;

import com.config.loader.experts.Expert;
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
    public String experience = StringUtils.EMPTY;

    @NotNull
    @JsonProperty
    public List<Expert> experts = Lists.newArrayList();
}
