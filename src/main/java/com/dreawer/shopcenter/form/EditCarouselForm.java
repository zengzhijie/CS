package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.VAL_OBJECT_ID_NOT_EMPTY;

/**
 * <CODE>EditCarouselForm</CODE>
 *
 * @author fenrir
 * @Date 18-8-7
 */
public class EditCarouselForm extends AddCarouselForm{

    @NotEmpty(message = VAL_OBJECT_ID_NOT_EMPTY)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
