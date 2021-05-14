package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle extends JFrame {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("items")
    private Map<String, Map<String, Item>> items;
    @JsonProperty("door")
    private Door door;
    private boolean isCompleted;
    @JsonProperty("hints")
    private ArrayList<String> hints;
    private String currentHint;
    private int currentHintIndex;






    /*
     * GETTER'S AND SETTERS
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Door getDoor() {
        return this.door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Map<String, Map<String, Item>> getItems() {
        return items;
    }

    public void setItems(Map<String, Map<String, Item>> items) {
        this.items = items;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCurrentHint() {
        return currentHint;
    }

    public void setCurrentHint(String currentHint) {
        this.currentHint = currentHint;
    }

    public ArrayList<String> getHints() {
        return hints;
    }

    public int getCurrentHintIndex() {
        return currentHintIndex;
    }

    public void setCurrentHintIndex(int currentHintIndex) {
        this.currentHintIndex = currentHintIndex;
    }

    public void getAHint(ArrayList<String> arrHints) {
        this.hints = arrHints;
        for (int i = 1; i < hints.size(); i++) {
            if(arrHints.get(i).equalsIgnoreCase(arrHints.get(currentHintIndex))){
                setCurrentHintIndex(i + 1);
                setCurrentHint(arrHints.get(currentHintIndex));
            }else if(arrHints.get(i).equalsIgnoreCase(arrHints.get(arrHints.size() - 1))){
                setCurrentHintIndex(0);
                setCurrentHint(arrHints.get(getCurrentHintIndex()));
            }else{
                setCurrentHint(arrHints.get(currentHintIndex));
                setCurrentHintIndex(i + 1);
            }
        }
        System.out.println(getCurrentHint());
    }
}