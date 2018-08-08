package com.dreawer.shopcenter.persistence;

import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import com.dreawer.shopcenter.domain.Carousel;
import com.dreawer.shopcenter.domain.Enterprise;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.shopcenter.consts.DAOConstants.ENTERPRISE_DAO;
import static com.dreawer.shopcenter.consts.DomainConstants.ID;
import static com.dreawer.shopcenter.consts.DomainConstants.TYPE;

@Repository(ENTERPRISE_DAO)
public class EntepriseDao extends MyBatisBaseDao<Enterprise> {
	
	/**
	 * 保存企业信息。
	 * @param enterprise 企业信息。
	 * @return
	 */
	public int save(Enterprise enterprise){
		return insert("save", enterprise);
	}
	
	/**
	 * 更新企业基本信息。
	 * @param enterprise 企业信息。
	 * @return
	 */
	public int updateBasic(Enterprise enterprise){
		return update("updateBasic", enterprise);
	}
	
	/**
	 * 查询企业信息。
	 * @param type 查询类型
	 * @return
	 */
	public Enterprise findEnterprise(String appid, String id, String type){
		Map<String, Object> params = new HashMap<>();
        params.put("appid", appid);
        params.put(TYPE, type);
        params.put(ID, id);
		return selectOne("findEnterprise", params);
	}

	/**
	 * 通过名称查询企业信息。
	 * @param name 企业名称
	 * @return
	 */
	public Enterprise findEnterpriseByName(String name) {
		return selectOne("findEnterpriseByName", name);
	}

	/**
	 * 查询所有企业id
	 * @return
	 */
    public List<String> findAllEnterpriseId() {
    	return this.getSqlSession().selectList("findAllEnterpriseId");
    }

	/**
	 * 更改企业商品类目信息
	 * @param enterprise
	 */
    public int updateMerchanCategories(Enterprise enterprise) {
    	return update("updateMerhcandiseCategory",enterprise);
    }

	public void updateMemberRegisterPort(Map<String, Object> map) {
    	update("updateMemberRegisterPort",map);
	}

    public Map<String,Object> findBootByAppid(String appid) {
    	return selectOne("findBootByAppid",appid);
    }

    public void updateMemberDisplay(Map<String,Object> map) {
		update("updateMemberDisplay",map);
    }

    public void save(Carousel carousel) {
    }
}
