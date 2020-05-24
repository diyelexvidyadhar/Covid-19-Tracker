package com.example.covid19.districtwise;

public class DataDistrict {
    String district;
    String confirmed;
    String active;
    String recover;

    public DataDistrict(String district, String confirmed, String active, String recover) {
        this.district = district;
        this.confirmed = confirmed;
        this.active = active;
        this.recover = recover;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }
}
