package com.nik.auth.api.commoncode.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nik.auth.api.commoncode.dto.CommonCodeDTO;
import com.nik.auth.api.commoncode.entity.CommonCode;

@Mapper
public interface CommonCodeMapper {

    @Mapping(source = "upperCode.id", target = "upperCodeId")
    @Mapping(source = "regId.id", target = "regId")
    @Mapping(source = "mdfcnId.id", target = "mdfcnId")
    CommonCodeDTO toDto(CommonCode commonCode);

    @Mapping(source = "upperCodeId", target = "upperCode.id")
    @Mapping(source = "regId", target = "regId.id")
    @Mapping(source = "mdfcnId", target = "mdfcnId.id")
    CommonCode toEntity(CommonCodeDTO commonCodeDTO);
}
