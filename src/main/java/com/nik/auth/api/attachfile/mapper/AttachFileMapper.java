package com.nik.auth.api.attachfile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nik.auth.api.attachfile.dto.AttachFileDTO;
import com.nik.auth.api.attachfile.entity.AttachFile;

@Mapper
public interface AttachFileMapper {

    @Mapping(source = "attachFileGroup.id", target = "attachFileGroupId")
    @Mapping(target = "delYn", ignore = true)
    AttachFileDTO toDto(AttachFile attachFile);

    @Mapping(source = "attachFileGroupId", target = "attachFileGroup.id")
    @Mapping(target = "fullPath", ignore = true)
    @Mapping(target = "tempName", ignore = true)
    AttachFile toEntity(AttachFileDTO attachFileDTO);
}
