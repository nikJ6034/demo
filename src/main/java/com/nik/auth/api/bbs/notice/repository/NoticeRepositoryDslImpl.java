package com.nik.auth.api.bbs.notice.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.auth.api.bbs.notice.dto.NoticeDTO;
import com.nik.auth.api.bbs.notice.dto.NoticeSearchDTO;
import com.nik.auth.api.bbs.notice.entity.Notice;
import com.nik.auth.api.bbs.notice.entity.QNotice;
import com.nik.auth.api.member.entity.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;

//@Slf4j
public class NoticeRepositoryDslImpl extends QuerydslRepositorySupport implements NoticeRepositoryDsl{

    public NoticeRepositoryDslImpl() {
        super(Notice.class);
    }

    @Override
    public Page<NoticeDTO> selectNoticeList(NoticeSearchDTO noticeSearchDTO, Pageable pageable){

        QNotice notice = QNotice.notice;
        QMember member = QMember.member;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(StringUtils.isNotBlank(noticeSearchDTO.getSearchKeyword())) {
            booleanBuilder
                .and(notice.content.contains(noticeSearchDTO.getSearchKeyword())
                    .or(notice.title.contains(noticeSearchDTO.getSearchKeyword()))
                );
        }

        QueryResults<NoticeDTO> fetchResults = from(notice)
                .join(notice.regId, member)
                .select(
                        Projections.bean(NoticeDTO.class
                                , notice.id
                                , notice.title
                                , notice.content
                                , notice.regDt
                                , notice.regId.id.as("regId")
                                , notice.beginDt
                                , notice.endDt
                                , notice.useYn)
                    )
                .where(
                        notice.delYn.eq(false).and(booleanBuilder)
                )
                .orderBy(notice.regDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());

    }

    @Override
    public NoticeDTO selectNotice(NoticeDTO noticeDTO) {

        QNotice notice = QNotice.notice;
        QMember member = QMember.member;

        return from(notice)
        .join(notice.regId, member)
        .select(Projections.bean(NoticeDTO.class
                , notice.id
                , notice.title
                , notice.content
                , notice.regDt
                , notice.regId.id.as("regId")
                , notice.regId.name.as("regNm")
                , notice.beginDt
                , notice.endDt
                , notice.useYn
                , notice.attachFileGroupId
                )
        )
        .where(
                notice.id.eq(noticeDTO.getId())
                .and(notice.delYn.eq(false))
        )
        .fetchOne();

    }

}
