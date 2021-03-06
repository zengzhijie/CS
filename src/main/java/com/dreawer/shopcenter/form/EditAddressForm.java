package com.dreawer.shopcenter.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.VAL_ADDRESS_ID_NOTEMPTY;

/**
 * <CODE>EditAddressForm</CODE>
 * 修改地址表单
 * @author fenrir
 * @Date 17-12-25
 */
@ApiModel(value = "修改地址表单")
public class EditAddressForm extends AddAddressForm{

    @ApiModelProperty(value = "店铺ID")
    @NotEmpty(message = VAL_ADDRESS_ID_NOTEMPTY)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
