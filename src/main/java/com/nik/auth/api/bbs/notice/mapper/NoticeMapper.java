package com.nik.auth.api.bbs.notice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nik.auth.api.bbs.notice.dto.NoticeDTO;
import com.nik.auth.api.bbs.notice.entity.Notice;

@Mapper
public interface NoticeMapper {

    @Mapping(source = "regId.id", target = "regId")
    @Mapping(source = "mdfcnId.id", target = "mdfcnId")
    @Mapping(target = "attachFileList", ignore = true)
    @Mapping(target = "regNm", ignore = true)
    NoticeDTO toDto(Notice notice);
    
    @Mapping(source = "regId", target = "regId.id")
    @Mapping(source = "mdfcnId", target = "mdfcnId.id")
    Notice toEntity(NoticeDTO noticeDTO);
}
