package com.dreawer.shopcenter.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>AddAddressForm</CODE>
 * 添加地址表单
 * @author fenrir
 * @Date 17-12-25
 */
@ApiModel(value = "添加地址表单")
public class AddAddressForm {

    @ApiModelProperty(value = "收货人姓名")
    @NotEmpty(message = VAL_CONSIGNEE_NOTEMPTY)
    @Length(min = 0,max=20, message=VAL_CONSIGNEE_LENTH)
    private String consignee = null;

    @ApiModelProperty(value = "店铺ID")
    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @ApiModelProperty(value = "联系方式")
    @NotEmpty(message = VAL_CONTACT_METHOD)
    @Pattern(regexp="^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$",
            message=VAL_PHONE_WRONG)
    private String contact = null;

    @ApiModelProperty(value = "发货地址")
    @NotEmpty(message = VAL_SHIPPING_ADDRESS_NOTEMPTY)
    private String shippingAddress = null;

    @ApiModelProperty(value = "收货地址")
    @NotEmpty(message = VAL_DETAIL_ADDRESS_NOTEMPTY)
    @Length(min =0,max = 500,message = VAL_ADDRESS_LENGTH)
    private String address = null;

    @ApiModelProperty(value = "邮编")
    @NotEmpty(message = VAL_POSTCODE_NOTEMPTY)
    @Pattern(regexp = "[0-9][0-9][0-9][0-9][0-9][0-9]",message = VAL_POSTCODE_WRONG)
    private String postcode = null;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
