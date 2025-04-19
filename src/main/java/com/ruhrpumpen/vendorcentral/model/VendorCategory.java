package com.ruhrpumpen.vendorcentral.model;

import java.util.ArrayList;
import java.util.List;

public class VendorCategory {
    private String categoryName;
    private List<Vendor> vendors; // Representa la relación uno a muchos con Vendor

    public VendorCategory(String categoryName) {
        this.categoryName = categoryName;
        this.vendors = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
