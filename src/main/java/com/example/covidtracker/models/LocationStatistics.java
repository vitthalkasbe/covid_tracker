package com.example.covidtracker.models;

import java.time.LocalDate;

public class LocationStatistics {
    private String state;
    private String country;
    private int latestTotalCases;
    private int totalCasesYesterday;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getTotalCasesYesterday() {
        return totalCasesYesterday;
    }

    public void setTotalCasesYesterday(int totalCasesYesterday) {
        this.totalCasesYesterday = totalCasesYesterday;
    }

    @Override
    public String toString() {
        return "LocationStatistics{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", totalCasesYesterday=" + totalCasesYesterday +
                '}';
    }
}
