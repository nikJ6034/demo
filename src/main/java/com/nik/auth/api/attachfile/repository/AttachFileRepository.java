package com.nik.auth.api.attachfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.auth.api.attachfile.entity.AttachFile;

public interface AttachFileRepository extends JpaRepository<AttachFile, Long>, AttachFileRepositoryDsl{

    public AttachFile findByUuid(String uuid);
}
