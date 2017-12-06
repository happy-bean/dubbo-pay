package pay.pay.serviceimpl.alipay;

import org.springframework.stereotype.Service;
import pay.common.baseRsult.Result;
import pay.pay.api.alipay.AliPayWebPay;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-06 上午9:49
 * @description 支付宝网站支付
 **/
@Service
public class AliPayWebPayImpl extends AlipayImpl implements AliPayWebPay{

    @Override
    public Result<?> pagePay(boolean isSandbox, Map<String, String> map) {
        return null;
    }

    @Override
    public Result<?> refundQuery(boolean isSandbox, Map<String, String> map) {
        return null;
    }

    @Override
    public Result<?> tradeClose(boolean isSandbox, Map<String, String> map) {
        return null;
    }
}
