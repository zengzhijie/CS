package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>EditFrontPicForm</CODE> 编辑门脸照片表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditFrontPicForm {

    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_FNT_PIC_NOTEMPTY)
    @Length(min=1, max=255, message=VAL_FNT_PIC_LENGTH)
    private String frontPic = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getFrontPic() {
        return frontPic;
    }

    public void setFrontPic(String frontPic) {
        this.frontPic = frontPic;
    }
}
