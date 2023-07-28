package com.project.ZeeWebApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ZeeWebApplication.Entity.GenreEntity;

public interface GenreRepositoryInterface extends JpaRepository<GenreEntity, Integer >{

	GenreEntity findByGname(String genrename);

}
