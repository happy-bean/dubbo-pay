package pay.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @author wgt
 * @date 2017-12-04 下午6:08
 * @description UUID工具类
 **/
public class UUIDUtil {
    /**
     * generate token
     *
     * @return token
     */
    public static String generateToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 生成带有前缀的uuid,格式为prefix-XXXXXX
     *
     * @param prefix
     * @return
     */
    public static String generateUUID(String prefix) {
        String uuid = UUID.randomUUID().toString();

        return prefix + "-" + StringUtils.replace(uuid, "-", "");
    }

    /**
     * 生成六位的随机数
     *
     * @return 六位随机数的字符串
     */
    public static String generateRandom() {
        return new Integer(new Random().nextInt(1000000)).toString();
    }
}
