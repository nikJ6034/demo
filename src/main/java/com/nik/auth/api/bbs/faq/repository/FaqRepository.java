package com.nik.auth.api.bbs.faq.repository;

import com.nik.auth.api.bbs.faq.entity.Faq;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long>, FaqRepositoryDsl {
    
}
