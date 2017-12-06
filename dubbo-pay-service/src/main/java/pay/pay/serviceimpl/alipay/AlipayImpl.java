package pay.pay.serviceimpl.alipay;

import org.springframework.stereotype.Service;
import pay.common.baseRsult.Result;
import pay.pay.api.alipay.AliPay;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-04 下午12:05
 * @description 支付宝支付
 **/
@Service("aliPay")
public class AlipayImpl implements AliPay{

    /**
     * 统一收单交易退款接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    @Override
    public Result<?> tradeRefund(boolean isSandbox, Map<String, String> map){

        System.out.println("dubbo");

        return null;
    }

    /**
     * 统一收单线下交易查询接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    @Override
    public Result<?> tradeQuery(boolean isSandbox,Map<String, String> map){

        return null;
    }

    /**
     * 查询对账单下载地址
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    @Override
   public Result<?> billDownloadUrlQuery(boolean isSandbox,Map<String, String> map){

        return null;
    }
}
