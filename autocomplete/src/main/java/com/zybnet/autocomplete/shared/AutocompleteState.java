package com.zybnet.autocomplete.shared;

import com.vaadin.shared.AbstractFieldState;
import com.vaadin.shared.annotations.DelegateToWidget;
import com.vaadin.shared.ui.textfield.AbstractTextFieldState;
import com.vaadin.ui.AbstractTextField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class AutocompleteState extends AbstractTextFieldState {
    @DelegateToWidget public List<AutocompleteFieldSuggestion> suggestions = new ArrayList<>();
    @DelegateToWidget public int delayMillis = 300;
    @DelegateToWidget public int minimumQueryCharacters = 2;
    @DelegateToWidget public boolean trimQuery = true;
}
