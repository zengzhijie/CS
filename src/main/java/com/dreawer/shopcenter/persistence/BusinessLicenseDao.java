package com.dreawer.shopcenter.persistence;

import com.dreawer.dream.persistence.mybatis.MyBatisBaseDao;
import com.dreawer.retail.domain.BusinessLicense;
import org.springframework.stereotype.Repository;

import static com.dreawer.retail.DAOConstants.BUSINESS_LICENSE_DAO;

@Repository(BUSINESS_LICENSE_DAO)
public class BusinessLicenseDao extends MyBatisBaseDao<BusinessLicense,String>{

    /**
     * 根据ID获取营业执照信息。
     * @param storeId 营业时间ID。
     * @return 营业执照信息。
     */
    public BusinessLicense findBusinessLicense(String storeId) {
        return selectOne("findBusinessLicense",storeId);
    }

    /**
     * 修改营业执照信息。
     * @param businessLicense 营业时间ID。
     */
    public void updateBusinessLicense(BusinessLicense businessLicense) {
        update("updateBusinessLicense",businessLicense);
    }

    /**
     * 添加营业执照。
     * @param businessLicense 营业执照信息。
     */
	public void addBusinessLicense(BusinessLicense businessLicense) {
		insert("addBusinessLicense", businessLicense);
	}

	/**
	 * 通过id查询营业执照。
	 * @param id 执照id。
	 * @return
	 */
	public BusinessLicense findBusinessLicenseById(String id) {
		return selectOne("findBusinessLicenseById", id);
	}
}
