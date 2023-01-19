package com.nik.auth.api.bbs.notice.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nik.auth.api.attachfile.dto.AttachFileDTO;
import com.nik.auth.api.attachfile.service.AttachFileService;
import com.nik.auth.api.bbs.notice.dto.NoticeDTO;
import com.nik.auth.api.bbs.notice.dto.NoticeSearchDTO;
import com.nik.auth.api.bbs.notice.entity.Notice;
import com.nik.auth.api.bbs.notice.mapper.NoticeMapper;
import com.nik.auth.api.bbs.notice.repository.NoticeRepository;
import com.nik.auth.utils.fileutils.exceptions.DeniedFileException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
// @Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final AttachFileService attachFileService;
    private final NoticeMapper noticeMapper;

    public Page<NoticeDTO> selectNoticeList(NoticeSearchDTO noticeSearchDTO, Pageable pageable){
        return noticeRepository.selectNoticeList(noticeSearchDTO, pageable);
    }

    public NoticeDTO selectNotice(NoticeDTO noticeDTO) {
        NoticeDTO selectNotice = noticeRepository.selectNotice(noticeDTO);

        if(selectNotice.getAttachFileGroupId() != null) {
            List<AttachFileDTO> selectAttachFileList = attachFileService.selectAttachFileList(selectNotice.getAttachFileGroupId());
            selectNotice.setAttachFileList(selectAttachFileList);

        }

        return selectNotice;
    }

    public Long insertNotice(NoticeDTO noticeDTO, List<MultipartFile> fileList) throws IOException, DeniedFileException {
        Notice notice = noticeMapper.toEntity(noticeDTO);

        Long insertAttachFile = attachFileService.modifyAttachFile(notice.getAttachFileGroupId(), noticeDTO.getAttachFileList(), fileList, "/notice");
        if(insertAttachFile != null) {
            notice.setAttachFileGroupId(insertAttachFile);
        }

        notice.setRegDt(LocalDateTime.now());

        return noticeRepository.save(notice).getId();

    }

    public Long updateNotice(NoticeDTO noticeDTO, List<MultipartFile> fileList) throws IOException, DeniedFileException {

        Notice notice = noticeMapper.toEntity(noticeDTO);

        Long insertAttachFile = attachFileService.modifyAttachFile(notice.getAttachFileGroupId(), noticeDTO.getAttachFileList(), fileList, "/notice");
        if(insertAttachFile != null) {
            notice.setAttachFileGroupId(insertAttachFile);
        }

        notice.setMdfcnDt(LocalDateTime.now());

        return noticeRepository.save(notice).getId();

    }

    public void deleteNotice(Notice notice) {
        Optional<Notice> selectNotice = noticeRepository.findById(notice.getId());

        selectNotice.ifPresent(b -> b.setDelYn(true));
    }

    public void selectTest(NoticeDTO noticeDTO){

        for(int i = 0; i < 500; i++){
            NoticeDTO selectNotice = noticeRepository.selectNotice(noticeDTO);
    
            if(selectNotice.getAttachFileGroupId() != null) {
                List<AttachFileDTO> selectAttachFileList = attachFileService.selectAttachFileList(selectNotice.getAttachFileGroupId());
                selectNotice.setAttachFileList(selectAttachFileList);
    
            }

        }
    }
}
