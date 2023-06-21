package com.company.WeGoDent.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenCageResponse {
    @JsonProperty("results")
    private List<Result> results;


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
