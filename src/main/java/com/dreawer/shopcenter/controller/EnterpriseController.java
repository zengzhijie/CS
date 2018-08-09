package com.dreawer.shopcenter.controller;

import com.dreawer.responsecode.rcdt.EntryError;
import com.dreawer.responsecode.rcdt.Error;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.Success;
import com.dreawer.shopcenter.domain.BusinessLicense;
import com.dreawer.shopcenter.domain.Carousel;
import com.dreawer.shopcenter.domain.Certificate;
import com.dreawer.shopcenter.domain.Enterprise;
import com.dreawer.shopcenter.form.*;
import com.dreawer.shopcenter.service.BusinessLicenseService;
import com.dreawer.shopcenter.service.CarouselService;
import com.dreawer.shopcenter.service.CertificateService;
import com.dreawer.shopcenter.service.EnterpriseService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dreawer.shopcenter.ControllerConstants.ENTERPRISE_CONTROLLER;
import static com.dreawer.shopcenter.MessageConstants.*;
import static com.dreawer.shopcenter.ServiceConstants.*;
import static com.dreawer.shopcenter.consts.DomainConstants.STORE_ID;

@Controller(ENTERPRISE_CONTROLLER)
@RequestMapping("/enterprise")
public class EnterpriseController extends BaseController{




	@Autowired
	private EnterpriseService enterpriseService; // 企业信息服务

	@Autowired
	private BusinessLicenseService businessLicenseService; // 媒体信息服务

	@Autowired
	private CertificateService certificateService; // 媒体信息服务

	@Autowired
	private CarouselService carouselService; //轮播图服务
    
    /**
     * 添加企业信息。
     * @param req 用户请求。
     * @param form 添加信息表单。
     * @param result 表单校验结果。
     * @return 执行结果。
     */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ApiOperation(value = "添加企业信息")
	public @ResponseBody
	ResponseCode add(HttpServletRequest req,
					 @RequestBody @Valid AddEnterpriseForm form, BindingResult result) {
    	if (result.hasErrors()) {
            return checkErrors(result);
        }
			String userId = req.getHeader("userId");
			String appid = form.getAppid();
	    	if(StringUtils.isBlank(appid)){
		    	return Error.EXT_RESPONSE(MSG_APP_NULL);
	    	}
	    	Enterprise enterprise = enterpriseService.findEnterpriseById(appid);
	    	if(enterprise!=null){
				return Error.DB(MSG_ENTERPRISE_EXISTS);
	    	}
			Enterprise enterpriseByApp = enterpriseService.findEnterpriseByApp(form.getAppid());
	    	if (enterpriseByApp!=null){
	    		return Error.DB(MSG_ENTERPRISE_EXISTS);
			}
			enterprise = enterpriseService.findEnterpriseByName(form.getName());
	    	if(enterprise!=null){
				return  Error.DB(MSG_ENTERPRISE_NAME_EXISTS);
	    	}
	    	Enterprise ep = new Enterprise();
	    	ep.setId(appid);
	    	ep.setAppid(form.getAppid());
	    	ep.setCategory("media");
	    	ep.setName(form.getName());
	    	ep.setShortname(form.getShortname());
	    	ep.setLogo(form.getLogo());
	    	ep.setIntro(form.getIntro());
	    	ep.setUserId(userId);
	    	enterpriseService.save(ep);
			return Success.SUCCESS(ep);

	}
    
