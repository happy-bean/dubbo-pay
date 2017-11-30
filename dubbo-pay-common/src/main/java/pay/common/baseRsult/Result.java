package pay.common.baseRsult;


import pay.common.constant.AbstractToString;

/**
 * @author wgt
 * @date 2017-11-16 下午9:42
 * @description
 **/
public class Result<T> extends AbstractToString {

    private static final long SERIVALVERSIONUID = 2212323556526756735L;


    /**
     * 是否处理成功
     **/
    private boolean success = true;

    /**
     * 非业务逻辑错误代码，仅在success==falses时才有效
     **/
    private String errorCode = "200";

    /**
     * 响应结果信息
     * */
    private String responseMess;

    /**
     * 响应结果代码
     * */
    private int responseCode;

    /**
     * 返回信息
     **/
    private String message;

    /**
     * 要返回的数据类型
     **/
    private T data;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResponseMess() {
        return responseMess;
    }

    public void setResponseMess(String responseMess) {
        this.responseMess = responseMess;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
