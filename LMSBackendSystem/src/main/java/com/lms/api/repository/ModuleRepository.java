package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.model.Module;

public interface ModuleRepository extends JpaRepository<Module,Long>{

	List<Module> findByCourseId(Long cid);

}
