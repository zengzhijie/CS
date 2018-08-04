package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.retail.MessageConstants.VAL_CERT_TYPE_NOTEMPTY;
import static com.dreawer.retail.MessageConstants.VAL_STORE_ID_NOTEMPTY;

/**
 * Created by fenrir on 9/26/17.
 */

/**
 * <CODE>CertificateQueryForm</CODE> 查询其他证件表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class CertificateQueryForm {

    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_CERT_TYPE_NOTEMPTY)
    private String type = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
