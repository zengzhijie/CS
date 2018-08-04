package com.dreawer.shopcenter.form;

import com.dreawer.dream.validation.constraint.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

import static com.dreawer.retail.MessageConstants.*;

public class BaseBusinessLicenseForm {

 	@NotEmpty(message=VAL_NAME_NOTEMPTY)
    @Length(min=1, max=50, message=VAL_NAME_LENTH)
    private String name = null; //营业执照名称

    @NotEmpty(message=VAL_IMAGE_NOTEMPTY)
    @Length(min=1, max=255, message=VAL_IMAGE_LENTH)
    private String image = null; //营业执照图片

    @Length(min=0, max=1000, message=VAL_SCOPE_LENTH)
    private String businessScope = null; //经营范围

    private String validityType = null; //经营时间类型

    @Length(min=1, max=50, message=VAL_VAL_LENTH)
    private String validity = null; //经营时间

    @NotEmpty(message=VAL_LEGAL_NOTEMPTY)
    @Length(min=1, max=50, message=VAL_LEGAL_LENTH)
    private String legalRepresentative = null; //法定代表人

    @NotEmpty(message=VAL_NUM_NOTEMPTY)
    @Pattern(regexp="^[0-9a-zA-Z]+$", message=VAL_NUM_TYPE_WRONG)
    @Length(min=1, max=60, message=VAL_NUM_LENTH)
    private String registrationNumber = null; //营业执照注册号

    @NotEmpty(message=VAL_ADDRESS_NOTEMPTY)
    @Length(min=1, max=500, message=VAL_ADDRESS_LENTH)
    private String address = null; //地址
    

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

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
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

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
