package com.dreawer.shopcenter.persistence;

import com.dreawer.dream.persistence.mybatis.MyBatisBaseDao;
import com.dreawer.retail.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.retail.DAOConstants.ADDRESS_DAO;
import static com.dreawer.retail.DomainConstants.ID;

/**
 * <CODE>AddressDao</CODE>
 * 地址Dao
 * @author fenrir
 * @Date 17-12-25
 */

@Repository(ADDRESS_DAO)
public class AddressDao extends MyBatisBaseDao<Address,String>{


    public Integer getCountByStoreId(String storeId) {
        return selectOne("getCountByStoreId",storeId);
    }

    public void addAddress(Address address) {
        insert("save",address);
    }

    public Address findAddressById(String id) {
        Map<String,String> param = new HashMap<>();
        param.put(ID,id);
        return selectOne("findAddressById",param);
    }

    public void updateAddress(Address address) {
        update("updateAddress",address);
    }

    public void removeAddress(Address address) {
        delete("deleteAddress",address);
    }

    public void updateDefaultAddress(Address address) {
        update("updateDefaultAddress",address);
    }

    public List<Address> findAllAddressByStoreId(String storeId) {
        return selectList("findAllAddressByStoreId",storeId);
    }
}
