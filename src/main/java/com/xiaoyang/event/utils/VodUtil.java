package com.xiaoyang.event.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.xiaoyang.event.common.PutObjectProgressListener;
import com.xiaoyang.event.constant.OSS;
import com.xiaoyang.event.domain.Video;

import sun.misc.BASE64Encoder;

public class VodUtil {
	
	//账号AK信息请填写(必选)
    private static String access_key_id = OSS.ACCESS_KEY_ID;
    
    //账号AK信息请填写(必选)
    private static String access_key_secret = OSS.ACCESS_KEY_SECRET;
    
    //以下参数不需要修改
    private final static String VOD_DOMAIN = OSS.VOD_DOMAIN;
    
    private final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    
    private final static String HTTP_METHOD = "GET";
    
    private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    
    private final static String method = "CreateUploadVideo";
    
    private final static String UTF_8 = "utf-8";
    
    private final static Logger LOG = Logger.getLogger(VodUtil.class.getName());
    
    /**
     * 修改视频信息
     *
     * @return
     */
    public static Boolean editVideo(Video video) {
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = editVideoPrivateParamters(video.getVideoId(), video.getTitle(), "", video.getHeadImage());
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        //发送HTTP GET 请求
        String result = HttpClientUtil.getInstance().getOpenApiHttpClientStr(URL);
        if(JSONObject.parseObject(result).get("RequestId")!=null) {
        	return true;
        }else {
        	return false;
        }
    }
    
    /**
     * 获取视频原始地址
     *
     * @return
     */
    public static Map getODVideo(String videoId) {
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = getODPrivateParamters(videoId);
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        //发送HTTP GET 请求
        String result = HttpClientUtil.getInstance().getOpenApiHttpClientStr(URL);
        Map map = new HashMap();
        map = (Map) JSONObject.parseObject(result).get("Mezzanine");
        return map;
    }
    
    /**
     * 获取视频信息
     *
     * @return
     */
    public static Map getVideoInfo(String videoId) {
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = videoInfoPrivateParamters(videoId);
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        //发送HTTP GET 请求
        String result = HttpClientUtil.getInstance().getOpenApiHttpClientStr(URL);
        Map map = JSONObject.parseObject(result);
        return map;
    }

    /**
     * 获取视频上传地址
     *
     * @return
     */
    public static Map getUploadConf(String title, String fileName) {
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = uploadPrivateParamters(title, fileName);
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        //发送HTTP GET 请求
        String result = HttpClientUtil.getInstance().getOpenApiHttpClientStr(URL);
        
        Map map = new HashMap();
        map.put("accessKeyId", OSS.ACCESS_KEY_ID);
        map.put("accessKeySecret", OSS.ACCESS_KEY_SECRET);
    	map.put("endpoint", OSS.END_POINT);
    	map.put("bucket", OSS.BUCKET_VIDEO);
    	map.put("objectPre", DateUtil.getDatePath());
        if(StringUtils.isEmpty(result)) {
        	map.put("videoId", "");
        	map.put("uploadAuth", "");
        	map.put("uploadAddress", "");
        	map.put("requestId", "");
        }else {
        	JSONObject jsonb = JSONObject.parseObject(result);
        	map.put("videoId", jsonb.get("VideoId").toString());
        	map.put("uploadAuth", jsonb.get("UploadAuth").toString());
        	map.put("uploadAddress", jsonb.get("UploadAddress").toString());
        	map.put("requestId", jsonb.get("RequestId").toString());
        }
        return map;
    }
    
