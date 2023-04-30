package com.project.carpediem.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.project.carpediem.dto.common.SchemaDescriptionUtils;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
public class SignupRequestDto {

    @NotNull(message = "학번은 필수 값입니다.")
    @Schema(description = SchemaDescriptionUtils.Member.STUDENTNUM, example = "학번" , maxLength = 20)
    private String studentNum;

    @NotNull(message = "비밀번호는 필수 값입니다.")
    @Schema(description = SchemaDescriptionUtils.Member.PASSWORD, example = "비밀번호" , maxLength = 20)
//    @Pattern(regexp = "^([a-zA-Z[0-9]]){8,15}$", message = "비밀번호는 8자 이상의 영대소문자, 숫자만 가능합니다.")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

}
