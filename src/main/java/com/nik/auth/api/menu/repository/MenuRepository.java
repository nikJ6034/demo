package com.nik.auth.api.menu.repository;

import com.nik.auth.api.menu.entity.Menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryDsl{

}
