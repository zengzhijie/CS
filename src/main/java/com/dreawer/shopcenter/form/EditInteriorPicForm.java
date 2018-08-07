package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>EditInteriorPicForm</CODE> 编辑店内照片表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditInteriorPicForm {
    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_INI_PIC_NOTEMPTY)
    @Length(min=1, max=1275, message=VAL_INI_PIC_LENGTH)
    private String interiorPic = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getInteriorPic() {
        return interiorPic;
    }

    public void setInteriorPic(String interiorPic) {
        this.interiorPic = interiorPic;
    }
}
