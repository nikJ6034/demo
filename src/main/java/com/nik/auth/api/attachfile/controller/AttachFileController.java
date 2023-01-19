package com.nik.auth.api.attachfile.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.attachfile.entity.AttachFile;
import com.nik.auth.api.attachfile.service.AttachFileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AttachFileController {

    private final AttachFileService attachFileService;

    @GetMapping(value = "/api/file/download")
    public ResponseEntity<Resource> download(String uuid) throws IOException {
        AttachFile selectAttachFileByUuid = attachFileService.selectAttachFileByUuid(uuid);

        if(selectAttachFileByUuid == null){
            return ResponseEntity.notFound().build();
        }

        String fullPath = selectAttachFileByUuid.getFullPath();
        String orginName = selectAttachFileByUuid.getOrginName();

        Path path = Paths.get(fullPath);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .contentLength(resource.contentLength())
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\""+URLEncoder.encode(orginName, "UTF-8")+"\";")
        .body(resource);

    }

}
