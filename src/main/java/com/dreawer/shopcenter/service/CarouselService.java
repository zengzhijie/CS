package com.dreawer.shopcenter.service;

import com.dreawer.shopcenter.domain.Carousel;
import com.dreawer.shopcenter.persistence.CarouselDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <CODE>CarouselService</CODE>
 *
 * @author fenrir
 * @Date 18-8-7
 */

@Service
@Transactional
public class CarouselService {

    @Autowired
    private CarouselDao carouselDao;

  

    /**
     * 添加一条轮播图
     * @param carousel
     */
    public void addCarousel(Carousel carousel) {
        Integer count = 0;
        if (carousel.getDisplay()){
            count = carouselDao.getCountByStoreId(carousel.getStoreId(), carousel.getDisplay().toString());
        }
        carousel.setSequence(count+1);
        carouselDao.addCarousel(carousel);
    }

    /**
     * 根据id查询轮播图
     * @param id 轮播图ID
     */
    public Carousel findCarouselById(String id) {
        return carouselDao.findCarouselById(id);
    }

    /**
     * 修改一条轮播图
     * @param Carousel
     */
    public void editCarousel(Carousel Carousel) {
        carouselDao.updateCarousel(Carousel);
    }

    /**
     * 删除一条记录
     * @param Carousel
     */
    public void removeCarousel(Carousel Carousel) {
        carouselDao.removeCarousel(Carousel);
    }



    /**
     * 查询所有轮播图信息
     * @return
     */
    public List<Carousel> findAllCarouselByStoreId(String storeId, String display) {
        return carouselDao.findAllCarouselByStoreId(storeId,display);
    }

    /**
     * 查询轮播图数量
     * @return
     */
    public Integer getCountByStoreId(String storeId,String display) {
        return carouselDao.getCountByStoreId(storeId,display);
    }
}
