package pay.pay.serviceimpl.wechat;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import org.springframework.stereotype.Service;
import pay.common.baseRsult.Result;
import pay.common.enums.ResultMess;
import pay.common.utils.UUIDUtil;
import pay.pay.api.wechat.WechatPay;
import pay.pay.serviceimpl.config.WechatPayConfig;

import java.util.Map;

/**
 * @author wgt
 * @date 2017-12-04 下午1:22
 * @description 微信支付
 **/
@Service
public class WechatPayImpl implements WechatPay {

    /**
     * 统一下单
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> unifiedOrder(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.unifiedOrder(map);
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

    /**
     * 查询订单
     * -支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，微信支付系统会将此订单关闭；如果用户支付成功，微信支付系统会将此订单资金退还给用户。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> orderQuery(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.orderQuery(map);
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

    /**
     * 撤销订单
     * -支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，微信支付系统会将此订单关闭；如果用户支付成功，微信支付系统会将此订单资金退还给用户。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> reverse(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.reverse(map);
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

    /**
     * 关闭订单
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> closeOrder(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.closeOrder(map);
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

    /**
     * 申请退款
     * -当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> refund(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.refund(map);
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


    /**
     * 查询退款
     * -提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> refundQuery(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.refundQuery(map);
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

    /**
     * 下载对账单
     * -商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> downloadBill(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.downloadBill(map);
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

    /**
     * 交易保障
     * -商户在调用微信支付提供的相关接口时，会得到微信支付返回的相关信息以及获得整个接口的响应时间。为提高整体的服务水平，协助商户一起提高服务质量，微信支付提供了相关接口调用耗时和返回信息的主动上报接口，微信支付可以根据商户侧上报的数据进一步优化网络部署，完善服务监控，和商户更好的协作为用户提供更好的业务体验。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> payitilReport(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.report(map);
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

    /**
     * 转换短链接
     * -该接口主要用于扫码原生支付模式一中的二维码链接转成短链接(weixin://wxpay/s/XXXXXX)，减小二维码数据量，提升扫描速度和精确度。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> shotUrl(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.shortUrl(map);
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

    /**
     * 授权码查询openid
     * -通过授权码查询公众号Openid，调用查询后，该授权码只能由此商户号发起扣款，直至授权码更新。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> authCodeToOpenid(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.authCodeToOpenid(map);
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

    /**
     * 拉取订单评价数据
     * -商户可以通过该接口拉取用户在微信支付交易记录中针对你的支付记录进行的评价内容。商户可结合商户系统逻辑对该内容数据进行存储、分析、展示、客服回访以及其他使用。如商户业务对评价内容有依赖，可主动引导用户进入微信支付交易记录进行评价。
     *
     * @param isSandbox 是否沙盒模式
     * @param map
     * @return
     */
    @Override
    public Result<Map<String, String>> batchQueryComment(boolean isSandbox, Map<String, String> map) {
        Result<Map<String, String>> result=new Result<>();

        map.put("nonce_str", UUIDUtil.generateToken());

        WechatPayConfig config=new WechatPayConfig();
        WXPay pay=new WXPay(config, WXPayConstants.SignType.MD5,isSandbox);

        try {
            Map<String,String> resultMap=pay.reverse(map);
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
