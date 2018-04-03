package com.rjxx.taxeasy.domains;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by 许黎明 on 2018/4/3.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Buyer")
public class Buyer {

    private String Identifier;

    private String Name;

    private String Address;

    private String TelephoneNo;

    private String Bank;

    private String BankAcc;

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTelephoneNo() {
        return TelephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        TelephoneNo = telephoneNo;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getBankAcc() {
        return BankAcc;
    }

    public void setBankAcc(String bankAcc) {
        BankAcc = bankAcc;
    }
}
