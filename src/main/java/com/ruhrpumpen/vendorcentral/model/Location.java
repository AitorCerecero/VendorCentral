package com.ruhrpumpen.vendorcentral.model;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String locationName;
    private String projectBiddingLocation;
    private String coveredCountries;
    private List<Vendor> vendors; // Representa la relación muchos a muchos con Vendor

    public Location(String locationName) {
        this.locationName = locationName;
        this.vendors = new ArrayList<>();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getProjectBiddingLocation() {
        return projectBiddingLocation;
    }

    public void setProjectBiddingLocation(String projectBiddingLocation) {
        this.projectBiddingLocation = projectBiddingLocation;
    }

    public String getCoveredCountries() {
        return coveredCountries;
    }

    public void setCoveredCountries(String coveredCountries) {
        this.coveredCountries = coveredCountries;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    // Puedes añadir métodos para gestionar la lista de vendors si es necesario
    public void addVendor(Vendor vendor) {
        this.vendors.add(vendor);
    }

    public void removeVendor(Vendor vendor) {
        this.vendors.remove(vendor);
    }
}
