package com.config.loader.model;

/**
 * Created by aasokan on 9/22/15.
 */
public interface Model {
    default public String getModelName() {
        return this.getClass().getName();
    }

}
