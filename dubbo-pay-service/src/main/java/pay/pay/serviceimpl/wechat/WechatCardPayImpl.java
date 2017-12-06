package pay.pay.serviceimpl.wechat;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import org.springframework.stereotype.Service;
import pay.common.baseRsult.Result;
import pay.common.enums.ResultMess;
import pay.common.utils.UUIDUtil;
import pay.pay.api.wechat.WechatCardPay;
import pay.pay.serviceimpl.config.WechatPayConfig;

import java.util.Map;
import java.util.UUID;

/**
 * @author wgt
 * @date 2017-12-04 下午3:59
 * @description 微信刷卡支付
 * -刷卡支付是用户展示微信钱包内的“刷卡条码/二维码”给商户系统扫描后直接完成支付的模式。主要应用线下面对面收银的场景。
 **/
@Service
public class WechatCardPayImpl extends WechatPayImpl implements WechatCardPay{

    /**
     * 提交刷卡支付
     * -收银员使用扫码设备读取微信用户刷卡授权码以后，二维码或条码信息传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> microPay(boolean isSandbox,Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.microPay(map);
            result.setCode(ResultMess.SUCCESS_CODE.getCode());
            result.setMess(ResultMess.SUCCESS_CODE.getMess());
            result.setData(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultMess.ERROR_CODE.getCode());
            result.setMess(ResultMess.ERROR_CODE.getMess());
        }
        return result;
    }
}
