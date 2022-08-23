package com.lms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.model.Artical;

public interface ArticleRepository extends JpaRepository<Artical, Long>{

}
