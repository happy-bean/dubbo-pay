package pay.pay.service.alipay;

import pay.common.baseRsult.Result;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-04 上午10:25
 * @description 支付宝app支付
 **/
public interface AliPayAppPay extends AliPay{

    /**
     * 统一收单交易关闭接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    Result<?> tradeClose(boolean isSandbox, Map<String, String> map);
}
