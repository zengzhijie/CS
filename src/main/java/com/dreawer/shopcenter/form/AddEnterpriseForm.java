package com.dreawer.shopcenter.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.VAL_APP_ID_EMPTY;

@ApiModel(value = "添加企业表单")
@Data
public class AddEnterpriseForm extends BaseEnterpriseForm{

    @NotEmpty(message="ib不能为空")
    private String id;

	@ApiModelProperty(value = "小程序ID")
	@NotEmpty(message=VAL_APP_ID_EMPTY)
	private String appid = null;



}
