package com.example.test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ElementCollection
    private List<Integer> updateHistory = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getUpdateHistory() {
        return updateHistory;
    }

    public void setUpdateHistory(List<Integer> updateHistory) {
        this.updateHistory = updateHistory;
    }
    }
