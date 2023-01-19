package com.nik.auth.api.bbs.notice.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nik.auth.api.attachfile.dto.AttachFileDTO;

import lombok.Data;

@Data
public class NoticeDTO {
    
    private Long id;

    @NotBlank(message = "제목은 필수 값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 값입니다.")
    private String content;

    @NotNull(message = "시작일은 필수 값입니다.")
    private LocalDateTime beginDt;

    @NotNull(message = "시작일은 필수 값입니다.")
    private LocalDateTime endDt;

    @NotNull
    private boolean useYn;

    private boolean delYn;

    private LocalDateTime regDt;

    private Long regId;

    private String regNm;

    private Long mdfcnId;

    private LocalDateTime mdfcnDt;

    private Long attachFileGroupId;

    private List<AttachFileDTO> attachFileList = new ArrayList<>();
}
