package com.nik.auth.api.attachfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.auth.api.attachfile.entity.AttachFileGroup;

public interface AttachFileGroupRepository extends JpaRepository<AttachFileGroup, Long> {

}
