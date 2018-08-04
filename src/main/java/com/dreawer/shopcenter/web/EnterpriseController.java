package com.dreawer.shopcenter.web;

import com.dreawer.dream.view.JSONResponse;
import com.dreawer.retail.domain.BusinessLicense;
import com.dreawer.retail.domain.Certificate;
import com.dreawer.retail.domain.Enterprise;
import com.dreawer.retail.exception.WxAppException;
import com.dreawer.retail.form.*;
import com.dreawer.retail.service.BusinessLicenseService;
import com.dreawer.retail.service.CertificateService;
import com.dreawer.retail.service.EnterpriseService;
import com.dreawer.user.domain.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dreawer.retail.ControllerConstants.ENTERPRISE_CONTROLLER;
import static com.dreawer.retail.MessageConstants.*;
import static com.dreawer.retail.ServiceConstants.*;

@Controller(ENTERPRISE_CONTROLLER)
@RequestMapping("/enterprise")
public class EnterpriseController extends BaseController{
	
	@Resource(name=ENTERPRISE_SERVICE)
	private EnterpriseService enterpriseService; // 企业信息服务

	@Resource(name=BUSSINESS_LICENCE_SERVICE)
	private BusinessLicenseService businessLicenseService; // 媒体信息服务
    
    @Resource(name=CERTIFICATE_SERVICE)
	private CertificateService certificateService; // 媒体信息服务

    private Logger logger = Logger.getLogger(this.getClass()); // 日志记录器

    
    /**
     * 添加企业信息。
     * @param req 用户请求。
     * @param form 添加信息表单。
     * @param result 表单校验结果。
     * @return 执行结果。
     */
    @RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody JSONResponse add(HttpServletRequest req,
                                          @RequestBody @Valid AddEnterpriseForm form, BindingResult result) {
    	if (result.hasErrors()) {
            return new JSONResponse(false, getErrors(result));
        }
		try {
	    	User user = getSignInUser(req);
	    	String appid = getApp(form.getAppid());
	    	if(StringUtils.isBlank(appid)){
		    	return new JSONResponse(false, new Error(MSG_APP_NULL));
	    	}
	    	Enterprise enterprise = enterpriseService.findEnterpriseById(appid);
	    	if(enterprise!=null){
				return new JSONResponse(false, new Error(MSG_ENTERPRISE_EXISTS));
	    	}
			Enterprise enterpriseByApp = enterpriseService.findEnterpriseByApp(form.getAppid());
	    	if (enterpriseByApp!=null){
	    		return new JSONResponse(false, new Error(MSG_ENTERPRISE_EXISTS));
			}
			enterprise = enterpriseService.findEnterpriseByName(form.getName());
	    	if(enterprise!=null){
				return new JSONResponse(false, new Error(MSG_ENTERPRISE_NAME_EXISTS));
	    	}
	    	Enterprise ep = new Enterprise();
	    	ep.setId(appid);
	    	ep.setAppid(form.getAppid());
	    	ep.setCategory("media");
	    	ep.setName(form.getName());
	    	ep.setShortname(form.getShortname());
	    	ep.setLogo(form.getLogo());
	    	ep.setIntro(form.getIntro());
	    	ep.setCreater(user);
	    	enterpriseService.save(ep);
			return new JSONResponse(true);
		} catch (Exception e) {
	    	e.printStackTrace();
            logger.error(e);
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
		}
	}
    
