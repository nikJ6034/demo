package com.nik.auth.api.commoncode.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.auth.api.commoncode.dto.UpperCodeDTO;
import com.nik.auth.api.commoncode.entity.CommonCode;
import com.nik.auth.api.commoncode.entity.QCommonCode;
import com.nik.auth.api.commoncode.entity.QUpperCode;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;

public class CommonCodeRepositoryDslImpl extends QuerydslRepositorySupport implements CommonCodeRepositoryDsl{

    public CommonCodeRepositoryDslImpl() {
        super(CommonCode.class);
    }

    @Override
    public Page<UpperCodeDTO> selectUpperCodeList(Pageable pageable) {

        QUpperCode qUpperCode = new QUpperCode("UpperCode");

        QueryResults<UpperCodeDTO> fetchResults = from(qUpperCode)
        .select(Projections.bean(UpperCodeDTO.class
            , qUpperCode.id
            , qUpperCode.code
            , qUpperCode.name
            , qUpperCode.desc
            , qUpperCode.regId.id.as("regId")
            , qUpperCode.regDt
            , qUpperCode.mdfcnId.id.as("mdfcnId")
            , qUpperCode.mdfcnDt
            ))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();

        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    @Override
    public UpperCodeDTO selectUpperCode(Long id) {
        QUpperCode qUpperCode = new QUpperCode("UpperCode");
        QCommonCode qCommonCode = new QCommonCode("CommonCode");

        return from(qUpperCode)
        .leftJoin(qUpperCode.codeList, qCommonCode)
        .where(qUpperCode.id.eq(id))
        .transform(groupBy(qUpperCode.id).as(
            Projections.bean(UpperCodeDTO.class, 
                qUpperCode.id
                , qUpperCode.code
                , qUpperCode.name
                , qUpperCode.desc
                , qUpperCode.regId.id.as("regId")
                , qUpperCode.regDt
                , qUpperCode.mdfcnId.id.as("mdfcnId")
                , qUpperCode.mdfcnDt
                , list(Projections.bean(UpperCodeDTO.class, 
                        qCommonCode.id
                        , qCommonCode.code
                        , qCommonCode.name
                        , qCommonCode.desc
                        , qCommonCode.upperCode.id.as("upperCodeId")
                        , qCommonCode.regId.id.as("regId")
                        , qCommonCode.regDt
                        , qCommonCode.mdfcnId.id.as("mdfcnId")
                        , qCommonCode.mdfcnDt
                    ).skipNulls()
                ).as("codeList")
            )
        ))
        .get(id);
    }

    @Override
    public boolean existsByCommonCode(Long upperCodeId, String commonCode) {
        QUpperCode qUpperCode = new QUpperCode("UpperCode");
        QCommonCode qCommonCode = new QCommonCode("CommonCode");

        return from(qUpperCode)
        .innerJoin(qUpperCode.codeList, qCommonCode)
        .where(qUpperCode.id.eq(upperCodeId).and(qCommonCode.code.eq(commonCode)))
        .fetchCount() > 0;
    }
    
}
