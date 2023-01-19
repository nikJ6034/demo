package com.nik.auth.api.member.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.member.entity.Member;
import com.nik.auth.api.member.entity.QMember;
import com.nik.auth.api.member.vo.MemberSearchVO;
import com.nik.auth.api.role.entity.QRole;
import com.nik.auth.api.rolegroup.entity.QRoleGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryDslImpl extends QuerydslRepositorySupport implements MemberRepositoryDsl{

    public MemberRepositoryDslImpl() {
        super(Member.class);
    }

    public Member selectMember(Long id) {
        log.info("MemberRepositoryDslImpl.selectMember");

        QMember qMember = QMember.member;
        QRole role = QRole.role;
        QRoleGroup roleGroup = QRoleGroup.roleGroup;

        return from(qMember)
        .leftJoin(qMember.roleGroup, roleGroup).fetchJoin()
        .leftJoin(roleGroup.roleList, role).fetchJoin()
        .leftJoin(role.privilegeList).fetchJoin()
        .where(
            qMember.id.eq(id)
            .and(qMember.delYn.eq(false))
        )
        .fetchOne();
    }

    public Member selectMemberByMemberId(String memberId) {
        log.info("MemberRepositoryDslImpl.selectMemberByMemberId");
        QMember qMember = QMember.member;
        QRole role = QRole.role;
        QRoleGroup roleGroup = QRoleGroup.roleGroup;

        return from(qMember)
        .leftJoin(qMember.roleGroup, roleGroup).fetchJoin()
        .leftJoin(roleGroup.roleList, role).fetchJoin()
        .leftJoin(role.privilegeList).fetchJoin()
        .where(
            qMember.memberId.eq(memberId)
            .and(qMember.delYn.eq(false))
        )
        .fetchOne();
    }

    public Page<MemberDTO> selectMemberList(MemberSearchVO memberSearchVO, Pageable pageable){

        log.info("MemberRepositoryDslImpl.selectMemberList");
        QMember qMember = QMember.member;

        //동적 쿼리 실행.
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        //검색 키워드가 있을 때
        if(StringUtils.isNotBlank(memberSearchVO.getSearchKeyword())) {
            booleanBuilder.and(qMember.name.contains(memberSearchVO.getSearchKeyword()));
        }

        //권한 아이디가 있을 때
        // if(memberSearchVO.getRoleId() != null) {
        //     BooleanExpression exists = JPAExpressions.selectFrom(qMemberRole)
        //     .where(
        //             qMemberRole.role.id.eq(memberSearchVO.getRoleId())
        //             .and(qMemberRole.member.eq(qMember))
        //             )
        //     .exists();
        //     booleanBuilder.and(exists);
        // }
        //동적 쿼리 실행.

        QueryResults<MemberDTO> fetchResults = from(qMember)
                .select(
                    Projections.bean(MemberDTO.class
                            , qMember.id
                            , qMember.memberId
                            , qMember.email
                            , qMember.name
                            )
                )
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
