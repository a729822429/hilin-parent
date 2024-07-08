package icu.hilin.core.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaptchaVO {

    private String id;
    private String code;
    private String base64Img;

}
