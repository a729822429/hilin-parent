package icu.hilin.web;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    SUCCESS(200, "成功"),
    BAD_REQUEST(401, "参数错误"),
    UNAUTHORIZED(401, "认证失败"),
    FORBIDDEN(403, "服务器拒绝请求"),
    NOT_FOUND(404, "资源不存在"),
    BAD_METHOD(405, "不支持该请求方式"),
    FAILED(500, "执行失败");


    private final int code;
    private final String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public <T> ResponseVO<T> data(T data) {
        return msgData(msg, data);
    }

    public <T> ResponseVO<T> build() {
        return msgData(msg, null);
    }

    public <T> ResponseVO<T> msg(String msg) {
        return msgData(msg, null);
    }

    public <T> ResponseVO<T> msgData(String msg, T data) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(code);
        responseVO.setMsg(msg);
        responseVO.setData(data);
        return responseVO;
    }
}
