package com.config.loader.experts;

import com.config.loader.model.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by aasokan on 10/8/15.
 */
public abstract class Expert {

    @NotNull
    @JsonProperty
    public List<Model> models = Lists.newArrayList();

    public String getExpertName() {
        return this.getClass().getSimpleName();
    }

    public abstract void applyModel();
}