    /**
     * 修改企业信息。
     * @param req 用户请求。
     * @param form 修改企业信息表单。
     * @param result 校验表单结果。
     * @return 执行结果。
     */
    @ApiOperation(value = "修改企业信息")
    @RequestMapping(value="/edit", method=RequestMethod.POST)
	public @ResponseBody ResponseCode edit(HttpServletRequest req,
                                           @RequestBody @Valid EditEnterpriseForm form, BindingResult result) {
    	if (result.hasErrors()){
            return checkErrors(result);
        }

			String userId = req.getHeader("userId");
			Enterprise ep = enterpriseService.findEnterpriseById(form.getId());
			if(ep==null){
				return Error.DB(MSG_ENTERPRISE_NULL);
			}

			if(StringUtils.isNotBlank(form.getName()) && !form.getName().equals(ep.getName())){
				Enterprise enterprise = enterpriseService.findEnterpriseByName(form.getName());
		    	if(enterprise!=null){
					return Error.DB(MSG_ENTERPRISE_NAME_EXISTS);
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
			    	return Error.ENTRY(VAL_EMAIL_WRONG);
		        }
			}
	    	if(StringUtils.isNotBlank(form.getPhone()) && StringUtils.isNotBlank(form.getMobile())){
		    	return Error.ENTRY(VAL_PHONE_DUMP);
			}
	    	if(StringUtils.isNotBlank(form.getPhone())){
	    		String check1 = "^((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$";
		        Pattern regex1 = Pattern.compile(check1);
		        Matcher matcher1 = regex1.matcher(form.getPhone());
	    		String check2 = "^[48]00-[0-9]{7}";
		        Pattern regex2 = Pattern.compile(check2);
		        Matcher matcher2 = regex2.matcher(form.getPhone());
		        if(!matcher2.matches() && !matcher1.matches()){
			    	return Error.ENTRY(VAL_PHONE_WRONG);
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
			    	return Error.ENTRY(MSG_BOOT_TIME_NULL);
	    		}
	    		if(5<form.getBootTime()){
			    	return Error.ENTRY(MSG_BOOT_TIME_LENGH);
	    		}
	    		ep.setBootImage(form.getBootImage());
	    		ep.setBootTime(form.getBootTime());
	    	}
	    	if(StringUtils.isNotBlank(form.getPosters())){
		    	ep.setPosters(form.getPosters());
			}
	    	if(StringUtils.isNotBlank(form.getTelephone()) && !form.getTelephone().equals(ep.getTelephone())){
	    		if(StringUtils.isBlank(form.getValue())){
			    	return Error.ENTRY(MSG_VALUE_NULL);
	    		}
	    		if(verifyPhone(form.getTelephone(), form.getValue(),userId)){
			    	ep.setTelephone(form.getTelephone());
	    		}else{
			    	return Error.ENTRY(MSG_VALUE_ERROR);
	    		}
	    	}
	    	if(StringUtils.isNotBlank(form.getUrl())){
	    		ep.setUrl(form.getUrl());
			}
	    	ep.setUserId(userId);
	    	ep.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            enterpriseService.updateBasic(ep);
            return Success.SUCCESS(ep);
	}
    
