package com.dreawer.shopcenter.web;

import com.dreawer.dream.view.JSONResponse;
import com.dreawer.retail.domain.Address;
import com.dreawer.retail.form.AddAddressForm;
import com.dreawer.retail.form.EditAddressForm;
import com.dreawer.retail.service.AddressService;
import com.dreawer.user.domain.User;
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

import static com.dreawer.order.MessageConstants.MSG_NOT_ADMIN;
import static com.dreawer.retail.ControllerConstants.*;
import static com.dreawer.retail.DomainConstants.*;
import static com.dreawer.retail.MessageConstants.*;
import static com.dreawer.retail.ServiceConstants.ADDRESS_SERVICE;
import static com.dreawer.retail.ViewConstants.USER;

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
    @RequestMapping(value = REQ_ADD,method = RequestMethod.POST)
    public @ResponseBody
    JSONResponse add(HttpServletRequest req, @RequestBody @Valid AddAddressForm form, BindingResult result) {
        try {
            User user = getSignInUser(req);
            // 校验是否是店铺管理员
            if(!isAppAdmin(req, form.getStoreId())){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
            }
            if (result.hasErrors()) {
                return new JSONResponse(false, getErrors(result));
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
            address.setCreater(user);
            address.setCreateTime(getNow());
            addressService.addAddress(address);
            Map<String, Object> viewObjects = new HashMap<String, Object>();
            viewObjects.put(ADDRESS, address);
            viewObjects.put(USER, user);
            return new JSONResponse(true, viewObjects);
        } catch (Exception e) {
            String log = ERR_OTHER;
            logger.error(log, e);
            // 返回失败标志及信息
            String message = MSG_SYSTEM_BUSY;
            return new JSONResponse(false, new Error(message));
        }
    }

    /**
     * 修改地址
     * @param req 用户请求
     * @param form 表单数据
     * @return 返回结果
     */
    @RequestMapping(value = REQ_EDIT,method = RequestMethod.POST)
    public @ResponseBody
    JSONResponse edit(HttpServletRequest req,@RequestBody @Valid EditAddressForm form, BindingResult result) {
        try {
            User user = getSignInUser(req);
            // 校验是否是店铺管理员
            if(!isAppAdmin(req, form.getStoreId())){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
            }
            if (result.hasErrors()) {
                return new JSONResponse(false, getErrors(result));
            }
            Address address = addressService.findAddressById(form.getId());
            if (address==null){
                return new JSONResponse(false,new Error(MSG_ADDRESS_NOTFOUND));
            }
            address.setAddress(form.getAddress());
            address.setConsignee(form.getConsignee());
            address.setContact(form.getContact());
            address.setPostcode(form.getPostcode());
            address.setShippingAddress(form.getShippingAddress());
            address.setUpdater(user);
            address.setUpdateTime(getNow());
            addressService.editAddress(address);
            Map<String, Object> viewObjects = new HashMap<String, Object>();
            viewObjects.put(ADDRESS, address);
            viewObjects.put(USER, user);
            return new JSONResponse(true, viewObjects);
        } catch (Exception e) {
            String log = ERR_OTHER;
            logger.error(log, e);
            // 返回失败标志及信息
            String message = MSG_SYSTEM_BUSY;
            return new JSONResponse(false, new Error(message));
        }
    }

    /**
     * 删除一条地址
     * @param req
     * @param id
     * @return
     */
    @RequestMapping(value = REQ_DELETE,method = RequestMethod.GET)
    public @ResponseBody JSONResponse delete(HttpServletRequest req, @RequestParam(ID) String id, @RequestParam(STORE_ID)String storeId){
        try {
            // 校验是否是店铺管理员
            if(!isAppAdmin(req, storeId)){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
            }
            Address address = addressService.findAddressById(id);
            if (address==null){
                return new JSONResponse(false,new Error(MSG_ADDRESS_NOTFOUND));
            }
            Integer count = addressService.getCountByStoreId(storeId);
            if (count==1){
                return new JSONResponse(false,new Error(MSG_ADDRESS_DELETE_ERROR));
            }
            addressService.removeAddress(address);
            return new JSONResponse(true);
        } catch (Exception e) {
                    String log = ERR_OTHER;
                    logger.error(log, e);

                    // 返回失败标志及信息
                    String message = MSG_SYSTEM_BUSY;
                    return new JSONResponse(false, new Error(message));
                }
    }

    /**
     * 修改当前地址为默认并设置其他id为非默认
     * @param req
     * @return
     */
    @RequestMapping(value = REQ_SET_DEFAULT,method = RequestMethod.GET)
    public @ResponseBody JSONResponse setDefault(HttpServletRequest req, @RequestParam(value = ID,required = true)String id, @RequestParam(STORE_ID)String storeId){
        try {
            // 校验是否是店铺管理员
            if(!isAppAdmin(req, storeId)){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
            }
            Address address = addressService.findAddressById(id);
            if (address==null){
                return new JSONResponse(false,new Error(MSG_ADDRESS_NOTFOUND));
            }
            addressService.updateDefaultAddress(address);
            return new JSONResponse(true);
        } catch (Exception e) {
                    String log = ERR_OTHER;
                    logger.error(log, e);

                    // 返回失败标志及信息
                    String message = MSG_SYSTEM_BUSY;
                    return new JSONResponse(false, new Error(message));
                }
    }

    /**
     * 查询当前用户所有地址信息
     * @param req
     * @return
     */
    @RequestMapping(value = REQ_VIEW,method = RequestMethod.GET)
    public @ResponseBody JSONResponse view(HttpServletRequest req,@RequestParam(STORE_ID)String storeId){
        try {
            // 校验是否是店铺管理员
            if(!isAppAdmin(req, storeId)){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
            }
            List<Address> list = addressService.findAllAddressByStoreId(storeId);
            return new JSONResponse(true,list);
                } catch (Exception e) {
                    String log = ERR_OTHER;
                    logger.error(log, e);

                    // 返回失败标志及信息
                    String message = MSG_SYSTEM_BUSY;
                    return new JSONResponse(false, new Error(message));
                }
    }

    /**
     * 根据id查询一条地址
     * @param req
     * @param id
     * @return
     */
    @RequestMapping(value = REQ_ONE,method = RequestMethod.GET)
    public @ResponseBody JSONResponse findOne(HttpServletRequest req, @RequestParam(ID)String id, @RequestParam(STORE_ID)String storeId){
        try {
            // 校验是否是店铺管理员
            if(!isAppAdmin(req, storeId)){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
            }
            Address address = addressService.findAddressById(id);
            if (address==null){
                return new JSONResponse(false,new Error(MSG_ADDRESS_NOTFOUND));
            }
            return new JSONResponse(true,address);
        } catch (Exception e) {
                    String log = ERR_OTHER;
                    logger.error(log, e);

                    // 返回失败标志及信息
                    String message = MSG_SYSTEM_BUSY;
                    return new JSONResponse(false, new Error(message));
                }
    }
}
