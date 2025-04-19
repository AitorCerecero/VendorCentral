package com.ruhrpumpen.vendorcentral.model;

public class ListDetail {
    private String vendor; // Clave foránea referenciando Vendor
    private String location; // Clave foránea referenciando Locations
    private String primaryContact; // Nuevo atributo
    private String contactPerson;
    private String standard;
    private String telephone;
    private String secondaryContact;
    private String secondaryTelephone;
    private String secondaryEmail;

    public ListDetail(String vendor, String location, String primaryContact, String contactPerson, String standard, String telephone, String secondaryContact, String secondaryTelephone, String secondaryEmail) {
        this.vendor = vendor;
        this.location = location;
        this.primaryContact = primaryContact;
        this.contactPerson = contactPerson;
        this.standard = standard;
        this.telephone = telephone;
        this.secondaryContact = secondaryContact;
        this.secondaryTelephone = secondaryTelephone;
        this.secondaryEmail = secondaryEmail;
    }

    // Modifica el constructor para incluir primaryContact
    public ListDetail(String vendor, String location, String primaryContact) {
        this.vendor = vendor;
        this.location = location;
        this.primaryContact = primaryContact;
    }

    // Sobrecarga el constructor si necesitas crear instancias sin primaryContact inicialmente
    public ListDetail(String vendor, String location) {
        this.vendor = vendor;
        this.location = location;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSecondaryContact() {
        return secondaryContact;
    }

    public void setSecondaryContact(String secondaryContact) {
        this.secondaryContact = secondaryContact;
    }

    public String getSecondaryTelephone() {
        return secondaryTelephone;
    }

    public void setSecondaryTelephone(String secondaryTelephone) {
        this.secondaryTelephone = secondaryTelephone;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }
}
