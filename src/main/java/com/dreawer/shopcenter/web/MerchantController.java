package com.dreawer.shopcenter.web;

import com.dreawer.dream.view.JSONResponse;
import com.dreawer.pay.domain.Merchant;
import com.dreawer.pay.form.AddMerchantForm;
import com.dreawer.pay.form.EditMerchantForm;
import com.dreawer.pay.service.MerchantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.dreawer.order.MessageConstants.MSG_NOT_ADMIN;
import static com.dreawer.pay.MessageConstants.*;

@Controller
public class MerchantController extends BaseController {
	
	@Autowired
    private MerchantService merchantService;
    
    private Logger logger = Logger.getLogger(this.getClass()); // 日志记录器

	
    /**
     * 添加商户信息。
     * @param req 用户请求。
     * @param form 商户信息表单。
     * @param result 表单校验结果。
     * @return
     */
	@RequestMapping(value="/merchant/add", method=RequestMethod.POST)
    public @ResponseBody JSONResponse add(HttpServletRequest req, 
    		@RequestBody @Valid AddMerchantForm form, BindingResult result) {
        if (result.hasErrors()) {
            return new JSONResponse(false, getErrors(result));
        }
    	try {
    		// 校验类型是否存在
    		if(form.getType()==null){
                return new JSONResponse(false, new Error(MSG_TYPE_ERROR));
    		}
    		
    		// 校验店铺是否存在
    		if(!isAppAdmin(req, form.getStoreId())){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
    		}
    		Merchant merchant = merchantService.findMerchantByType(form.getStoreId(), form.getType());
    		if(merchant!=null){
                return new JSONResponse(false, new Error(MSG_MERCHANT_EXISTS));
    		}
    		merchant = new Merchant();
    		merchant.setStoreId(form.getStoreId());
    		merchant.setAppid(form.getAppid());
    		merchant.setMchid(form.getMchid());
    		merchant.setKey(form.getKey());
    		merchant.setCert(form.getCert());
    		merchant.setType(form.getType());
    		merchant.setCreater(getSignInUser(req));
    		merchantService.save(merchant);
    		return new JSONResponse(true);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e);

            // 返回失败标志及信息
            return new JSONResponse(false, new Error(MSG_SYSTEM_BUSY));
        }
    }
	
	/**
     * 修改商户信息。
     * @param req 用户请求。
     * @param form 商户信息表单。
     * @param result 表单校验结果。
     * @return
     */
	@RequestMapping(value="/merchant/edit", method=RequestMethod.POST)
    public @ResponseBody JSONResponse edit(HttpServletRequest req, 
    		@RequestBody @Valid EditMerchantForm form, BindingResult result) {
        if (result.hasErrors()) {
            return new JSONResponse(false, getErrors(result));
        }
    	try {
    		if(form.getType()==null){
                return new JSONResponse(false, new Error(MSG_TYPE_ERROR));
    		}
    		Merchant merchant = merchantService.findMerchantById(form.getId());
    		if(merchant==null){
                return new JSONResponse(false, new Error(MSG_MERCHANT_NULL));
    		}
    		merchant.setMchid(form.getMchid());
    		merchant.setKey(form.getKey());
    		merchant.setCert(form.getCert());
    		merchant.setType(form.getType());
    		merchantService.updateBasic(merchant);
    		return new JSONResponse(true);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e);

            // 返回失败标志及信息
            return new JSONResponse(false, new Error(MSG_SYSTEM_BUSY));
        }
    }
	
	/**
     * 添加商户信息。
     * @param req 用户请求。
     * @return
     */
	@RequestMapping(value="/merchant/delete", method=RequestMethod.GET)
    public @ResponseBody JSONResponse delete(HttpServletRequest req, @RequestParam("id") String id) {
    	try {
    		Merchant merchant = merchantService.findMerchantById(id);
    		if(merchant==null){
                return new JSONResponse(false, new Error(MSG_MERCHANT_NULL));
    		}
    		merchantService.delete(merchant);
    		return new JSONResponse(true);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e);

            // 返回失败标志及信息
            return new JSONResponse(false, new Error(MSG_SYSTEM_BUSY));
        }
    }
	
	/**
	 * 查询店铺的商户列表。
	 * @param req 用户请求。
	 * @param storeId 店铺id。
	 * @return
	 */
	@RequestMapping(value="/merchant/list", method=RequestMethod.GET)
    public @ResponseBody JSONResponse list(HttpServletRequest req, @RequestParam("storeId") String storeId) {
    	try {
    		List<Merchant> merchants = merchantService.findMerchantByStore(storeId);
    		// 校验店铺是否存在
    		if(!isAppAdmin(req, storeId)){
                return new JSONResponse(false, new Error(MSG_NOT_ADMIN));
    		}
    		return new JSONResponse(true, merchants);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e);

            // 返回失败标志及信息
            return new JSONResponse(false, new Error(MSG_SYSTEM_BUSY));
        }
    }
	
	/**
	 * 通过id查询商户详情。
	 * @param req 用户请求。
	 * @param id 商户信息id。
	 * @return
	 */
	@RequestMapping(value="/merchant/detail", method=RequestMethod.GET)
    public @ResponseBody JSONResponse detail(HttpServletRequest req, @RequestParam("id") String id) {
    	try {
    		Merchant merchant = merchantService.findMerchantById(id);
    		if(merchant==null){
                return new JSONResponse(false, new Error(MSG_MERCHANT_NULL));
    		}
    		return new JSONResponse(true, merchant);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e);

            // 返回失败标志及信息
            return new JSONResponse(false, new Error(MSG_SYSTEM_BUSY));
        }
    }

}