    /**
     * 修改企业信息。
     * @param req 用户请求。
     * @param form 修改企业信息表单。
     * @param result 校验表单结果。
     * @return 执行结果。
     */
    @RequestMapping(value="/edit", method=RequestMethod.POST)
	public @ResponseBody JSONResponse edit(HttpServletRequest req,
                                           @RequestBody @Valid EditEnterpriseForm form, BindingResult result) {
    	if (result.hasErrors()){
            return new JSONResponse(false, getErrors(result));
        }
		try {
	    	User user = getSignInUser(req);
			Enterprise ep = enterpriseService.findEnterpriseById(form.getId());
			if(ep==null){
				return new JSONResponse(false, new Error(MSG_ENTERPRISE_NULL));
			}

			if(StringUtils.isNotBlank(form.getName()) && !form.getName().equals(ep.getName())){
				Enterprise enterprise = enterpriseService.findEnterpriseByName(form.getName());
		    	if(enterprise!=null){
					return new JSONResponse(false, new Error(MSG_ENTERPRISE_NAME_EXISTS));
		    	}
				ep.setName(form.getName());
			}
			if(StringUtils.isNotBlank(form.getShortname())){
		    	ep.setShortname(form.getShortname());
			}
			if(StringUtils.isNotBlank(form.getLogo())){
		    	ep.setLogo(form.getLogo());
			}
			if(StringUtils.isNotBlank(form.getIntro())){
		    	ep.setIntro(form.getIntro());
			}
			if(StringUtils.isNotBlank(form.getCoordinate())){
				ep.setCoordinate(form.getCoordinate());
			}
	    	if(StringUtils.isNotBlank(form.getEmail())){
				String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		        Pattern regex = Pattern.compile(check);
		        Matcher matcher = regex.matcher(form.getEmail());
		        if(matcher.matches()){
			    	ep.setEmail(form.getEmail());
		        }else{
			    	return new JSONResponse(false, new Error(VAL_EMAIL_WRONG));
		        }
			}
	    	if(StringUtils.isNotBlank(form.getPhone()) && StringUtils.isNotBlank(form.getMobile())){
		    	return new JSONResponse(false, new Error(VAL_PHONE_DUMP));
			}
	    	if(StringUtils.isNotBlank(form.getPhone())){
	    		String check1 = "^((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$";
		        Pattern regex1 = Pattern.compile(check1);
		        Matcher matcher1 = regex1.matcher(form.getPhone());
	    		String check2 = "^[48]00-[0-9]{7}";
		        Pattern regex2 = Pattern.compile(check2);
		        Matcher matcher2 = regex2.matcher(form.getPhone());
		        if(!matcher2.matches() && !matcher1.matches()){
			    	return new JSONResponse(false, new Error(VAL_PHONE_WRONG));
		        }
		    	ep.setPhone(form.getPhone());
		    	ep.setMobile(form.getMobile());
			}
	    	if(StringUtils.isNotBlank(form.getMobile())){
	    		ep.setPhone(form.getPhone());
		    	ep.setMobile(form.getMobile());
			}
	    	if(StringUtils.isNotBlank(form.getBootImage())){
	    		// 设置启动画面和启动时间
	    		if(null==form.getBootTime()){
			    	return new JSONResponse(false, new Error(MSG_BOOT_TIME_NULL));
	    		}
	    		if(5<form.getBootTime()){
			    	return new JSONResponse(false, new Error(MSG_BOOT_TIME_LENGH));
	    		}
	    		ep.setBootImage(form.getBootImage());
	    		ep.setBootTime(form.getBootTime());
	    	}
	    	if(StringUtils.isNotBlank(form.getPosters())){
		    	ep.setPosters(form.getPosters());
			}
	    	if(StringUtils.isNotBlank(form.getTelephone()) && !form.getTelephone().equals(ep.getTelephone())){
	    		if(StringUtils.isBlank(form.getValue())){
			    	return new JSONResponse(false, new Error(MSG_VALUE_NULL));
	    		}
	    		if(verifyPhone(form.getTelephone(), form.getValue())){
			    	ep.setTelephone(form.getTelephone());
	    		}else{
			    	return new JSONResponse(false, new Error(MSG_VALUE_ERROR));
	    		}
	    	}
	    	if(StringUtils.isNotBlank(form.getUrl())){
	    		ep.setUrl(form.getUrl());
			}
	    	ep.setUpdater(user);
	    	ep.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            enterpriseService.updateBasic(ep);
            return new JSONResponse(true);
		} catch (Exception e) {
	    	e.printStackTrace();
            logger.error(e);
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
		}
	}
    
    /**
     * 查询企业信息详情。
     * @param req 用户请求。
     * @return
     */
    @RequestMapping(value="/detail", method=RequestMethod.GET)
   	public @ResponseBody JSONResponse detail(HttpServletRequest req) {
   		try {
   			String id = req.getParameter("id");
			String app_id = req.getParameter("appid");
   			Enterprise ep = null;
   			if(StringUtils.isNotBlank(id)){
   				ep = enterpriseService.findEnterpriseById(id);
   			}else if(StringUtils.isNotBlank(app_id)){
   				ep = enterpriseService.findEnterpriseByApp(app_id);
   			}else{
   	   			return new JSONResponse(false);
   			}
   			return new JSONResponse(true, ep);
   		} catch (Exception e) {
   	    	e.printStackTrace();
            logger.error(e);
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
   		}
   	}
    
