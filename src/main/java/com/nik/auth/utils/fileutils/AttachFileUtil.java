package com.nik.auth.utils.fileutils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.nik.auth.api.attachfile.dto.AttachFileDTO;
import com.nik.auth.api.attachfile.entity.AttachFile;
import com.nik.auth.api.attachfile.entity.AttachFileGroup;
import com.nik.auth.utils.fileutils.exceptions.DeniedFileException;

@Component
public class AttachFileUtil {

    @Value("${globals.fileStorePath}")
    private String basePath;

    public AttachFile overWriteSave(AttachFile attachFile, final MultipartFile multipartFile, String path) throws DeniedFileException, IOException {

        if(multipartFile.isEmpty()) return null;

        UUID uuid = UUID.randomUUID();
        String originalFilename = multipartFile.getOriginalFilename();
        String tempFileName = uuid.toString();
        String ext = FilenameUtils.getExtension(originalFilename);
        String fullPath = basePath+path+File.separator+attachFile.getAttachFileGroup().getId()+File.separator+tempFileName;
        String beforeFilePath = attachFile.getFullPath();

        for(DeniedFile dFile : DeniedFile.values()) {
            if(dFile.toString().equalsIgnoreCase(ext)) {
                throw new DeniedFileException("첨부가 제한된 파일입니다.");
            }
        }

        File file = new File(fullPath);

        FileUtils.touch(file);

        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());

        attachFile.setExt(ext);
        attachFile.setRegDt(LocalDateTime.now());
        attachFile.setFullPath(fullPath);
        attachFile.setOrginName(originalFilename);
        attachFile.setSize(FileUtils.sizeOf(file));
        attachFile.setTempName(tempFileName);

        //모든게 완료되면 이전 파일은 삭제한다.
        File beforFile = new File(beforeFilePath);
        if(beforFile.isFile()) {
            FileUtils.delete(beforFile);
        }


        return attachFile;
    }

    public AttachFile save(final Long attachFileGroupId, final AttachFileDTO attachFileDTO, final MultipartFile multipartFile, final String path) throws IOException, DeniedFileException {

        if(multipartFile.isEmpty()) return null;

        UUID uuid = UUID.randomUUID();
        String originalFilename = multipartFile.getOriginalFilename();
        String tempFileName = uuid.toString();
        String ext = FilenameUtils.getExtension(originalFilename);
        String fullPath = basePath+path+File.separator+attachFileGroupId+File.separator+tempFileName;

        for(DeniedFile dFile : DeniedFile.values()) {
            if(dFile.toString().equalsIgnoreCase(ext)) {
                throw new DeniedFileException("첨부가 제한된 파일입니다.");
            }
        }

        File file = new File(fullPath);

        FileUtils.touch(file);

        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());

        AttachFileGroup attachFileGroup = new AttachFileGroup();
        attachFileGroup.setId(attachFileGroupId);

        AttachFile attachFile = new AttachFile();

        attachFile.setId(attachFileDTO.getId());
        attachFile.setExt(ext);
        attachFile.setRegDt(LocalDateTime.now());
        attachFile.setAttachFileGroup(attachFileGroup);
        attachFile.setFullPath(fullPath);
        attachFile.setOrginName(originalFilename);
        attachFile.setOrder(attachFileDTO.getOrder());
        attachFile.setSize(FileUtils.sizeOf(file));
        attachFile.setTempName(tempFileName);
        attachFile.setUuid(tempFileName);

        return attachFile;

    }

}
