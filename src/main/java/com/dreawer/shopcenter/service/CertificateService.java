package com.dreawer.shopcenter.service;

import com.dreawer.retail.domain.Certificate;
import com.dreawer.retail.persistence.CertificateDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.dreawer.retail.DAOConstants.CERTIFICATE_DAO;
import static com.dreawer.retail.ServiceConstants.CERTIFICATE_SERVICE;

@Service(CERTIFICATE_SERVICE)
public class CertificateService {

    @Resource(name = CERTIFICATE_DAO)
    private CertificateDao certificateDao;

    /**
     * 查找其他证件信息。
     * @param storeId 店铺id
     * @param type 证件类型
     * @return 证件信息
     */
    public Certificate findCertificateById(String id) {
        return certificateDao.findCertificateById(id);
    }

    public void updateCertificate(Certificate certificate) {
        certificateDao.updateCertificate(certificate);
    }

	public void addCertificate(Certificate certificate) {
		certificateDao.addCertificate(certificate);
	}

	public List<Certificate> findCertificates(String enterpriseId) {
		return certificateDao.findCertificates(enterpriseId);
	}
}
