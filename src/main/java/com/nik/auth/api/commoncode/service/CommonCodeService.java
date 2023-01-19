package com.nik.auth.api.commoncode.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.auth.api.commoncode.dto.CommonCodeDTO;
import com.nik.auth.api.commoncode.dto.UpperCodeDTO;
import com.nik.auth.api.commoncode.entity.CommonCode;
import com.nik.auth.api.commoncode.entity.UpperCode;
import com.nik.auth.api.commoncode.mapper.CommonCodeMapper;
import com.nik.auth.api.commoncode.mapper.UpperCodeMapper;
import com.nik.auth.api.commoncode.repository.CommonCodeRepository;
import com.nik.auth.api.commoncode.repository.UpperCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommonCodeService {

    private final UpperCodeRepository upperCodeRepository;
    private final CommonCodeRepository commonCodeRepository;
    private final UpperCodeMapper upperCodeMapper;
    private final CommonCodeMapper commonCodeMapper;

    /**
     * 부모코드 목록을 조회합니다.
     * @param pageable
     * @return
     */
    public Page<UpperCodeDTO> selectUpperCodeList(Pageable pageable) {
        return commonCodeRepository.selectUpperCodeList(pageable);
    }

    /**
     * 부모코드 상세정보를 조회합니다.
     * @param id
     * @return
     */
    public UpperCodeDTO selectUpperCode(Long id) {
        return commonCodeRepository.selectUpperCode(id);
    }

    /**
     * 부모코드를 등록합니다.
     * @param upperCode
     * @return
     */
    public UpperCodeDTO insertUpperCode(UpperCodeDTO upperCodeDTO) {
        UpperCode upperCode = upperCodeMapper.toEntity(upperCodeDTO);
        upperCode.setRegDt(LocalDateTime.now());
        UpperCode save = upperCodeRepository.save(upperCode);
        return upperCodeMapper.toDto(save);
    }

    /**
     * 부모코드를 수정합니다.
     * @param upperCode
     * @return
     */
    public UpperCodeDTO updateUpperCode(UpperCodeDTO upperCodeDTO) {
        UpperCode upperCode = upperCodeMapper.toEntity(upperCodeDTO);

        Optional<UpperCode> findById = upperCodeRepository.findById(upperCodeDTO.getId());

        if(findById.isPresent()){
            UpperCode entity = findById.get();
            entity.setDesc(upperCode.getDesc());
            entity.setName(upperCode.getName());
            entity.setMdfcnId(upperCode.getMdfcnId());
            entity.setMdfcnDt(LocalDateTime.now());
            return upperCodeMapper.toDto(entity);
        }else{
            return null;
        }

    }

    /**
     * 공통코드를 등록합니다.
     * @param commonCode
     * @return
     */
    public CommonCodeDTO insertCommonCode(CommonCodeDTO commonCodeDTO) {

        CommonCode commonCode = commonCodeMapper.toEntity(commonCodeDTO);
        commonCode.setRegDt(LocalDateTime.now());

        commonCodeRepository.save(commonCode);
        return commonCodeMapper.toDto(commonCode);
    }

    /**
     * 공통코드를 수정합니다.
     * @param commonCode
     * @return
     */
    public CommonCodeDTO updateCommonCode(CommonCodeDTO commonCodeDTO) {
        CommonCode commonCode = commonCodeMapper.toEntity(commonCodeDTO);

        Optional<CommonCode> findById = commonCodeRepository.findById(commonCode.getId());

        if(findById.isPresent()){
            CommonCode entity = findById.get();
            entity.setDesc(commonCode.getDesc());
            entity.setName(commonCode.getName());
            entity.setMdfcnId(commonCode.getMdfcnId());
            entity.setMdfcnDt(LocalDateTime.now());
            return commonCodeMapper.toDto(entity);
        }else{
            return null;
        }

    }

    public boolean existsByUpperCode(String code){
        return upperCodeRepository.existsByCode(code);
    }

    public void deleteUpperCode(Long id) {
        upperCodeRepository.deleteById(id);
    }

    public boolean existsByCommonCode(Long upperCodeId, String commonCode){
        return commonCodeRepository.existsByCommonCode(upperCodeId, commonCode);
    }

    public void deleteCommonCode(Long id) {
        commonCodeRepository.deleteById(id);
    }
    
}
