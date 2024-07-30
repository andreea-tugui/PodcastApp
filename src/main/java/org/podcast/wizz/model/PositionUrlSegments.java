package org.podcast.wizz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionUrlSegments {

    @JsonProperty("aw_0_ais.adBreakIndex")
    private String[] adBreakIndex;
    @JsonProperty("aw_0_ais.nextEventMs")
    private String[] nextEventMs;
    private Integer insertionRate;

    public String[] getAdBreakIndex() {
        return adBreakIndex;
    }

    public void setAdBreakIndex(String[] adBreakIndex) {
        this.adBreakIndex = adBreakIndex;
    }

    public String[] getNextEventMs() {
        return nextEventMs;
    }

    public void setNextEventMs(String[] nextEventMs) {
        this.nextEventMs = nextEventMs;
    }

    public Integer getInsertionRate() {
        return insertionRate;
    }

    public void setInsertionRate(Integer insertionRate) {
        this.insertionRate = insertionRate;
    }
}