    /**
     * 查询营业执照信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/businessLicense", method = RequestMethod.GET)
    public @ResponseBody JSONResponse getBusLic(HttpServletRequest req, 
    		@RequestParam("enterpriseId") String enterpriseId){
        try {
            BusinessLicense businessLicense = businessLicenseService.findbusinessLicense(enterpriseId);
            return new JSONResponse(true, businessLicense);
        }catch(Exception e){
            logger.error(e);
            // 返回失败标志及信息
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
        }

    }

    /**
     * 添加营业执照。
     * @param req 用户请求。
     * @param form 添加执照表单。
     * @param result 表单校验结果。
     * @return
     */
    @RequestMapping(value = "/addBusinessLicense", method = RequestMethod.POST)
    public @ResponseBody JSONResponse addBusinessLicense(HttpServletRequest req,
                                                         @RequestBody @Valid AddBusinessLicenseForm form, BindingResult result){
        if (result.hasErrors()) {
            return new JSONResponse(false, getErrors(result));
        }
        try {
            User user = getSignInUser(req);
            Enterprise enterprise = enterpriseService.findEnterpriseById(form.getEnterpriseId());
            if(enterprise==null){
    	    	return new JSONResponse(false, new Error(MSG_ENTERPRISE_NULL));
            }
            BusinessLicense bl = businessLicenseService.findbusinessLicense(form.getEnterpriseId());
            if(bl!=null){
    	    	return new JSONResponse(false, new Error(MSG_LICENSE_EXISTS));
            }
            BusinessLicense businessLicense = new BusinessLicense();
            businessLicense.setName(form.getName());
            businessLicense.setImage(form.getImage());
            businessLicense.setBusinessScope(form.getBusinessScope());
            if(form.getValidityType()!=null){
                businessLicense.setValidityType(form.getValidityType());
            }
            if(form.getValidity()!=null){
                businessLicense.setValidity(form.getValidity());
            }
            businessLicense.setLegalRepresentative(form.getLegalRepresentative());
            businessLicense.setRegistrationNumber(form.getRegistrationNumber());
            businessLicense.setAddress(form.getAddress());
        	businessLicense.setEnterpriseId(form.getEnterpriseId());
        	businessLicense.setCreater(user);
        	businessLicenseService.addBusinessLicense(businessLicense);
            return new JSONResponse(true);
        }catch (Exception e) {
            logger.error(e);
            // 返回失败标志及信息
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
        }
    }
        
    /**
     * 修改营业执照信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/editBusinessLicense", method = RequestMethod.POST)
    public @ResponseBody JSONResponse editBusinessLicense(HttpServletRequest req,
                                                          @RequestBody @Valid EditBusinessLicenseForm form, BindingResult result){
        if (result.hasErrors()) {
            return new JSONResponse(false, getErrors(result));
        }
        try {
            User user = getSignInUser(req);
            BusinessLicense businessLicense = businessLicenseService.findBusinessLicenseById(form.getId());
            if(businessLicense==null){
    	    	return new JSONResponse(false, new Error(MSG_LICENSE_NULL));
            }
            businessLicense.setName(form.getName());
            businessLicense.setImage(form.getImage());
            businessLicense.setBusinessScope(form.getBusinessScope());
            if(form.getValidityType()!=null){
                businessLicense.setValidityType(form.getValidityType());
            }
            if(form.getValidity()!=null){
                businessLicense.setValidity(form.getValidity());
            }
            businessLicense.setLegalRepresentative(form.getLegalRepresentative());
            businessLicense.setRegistrationNumber(form.getRegistrationNumber());
            businessLicense.setAddress(form.getAddress());
        	businessLicense.setUpdater(user);
            businessLicense.setUpdateTime(getNow());
            businessLicenseService.updateBusinessLicense(businessLicense);
            return new JSONResponse(true);
        }catch (Exception e) {
            logger.error(e);
            // 返回失败标志及信息
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
        }

    }

    /**
     * 查询其他证件信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/certificate/list", method = RequestMethod.GET)
    public @ResponseBody JSONResponse getCertificate(HttpServletRequest req){
        try {
        	String enterpriseId = req.getParameter("enterpriseId");
        	if(StringUtils.isBlank(enterpriseId)){
    	    	return new JSONResponse(false, new Error(VAL_ENTERPRISE_ID_EMPTY));
        	}
            List<Certificate> certificates = certificateService.findCertificates(enterpriseId);
            return new JSONResponse(true, certificates);
        }catch (Exception e){
            logger.error(e);
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
        }
    }

    /**
     * 查询特殊证件详情。
     * @param req 用户请求。
     * @return
     */
    @RequestMapping(value = "/certificate/detail", method = RequestMethod.GET)
    public @ResponseBody JSONResponse certificateDetail(HttpServletRequest req){
        try {
        	String id = req.getParameter("id");
        	if(StringUtils.isBlank(id)){
    	    	return new JSONResponse(false, new Error(VAL_ENTERPRISE_ID_EMPTY));
        	}
            Certificate certificate = certificateService.findCertificateById(id);
            return new JSONResponse(true, certificate);
        }catch (Exception e){
            logger.error(e);
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
        }
    }

