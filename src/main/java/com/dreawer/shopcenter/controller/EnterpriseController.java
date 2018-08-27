package com.dreawer.shopcenter.controller;

import com.dreawer.responsecode.rcdt.EntryError;
import com.dreawer.responsecode.rcdt.Error;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.Success;
import com.dreawer.shopcenter.domain.Carousel;
import com.dreawer.shopcenter.domain.Enterprise;
import com.dreawer.shopcenter.exception.ResponseCodeException;
import com.dreawer.shopcenter.form.*;
import com.dreawer.shopcenter.service.CarouselService;
import com.dreawer.shopcenter.service.EnterpriseService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
import static com.dreawer.shopcenter.consts.DomainConstants.STORE_ID;

@Controller(ENTERPRISE_CONTROLLER)
@RequestMapping("/enterprise")
@Slf4j
public class EnterpriseController extends BaseController{

	/** 获取小程序头像昵称接口 **/
	private static final String URL_APP_INFO = "http://mpomc/auth/appInfo";

	/** 获取小程序类目接口 **/
	private static final String URL_APP_CATEGORY = "http://mpomc/auth/appCategory";

	@Autowired
	private EnterpriseService enterpriseService; // 企业信息服务


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
        	//店铺通过授权创建,如果该接口被重新授权调用则只用更新小程序授权信息即可,无需重新创建店铺
			String userId = req.getHeader("userid");
			String appid = form.getAppid();
	    	if(StringUtils.isBlank(appid)){
		    	return Error.EXT_RESPONSE(MSG_APP_NULL);
	    	}

			Enterprise ep = enterpriseService.findEnterpriseByApp(form.getAppid());
	    	if (ep!=null){


			}else {
				ep.setAppid(form.getAppid());
				ep.setCategory("media");
				ep.setName(form.getName());
				ep.setAppName(form.getAppName());
				ep.setLogo(form.getLogo());
				ep.setIntro(form.getIntro());
			}




	    	//添加特许证件和营业执照
            if (!StringUtils.isBlank(form.getBusinessLicense())){
                ep.setBusinessLicense(form.getBusinessLicense());
            }
            if (!StringUtils.isBlank(form.getCertificate())&&StringUtils.isBlank(form.getCertType())){
                return EntryError.EMPTY(MSG_CERTIFICATE_TYPE_NULL);
            }

            if (!StringUtils.isBlank(form.getCertificate())){
                ep.setCertificate(form.getCertificate());
                ep.setCertType(form.getCertType());
            }

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

			String userId = req.getHeader("userid");
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
			if(StringUtils.isNotBlank(form.getAppName())){
		    	ep.setAppName(form.getAppName());
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

        //添加特许证件和营业执照
        if (!StringUtils.isBlank(form.getBusinessLicense())){
            ep.setBusinessLicense(form.getBusinessLicense());
        }
        if (!StringUtils.isBlank(form.getCertificate())&&StringUtils.isBlank(form.getCertType())){
            return EntryError.EMPTY(MSG_CERTIFICATE_TYPE_NULL);
        }

        if (!StringUtils.isBlank(form.getCertificate())){
            ep.setCertificate(form.getCertificate());
            ep.setCertType(form.getCertType());
        }

        //修改省市区
		if (!StringUtils.isBlank(form.getCity())){
			ep.setCity(form.getCity());
		}
		if (!StringUtils.isBlank(form.getArea())){
			ep.setArea(form.getArea());
		}
		if (!StringUtils.isBlank(form.getProvince())){
			ep.setProvince(form.getProvince());
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
		String userId = req.getHeader("userid");
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
		String userId = req.getHeader("userid");
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

	/**
	 * 轮播图排序。
	 * @param req 用户请求。
	 * @return 执行结果。
	 */
	@RequestMapping(value="/orderCarousel", method=RequestMethod.POST)
	public @ResponseBody
	ResponseCode orderCarousel(HttpServletRequest req, @RequestBody OrderCarouselForm form) {
		String userid = req.getHeader("userid");
		carouselService.updateSequenceById(form.getForm());
		return Success.SUCCESS;
	}

	/**
	 * 同步微信小程序信息
	 * @param req 用户请求
	 * @return
	 */
	@GetMapping(value = "/syncApp")
	public @ResponseBody ResponseCode syncApp(HttpServletRequest req) throws ResponseCodeException {
		String storeid = req.getHeader("appid");
		if (StringUtils.isBlank(storeid)){
			return Error.EXT_RESPONSE("未获取到店铺id");
		}
		Enterprise enterprise = enterpriseService.findEnterpriseById(storeid);
		if(enterprise==null){
			return Error.DB("未找到该企业");
		}
		String response = restGet(URL_APP_INFO + "?" + "storeId=" + storeid);
		log.info("运维中心返回结果:"+response);
        ResponseCode responseCode = ResponseCode.instanceOf(response);
        if (!responseCode.getCode().equals("000000")){
            throw new ResponseCodeException(responseCode);
        }
		Map<String,String> map = new Gson().fromJson(responseCode.getData().toString(), Map.class);
		String nickName = map.get("nickName");
		String logo = map.get("logo");
		enterprise.setLogo(logo);
		enterprise.setAppName(nickName);
		enterpriseService.updateBasic(enterprise);
		return Success.SUCCESS(enterprise);
	}


	/**
	 * 获取小程序服务类目
	 * @param req 用户请求
	 * @return
	 */
	@GetMapping(value = "/getAppCategory")
	public @ResponseBody ResponseCode getAppCategory(HttpServletRequest req) throws ResponseCodeException {
		String storeid = req.getHeader("appid");
		if (StringUtils.isBlank(storeid)){
			return Error.EXT_RESPONSE("未获取到店铺id");
		}
		String response = restGet(URL_APP_CATEGORY + "?" + "storeId=" + storeid);
		log.info("运维中心返回结果:"+response);
		ResponseCode responseCode = ResponseCode.instanceOf(response);
		if (!responseCode.getCode().equals("000000")){
			throw new ResponseCodeException(responseCode);
		}
		return Success.SUCCESS(responseCode.getData());
	}


	/**
	 * 获取小程序服务类目
	 * @param req 用户请求
	 * @return
	 */
	@GetMapping(value = "/test")
	public @ResponseBody ResponseCode test(HttpServletRequest req) throws ResponseCodeException {

		String response = restGet("http://nc/api/send");
		log.info("通知中心返回结果:"+response);
		ResponseCode responseCode = ResponseCode.instanceOf(response);
		if (!responseCode.getCode().equals("000000")){
			throw new ResponseCodeException(responseCode);
		}
		return Success.SUCCESS(responseCode.getData());
	}
}
