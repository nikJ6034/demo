package com.nik.auth.api.rolegroup.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class RoleGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @DisplayName("역할그룹 목록조회")
    void testGetRoleGroups() throws Exception {
        
        this.mockMvc.perform(get("/api/roleGroups"))
        .andExpect(status().isOk())
        .andDo(
            document("roleGroups",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("[].id").description("아이디"),
                    fieldWithPath("[].groupName").description("그룹이름"),
                    fieldWithPath("[].groupDesc").description("그룹설명"),
                    fieldWithPath("[].roleList.[].id").description("역할아이디"),
                    fieldWithPath("[].roleList.[].roleValue").description("역할값"),
                    fieldWithPath("[].roleList.[].roleName").description("역할이름")
                )
            )
        );
    }

    @Test
    void testDeleteRoleGroup() {

    }


    @Test
    void testInsertRoleGroup() {

    }

    @Test
    void testModifyRoleGroup() {

    }

    @Test
    void testModifyRoleGroupRoleList() {

    }
}
