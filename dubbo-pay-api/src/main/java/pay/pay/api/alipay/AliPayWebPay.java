package pay.pay.api.alipay;

import pay.common.baseRsult.Result;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-01 下午2:17
 * @description 支付宝网站支付
 **/
public interface AliPayWebPay extends AliPay{

    /**
     * 统一收单下单并支付页面接口
     * -PC场景下单并支付
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> pagePay(boolean isSandbox,Map<String, String> map);

    /**
     * 统一收单交易退款查询接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> refundQuery(boolean isSandbox,Map<String, String> map);

    /**
     * 统一收单交易关闭接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> tradeClose(boolean isSandbox,Map<String, String> map);

 }
