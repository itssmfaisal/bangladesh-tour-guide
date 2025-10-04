package com.example.banglaeshtourguide.model;

import java.math.BigDecimal;

public class Spot {
    private int spotId;
    private String spotName;
    private int districtId; // Foreign key
    private String photoUrl;
    private BigDecimal ticketPrice;
    private String facilities;

    public Spot(int spotId, String spotName, int districtId, String photoUrl, BigDecimal ticketPrice, String facilities) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.districtId = districtId;
        this.photoUrl = photoUrl;
        this.ticketPrice = ticketPrice;
        this.facilities = facilities;
    }

    // Getters and Setters
    public int getSpotId() { return spotId; }
    public void setSpotId(int spotId) { this.spotId = spotId; }
    public String getSpotName() { return spotName; }
    public void setSpotName(String spotName) { this.spotName = spotName; }
    public int getDistrictId() { return districtId; }
    public void setDistrictId(int districtId) { this.districtId = districtId; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public BigDecimal getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }
    public String getFacilities() { return facilities; }
    public void setFacilities(String facilities) { this.facilities = facilities; }

    @Override
    public String toString() {
        return spotName;
    }
}
