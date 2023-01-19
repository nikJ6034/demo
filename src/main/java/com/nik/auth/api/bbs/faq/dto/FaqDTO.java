package com.nik.auth.api.bbs.faq.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FaqDTO {
    
    //아이디
    private Long id;
    
    //제목
    private String title;

    //내용
    private String content;
    
    //구분
    private String se;

    //구분 코드 이름
    private String seNm;

    //순번
    private int sort;

    //사용유무
    private boolean useYn;

    //등록자아이디
    private Long regId;

    //등록일
    private LocalDateTime regDt;

    //수정자아이디
    private Long mdfcnId;

    //수정일
    private LocalDateTime mdfcnDt;

    //삭제유무
    private boolean delYn;
}
