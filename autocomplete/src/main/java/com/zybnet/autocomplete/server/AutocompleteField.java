package com.zybnet.autocomplete.server;

import com.vaadin.ui.AbstractTextField;
import com.zybnet.autocomplete.shared.AutocompleteFieldSuggestion;
import com.zybnet.autocomplete.shared.AutocompleteServerRpc;
import com.zybnet.autocomplete.shared.AutocompleteState;

import java.util.*;

@SuppressWarnings("serial")
public class AutocompleteField<E> extends AbstractTextField implements AutocompleteServerRpc {

    private AutocompleteQueryListener<E> queryListener;
    private AutocompleteSuggestionPickedListener<E> suggestionPickedListener;
    private Map<Integer, E> items = new HashMap<Integer, E>();

    public AutocompleteField() {
        registerRpc(this, AutocompleteServerRpc.class);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    public void clearChoices() {
        getState().suggestions = Collections.emptyList();
        items = new HashMap<Integer, E>();
    }

    @Override
    public AutocompleteState getState() {
        return (AutocompleteState) super.getState();
    }

    public void onQuery(String query) {
        clearChoices();
        if (queryListener != null) {
            queryListener.handleUserQuery(this, query);
        }
    }

    public void setQueryListener(AutocompleteQueryListener<E> listener) {
        this.queryListener = listener;
    }

    @Override
    public void onSuggestionPicked(AutocompleteFieldSuggestion suggestion) {
        setValue(suggestion.getValue());
        if (suggestionPickedListener != null)
            suggestionPickedListener.onSuggestionPicked(items.get(suggestion.getId()));
    }

    public void setSuggestionPickedListener(AutocompleteSuggestionPickedListener<E> listener) {
        this.suggestionPickedListener = listener;
    }

    public void setDelay(int delayMillis) {
        getState().delayMillis = delayMillis;
    }

    public void setTabIndex(int tabIdx) {
        getState().tabIndex = tabIdx;
    }

    public void setEnabled(boolean enabled) {
        getState().enabled = enabled;
    }

    public void addSuggestion(E id, String value, String displayedValue) {
        int index = getState().suggestions.size();
        items.put(index, id);
        List<AutocompleteFieldSuggestion> newSuggestionList = new ArrayList<AutocompleteFieldSuggestion>(getState().suggestions);
        AutocompleteFieldSuggestion suggestion = new AutocompleteFieldSuggestion();
        suggestion.setId(index);
        suggestion.setValue(value);
        suggestion.setDisplayValue(displayedValue);
        newSuggestionList.add(suggestion);
        getState().suggestions = newSuggestionList;
    }

    public void setMinimumQueryCharacters(int minimumQueryCharacters) {
        getState().minimumQueryCharacters = minimumQueryCharacters;
    }

    public void setTrimQuery(boolean trimQuery) {
        getState().trimQuery = trimQuery;
    }
}