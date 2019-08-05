package util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class AliNoteSend {
    //短信签名
    private static final String SignName = "千云腾跃";
    //短信模板编号
    private static final String TemplateCode = "SMS_160301721";
    //akid
    private static final String AccessKeyId = "LTAILvdoGZktd8Mn";
    //aksecret
    private static final String AccessSecret = "M25UxHhXg4PMNDpnY6hVQQqQbNW1r7";

    //传入两个参数，一个是手机号，一个是验证码内容
    public static void sendNote(String PhoneNumbers,String Code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", AccessKeyId, AccessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+Code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
