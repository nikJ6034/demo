package com.nik.auth.api.commoncode.repository;

import com.nik.auth.api.commoncode.entity.UpperCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UpperCodeRepository extends JpaRepository<UpperCode, Long>{
    
    /**
     * 동일한 코드값이 존재하는지 확인.
     * @param code
     * @return
     */
    public boolean existsByCode(String code);
}
