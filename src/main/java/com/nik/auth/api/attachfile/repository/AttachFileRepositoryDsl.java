package com.nik.auth.api.attachfile.repository;

import java.util.List;

import com.nik.auth.api.attachfile.dto.AttachFileDTO;

public interface AttachFileRepositoryDsl {

    public List<AttachFileDTO> selectAttachFileList(long attachFileGroupId);

    public AttachFileDTO selectAttachFile(long attachFileId);


}
