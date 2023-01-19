package com.nik.auth.api.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.member.entity.Member;
import com.nik.auth.api.member.vo.MemberSearchVO;

public interface MemberRepositoryDsl {

    public Member selectMemberByMemberId(String memberId);

    public Member selectMember(Long id);

    public Page<MemberDTO> selectMemberList(MemberSearchVO memberSearchVO, Pageable pageable);

}
