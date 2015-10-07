package com.config.loader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by aasokan on 9/22/15.
 */
public class AModel implements Model {

    @JsonProperty
    public int aValue;

    @JsonProperty
    public int aValue2 = 3;
}
