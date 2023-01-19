package com.nik.auth.api.bbs.notice.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    
    @Test
    void testInsertNotice() {

    }

    @Test
    void testSelectNotice() throws Exception {
        this.mockMvc.perform(get("/api/notice/2"))
        .andExpect(status().isOk())
        .andDo(
            document("selectNotice",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("id").description("아이디"),
                    fieldWithPath("title").description("제목"),
                    fieldWithPath("content").description("내용"),
                    fieldWithPath("beginDt").description("시작일"),
                    fieldWithPath("endDt").description("시작일"),
                    fieldWithPath("useYn").description("사용유무"),
                    fieldWithPath("delYn").description("삭제유무"),
                    fieldWithPath("regDt").description("등록일"),
                    fieldWithPath("regId").description("등록자아이디"),
                    fieldWithPath("regNm").description("등록자이름"),
                    fieldWithPath("mdfcnId").description("수정자아이디"),
                    fieldWithPath("mdfcnDt").description("수정일"),
                    fieldWithPath("attachFileGroupId").description("첨부파일그룹아이디"),
                    fieldWithPath("attachFileList[].id").description("첨부파일아이디"),
                    fieldWithPath("attachFileList[].orginName").description("첨부파일원본이름"),
                    fieldWithPath("attachFileList[].size").description("첨부파일크기"),
                    fieldWithPath("attachFileList[].ext").description("첨부파일확장자"),
                    fieldWithPath("attachFileList[].regDt").description("첨부파일등록일"),
                    fieldWithPath("attachFileList[].delYn").description("첨부파일삭제유무"),
                    fieldWithPath("attachFileList[].order").description("첨부파일순번"),
                    fieldWithPath("attachFileList[].attachFileGroupId").description("첨부파일그룹아이디"),
                    fieldWithPath("attachFileList[].uuid").description("첨부파일UUID")
                )
            )
        );
    }

    @Test
    void testSelectNoticeList() {

    }

    @Test
    void testUpdateNotice() {

    }
}
