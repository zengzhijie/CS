package com.dreawer.shopcenter.form;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

import static com.dreawer.shopcenter.MessageConstants.*;

public class BaseCertificateForm {

	@Length(min=1, max=50, message=VAL_NAME_LENTH)
    private String name = null;  //许可证类型名称

    @Length(min=1, max=255, message=VAL_IMAGE_LENTH)
    private String image = null;  //营业执照图片

    @Pattern(regexp="^[0-9a-zA-Z]+$", message=VAL_NUM_TYPE_WRONG)
    @Length(min=1, max=60, message=VAL_NUM_LENTH)
    private String number = null;  //营业执照编号

    private String validityType = null;  //类型(LONG_TERM-长期、REGULARLY-定期)

    @Length(min=1, max=50, message=VAL_VAL_LENTH)
    private String validity = null;  //有效期

    @Length(min=1, max=500, message=VAL_ADDRESS_LENTH)
    private String address = null;  //地址
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidityType() {
        return validityType;
    }

    public void setValidityType(String validityType) {
        this.validityType = validityType;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
