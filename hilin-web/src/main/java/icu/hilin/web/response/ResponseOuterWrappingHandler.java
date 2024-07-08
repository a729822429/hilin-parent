package icu.hilin.web.response;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import icu.hilin.core.entity.vo.ResponseEnum;
import icu.hilin.core.entity.vo.ResponseVO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@RestControllerAdvice
@ConditionalOnProperty(prefix = "icu.hilin.web",
        name = "responseWrapping",
        havingValue = "true"
)
public class ResponseOuterWrappingHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        NoResponseWrapping noResponseWrapping = returnType.getMethodAnnotation(NoResponseWrapping.class);
        return ObjectUtil.isNull(noResponseWrapping);
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType, final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType, final ServerHttpRequest request, final ServerHttpResponse response) {
        ServletServerHttpResponse serverHttpResponse = (ServletServerHttpResponse) response;
        if (serverHttpResponse.getServletResponse().getStatus() != 200) {
            return body;
        }
        if (body instanceof String) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return JSONUtil.toJsonStr(ResponseEnum.SUCCESS.data(body));
        }
        if (Objects.isNull(body)) {
            return ResponseEnum.SUCCESS.build();
        }
        if (body instanceof ResponseVO) {
            return body;
        }
        return ResponseEnum.SUCCESS.data(body);
    }

}
