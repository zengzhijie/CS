package com.dreawer.shopcenter.persistence;

import com.dreawer.dream.persistence.mybatis.MyBatisBaseDao;
import com.dreawer.retail.domain.Certificate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dreawer.retail.DAOConstants.CERTIFICATE_DAO;

@Repository(CERTIFICATE_DAO)
public class CertificateDao extends MyBatisBaseDao<Certificate,String>{

    /**
     * 查找其他证件信息。
     * @param storeId 店铺id
     * @param type 证件类型
     * @return 证件信息
     */
    public Certificate findCertificateById(String id) {
        return selectOne("findCertificateById", id);
    }

    /**
     * 编辑其他证件信息。
     * @param certificate 证件信息。
     */
    public void updateCertificate(Certificate certificate) {
        update("updateCertificate",certificate);
    }

	public void addCertificate(Certificate certificate) {
		insert("addCertificate", certificate);
	}

	public List<Certificate> findCertificates(String enterpriseId) {
		return selectList("findCertificates", enterpriseId);
	}
}
