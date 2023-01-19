package com.nik.auth.api.bbs.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nik.auth.api.bbs.notice.dto.NoticeDTO;
import com.nik.auth.api.bbs.notice.dto.NoticeSearchDTO;
import com.nik.auth.api.bbs.notice.service.NoticeService;
import com.nik.auth.api.privilege.code.PrivNotice;
import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;
import com.nik.auth.security.dto.PrincipalDetails;
import com.nik.auth.utils.fileutils.exceptions.DeniedFileException;

import lombok.RequiredArgsConstructor;


//@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping(value = "/api/notice")
    public Page<NoticeDTO> selectNoticeList(@AuthenticationPrincipal PrincipalDetails principalDetails, NoticeSearchDTO noticeSearchDTO, Pageable pageable){
        return noticeService.selectNoticeList(noticeSearchDTO, pageable);

    }

    @GetMapping(value = "/api/notice/{id}")
    public NoticeDTO selectNotice(NoticeDTO noticeDTO){

        return noticeService.selectNotice(noticeDTO);

    }

    //@PreAuthorize("@authorizationChecker.check(authentication,"+PrivNotice.Privilege.WRITE+")")
    @PreAuthorize("hasAuthority("+PrivNotice.P_WRITE+")")
    @PostMapping(value = "/api/notice")
    public ResultData<Long> insertNotice(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestPart(name = "notice") NoticeDTO noticeDTO, @RequestPart(name="attachFileList", required = false) List<MultipartFile> attachFileList) throws IOException, DeniedFileException{

        ResultData<Long> resultData = new ResultData<>();

        if(principalDetails != null && principalDetails.getId() != null) {

            noticeDTO.setRegId(principalDetails.getId());

            Long noticeId = noticeService.insertNotice(noticeDTO, attachFileList);

            resultData.setResultType(ResultType.SUCCESS);
            resultData.setData(noticeId);
            resultData.setMsg("등록되었습니다.");

            return resultData;
        }else {

            resultData.setResultType(ResultType.FAIL);
            resultData.setData(null);
            resultData.setMsg("로그인이 필요한 서비스입니다.");

            return resultData;
        }

    }

    //@PreAuthorize("@authorizationChecker.check(authentication,'NOTICE_MODIFY')")
    @PreAuthorize("hasAuthority("+PrivNotice.P_MODIFY+")")
    @PutMapping(value = "/api/notice")
    public ResultData<Long> updateNotice(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestPart(name = "notice") NoticeDTO noticeDTO, @RequestPart(name="attachFileList", required = false) List<MultipartFile> attachFileList) throws IOException, DeniedFileException{

        ResultData<Long> resultData = new ResultData<>();

        if(principalDetails != null && principalDetails.getId() != null) {

            noticeDTO.setMdfcnId(principalDetails.getId());

            Long noticeId = noticeService.updateNotice(noticeDTO, attachFileList);

            resultData.setResultType(ResultType.SUCCESS);
            resultData.setData(noticeId);
            resultData.setMsg("수정되었습니다.");

            return resultData;
        }else {

            resultData.setResultType(ResultType.FAIL);
            resultData.setData(null);
            resultData.setMsg("로그인이 필요한 서비스입니다.");

            return resultData;
        }

    }

    @GetMapping(value="/api/notice/test/{id}")
    @ResponseBody
    public String getMethodName(NoticeDTO noticeDTO) {

        Long startTime = System.currentTimeMillis();

        for(int i = 0; i < 500; i++){
            noticeService.selectNotice(noticeDTO);
        }

        System.out.println(System.currentTimeMillis() - startTime);
        return "결과";
    }

    @GetMapping(value="/api/notice/test2/{id}")
    @ResponseBody
    public String getMethodName2(NoticeDTO noticeDTO) {

        Long startTime = System.currentTimeMillis();

        noticeService.selectTest(noticeDTO);

        System.out.println(System.currentTimeMillis() - startTime);
        return "결과";
    }
    

}
