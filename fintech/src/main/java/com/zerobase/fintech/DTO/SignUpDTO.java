package com.zerobase.fintech.DTO;

import com.zerobase.fintech.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SignUpDTO {

    @Size(max = 30, message = "이메일은 30자이상 입력하면 안됩니다.")
    @Email(message = "이메일 형식으로 작성해주세요.")
    @NotBlank(message = "이메일은 필수로 작성해야합니다.")
    private String email;

    @Size(max = 30, message = "비밀번호는 30자리 이하로 입력해주세요.")
    @NotBlank(message = "비밀번호는 필수로 작성해야합니다.")
    private String password;

    @Size(max = 10, message = "이름은 10자리 이하로 입력해주세요.")
    @NotBlank(message = "이름은 필수로 작성해야합니다.")
    private String userName;

    @Size(max =15, message = "연락처는 15자리 이하로 입력해주세요.")
    private String phoneNumber;

    private Authority role;

}
