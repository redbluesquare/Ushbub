package com.ushbub.shopbox.ushbub.models;

/**
 * Created by usheruk on 12/06/2017.
 */

public class ProductModel {

    private String vendor_product_name;
    private String ddc_vendor_product_id;
    private String product_weight;
    private String product_weight_uom;
    //private String vendor_name;
    private String product_description_small;
    //private String product_description;
    private String image_link;

    public String getVendor_product_name() {
        return vendor_product_name;
    }

    public void setVendor_product_name(String vendor_product_name) {
        this.vendor_product_name = vendor_product_name;
    }

    public String getDdc_vendor_product_id() {
        return ddc_vendor_product_id;
    }

    public void setDdc_vendor_product_id(String ddc_vendor_product_id) {
        this.ddc_vendor_product_id = ddc_vendor_product_id;
    }

    public String getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(String product_weight) {
        this.product_weight = product_weight;
    }

    public String getProduct_weight_uom() {
        return product_weight_uom;
    }

    public void setProduct_weight_uom(String product_weight_uom) {
        this.product_weight_uom = product_weight_uom;
    }

    //public String getVendor_name() {
    //    return vendor_name;
    //}

    //public void setVendor_name(String vendor_name) {
    //    this.vendor_name = vendor_name;
    //}

    public String getProduct_description_small() {
        return product_description_small;
    }

    public void setProduct_description_small(String product_description_small) {
        this.product_description_small = product_description_small;
    }

//    public String getProduct_description() {
//        return product_description;
//    }
//
//    public void setProduct_description(String product_description) {
//        this.product_description = product_description;
//    }
//
    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
