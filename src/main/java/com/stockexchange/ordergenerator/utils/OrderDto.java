package com.stockexchange.ordergenerator.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.DocTreeVisitor;
import com.sun.source.doctree.SerialDataTree;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements SerialDataTree {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("symbol")
    public String symbol;
    @JsonProperty("quantity")
    public int quantity;
    @JsonProperty("price")
    public Double price;
    @Override
    public List<? extends DocTree> getDescription() {
        return null;
    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public Kind getKind() {
        return null;
    }

    @Override
    public <R, D> R accept(DocTreeVisitor<R, D> visitor, D data) {
        return null;
    }

}
