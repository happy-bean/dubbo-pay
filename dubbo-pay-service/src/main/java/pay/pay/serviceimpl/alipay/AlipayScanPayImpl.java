package pay.pay.serviceimpl.alipay;

import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.alipay.demo.trade.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import pay.common.baseRsult.Result;
import pay.common.enums.ResultMess;
import pay.pay.api.alipay.AlipayScanPay;

import java.util.List;
import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-05 上午10:38
 * @description 支付宝扫码支付（当面付）
 **/
public class AlipayScanPayImpl extends AlipayImpl implements AlipayScanPay {

    /**
     * 支付宝当面付2.0服务
     */
    private AlipayTradeService tradeService;

    /**
     * 支付宝当面付2.0服务（集成了交易保障接口逻辑）
     */
    private AlipayTradeService tradeWithHBService;

    /**
     * 支付宝交易保障接口，供测试接口api使用
     */
    private AlipayMonitorService monitorService;

    /**
     * 构造函数
     */
    public AlipayScanPayImpl() {

        /**
         * 在创建AlipayTradeService之前调用Configs.init() 设置默认参数
         * */
        Configs.init("alipayInfo.properties");

        /**
         * 使用Configs提供默认参数
         * AlipayTradeService可以使用单例或者静态成员对象，不需要反复new
         * */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        /**
         * 支付宝当面付2.0服务（集成了交易保障接口逻辑）
         * */
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /**
         * 如果需要在程序中覆盖Configs提供的默认参数，可以使用ClientBuilder类的setXXX方法修改默认参数
         * */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
                .setGatewayUrl("http://mcloudmonitor.com/gateway.do")
                .setCharset("GBK")
                .setFormat("json")
                .build();
    }

    /**
     * 统一收单交易支付接口（条码支付）
     *
     * @param isSandbox       是否沙箱模式
     * @param map
     * @param goodsDetailList
     * @return
     */
    @Override
    public Result<?> tradePay(boolean isSandbox, Map<String, String> map, List<GoodsDetail> goodsDetailList) {

        Result<?> result = new Result<>();

        /**
         *  (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
         *  需保证商户系统端不能重复，建议通过数据库sequence生成，
         * */
        String outTradeNo = map.get("outTradeNo");

        /**
         * (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店消费”
         * */
        String subject = map.get("subject");

        /**
         * (必填) 订单总金额，单位为元，不能超过1亿元
         * 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
         * */
        String totalAmount = map.get("totalAmount");

        /**
         * (必填) 付款条码，用户支付宝钱包手机app点击“付款”产生的付款条码
         * "用户自己的支付宝付款码"; 条码示例，286648048691290423
         * */
        String authCode = map.get("authCode");

        /**
         * (可选，根据需要决定是否使用) 订单可打折金额，可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
         * 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
         * */
        String discountableAmount = map.get("discountableAmount");

        /**
         * (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
         * 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
         * */
        String undiscountableAmount = map.get("undiscountableAmount");

        /**
         * 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
         * 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
         * */
        String sellerId = map.get("sellerId");

        /**
         * 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元" 
         * */
        String body = map.get("body");

        /**
         * 商户操作员编号，添加此参数可以为商户操作员做销售统计
         * */
        String operatorId = map.get("operatorId");

        /**
         * (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
         * */
        String storeId = map.get("storeId");

        /**
         * 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
         * */
        String providerId = map.get("providerId");

        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId(providerId);

        /**
         * 支付超时，线下扫码交易定义为5分钟   5m
         * */
        String timeoutExpress = map.get("timeoutExpress");

        /**
         * 应用授权令牌,根据真实值填写
         * */
        String appAuthToken = map.get("appAuthToken");

        /**
         * 创建条码支付请求builder，设置请求参数
         * */
        AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
                .setAppAuthToken(appAuthToken)
                .setOutTradeNo(outTradeNo).setSubject(subject).setAuthCode(authCode)
                .setTotalAmount(totalAmount).setStoreId(storeId)
                .setUndiscountableAmount(undiscountableAmount).setBody(body).setOperatorId(operatorId)
                .setExtendParams(extendParams).setSellerId(sellerId)
                .setGoodsDetailList(goodsDetailList).setTimeoutExpress(timeoutExpress);

        /**
         * 调用tradePay方法获取当面付应答
         * */
        AlipayF2FPayResult payResult = tradeWithHBService.tradePay(builder);
        switch (payResult.getTradeStatus()) {
            case SUCCESS:
                result.setCode(ResultMess.SUCCESS_CODE.getCode());
                result.setMess("支付宝支付成功");
                break;

            case FAILED:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("支付宝支付失败");
                break;

            case UNKNOWN:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("系统异常，订单状态未知");
                break;

            default:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("不支持的交易状态，交易返回异常");
                break;
        }

        return result;
    }

