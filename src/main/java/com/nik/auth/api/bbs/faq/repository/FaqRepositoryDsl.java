package com.nik.auth.api.bbs.faq.repository;

import java.util.List;

import com.nik.auth.api.bbs.faq.dto.FaqDTO;

public interface FaqRepositoryDsl {
    
    public List<FaqDTO> selectFaqList(String se);

    public FaqDTO selectFaq(Long id);

    public void modifyOrder(List<FaqDTO> faqList);
}
