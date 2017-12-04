package pay.pay.api.wechat;

import pay.common.baseRsult.Result;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-11-30 下午5:39
 * @description 微信刷卡支付
 * -刷卡支付是用户展示微信钱包内的“刷卡条码/二维码”给商户系统扫描后直接完成支付的模式。主要应用线下面对面收银的场景。
 **/
public interface WechatCardPay extends WechatPay{

    /**
     * 提交刷卡支付
     * -收银员使用扫码设备读取微信用户刷卡授权码以后，二维码或条码信息传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    Result<Map<String, String>> microPay(boolean isSandbox,Map<String, String> map);

}
