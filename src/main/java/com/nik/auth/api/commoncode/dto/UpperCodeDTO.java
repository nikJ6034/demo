package com.nik.auth.api.commoncode.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpperCodeDTO {
    //아이디
    private Long id;

    //코드
    @NotBlank(message = "코드는 필수값입니다.")
    private String code;
    
    //코드명
    @NotBlank(message = "코드명은 필수값입니다.")
    private String name;

    //코드 설명
    private String desc;

    //자식코드
    private List<CommonCodeDTO> codeList;

    //등록자아이디
    private Long regId;

    //등록일
    private LocalDateTime regDt;

    //수정자아이디
    private Long mdfcnId;

    //수정일
    private LocalDateTime mdfcnDt;
}
