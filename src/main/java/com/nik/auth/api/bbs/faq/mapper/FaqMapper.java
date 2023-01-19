package com.nik.auth.api.bbs.faq.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nik.auth.api.bbs.faq.dto.FaqDTO;
import com.nik.auth.api.bbs.faq.entity.Faq;

@Mapper
public interface FaqMapper {

    @Mapping(source = "regId.id", target = "regId")
    @Mapping(source = "mdfcnId.id", target = "mdfcnId")
    @Mapping(target = "seNm", ignore = true)
    FaqDTO toDto(Faq faq);

    @Mapping(source = "regId", target = "regId.id")
    @Mapping(source = "mdfcnId", target = "mdfcnId.id")
    Faq toEntity(FaqDTO faqDTO);
}
