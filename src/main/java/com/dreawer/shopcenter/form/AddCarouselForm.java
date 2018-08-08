package com.dreawer.shopcenter.form;

import com.dreawer.shopcenter.lang.RedirectType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>AddCarouselForm</CODE>
 *
 * @author fenrir
 * @Date 18-8-7
 */
public class AddCarouselForm {

    @NotEmpty(message = VAL_TITLE_NOTEMPTY)
    @Length(min = 1,max=40, message=VAL_TITLE_LENGTH)
    private String title; //标题

    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId; //店铺ID

    @NotNull(message = VAL_TYPE_NOTEMPTY)
    private RedirectType type; //跳转类型

    @NotEmpty(message=VAL_REDIRECTID_NOTEMPTY)
    private String redirectId; //跳转链接

    @NotEmpty(message=VAL_REDIRECT_INFO_NOTEMPTY)
    private String redirectInfo; //跳转目标

    @NotEmpty(message=VAL_IMAGE_NOTEMPTY)
    private String image; //图片链接

    @NotEmpty(message=VAL_DISPLAY_NOTEMPTY)
    private String display; //首页展示

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public RedirectType getType() {
        return type;
    }

    public void setType(RedirectType type) {
        this.type = type;
    }

    public String getRedirectId() {
        return redirectId;
    }

    public void setRedirectId(String redirectId) {
        this.redirectId = redirectId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getRedirectInfo() {
        return redirectInfo;
    }

    public void setRedirectInfo(String redirectInfo) {
        this.redirectInfo = redirectInfo;
    }
}
