package com.nik.auth.api.commoncode.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.commoncode.dto.CommonCodeDTO;
import com.nik.auth.api.commoncode.dto.UpperCodeDTO;
import com.nik.auth.api.commoncode.service.CommonCodeService;
import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;
import com.nik.auth.security.dto.PrincipalDetails;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class CommonCodeController {
    
    private final CommonCodeService commonCodeService;

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_READ')")
    @GetMapping(value="/api/code/upperCode")
    public Page<UpperCodeDTO>  selectUpperCodeList(Pageable pageable) {
        
        return commonCodeService.selectUpperCodeList(pageable);

    }

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_READ')")
    @GetMapping(value="/api/code/upperCode/{id}")
    public UpperCodeDTO selectUpperCode(@PathVariable Long id) {
        
        return commonCodeService.selectUpperCode(id);

    }

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_WRITE')")
    @PostMapping(value="/api/code/upperCode")
    public ResultData<Long> insertUpperCode(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody UpperCodeDTO upperCodeDTO) {

        ResultData<Long> resultData = new ResultData<>();

        if(commonCodeService.existsByUpperCode(upperCodeDTO.getCode())){
            resultData.setResultType(ResultType.FAIL);
            resultData.setMsg("?????? ????????? ???????????????.");
        }else{
            upperCodeDTO.setRegId(principalDetails.getId());

            UpperCodeDTO resultUpperCode = commonCodeService.insertUpperCode(upperCodeDTO);

            resultData.setData(resultUpperCode.getId());
            resultData.setResultType(ResultType.SUCCESS);
            resultData.setMsg("?????????????????????.");
        }

        return resultData;
    }

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_MODIFY')")
    @PutMapping(value="/api/code/upperCode")
    public ResultData<Long> updateUpperCode(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody UpperCodeDTO upperCodeDTO) {

        ResultData<Long> resultData = new ResultData<>();

        upperCodeDTO.setMdfcnId(principalDetails.getId());

        UpperCodeDTO resultUpperCode = commonCodeService.updateUpperCode(upperCodeDTO);

        if(resultUpperCode != null){
            resultData.setData(resultUpperCode.getId());
            resultData.setResultType(ResultType.SUCCESS);
            resultData.setMsg("?????????????????????.");
        }else{
            resultData.setResultType(ResultType.FAIL);
            resultData.setMsg("???????????? ???????????? ????????????.");
        }

        return resultData;
    }

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_DELETE')")
    @DeleteMapping(value="/api/code/upperCode/{id}")
    public ResultData<Void> deleteUpperCode(@PathVariable Long id) {
        
        ResultData<Void> resultData = new ResultData<>();

        UpperCodeDTO selectUpperCode = commonCodeService.selectUpperCode(id);

        if(selectUpperCode.getCodeList() != null && !selectUpperCode.getCodeList().isEmpty()){
            resultData.setResultType(ResultType.FAIL);
            resultData.setMsg("??????????????? ???????????????.\n??????????????? ?????? ??????????????????.");
        }else{
            commonCodeService.deleteUpperCode(id);
    
            resultData.setResultType(ResultType.SUCCESS);
            resultData.setMsg("?????????????????????.");
        }
        return resultData;
    }

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_WRITE')")
    @PostMapping(value="/api/code/commonCode")
    public ResultData<Long> insertCommonCode(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody CommonCodeDTO commonCodeDTO) {

        ResultData<Long> resultData = new ResultData<>();
        if(commonCodeService.existsByCommonCode(commonCodeDTO.getUpperCodeId(), commonCodeDTO.getCode())){
            resultData.setResultType(ResultType.FAIL);
            resultData.setMsg("?????? ????????? ???????????????.");
        }else{
            commonCodeDTO.setRegId(principalDetails.getId());
            commonCodeDTO.setRegDt(LocalDateTime.now());
    
            CommonCodeDTO resultCommonCode =commonCodeService.insertCommonCode(commonCodeDTO);
    
            resultData.setData(resultCommonCode.getId());
            resultData.setResultType(ResultType.SUCCESS);
            resultData.setMsg("?????????????????????.");
        }


        return resultData;
    }

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_MODIFY')")
    @PutMapping(value="/api/code/commonCode")
    public ResultData<Long> updateCommonCode(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody CommonCodeDTO commonCodeDTO) {

        ResultData<Long> resultData = new ResultData<>();
        commonCodeDTO.setMdfcnId(principalDetails.getId());

        CommonCodeDTO resultCommonCode = commonCodeService.updateCommonCode(commonCodeDTO);

        if(resultCommonCode != null){
            resultData.setData(resultCommonCode.getId());
            resultData.setResultType(ResultType.SUCCESS);
            resultData.setMsg("?????????????????????.");
        }else{
            resultData.setResultType(ResultType.FAIL);
            resultData.setMsg("???????????? ???????????? ????????????.");
        }
        
        return resultData;
    }

    @PreAuthorize("@authorizationChecker.check(authentication,'CODE_DELETE')")
    @DeleteMapping(value="/api/code/commonCode/{id}")
    public ResultData<Void> deleteCommonCode(@PathVariable Long id) {
        
        ResultData<Void> resultData = new ResultData<>();

        commonCodeService.deleteCommonCode(id);
        resultData.setResultType(ResultType.SUCCESS);
        resultData.setMsg("?????????????????????.");
        return resultData;
    }
    
}