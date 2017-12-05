package pay.pay.api.alipay;

import com.alipay.demo.trade.model.GoodsDetail;
import pay.common.baseRsult.Result;

import java.util.List;
import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-01 下午3:33
 * @description 支付宝扫码支付（当面付）
 **/
public interface AlipayScanPay extends AliPay{

    /**
     * 统一收单交易支付接口（条码支付）
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @param goodsDetailList
     * @return
     * */
    Result<?> tradePay(boolean isSandbox, Map<String, String> map,List<GoodsDetail> goodsDetailList);

    /**
     * 统一收单交易撤销接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     * */
    Result<?> tradeCancel(boolean isSandbox, Map<String, String> map);

    /**
     * 交易保障接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     * */
    Result<?> heartbeatSyn(boolean isSandbox, Map<String, String> map);
}
