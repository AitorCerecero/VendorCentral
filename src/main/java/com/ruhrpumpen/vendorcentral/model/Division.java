package com.ruhrpumpen.vendorcentral.model;

import java.util.ArrayList;
import java.util.List;


public class Division {
    private String divisionName;
    private List<Vendor> vendors; // Representa la relación muchos a muchos con Vendor

    public Division(String divisionName) {
        this.divisionName = divisionName;
        this.vendors = new ArrayList<>();
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    // Métodos para gestionar la lista de vendors si es necesario
    public void addVendor(Vendor vendor) {
        this.vendors.add(vendor);
    }

    public void removeVendor(Vendor vendor) {
        this.vendors.remove(vendor);
    }
}