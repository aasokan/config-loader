package com.config.loader.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Deserializer for List of Models
 */
public class ModelDeserializer extends JsonDeserializer<List<Model>> {

    @Override
    public List<Model> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode node = objectCodec.readTree(jsonParser);
        final List<Model> modelList = Lists.newArrayList();
        if (node.isArray()) {
            Iterator<JsonNode> elements = node.elements();
            while (elements.hasNext()) {
                final JsonNode children = elements.next();
                final ModelRepresentation modelRepresentation = objectCodec.treeToValue(children, ModelRepresentation.class);
                final Model model = modelRepresentation.getModel(objectCodec);
                if (model != null) {
                    modelList.add(model);
                }
            }
        }
        return modelList;
    }
}
