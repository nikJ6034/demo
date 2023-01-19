package com.nik.auth.api.attachfile.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttachFileDTO {
    
    private Long id;

    private String orginName;

    private long size;

    private String ext;

    private LocalDateTime regDt;

    private boolean delYn;

    private int order;

    private Long attachFileGroupId;

    private String uuid;

}