    /**
     * 查询企业信息详情。
     * @param req 用户请求。
     * @return
     */
    @ApiOperation(value = "查询企业信息详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "企业ID"),
    @ApiImplicitParam(name = "appid",value = "小程序ID")})
    @RequestMapping(value="/detail", method=RequestMethod.GET)
   	public @ResponseBody ResponseCode detail(HttpServletRequest req) {

   			String id = req.getParameter("id");
			String app_id = req.getParameter("appid");
   			Enterprise ep = null;
   			if(StringUtils.isNotBlank(id)){
   				ep = enterpriseService.findEnterpriseById(id);
   			}else if(StringUtils.isNotBlank(app_id)){
   				ep = enterpriseService.findEnterpriseByApp(app_id);
   			}else{
   	   			return Error.DB("未找到企业");
   			}
   			return Success.SUCCESS(ep);
   	}
    
    /**
     * 查询营业执照信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/businessLicense", method = RequestMethod.GET)
    public @ResponseBody ResponseCode getBusLic(HttpServletRequest req,
    		@RequestParam("enterpriseId") String enterpriseId){
            BusinessLicense businessLicense = businessLicenseService.findbusinessLicense(enterpriseId);
            return Success.SUCCESS(businessLicense);
    }

    /**
     * 添加营业执照。
     * @param req 用户请求。
     * @param form 添加执照表单。
     * @param result 表单校验结果。
     * @return
     */
    @RequestMapping(value = "/addBusinessLicense", method = RequestMethod.POST)
    public @ResponseBody ResponseCode addBusinessLicense(HttpServletRequest req,
                                                         @RequestBody @Valid AddBusinessLicenseForm form, BindingResult result){
        if (result.hasErrors()) {
            return checkErrors(result);
        }
			String userId = req.getHeader("userId");
			Enterprise enterprise = enterpriseService.findEnterpriseById(form.getEnterpriseId());
            if(enterprise==null){
    	    	return Error.DB("未查询到该企业");
            }
            BusinessLicense bl = businessLicenseService.findbusinessLicense(form.getEnterpriseId());
            if(bl!=null){
    	    	return Error.DB(MSG_LICENSE_EXISTS);
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
        	businessLicense.setUserId(userId);
        	businessLicenseService.addBusinessLicense(businessLicense);
            return Success.SUCCESS(businessLicense);
    }
        
    /**
     * 修改营业执照信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/editBusinessLicense", method = RequestMethod.POST)
    public @ResponseBody ResponseCode editBusinessLicense(HttpServletRequest req,
                                                          @RequestBody @Valid EditBusinessLicenseForm form, BindingResult result){
        if (result.hasErrors()) {
            return checkErrors(result);
        }
			String userId = req.getHeader("userId");
			BusinessLicense businessLicense = businessLicenseService.findBusinessLicenseById(form.getId());
            if(businessLicense==null){
    	    	return Error.DB(MSG_LICENSE_NULL);
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
        	businessLicense.setUpdaterId(userId);
            businessLicense.setUpdateTime(getNow());
            businessLicenseService.updateBusinessLicense(businessLicense);
            return Success.SUCCESS(businessLicense);

    }

    /**
     * 查询其他证件信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/certificate/list", method = RequestMethod.GET)
    public @ResponseBody ResponseCode getCertificate(HttpServletRequest req){
        	String enterpriseId = req.getParameter("enterpriseId");
        	if(StringUtils.isBlank(enterpriseId)){
    	    	return Error.DB(VAL_ENTERPRISE_ID_EMPTY);
        	}
            List<Certificate> certificates = certificateService.findCertificates(enterpriseId);
            return Success.SUCCESS(certificates);
    }

    /**
     * 查询特殊证件详情。
     * @param req 用户请求。
     * @return
     */
    @RequestMapping(value = "/certificate/detail", method = RequestMethod.GET)
    public @ResponseBody ResponseCode certificateDetail(HttpServletRequest req){

        	String id = req.getParameter("id");
        	if(StringUtils.isBlank(id)){
    	    	return EntryError.EMPTY(VAL_ENTERPRISE_ID_EMPTY);
        	}
            Certificate certificate = certificateService.findCertificateById(id);
            return Success.SUCCESS(certificate);
    }

    /**
     * 添加特殊证件信息。
     * @param req 用户请求。
     * @param form 添加证件表单。
     * @param result 表单校验结果。
     * @return
     */
    @RequestMapping(value = "/addCertificate", method = RequestMethod.POST)
    public @ResponseBody ResponseCode addCertificate(HttpServletRequest req,
    		@RequestBody @Valid AddCertificateForm form, BindingResult result){
        if(result.hasErrors()){
        	return checkErrors(result);
        }

			String userId = req.getHeader("userId");
			Enterprise enterprise = enterpriseService.findEnterpriseById(form.getEnterpriseId());
            if(enterprise==null){
    	    	return Error.DB(MSG_ENTERPRISE_NULL);
            }
            Certificate certificate = new Certificate();
            certificate.setEnterpriseId(form.getEnterpriseId());
            certificate.setName(form.getName());
            certificate.setImage(form.getImage());
            certificate.setNumber(form.getNumber());
            certificate.setValidityType(form.getValidityType());
            certificate.setValidity(form.getValidity());
            certificate.setAddress(form.getAddress());
        	certificate.setUserId(userId);
        	certificateService.addCertificate(certificate);
            return Success.SUCCESS(certificate);

    }
    
    /**
     * 编辑其他营业证件信息。
     * @param req 用户请求
     * @author lyan
     * @since 1.0
     */
    @RequestMapping(value = "/editCertificate", method = RequestMethod.POST)
    public @ResponseBody ResponseCode editCertificate(HttpServletRequest req,
    		@RequestBody @Valid EditCertificateForm form, BindingResult result){
        if(result.hasErrors()){
        	return checkErrors(result);
        }

			String userId = req.getHeader("userId");
            Certificate certificate = certificateService.findCertificateById(form.getId());
            if(certificate==null){
    	    	return Error.DB(MSG_CERTIFICATE_NULL);
            }
            certificate.setName(form.getName());
            certificate.setImage(form.getImage());
            certificate.setNumber(form.getNumber());
            certificate.setValidityType(form.getValidityType());
            certificate.setValidity(form.getValidity());
            certificate.setAddress(form.getAddress());
        	certificate.setUpdaterId(userId);
            certificate.setUpdateTime(getNow());
        	certificateService.updateCertificate(certificate);
            return Success.SUCCESS(certificate);

    }

    /**
     * 通过appid获取开屏海报
     * @param req
     * @param appid
     * @return
     */
    @RequestMapping(value = "/getBoot",method = RequestMethod.GET)
    public @ResponseBody ResponseCode getBoot(HttpServletRequest req,@RequestParam("appid")String appid){
                Map<String,Object> map = enterpriseService.findBootByAppid(appid);
                if (map==null){
                    map= new HashMap<>();
                    map.put("id",null);
                    map.put("bootImage",null);
                    map.put("bootTime",null);
                }
                return Success.SUCCESS(map);
    }
    
//    /**
//     * 获取appid。
//     * @param appid
//     * @return
//     */
//    private String getApp(String appid) throws JSONException {
//    	String result = httpGet("https://account.dreawer.com/app/detail", "appid=" + appid);
//    	if(StringUtils.isNotBlank(result)){
//    		JSONObject json = new JSONObject(result);
//    		if(json!=null && json.getBoolean("status")){
//    			JSONObject data = json.getJSONObject("data");
//    			return data.getString("id");
//    		}
//    		logger.error("result="+result);
//    	}
//    	return null;
//    }

	/**
	 * 修改企业可用类目信息
	 */
	@RequestMapping(value = "/editMerchanCategories",method = RequestMethod.POST)
	public @ResponseBody ResponseCode editMerchanCategories(HttpServletRequest req,@RequestBody @Valid AddCategoryForm form,BindingResult result){
		if (result.hasErrors()) {
			return checkErrors(result);
		}
			String ids = form.getIds();
			String storeId = form.getStoreId();
			Enterprise enterprise = enterpriseService.findEnterpriseById(storeId);
			if (enterprise==null){
				return Error.DB("未查询到该企业");
			}
			enterprise.setMerchanCategories(ids);
			enterpriseService.updateMerchanCategories(enterprise);
			return Success.SUCCESS(enterprise);

	}

	/**
	 * 修改是否在商品详情显示会员注册通道
	 */
	@RequestMapping(value = "/editPortStatus",method = RequestMethod.POST)
	public @ResponseBody ResponseCode editMemberRegisterPort(HttpServletRequest req, @RequestBody @Valid EditMerchanCategoriesForm form, BindingResult result){
		if (result.hasErrors()) {
			return checkErrors(result);
		}

			Boolean type = Boolean.valueOf(form.getType());
			String storeId = form.getStoreId();
			Enterprise enterprise = enterpriseService.findEnterpriseById(storeId);
			if (enterprise==null){
				return Error.DB("未查询到该企业");
			}
			enterpriseService.updateMemberRegisterPort(storeId,type);
			return Success.SUCCESS(enterprise);

	}

	/**
	 * 修改非会员是否显示会员权益
	 */
	@RequestMapping(value = "/editMemberDisplay",method = RequestMethod.POST)
	public @ResponseBody ResponseCode editMemberDisplay(HttpServletRequest req, @RequestBody @Valid EditMerchanCategoriesForm form, BindingResult result){
		if (result.hasErrors()) {
			return checkErrors(result);
		}
		Boolean type = Boolean.valueOf(form.getType());
		String storeId = form.getStoreId();
		Enterprise enterprise = enterpriseService.findEnterpriseById(storeId);
		if (enterprise==null){
			return Error.DB("未查询到该企业");
		}
		enterpriseService.updateMemberDisplay(storeId,type);
		return Success.SUCCESS(enterprise);

	}

	/**
	 * 添加轮播图。
	 * @param req 用户请求。
	 * @param form 添加信息表单。
	 * @param result 表单校验结果。
	 * @return 执行结果。
	 */
	@RequestMapping(value="/addCarousel", method=RequestMethod.POST)
	public @ResponseBody
	ResponseCode addCarousel(HttpServletRequest req,
					 @RequestBody @Valid AddCarouselForm form, BindingResult result) {
		if (result.hasErrors()) {
			return checkErrors(result);
		}
		String userId = req.getHeader("userId");
		Carousel carousel = new Carousel();
		carousel.setDisplay(Boolean.valueOf(form.getDisplay()));
		carousel.setImage(form.getImage());
		carousel.setRedirectId(form.getRedirectId());
		carousel.setRedirectInfo(form.getRedirectInfo());
		carousel.setStoreId(form.getStoreId());
		carousel.setTitle(form.getTitle());
		carousel.setType(form.getType());
		carousel.setUserId(userId);
		carousel.setCreateTime(getNow());
		carouselService.addCarousel(carousel);
		return Success.SUCCESS(carousel);

	}

	/**
	 * 修改轮播图。
	 * @param req 用户请求。
	 * @param form 添加信息表单。
	 * @param result 表单校验结果。
	 * @return 执行结果。
	 */
	@RequestMapping(value="/editCarousel", method=RequestMethod.POST)
	public @ResponseBody
	ResponseCode editCarousel(HttpServletRequest req,
					 @RequestBody @Valid EditCarouselForm form, BindingResult result) {
		if (result.hasErrors()) {
			return checkErrors(result);
		}
		String userId = req.getHeader("userId");
		Carousel carousel = carouselService.findCarouselById(form.getId());
		if (carousel==null){
			return Error.DB("轮播图不存在");
		}
		carousel.setDisplay(Boolean.valueOf(form.getDisplay()));
		carousel.setImage(form.getImage());
		carousel.setRedirectId(form.getRedirectId());
		carousel.setRedirectInfo(form.getRedirectInfo());
		carousel.setStoreId(form.getStoreId());
		carousel.setTitle(form.getTitle());
		carousel.setType(form.getType());
		carousel.setUpdaterId(userId);
		carousel.setUpdateTime(getNow());
		carouselService.editCarousel(carousel);
		return Success.SUCCESS(carousel);
	}

	/**
	 * 删除轮播图。
	 * @param req 用户请求。
	 * @return 执行结果。
	 */
	@RequestMapping(value="/deleteCarousel", method=RequestMethod.GET)
	public @ResponseBody
	ResponseCode deleteCarousel(HttpServletRequest req,@RequestParam("ID")String id) {
		Carousel carousel = carouselService.findCarouselById(id);
		if (carousel==null){
			return Error.DB("轮播图不存在");
		}
		carouselService.removeCarousel(carousel);
		return Success.SUCCESS(carousel);
	}

	/**
	 * 轮播图列表。
	 * @param req 用户请求。
	 * @return 执行结果。
	 */
	@RequestMapping(value="/listCarousel", method=RequestMethod.POST)
	public @ResponseBody
	ResponseCode listCarousel(HttpServletRequest req,@RequestParam("display")String display,
							  @RequestParam(STORE_ID)String storeId) {

		if (display==null){
			return EntryError.EMPTY("查询类型不存在");
		}

		List<Carousel> list = carouselService.findAllCarouselByStoreId(storeId,display);
		return Success.SUCCESS(list);
	}


}
