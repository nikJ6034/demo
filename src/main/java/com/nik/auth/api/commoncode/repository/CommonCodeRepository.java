package com.nik.auth.api.commoncode.repository;

import com.nik.auth.api.commoncode.entity.CommonCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long>, CommonCodeRepositoryDsl{

}
