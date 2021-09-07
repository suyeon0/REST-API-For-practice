package com.example.apitest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"pwd"})
@JsonInclude(Include.NON_NULL)
public class UserDTO {

    private int no;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{3,10}$",
        message = "숫자, 영문, 특수문자 각 1자리 이상이면서 3자에서 10자 사이")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String pwd;

    private String createdDate;

    private String modifiedDate;

}
