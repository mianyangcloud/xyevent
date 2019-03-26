package com.xiaoyang.event.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.constant.OSS;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.SMSService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SMSServiceImpl implements SMSService{
	
	public int sendCode(String mobilenum) {
		Integer code = (int)((Math.random()*9+1)*100000);
		JSONObject jsob = new JSONObject();
		jsob.put("code", code);
		DefaultProfile profile = DefaultProfile.getProfile("default", OSS.ACCESS_KEY_ID, OSS.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobilenum);
        request.putQueryParameter("SignName", "华米科技");
        request.putQueryParameter("TemplateCode", "SMS_161592988");
        request.putQueryParameter("TemplateParam", jsob.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("短信发送成功:" + response.getData());
        } catch (Exception e) {
        	throw new SysException(CommonCodes.ERROR_SYSTEM, e);
        }
		return code;
	}
}
