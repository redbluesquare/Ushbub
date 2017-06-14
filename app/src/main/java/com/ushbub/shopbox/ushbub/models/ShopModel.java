package com.ushbub.shopbox.ushbub.models;

import java.util.List;

/**
 * Created by usheruk on 12/06/2017.
 */

public class ShopModel {

    private String vendor_name;
    private String ddc_vendor_id;
    private String address_line_1;
    private String address_line_2;
    private String city;
    private String county;
    private String post_code;
    private String country;
    private String introduction;
    private String description;
    private String images;
    private List<Vendordetails> vendordetailsList;



    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getDdc_vendor_id() {
        return ddc_vendor_id;
    }

    public void setDdc_vendor_id(String ddc_vendor_id) {
        this.ddc_vendor_id = ddc_vendor_id;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<Vendordetails> getVendordetailsList() {
        return vendordetailsList;
    }

    public void setVendordetailsList(List<Vendordetails> vendordetailsList) {
        this.vendordetailsList = vendordetailsList;
    }

    public static class Vendordetails {
        private String day_1_open;

        public String getDay_1_open() {
            return day_1_open;
        }

        public void setDay_1_open(String day_1_open) {
            this.day_1_open = day_1_open;
        }
    }
}
