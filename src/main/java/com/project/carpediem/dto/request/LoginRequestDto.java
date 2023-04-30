package com.project.carpediem.dto.request;

import lombok.Getter;
import com.project.carpediem.dto.common.SchemaDescriptionUtils;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
public class LoginRequestDto {
    @Schema(description = SchemaDescriptionUtils.Member.STUDENTNUM, example = "201800111" , maxLength = 50)
    private String studentNum;
    @Schema(description = SchemaDescriptionUtils.Member.PASSWORD, example = "201800111" , maxLength = 50)
    private String password;
}
