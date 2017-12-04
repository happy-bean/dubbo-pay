package pay.pay.serviceimpl.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

/**
 * @author wgt
 * @date 2017-12-04 下午2:30
 * @description 微信配置类
 **/
public class WechatPayConfig implements WXPayConfig {

    /**
     * 证书路径
     * */
    private String certPath="";

    /**
     * 公众账号id
     * */
    private String appID="";

    /**
     * 商户号
     * */
    private String mchID="";

    /**
     * 密钥
     * */
    private String key="";

    /**
     * 连接超时时间
     * */
    private int httpConnectTimeoutMs=0;

    /**
     * 读取超时时间
     * */
    private int httpReadTimeoutMs=0;

    @Override
    public String getAppID() {
        return this.appID;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        byte[] certData=null;
        File file=new File(this.certPath);
        try {
            InputStream stream=new FileInputStream(file);
            certData=new byte[(int)file.length()];
            stream.read(certData);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream stream=new ByteArrayInputStream(certData);
        return stream;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return this.httpConnectTimeoutMs;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return this.httpReadTimeoutMs;
    }
}
