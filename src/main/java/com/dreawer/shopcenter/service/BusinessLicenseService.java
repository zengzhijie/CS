package com.dreawer.shopcenter.service;


import com.dreawer.shopcenter.domain.BusinessLicense;
import com.dreawer.shopcenter.persistence.BusinessLicenseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.dreawer.shopcenter.ServiceConstants.BUSSINESS_LICENCE_SERVICE;
import static com.dreawer.shopcenter.consts.DAOConstants.BUSINESS_LICENSE_DAO;

@Service(BUSSINESS_LICENCE_SERVICE)
public class BusinessLicenseService {

    @Resource(name= BUSINESS_LICENSE_DAO)
    private BusinessLicenseDao businessLicenseDao; //营业执照dao

    /**
     * 查找营业执照。
     * @param storeId 店铺id。
     * @return 证件信息
     */
    public BusinessLicense findbusinessLicense(String storeId) {
        return businessLicenseDao.findBusinessLicense(storeId);
    }

    /**
     * 修改营业执照。
     * @param businessLicense 营业执照信息。
     */
    public void updateBusinessLicense(BusinessLicense businessLicense) {
        businessLicenseDao.updateBusinessLicense(businessLicense);
    }

    /**
     * 添加营业执照。
     * @param businessLicense 营业执照信息。
     */
	public void addBusinessLicense(BusinessLicense businessLicense) {
        businessLicenseDao.addBusinessLicense(businessLicense);
	}
	
	/**
	 * 通过id查询营业执照。
	 * @param id 执照id。
	 * @return
	 */
	public BusinessLicense findBusinessLicenseById(String id) {
		return businessLicenseDao.findBusinessLicenseById(id);
	}
}
