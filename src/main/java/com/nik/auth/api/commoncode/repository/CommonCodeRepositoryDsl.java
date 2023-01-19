package com.nik.auth.api.commoncode.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.auth.api.commoncode.dto.UpperCodeDTO;

public interface CommonCodeRepositoryDsl {

    Page<UpperCodeDTO> selectUpperCodeList(Pageable pageable);

    UpperCodeDTO selectUpperCode(Long id);

    boolean existsByCommonCode(Long upperCodeId, String commonCode);
    
}
