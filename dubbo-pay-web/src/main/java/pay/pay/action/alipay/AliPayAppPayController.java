package pay.pay.action.alipay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pay.pay.api.alipay.AliPay;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author wgt
 * @date 2017-12-06 上午10:53
 * @description 支付宝app支付
 **/
@Controller
@RequestMapping("/pay/alipay/apppay")
public class AliPayAppPayController {

    @Resource(name="aliPay")
    private AliPay aliPay;

    @ResponseBody
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String test(){

        aliPay.tradeRefund(true, new Map<String, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(String key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends String> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<String> values() {
                return null;
            }

            @Override
            public Set<Entry<String, String>> entrySet() {
                return null;
            }
        });
        return "dubbo";
    }
}
