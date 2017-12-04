package pay.pay.service.alipay;

import pay.common.baseRsult.Result;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-01 下午3:57
 * @description 支付宝支付
 **/
public interface AliPay {

    /**
     * 统一收单交易退款接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> tradeRefund(boolean isSandbox, Map<String, String> map);

    /**
     * 统一收单线下交易查询接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> tradeQuery(boolean isSandbox,Map<String, String> map);

    /**
     * 查询对账单下载地址
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> billDownloadUrlQuery(boolean isSandbox,Map<String, String> map);

}
