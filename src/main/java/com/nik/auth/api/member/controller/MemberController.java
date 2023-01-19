package com.nik.auth.api.member.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.member.entity.Member;
import com.nik.auth.api.member.service.MemberService;
import com.nik.auth.api.member.vo.MemberSearchVO;
import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;
import com.nik.auth.security.dto.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/api/userInfo")
    public ResultData<PrincipalDetails> userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        ResultData<PrincipalDetails> resultData = new ResultData<>();

        resultData.setResultType(ResultType.SUCCESS);
        resultData.setData(principalDetails);
        resultData.setMsg("회원가입완료");

        return resultData;

    }

    @PostMapping(value = "/api/members")
    public ResultData<Member> createMember(@Valid MemberDTO memberDTO) {

        ResultData<Member> resultData = new ResultData<>();

        resultData.setResultType(ResultType.SUCCESS);
        resultData.setData(memberService.createMember(memberDTO));
        resultData.setMsg("회원가입완료");

        return resultData;

    }

    @GetMapping(value = "/api/members")
    public Page<MemberDTO> memberList(MemberSearchVO memberSearchVO, Pageable pageable) {

        return memberService.selectMemberList(memberSearchVO, pageable);

    }

    @GetMapping(value = "/api/members/{id}")
    public MemberDTO memberDetail(MemberDTO memberDTO) {

        return memberService.selectMember(memberDTO.getId());

    }

    @PostMapping(value = "/api/role/members")
    public ResultData<Void> modifyRoleMember(@RequestBody MemberDTO memberDTO) {

        ResultData<Void> resultData = new ResultData<>();

        memberService.modifyMemberRoleGroup(memberDTO);
        resultData.setMsg("수정되었습니다.");
        resultData.setResultType(ResultType.SUCCESS);

        return resultData;

    }

}
