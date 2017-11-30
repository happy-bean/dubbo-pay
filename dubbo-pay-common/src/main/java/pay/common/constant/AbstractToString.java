package pay.common.constant;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author wgt
 * @date 2017-11-16 下午9:43
 * @description 对象序列化基类
 **/
public abstract class AbstractToString implements Serializable {

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
