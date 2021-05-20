package com.example.schedule.Model;

import java.util.ArrayList;

public class GroupResponse {

    private Integer count;
    private ArrayList<Group> results;

    public GroupResponse(Integer count, ArrayList<Group> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<Group> getResults() {
        return results;
    }

    public void setResults(ArrayList<Group> results) {
        this.results = results;
    }
}
