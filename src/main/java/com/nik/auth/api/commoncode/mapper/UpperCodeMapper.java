package com.nik.auth.api.commoncode.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nik.auth.api.commoncode.dto.UpperCodeDTO;
import com.nik.auth.api.commoncode.entity.UpperCode;

@Mapper(uses = {CommonCodeMapper.class})
public interface UpperCodeMapper {
    
    @Mapping(source = "regId.id", target = "regId")
    @Mapping(source = "mdfcnId.id", target = "mdfcnId")
    UpperCodeDTO toDto(UpperCode upperCode);

    @Mapping(source = "regId", target = "regId.id")
    @Mapping(source = "mdfcnId", target = "mdfcnId.id")
    UpperCode toEntity(UpperCodeDTO upperCodeDTO);
}
