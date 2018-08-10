package com.dreawer.shopcenter.persistence;

import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import com.dreawer.shopcenter.domain.Carousel;
import com.dreawer.shopcenter.domain.Carousel;
import com.dreawer.shopcenter.form.CarouselSequenceForm;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.shopcenter.consts.DomainConstants.ID;

/**
 * <CODE>CarouselDao</CODE>
 *
 * @author fenrir
 * @Date 18-8-7
 */
@Repository
public class CarouselDao extends MyBatisBaseDao<Carousel> {

    public void addCarousel(Carousel Carousel) {
        insert("save",Carousel);
    }

    public Carousel findCarouselById(String id) {
        Map<String,String> param = new HashMap<>();
        param.put(ID,id);
        return selectOne("findCarouselById",param);
    }

    public void updateCarousel(Carousel Carousel) {
        update("updateCarousel",Carousel);
    }

    public void removeCarousel(Carousel Carousel) {
        delete("deleteCarousel",Carousel);
    }


    public List<Carousel> findAllCarouselByStoreId(String storeId, String display) {
        Map<String,Object> param = new HashMap<>();
        param.put("storeId",storeId);
        param.put("display",Boolean.valueOf(display));
        return selectList("findAllCarouselByStoreId",param);
    }

    public Integer getCountByStoreId(String storeId, String display) {
        Map<String,Object> param = new HashMap<>();
        param.put("storeId",storeId);
        param.put("display",display);
        return selectOne("getCountByStoreId",param);
    }

    public void updateSequenceById(List<CarouselSequenceForm> form) {
         update("updateSequenceById",form);
    }
}
