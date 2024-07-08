package icu.hilin.web.util;

import cn.hutool.core.util.ObjectUtil;
import icu.hilin.core.entity.vo.ResponseError;
import icu.hilin.core.entity.vo.ResponseVO;

public class AssertUtils {

    public static void isNotEmpty(Object obj, String msg) {
        if (ObjectUtil.isEmpty(obj)) {
            throw new ResponseError(msg);
        }
    }

    public static void isNotEmpty(Object obj, ResponseVO<?> respVo) {
        if (ObjectUtil.isEmpty(obj)) {
            throw new ResponseError(respVo);
        }
    }

    public static void isNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new ResponseError(msg);
        }
    }

    public static void isNotNull(Object obj, ResponseVO<?> respVo) {
        if (obj == null) {
            throw new ResponseError(respVo);
        }
    }

    public static void isRex(boolean rex, String msg) {
        if (!rex) {
            throw new ResponseError(msg);
        }
    }

    public static void isRex(boolean rex, ResponseVO<?> respVo) {
        if (!rex) {
            throw new ResponseError(respVo);
        }
    }

    public static void throwEx(ResponseVO<?> respVo) {
        throw new ResponseError(respVo);
    }

    public static void throwEx(String msg) {
        throw new ResponseError(msg);
    }



    public static void isEmpty(Object obj, String msg) {
        if (ObjectUtil.isNotEmpty(obj)) {
            throw new ResponseError(msg);
        }
    }

    public static void isEmpty(Object obj, ResponseVO<?> respVo) {
        if (ObjectUtil.isNotEmpty(obj)) {
            throw new ResponseError(respVo);
        }
    }

    public static void isNull(Object obj, String msg) {
        if (obj != null) {
            throw new ResponseError(msg);
        }
    }

    public static void isNull(Object obj, ResponseVO<?> respVo) {
        if (obj != null) {
            throw new ResponseError(respVo);
        }
    }
    
}