    /**
     * 修改视频信息私有参数
     *
     * @return
     */
    private static Map<String, String> editVideoPrivateParamters(String videoId, String title, String Description, String CoverURL) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // API名称
        privateParams.put("Action", "UpdateVideoInfo");
        privateParams.put("VideoId", videoId);
        privateParams.put("Title", videoId);
        privateParams.put("Description", Description);
        privateParams.put("CoverURL", CoverURL);
        return privateParams;
    }
    
    /**
     * 获取视频原始地址私有参数
     *
     * @return
     */
    private static Map<String, String> getODPrivateParamters(String videoId) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // API名称
        privateParams.put("Action", "GetMezzanineInfo");
        privateParams.put("VideoId", videoId);
        return privateParams;
    }

    /**
     * 获取视频上传地址接口私有参数
     *
     * @return
     */
    private static Map<String, String> uploadPrivateParamters(String title, String fileName) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("Title", title);
        // API名称
        privateParams.put("Action", "CreateUploadVideo");
        privateParams.put("FileName", fileName);
        return privateParams;
    }
    
    /**
     * 获取视频信息接口私有参数
     *
     * @return
     */
    private static Map<String, String> videoInfoPrivateParamters(String videoId) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("VideoId", videoId);
        // API名称
        privateParams.put("Action", "GetPlayInfo");
        return privateParams;
    }
    
    /**
     * 刷新视频上传地址接口私有参数
     *
     * @return
     */
    private static Map<String, String> refreshVideoPrivateParamters(String videoId) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("VideoId", videoId);
        // API名称
        privateParams.put("Action", "RefreshUploadVideo");
        return privateParams;
    }

    /**
     * 生成视频点播OpenAPI公共参数
     * 不需要修改
     *
     * @return
     */
    private static Map<String, String> generatePublicParamters() {
        Map<String, String> publicParams = new HashMap<>();
        publicParams.put("Format", "JSON");
        publicParams.put("Version", "2017-03-21");
        publicParams.put("AccessKeyId", access_key_id);
        publicParams.put("SignatureMethod", "HMAC-SHA1");
        publicParams.put("Timestamp", generateTimestamp());
        publicParams.put("SignatureVersion", "1.0");
        publicParams.put("SignatureNonce", generateRandom());
//        if (security_token != null && security_token.length() > 0) {
//            publicParams.put("SecurityToken", security_token);
//        }
        return publicParams;
    }

    /**
     * @param domain        请求地址
     * @param httpMethod    HTTP请求方式GET，POST等
     * @param publicParams  公共参数
     * @param privateParams 接口的私有参数
     * @return 最后的url
     */
    private static String generateURL(String domain, String httpMethod, Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> allEncodeParams = getAllParams(publicParams, privateParams);
        String cqsString = getCQS(allEncodeParams);
        //out("CanonicalizedQueryString = " + cqsString);
        String stringToSign = httpMethod + "&" + percentEncode("/") + "&" + percentEncode(cqsString);
        //out("StringtoSign = " + stringToSign);
        String signature = hmacSHA1Signature(access_key_secret, stringToSign);
        //out("Signature = " + signature);
        return domain + "?" + cqsString + "&" + percentEncode("Signature") + "=" + percentEncode(signature);
    }

    private static List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> encodeParams = new ArrayList<String>();
        if (publicParams != null) {
            for (String key : publicParams.keySet()) {
                String value = publicParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        if (privateParams != null) {
            for (String key : privateParams.keySet()) {
                String value = privateParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        return encodeParams;
    }

    /**
     * 参数urlEncode
     *
     * @param value
     * @return
     */
    private static String percentEncode(String value) {
        try {
            String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
            String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
            String starReplaced = plusReplaced.replace("*", "%2A");
            String waveReplaced = starReplaced.replace("%7E", "~");
            return waveReplaced;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取CQS 的字符串
     *
     * @param allParams
     * @return
     */
    private static String getCQS(List<String> allParams) {
        ParamsComparator paramsComparator = new ParamsComparator();
        Collections.sort(allParams, paramsComparator);
        String cqString = "";
        for (int i = 0; i < allParams.size(); i++) {
            cqString += allParams.get(i);
            if (i != allParams.size() - 1) {
                cqString += "&";
            }
        }

        return cqString;
    }

    private static class ParamsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }

    private static String hmacSHA1Signature(String accessKeySecret, String stringtoSign) {
        try {
            String key = accessKeySecret + "&";
            try {
                SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
                Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
                mac.init(signKey);
                byte[] rawHmac = mac.doFinal(stringtoSign.getBytes());
                //按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
                return new String(new BASE64Encoder().encode(rawHmac));
            } catch (Exception e) {
                throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
            }
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }

    /**
     * 生成当前UTC时间戳
     *
     * @return
     */
    public static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    private static void out(String newLine) {
        LOG.log(Level.INFO, newLine);
    }
    
    /**
     * 生成OpenAPI地址
     * @param privateParams
     * @return
     * @throws Exception
     */
    private static String generateOpenAPIURL(Map<String, String> publicParams, Map<String, String> privateParams) {
        return generateURL(VOD_DOMAIN, HTTP_METHOD, publicParams, privateParams);
    }

    public static String videoUploadStream(String accessKeyId, String accessKeySecret, String title, String fileName, InputStream inputStream, Integer videoId) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        request.setCallback("https://www.ehuami.cn/xyevent/api/appleTree/video/uploadSuccess");
        /* 自定义消息回调设置，参数说明参考文档 https://help.aliyun.com/document_detail/86952.html#UserData */
        request.setUserData("{\"Extend\":{\"videoId\":\"" + videoId + "\"},\"MessageCallback\":{\"CallbackURL\":\"https://www.ehuami.cn/xyevent/api/appleTree/video/uploadSuccess\"}}");
        /* 模板组ID(可选) */
        request.setTemplateGroupId("613c013504c0bb350eefffd2ae49b017");
        /* 存储区域(可选) */
        request.setStorageLocation("out-20180417165559142-e33m6aeum1.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        if (response.isSuccess()) {
            return response.getVideoId();
        }
        return null;
    }

}
