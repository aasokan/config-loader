package com.config.loader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by aasokan on 9/22/15.
 */
public class BModel implements Model {

    @JsonProperty
    public int bValue;

    @JsonProperty
    public int bValue2;
}
