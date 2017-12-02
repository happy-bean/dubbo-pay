package pay.pay.service.alipay;

import pay.common.baseRsult.Result;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-01 下午2:17
 * @description 支付宝网站支付
 **/
public interface AliPayWebPay {

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
     * 统一收单交易退款接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> refund(boolean isSandbox,Map<String, String> map);

    /**
     * 统一收单交易退款查询接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> refundQuery(boolean isSandbox,Map<String, String> map);

    /**
     * 统一收单线下交易查询接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> tradeQuery(boolean isSandbox,Map<String, String> map);

    /**
     * 统一收单交易关闭接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> tradeClose(boolean isSandbox,Map<String, String> map);

    /**
     * 查询对账单下载地址
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> billDownloadUrlQuery(boolean isSandbox,Map<String, String> map);
}
