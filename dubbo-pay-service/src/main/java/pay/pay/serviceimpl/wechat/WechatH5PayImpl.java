package pay.pay.serviceimpl.wechat;

import org.springframework.stereotype.Service;
import pay.pay.api.wechat.WechatH5Pay;

/**
 * @author wgt
 * @date 2017-12-04 下午4:38
 * @description 微信H5支付
 * -商户已有H5商城网站，用户通过消息或扫描二维码在微信内打开网页时，可以调用微信支付完成下单购买的流程。
 **/
@Service
public class WechatH5PayImpl extends WechatPayImpl implements WechatH5Pay {
}