    /**
     * 添加特殊证件信息。
     * @param req 用户请求。
     * @param form 添加证件表单。
     * @param result 表单校验结果。
     * @return
     */
    @RequestMapping(value = "/addCertificate", method = RequestMethod.POST)
    public @ResponseBody JSONResponse addCertificate(HttpServletRequest req, 
    		@RequestBody @Valid AddCertificateForm form, BindingResult result){
        if(result.hasErrors()){
        	return new JSONResponse(false,getErrors(result));
        }
        try {
            User user = getSignInUser(req);
            Enterprise enterprise = enterpriseService.findEnterpriseById(form.getEnterpriseId());
            if(enterprise==null){
    	    	return new JSONResponse(false, new Error(MSG_ENTERPRISE_NULL));
            }
            Certificate certificate = new Certificate();
            certificate.setEnterpriseId(form.getEnterpriseId());
            certificate.setName(form.getName());
            certificate.setImage(form.getImage());
            certificate.setNumber(form.getNumber());
            certificate.setValidityType(form.getValidityType());
            certificate.setValidity(form.getValidity());
            certificate.setAddress(form.getAddress());
        	certificate.setCreater(user);
        	certificateService.addCertificate(certificate);
            return new JSONResponse(true);
        }catch (Exception e){
            logger.error(e);
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
        }
    }
    
    /**
     * 编辑其他营业证件信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/editCertificate", method = RequestMethod.POST)
    public @ResponseBody JSONResponse editCertificate(HttpServletRequest req, 
    		@RequestBody @Valid EditCertificateForm form, BindingResult result){
        if(result.hasErrors()){
        	return new JSONResponse(false, getErrors(result));
        }
        try {
            User user = getSignInUser(req);
            Certificate certificate = certificateService.findCertificateById(form.getId());
            if(certificate==null){
    	    	return new JSONResponse(false, new Error(MSG_CERTIFICATE_NULL));
            }
            certificate.setName(form.getName());
            certificate.setImage(form.getImage());
            certificate.setNumber(form.getNumber());
            certificate.setValidityType(form.getValidityType());
            certificate.setValidity(form.getValidity());
            certificate.setAddress(form.getAddress());
        	certificate.setUpdater(user);
            certificate.setUpdateTime(getNow());
        	certificateService.updateCertificate(certificate);
            return new JSONResponse(true);
        }catch (Exception e){
            logger.error(e);
	    	return new JSONResponse(false, new Error(MSG_SYS_BUSY));
        }
    }

    /**
     * 通过appid获取开屏海报
     * @param req
     * @param appid
     * @return
     */
    @RequestMapping(value = "/getBoot",method = RequestMethod.GET)
    public @ResponseBody JSONResponse getBoot(HttpServletRequest req,@RequestParam("appid")String appid){
        try {
                Map<String,Object> map = enterpriseService.findBootByAppid(appid);
                if (map==null){
                    map= new HashMap<>();
                    map.put("id",null);
                    map.put("bootImage",null);
                    map.put("bootTime",null);
                }
                return new JSONResponse(true,map);
                } catch (Exception e) {
                    String log = ERR_OTHER;
                    logger.error(log, e);

                    // 返回失败标志及信息
                    String message = MSG_SYSTEM_BUSY;
                    return new JSONResponse(false, new Error(message));
                }
    }
    
