package com.dreawer.shopcenter.form;

import io.swagger.annotations.ApiModel;

/**
 * <CODE>CarouselSequenceForm</CODE>
 *
 * @author fenrir
 * @Date 18-8-10
 */
@ApiModel(value = "轮播图排序表单")
public class CarouselSequenceForm {

    private String id;

    private Integer sequence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
