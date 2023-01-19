package com.nik.auth.api.member.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.member.entity.Member;
import com.nik.auth.api.member.mapper.MemberMapper;
import com.nik.auth.api.member.repository.MemberRepository;
import com.nik.auth.api.member.vo.MemberSearchVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Member createMember(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        return memberRepository.save(member);

    }

    public MemberDTO selectMember(Long id) {
        return memberMapper.toDto(memberRepository.selectMember(id));
    }

    public MemberDTO selectMemberByMemberId(String memberId) {
        Member member = memberRepository.selectMemberByMemberId(memberId);
        return memberMapper.toDto(member);
    }

    public Page<MemberDTO> selectMemberList(MemberSearchVO memberSearchVO, Pageable pageable){
        return memberRepository.selectMemberList(memberSearchVO, pageable);
    }

    public void modifyMemberRoleGroup(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        memberRepository.save(member);
    }
}
