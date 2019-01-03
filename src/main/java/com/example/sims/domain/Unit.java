package com.example.sims.domain;

import java.util.ArrayList;
import java.util.List;

public class Unit {

    private String value;

    private String label;

    private List<Unit> children = new ArrayList<>();

    public Unit(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Unit> getChildren() {
        return children;
    }

    public void setChildren(List<Unit> children) {
        this.children = children;
    }
}
