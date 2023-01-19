package com.nik.auth.api.bbs.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.auth.api.bbs.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryDsl{

}
