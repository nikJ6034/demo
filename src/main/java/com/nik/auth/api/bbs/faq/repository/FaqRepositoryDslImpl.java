package com.nik.auth.api.bbs.faq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.auth.api.bbs.faq.dto.FaqDTO;
import com.nik.auth.api.bbs.faq.entity.Faq;
import com.nik.auth.api.bbs.faq.entity.QFaq;
import com.nik.auth.api.commoncode.entity.QCommonCode;
import com.nik.auth.api.commoncode.entity.QUpperCode;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;

public class FaqRepositoryDslImpl extends QuerydslRepositorySupport implements FaqRepositoryDsl{

    public FaqRepositoryDslImpl() {
        super(Faq.class);
    }

    @Override
    public List<FaqDTO> selectFaqList(String se) {
        QFaq qFaq = new QFaq("qFaq");
        QUpperCode qUpperCode = new QUpperCode("qUpperCode");
        QCommonCode qCommonCode = new QCommonCode("qCommonCode");

        return from(qFaq)
        .select(Projections.bean(FaqDTO.class
            , qFaq.id
            , qFaq.title
            , qFaq.content
            , qFaq.se
            , ExpressionUtils.as(
                JPAExpressions
                .select(qCommonCode.name)
                .innerJoin(qUpperCode.codeList, qCommonCode)
                .where(qUpperCode.code.eq("NOTICE"), qCommonCode.code.eq(qFaq.se))
                , "seNm")
            , qFaq.sort
            , qFaq.useYn
            , qFaq.regId.id.as("regId")
            , qFaq.regDt
            , qFaq.mdfcnId.id.as("mdfcnId")
            , qFaq.mdfcnDt
        ))
        .where(qFaq.se.eq(se), qFaq.delYn.eq(false))
        .fetch();
    }

    @Override
    public FaqDTO selectFaq(Long id) {
        QFaq qFaq = new QFaq("qFaq");
        QUpperCode qUpperCode = new QUpperCode("qUpperCode");
        QCommonCode qCommonCode = new QCommonCode("qCommonCode");

        return from(qFaq)
        .select(Projections.bean(FaqDTO.class
            , qFaq.id
            , qFaq.title
            , qFaq.content
            , qFaq.se
            , ExpressionUtils.as(
                JPAExpressions
                .select(qCommonCode.name)
                .innerJoin(qUpperCode.codeList, qCommonCode)
                .where(qUpperCode.code.eq("NOTICE"), qCommonCode.code.eq(qFaq.se))
                , "seNm")
            , qFaq.sort
            , qFaq.useYn
            , qFaq.regId.id.as("regId")
            , qFaq.regDt
            , qFaq.mdfcnId.id.as("mdfcnId")
            , qFaq.mdfcnDt
        ))
        .where(qFaq.id.eq(id), qFaq.delYn.eq(false))
        .fetchOne();
    }

    @Override
    public void modifyOrder(List<FaqDTO> faqList) {
        //아직
    }
    
}
