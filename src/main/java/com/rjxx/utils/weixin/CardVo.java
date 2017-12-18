package com.rjxx.utils.weixin;

/**
 * Created by Administrator on 2017-12-14.
 */
public class CardVo {

    private String sid;

    private String business_name;

    private String branch_name;

    private String province;

    private String city;

    private String district;

    private String address;

    private String telephone;

    private String categories;

    private String offset_type;

    private String longitude;

    private String latitude;

    @Override
    public String toString() {
        return "CardVo{" +
                "sid='" + sid + '\'' +
                ", business_name='" + business_name + '\'' +
                ", branch_name='" + branch_name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", categories='" + categories + '\'' +
                ", offset_type='" + offset_type + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getOffset_type() {
        return offset_type;
    }

    public void setOffset_type(String offset_type) {
        this.offset_type = offset_type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