    /**
     * 获取appid。
     * @param appid
     * @return
     */
    private String getApp(String appid) throws JSONException {
    	String result = httpGet("https://account.dreawer.com/app/detail", "appid=" + appid);
    	if(StringUtils.isNotBlank(result)){
    		JSONObject json = new JSONObject(result);
    		if(json!=null && json.getBoolean("status")){
    			JSONObject data = json.getJSONObject("data");
    			return data.getString("id");
    		}
    		logger.error("result="+result);
    	}
    	return null;
    }
    
    /**
     * 验证手机号。
     * @param phoneNumber 手机号。
     * @param value 验证码。
     * @return 
     */
    private boolean verifyPhone(String phoneNumber, String value) throws JSONException {
    	Map<String, String> params = new HashMap<>();
    	params.put("phone", phoneNumber);
    	params.put("captcha", value);
    	String result = httpPost("https://account.dreawer.com/verify/phone/commen", params);
		logger.error("phone="+phoneNumber+"captcha="+value);
		logger.error("result="+result);
    	if(StringUtils.isNotBlank(result)){
    		JSONObject json = new JSONObject(result);
    		if(json!=null && json.getBoolean("status")){
    			return true;
    		}
    	}
    	return false;
    }
	/**
	 * 获取小程序类目
	 */
	@RequestMapping(value = "/appCategory",method = RequestMethod.GET)
    public @ResponseBody JSONResponse chooseIndustry(HttpServletRequest req,@RequestParam("id") String storeId){
		try {
		    User user = getSignInUser(req);
		    isAppAdmin(req,storeId);
			Enterprise enterprise = enterpriseService.findEnterpriseById(storeId);
			if (enterprise==null){
				return new JSONResponse(false,new Error("未查询到该企业"));
			}
			String appid = enterprise.getAppid();
			if (StringUtils.isBlank(appid)){
				logger.error("企业信息错误"+enterprise.toString());
				return new JSONResponse(false,new Error("未查询到小程序appId"));
			}
			String token = checkTokenAvailability(appid);
			JSONArray category = getCategory(token);
			logger.error(category+"---------------------------------------------");
			Map<String,Object> map = new HashMap<>();
			map.put("category",category);
			return new JSONResponse(true,map);
		} catch (WxAppException e) {
			logger.error(e.getErrMsg());
			return new JSONResponse(false,new Error(e.getErrMsg()));
		} catch (Exception e) {
			String log = ERR_OTHER;
			logger.error(log, e);
			// 返回失败标志及信息
			String message = MSG_SYSTEM_BUSY;
			return new JSONResponse(false, new Error(message));
		}
	}

	/**
	 * 修改企业可用类目信息
	 */
	@RequestMapping(value = "/editMerchanCategories",method = RequestMethod.POST)
	public @ResponseBody JSONResponse editMerchanCategories(HttpServletRequest req,@RequestBody @Valid AddCategoryForm form,BindingResult result){
		if (result.hasErrors()) {
			return new JSONResponse(false, getErrors(result));
		}
		try {
			String ids = form.getIds();
			String storeId = form.getStoreId();
			isAppAdmin(req,storeId);
			Enterprise enterprise = enterpriseService.findEnterpriseById(storeId);
			if (enterprise==null){
				return new JSONResponse(false,new Error("未查询到该企业"));
			}
			enterprise.setMerchanCategories(ids);
			enterpriseService.updateMerchanCategories(enterprise);
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
	 * 修改是否在商品详情显示会员注册通道
	 */
	@RequestMapping(value = "/editPortStatus",method = RequestMethod.POST)
	public @ResponseBody JSONResponse editMemberRegisterPort(HttpServletRequest req, @RequestBody @Valid EditMerchanCategoriesForm form, BindingResult result){
		if (result.hasErrors()) {
			return new JSONResponse(false, getErrors(result));
		}
		try {
			Boolean type = Boolean.valueOf(form.getType());
			String storeId = form.getStoreId();
			isAppAdmin(req,storeId);
			Enterprise enterprise = enterpriseService.findEnterpriseById(storeId);
			if (enterprise==null){
				return new JSONResponse(false,new Error("未查询到该企业"));
			}
			enterpriseService.updateMemberRegisterPort(storeId,type);
			return new JSONResponse(true,enterprise);
		} catch (Exception e) {
			String log = ERR_OTHER;
			logger.error(log, e);

			// 返回失败标志及信息
			String message = MSG_SYSTEM_BUSY;
			return new JSONResponse(false, new Error(message));
		}
	}

}
