package com.ruhrpumpen.vendorcentral.model;

import java.util.ArrayList;
import java.util.List;

public class VendorCentral {
    private List<Division> divisions;

    public VendorCentral() {
        this.divisions = new ArrayList<>();
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    public void addDivision(Division division) {
        this.divisions.add(division);
    }

    public void removeDivision(Division division) {
        this.divisions.remove(division);
    }
}
