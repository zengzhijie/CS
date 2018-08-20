package com.dreawer.shopcenter.controller;

import com.dreawer.responsecode.rcdt.Error;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.Success;
import com.dreawer.shopcenter.domain.Address;
import com.dreawer.shopcenter.form.AddAddressForm;
import com.dreawer.shopcenter.form.EditAddressForm;
import com.dreawer.shopcenter.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.shopcenter.ControllerConstants.*;
import static com.dreawer.shopcenter.MessageConstants.MSG_ADDRESS_DELETE_ERROR;
import static com.dreawer.shopcenter.MessageConstants.MSG_ADDRESS_NOTFOUND;
import static com.dreawer.shopcenter.ServiceConstants.ADDRESS_SERVICE;
import static com.dreawer.shopcenter.ViewConstants.USER;
import static com.dreawer.shopcenter.consts.DomainConstants.*;

/**
 * <CODE>AddressController</CODE>
 * 地址控制器
 *
 * @author fenrir
 * @Date 17-12-25
 */
@Controller(ADDRESS_CONTROLLER)
@RequestMapping(REQ_ADDRESS)
public class AddressController extends BaseController {

    @Resource(name = ADDRESS_SERVICE)
    private AddressService addressService;

    private Logger logger = Logger.getLogger(this.getClass()); // 日志记录器

    /**
     * 添加一条地址
     * @param req 用户请求
     * @param form 表单数据
     * @return 返回结果
     */
    @ApiOperation(value = "添加一条地址")
    @RequestMapping(value = REQ_ADD,method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode add(HttpServletRequest req, @RequestBody @Valid AddAddressForm form, BindingResult result) {

            String userId = req.getHeader("userid");

            if (result.hasErrors()) {
                return checkErrors(result);
            }
            Address address = new Address();
            address.setAddress(form.getAddress());
            address.setStoreId(form.getStoreId());
            address.setConsignee(form.getConsignee());
            address.setContact(form.getContact());
            address.setPostcode(form.getPostcode());
            address.setShippingAddress(form.getShippingAddress());
            address.setIsDefault(false);
            Integer count = addressService.getCountByStoreId(form.getStoreId());
            //如果没有新建过地址则设置为默认
            if (count == 0) {
                address.setIsDefault(true);
            }
            address.setUserId(userId);
            address.setCreateTime(getNow());
            addressService.addAddress(address);
            Map<String, Object> viewObjects = new HashMap<String, Object>();
            viewObjects.put(ADDRESS, address);
            viewObjects.put(USER, userId);
            return Success.SUCCESS(viewObjects);

    }

    /**
     * 修改地址
     * @param req 用户请求
     * @param form 表单数据
     * @return 返回结果
     */
    @ApiOperation(value = "修改地址")
    @RequestMapping(value = REQ_EDIT,method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode edit(HttpServletRequest req,@RequestBody @Valid EditAddressForm form, BindingResult result) {

            String userId = req.getHeader("userid");

            if (result.hasErrors()) {
                return checkErrors(result);
            }
            Address address = addressService.findAddressById(form.getId());
            if (address==null){
                return Error.DB(MSG_ADDRESS_NOTFOUND);
            }
            address.setAddress(form.getAddress());
            address.setConsignee(form.getConsignee());
            address.setContact(form.getContact());
            address.setPostcode(form.getPostcode());
            address.setShippingAddress(form.getShippingAddress());
            address.setUpdaterId(userId);
            address.setUpdateTime(getNow());
            addressService.editAddress(address);
            Map<String, Object> viewObjects = new HashMap<String, Object>();
            viewObjects.put(ADDRESS, address);
            viewObjects.put(USER, userId);
            return Success.SUCCESS(viewObjects);

    }

    /**
     * 删除一条地址
     * @param req
     * @param id
     * @return
     */

    @ApiOperation(value = "删除一条地址")
    @RequestMapping(value = REQ_DELETE,method = RequestMethod.GET)
    public @ResponseBody ResponseCode delete(HttpServletRequest req, @RequestParam(ID) String id,@RequestParam(STORE_ID)String storeId){

            Address address = addressService.findAddressById(id);
            if (address==null){
                return Error.DB(MSG_ADDRESS_NOTFOUND);
            }
            Integer count = addressService.getCountByStoreId(storeId);
            if (count==1){
                return Error.DB(MSG_ADDRESS_DELETE_ERROR);
            }
            addressService.removeAddress(address);
            return Success.SUCCESS(address);

    }

    /**
     * 修改当前地址为默认并设置其他id为非默认
     * @param req
     * @return
     */
    @ApiOperation(value = "修改当前地址为默认并设置其他id为非默认")
    @RequestMapping(value = REQ_SET_DEFAULT,method = RequestMethod.GET)
    public @ResponseBody ResponseCode setDefault(HttpServletRequest req,@RequestParam(value = ID,required = true)String id,@RequestParam(STORE_ID)String storeId){

            Address address = addressService.findAddressById(id);
            if (address==null){
                return Error.DB(MSG_ADDRESS_NOTFOUND);
            }
            addressService.updateDefaultAddress(address);
            return Success.SUCCESS(address);

    }

    /**
     * 查询当前用户所有地址信息
     * @param req
     * @return
     */
    @ApiOperation(value = "查询当前用户所有地址信息")
    @RequestMapping(value = REQ_VIEW,method = RequestMethod.GET)
    public @ResponseBody ResponseCode view(HttpServletRequest req,@RequestParam(STORE_ID)String storeId){

            List<Address> list = addressService.findAllAddressByStoreId(storeId);
            return Success.SUCCESS(list);

    }

    /**
     * 根据id查询一条地址
     * @param req
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询一条地址")
    @RequestMapping(value = REQ_ONE,method = RequestMethod.GET)
    public @ResponseBody ResponseCode findOne(HttpServletRequest req,@RequestParam(ID)String id,@RequestParam(STORE_ID)String storeId){


            Address address = addressService.findAddressById(id);
            if (address==null){
                return Error.DB(MSG_ADDRESS_NOTFOUND);
            }
            return Success.SUCCESS(address);

    }
}
