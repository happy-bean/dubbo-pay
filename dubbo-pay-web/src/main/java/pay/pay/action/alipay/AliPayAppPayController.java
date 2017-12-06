package pay.pay.action.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pay.pay.api.alipay.AliPay;
import java.util.HashMap;


/**
 * @author wgt
 * @date 2017-12-06 上午10:53
 * @description 支付宝app支付
 **/
@Controller
@RequestMapping(value = "/pay/alipay/apppay")
public class AliPayAppPayController {

    @Autowired
    private AliPay aliPay;

    @ResponseBody
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String test(){

        aliPay.tradeRefund(true, new HashMap<>());

        return "dubbo";
    }
}
