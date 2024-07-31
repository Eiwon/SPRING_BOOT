package com.mokcoding.mysite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mokcoding.mysite.domain.Attach;
import com.mokcoding.mysite.domain.AttachDTO;
import com.mokcoding.mysite.repository.AttachRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AttachServiceImple implements AttachService {

	@Autowired
    private AttachRepository attachRepository;


    @Override
    public AttachDTO getAttachById(int attachId) {
    	log.info("getAttachById()");
    	Optional<Attach> attach = attachRepository.findById(attachId);
        return toDTO(attach.get());
    }
    
    // Attach를 AttachDTO로 변환하는 메서드
    private AttachDTO toDTO(Attach attach) {
        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setAttachId(attach.getAttachId());
        attachDTO.setBoardId(attach.getBoardId());
        attachDTO.setAttachPath(attach.getAttachPath());
        attachDTO.setAttachRealName(attach.getAttachRealName());
        attachDTO.setAttachChgName(attach.getAttachChgName());
        attachDTO.setAttachExtension(attach.getAttachExtension());
        attachDTO.setAttachDateCreated(attach.getAttachDateCreated());

        return attachDTO;
    }
}
