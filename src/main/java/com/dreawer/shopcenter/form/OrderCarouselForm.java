package com.dreawer.shopcenter.form;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * <CODE>OrderCarouselForm</CODE>
 *
 * @author fenrir
 * @Date 18-8-10
 */
@ApiModel(value = "轮播图排序表单")
public class OrderCarouselForm {

    private List<CarouselSequenceForm> form;

    public List<CarouselSequenceForm> getForm() {
        return form;
    }

    public void setForm(List<CarouselSequenceForm> form) {
        this.form = form;
    }
}
