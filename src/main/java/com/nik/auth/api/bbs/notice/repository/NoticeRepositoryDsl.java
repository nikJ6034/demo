package com.nik.auth.api.bbs.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nik.auth.api.bbs.notice.dto.NoticeDTO;
import com.nik.auth.api.bbs.notice.dto.NoticeSearchDTO;

public interface NoticeRepositoryDsl {

    public Page<NoticeDTO> selectNoticeList(NoticeSearchDTO noticeSearchDTO, Pageable pageable);

    public NoticeDTO selectNotice(NoticeDTO noticeDTO);
}
