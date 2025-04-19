package com.ruhrpumpen.vendorcentral.model;

import java.util.ArrayList;
import java.util.List;

public class Vendor {
    private String vendorName;
    private List<String> locations; // Representa la lista de ubicaciones (String por simplicidad)
    private List<ListDetail> details; // Representa la relación uno a muchos con ListDetails
    private VendorCategory category; // Representa la relación muchos a uno con VendorCategory
    private List<Division> divisions; // Representa la relación muchos a muchos con Division

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
        this.locations = new ArrayList<>();
        this.details = new ArrayList<>();
        this.divisions = new ArrayList<>();
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<ListDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ListDetail> details) {
        this.details = details;
    }

    public VendorCategory getCategory() {
        return category;
    }

    public void setCategory(VendorCategory category) {
        this.category = category;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    // Puedes añadir métodos para gestionar las listas si es necesario
    public void addLocation(String location) {
        this.locations.add(location);
    }

    public void removeLocation(String location) {
        this.locations.remove(location);
    }

    public void addDetail(ListDetail detail) {
        this.details.add(detail);
    }

    public void removeDetail(ListDetail detail) {
        this.details.remove(detail);
    }

    public void addDivision(Division division) {
        this.divisions.add(division);
    }

    public void removeDivision(Division division) {
        this.divisions.remove(division);
    }
}
