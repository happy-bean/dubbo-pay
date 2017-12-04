package pay.common.enums;

/**
 * @author wgt
 * @date 2017-11-17 下午4:35
 * @description 响应信息
 **/
public enum ResultMess {

    /**
     * 成功
     */
    SUCCESS_CODE(200, "成功"),

    /**
     * 请求错误
     */
    ERROR_CODE(400, "请求错误"),

    /**
     * 未授权
     */
    PERMISSION_ERROR_CODE(401, "未授权"),

    /**
     * 未满足期望值
     */
    SATISFY_ERROR_CODE(417, "未满足期望值"),

    /**
     * 服务器错误
     */
    SERVER_ERROR_CODE(500, "服务器错误");

    /**
     * 枚举代码
     */
    private int code;

    /**
     * 枚举信息
     */
    private String mess;

    /**
     * 构造函数
     *
     * @param code 枚举代码
     * @param mess 枚举信息
     */
    private ResultMess(int code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    /**
     * 通过枚举 code 获得枚举。
     *
     * @param code
     * @return
     */
    public static ResultMess getByCode(int code) {
        for (ResultMess param : values()) {
            if (param.getCode() == code) {
                return param;
            }
        }
        return null;
    }

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
}
