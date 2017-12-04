package pay.common.baseRsult;


import pay.common.constant.AbstractToString;

/**
 * @author wgt
 * @date 2017-11-16 下午9:42
 * @description 响应结果
 **/
public class Result<T> extends AbstractToString {

    private static final long SERIVALVERSIONUID = 2212323556526756735L;

    /**
     * 返回结果码
     */
    private int code;

    /**
     * 响应结果信息
     */
    private String mess;

    /**
     * 要返回的数据类型
     **/
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