    /**
     * 统一收单线下交易查询接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    @Override
    public Result<AlipayTradeQueryResponse> tradeQuery(boolean isSandbox, Map<String, String> map) {

        Result<AlipayTradeQueryResponse> result = new Result<>();

        /**
         * (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
         * */
        String outTradeNo = map.get("outTradeNo");

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(outTradeNo);

        AlipayF2FQueryResult queryResult = tradeService.queryTradeResult(builder);
        switch (queryResult.getTradeStatus()) {
            case SUCCESS:
                AlipayTradeQueryResponse response = queryResult.getResponse();
                if (response != null) {
                    //log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
                    if (StringUtils.isNotEmpty(response.getSubCode())) {
                        //   log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),response.getSubMsg()));
                    }
                    //log.info("body:" + response.getBody())
                }

                //log.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        // log.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }

                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("系统异常，订单支付状态未知");
                result.setData(response);

                break;

            case FAILED:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("查询返回该订单支付失败或被关闭");
                break;

            case UNKNOWN:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("系统异常，订单支付状态未知");
                break;

            default:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("不支持的交易状态，交易返回异常");
                break;
        }


        return result;
    }


    /**
     * 统一收单交易退款接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    @Override
    public Result<?> tradeRefund(boolean isSandbox, Map<String, String> map) {

        Result<?> result=new Result<>();

        /**
         * (必填) 外部订单号，需要退款交易的商户外部订单号
         * */
        String outTradeNo = map.get("outTradeNo");

        /**
         * (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
         * */
        String refundAmount = map.get("refundAmount");

        /**
         * (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
         * 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
         * */
        String outRequestNo = map.get("outRequestNo");

        /**
         * (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计 如："正常退款，用户买多了"
         * */
        String refundReason = map.get("refundReason");

        /**
         * (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
         * */
        String storeId = "test_store_id";

        /**
         * 创建退款请求builder，设置请求参数
         * */
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
                .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
                .setOutRequestNo(outRequestNo).setStoreId(storeId);

        AlipayF2FRefundResult f2FRefundResult = tradeService.tradeRefund(builder);
        switch (f2FRefundResult.getTradeStatus()) {
            case SUCCESS:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("支付宝退款成功");
                break;

            case FAILED:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("支付宝退款失败");
                break;

            case UNKNOWN:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("系统异常，订单退款状态未知");
                break;

            default:
                result.setCode(ResultMess.ERROR_CODE.getCode());
                result.setMess("不支持的交易状态，交易返回异常");
                break;
        }

        return result;
    }

    /**
     * 统一收单交易撤销接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    @Override
    public Result<?> tradeCancel(boolean isSandbox, Map<String, String> map) {
        return null;
    }

    /**
     * 交易保障接口
     *
     * @param isSandbox 是否沙箱模式
     * @param map
     * @return
     */
    @Override
    public Result<?> heartbeatSyn(boolean isSandbox, Map<String, String> map) {
        return null;
    }

    public static void main(String[] args) {

    }
}

