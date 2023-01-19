package com.nik.auth.api.attachfile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.auth.api.attachfile.dto.AttachFileDTO;
import com.nik.auth.api.attachfile.entity.AttachFile;
import com.nik.auth.api.attachfile.entity.QAttachFile;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;

public class AttachFileRepositoryDslImpl extends QuerydslRepositorySupport implements AttachFileRepositoryDsl {

    public AttachFileRepositoryDslImpl() {
        super(AttachFile.class);
    }

    private Expression<AttachFileDTO> commonSelect(QAttachFile attachfile) {

        return Projections.bean(AttachFileDTO.class,
                attachfile.attachFileGroup.id.as("attachFileGroupId"),
                attachfile.id,
                attachfile.ext,
                attachfile.orginName,
                attachfile.order,
                attachfile.size,
                attachfile.regDt,
                attachfile.uuid
                );
    }

    @Override
    public List<AttachFileDTO> selectAttachFileList(long attachFileGroupId){

        QAttachFile attachfile = QAttachFile.attachFile;

        return from(attachfile)
        .where(attachfile.attachFileGroup.id.eq(attachFileGroupId))
        .orderBy(attachfile.order.asc())
        .select(commonSelect(attachfile))
        .fetch();
    }

    @Override
    public AttachFileDTO selectAttachFile(long attachFileId) {
        QAttachFile attachfile = QAttachFile.attachFile;

        return from(attachfile)
                .where(attachfile.id.eq(attachFileId))
                .select(commonSelect(attachfile))
                .fetchOne();
    }

}
