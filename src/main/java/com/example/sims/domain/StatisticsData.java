package com.example.sims.domain;

public class StatisticsData {
    private String avg;

    private String max;

    private String min;

    private String rate;

    public StatisticsData(String avg, String max, String min, String rate) {
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.rate = rate;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
