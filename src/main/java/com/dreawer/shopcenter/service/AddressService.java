package com.dreawer.shopcenter.service;

import com.dreawer.shopcenter.domain.Address;
import com.dreawer.shopcenter.persistence.AddressDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.dreawer.shopcenter.ServiceConstants.ADDRESS_SERVICE;
import static com.dreawer.shopcenter.consts.DAOConstants.ADDRESS_DAO;

/**
 * <CODE>AddressService</CODE>
 *
 * @author fenrir
 * @Date 17-12-25
 */

@Service(ADDRESS_SERVICE)
public class AddressService {

    @Resource(name = ADDRESS_DAO)
    private AddressDao addressDao;

    /**
     * 查询地址数量
     * @return
     */
    public Integer getCountByStoreId(String storeId) {
        return addressDao.getCountByStoreId(storeId);
    }

    /**
     * 添加一条地址
     * @param address
     */
    public void addAddress(Address address) {
        addressDao.addAddress(address);
    }

    /**
     * 根据id查询地址
     * @param id 地址ID
     */
    public Address findAddressById(String id) {
        return addressDao.findAddressById(id);
    }

    /**
     * 修改一条地址
     * @param address
     */
    public void editAddress(Address address) {
        addressDao.updateAddress(address);
    }

    /**
     * 删除一条记录
     * @param address
     */
    public void removeAddress(Address address) {
        addressDao.removeAddress(address);
    }

    /**
     * 通过id更新默认地址
     * @param address
     */
    public void updateDefaultAddress(Address address) {
        addressDao.updateDefaultAddress(address);
    }

    /**
     * 查询所有地址信息
     * @return
     */
    public List<Address> findAllAddressByStoreId(String storeId) {
        return addressDao.findAllAddressByStoreId(storeId);
    }
}
