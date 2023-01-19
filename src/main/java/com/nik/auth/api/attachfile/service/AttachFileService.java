package com.nik.auth.api.attachfile.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nik.auth.api.attachfile.dto.AttachFileDTO;
import com.nik.auth.api.attachfile.entity.AttachFile;
import com.nik.auth.api.attachfile.entity.AttachFileGroup;
import com.nik.auth.api.attachfile.repository.AttachFileGroupRepository;
import com.nik.auth.api.attachfile.repository.AttachFileRepository;
import com.nik.auth.utils.fileutils.AttachFileUtil;
import com.nik.auth.utils.fileutils.exceptions.DeniedFileException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttachFileService {

	private final AttachFileGroupRepository attachFileGroupRepository;
	private final AttachFileRepository attachFileRepository;
	private final AttachFileUtil attachFileUtil;

	public AttachFile selectAttachFileByUuid(String uuid) {
		return attachFileRepository.findByUuid(uuid);
	}

	public List<AttachFileDTO> selectAttachFileList(long attachFileGroupId){

		return attachFileRepository.selectAttachFileList(attachFileGroupId);

	}

	public AttachFileDTO selectAttachFile(long attachFileId) {
		attachFileRepository.findById(attachFileId);

		return null;
	}

	public Long modifyAttachFile(Long attachFileGroupId, List<AttachFileDTO> attachFileList, List<MultipartFile> fileList, String path) throws IOException, DeniedFileException {

		if(attachFileList != null && !attachFileList.isEmpty()) {
			if(attachFileGroupId == null) {

				AttachFileGroup attachFileGroup = attachFileGroupRepository.save(new AttachFileGroup());
				attachFileGroupId = attachFileGroup.getId();

			}

			Iterator<MultipartFile> iterator = null;
			if(fileList != null) {
				iterator = fileList.iterator();
			}

			int orderCount = 0;
			for(AttachFileDTO attachFileDTO : attachFileList) {
				AttachFile attachFile = null;

				if(attachFileDTO.getId() == null) {

					attachFileDTO.setOrder(orderCount);
					attachFile = attachFileUtil.save(attachFileGroupId, attachFileDTO, iterator.next(), path);
					attachFileRepository.save(attachFile);
					orderCount++;
				}else {

					attachFile = attachFileRepository.getById(attachFileDTO.getId());
					if(attachFileDTO.isDelYn()) {
						File deleteFile = new File(attachFile.getFullPath());
						if(deleteFile.exists()) {
							FileUtils.delete(deleteFile);
						}
						attachFileRepository.delete(attachFile);

					}else {
						attachFile.setOrder(orderCount);
						orderCount++;
					}
				}
			}

		}

		return attachFileGroupId;

	}

}
