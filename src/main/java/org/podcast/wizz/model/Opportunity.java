package org.podcast.wizz.model;

import java.util.Map;

public class Opportunity {
    private long originalEventTime;
    private int maxDuration;
    private Zones zones;
    private PositionUrlSegments positionUrlSegments;
    private int insertionRate;

    public long getOriginalEventTime() {
        return originalEventTime;
    }

    public void setOriginalEventTime(long originalEventTime) {
        this.originalEventTime = originalEventTime;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Zones getZones() {
        return zones;
    }

    public void setZones(Zones zones) {
        this.zones = zones;
    }

    public PositionUrlSegments getPositionUrlSegments() {
        return positionUrlSegments;
    }

    public void setPositionUrlSegments(PositionUrlSegments positionUrlSegments) {
        this.positionUrlSegments = positionUrlSegments;
    }

    public int getInsertionRate() {
        return insertionRate;
    }

    public void setInsertionRate(int insertionRate) {
        this.insertionRate = insertionRate;
    }
}