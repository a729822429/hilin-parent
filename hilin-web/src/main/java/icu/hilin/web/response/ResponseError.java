package icu.hilin.web.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseError extends RuntimeException {

    private final ResponseVO<?> responseVO;

    public ResponseError() {
        this(ResponseEnum.FAILED);
    }

    public ResponseError(int code) {
        this(code, ResponseEnum.FAILED.getMsg());
    }

    public ResponseError(String msg) {
        this(ResponseEnum.FAILED.getCode(), msg);
    }

    public ResponseError(int code, String msg) {
        this.responseVO = ResponseVO.builder()
                .code(code)
                .msg(msg)
                .time(LocalDateTime.now())
                .build();
    }

    public ResponseError(ResponseEnum responseEnum) {
        this.responseVO = responseEnum.build();
    }

    public ResponseError(ResponseVO<?> responseVO) {
        this.responseVO = responseVO;
    }
}
