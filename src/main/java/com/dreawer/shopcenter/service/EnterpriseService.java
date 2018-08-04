package com.dreawer.shopcenter.service;

import com.dreawer.retail.domain.Enterprise;
import com.dreawer.retail.persistence.EntepriseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.retail.DAOConstants.ENTERPRISE_DAO;
import static com.dreawer.retail.ServiceConstants.ENTERPRISE_SERVICE;

@Service(ENTERPRISE_SERVICE)
public class EnterpriseService {

    @Resource(name=ENTERPRISE_DAO)
    private EntepriseDao entepriseDao; // 企业信息DAO

	/**
	 * 保存企业信息。
	 * @param enterprise 企业信息。
	 * @return
	 */
	public void save(Enterprise enterprise) {
		entepriseDao.save(enterprise);

	}

	/**
	 * 通过id查询企业信息。
	 * @param id 企业id。
	 * @return
	 */
	public Enterprise findEnterpriseById(String id) {
		return entepriseDao.findEnterprise(null, id, "id");
	}
	
	/**
	 * 通过app查询企业信息。
	 * @return
	 */
	public Enterprise findEnterpriseByApp(String appid) {
		return entepriseDao.findEnterprise(appid, null, "app");
	}

	/**
	 * 更新企业基本信息。
	 * @param enterprise 企业信息。
	 * @return
	 */
	public void updateBasic(Enterprise enterprise) {
		entepriseDao.updateBasic(enterprise);
	}

	/**
	 * 通过名称查询企业信息。
	 * @param name 企业名称
	 * @return
	 */
	public Enterprise findEnterpriseByName(String name) {
		return entepriseDao.findEnterpriseByName(name);
	}

	/**
	 * 查询所有企业id
	 * @return
	 */
	public List<String> findAllEnterpriseId(){
		return entepriseDao.findAllEnterpriseId();
	}

	/**
	 * 更改企业商品类目信息
	 * @param enterprise
	 */
    public void updateMerchanCategories(Enterprise enterprise) {
    	entepriseDao.updateMerchanCategories(enterprise);
    }

	/**
	 * 是否在商品详情显示会员注册通道
	 * @param storeId
	 * @param type
	 */
	public void updateMemberRegisterPort(String storeId, Boolean type) {
		Map<String,Object> map = new HashMap<>();
		map.put("id",storeId);
		map.put("type",type);
		entepriseDao.updateMemberRegisterPort(map);
	}

	/**
	 * 通过appid获取开屏海报
	 * @param appid
	 * @return
	 */
    public Map<String,Object> findBootByAppid(String appid) {
    	return entepriseDao.findBootByAppid(appid);
    }
}
