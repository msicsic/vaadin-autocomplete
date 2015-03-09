package com.zybnet.autocomplete.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AutocompleteFieldSuggestion implements Serializable {

    private Integer id;
    private String value;
    private String displayValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

}