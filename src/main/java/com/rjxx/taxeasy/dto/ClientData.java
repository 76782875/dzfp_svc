package com.rjxx.taxeasy.dto;

/**
 * @author kzx
 * @company 上海容津信息技术有限公司
 * @date 2018/3/22
 */
public class ClientData {

    private String clientNO;
    private String name;
    private String brandCode;
    private String brandName;
    private String taxEquip;
    private String equipNum;
    private String taxDiskPass;
    private String certiCipher;

    public String getClientNO() {
        return clientNO;
    }

    public void setClientNO(String clientNO) {
        this.clientNO = clientNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTaxEquip() {
        return taxEquip;
    }

    public void setTaxEquip(String taxEquip) {
        this.taxEquip = taxEquip;
    }

    public String getEquipNum() {
        return equipNum;
    }

    public void setEquipNum(String equipNum) {
        this.equipNum = equipNum;
    }

    public String getTaxDiskPass() {
        return taxDiskPass;
    }

    public void setTaxDiskPass(String taxDiskPass) {
        this.taxDiskPass = taxDiskPass;
    }

    public String getCertiCipher() {
        return certiCipher;
    }

    public void setCertiCipher(String certiCipher) {
        this.certiCipher = certiCipher;
    }
}
