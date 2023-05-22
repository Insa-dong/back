package com.insadong.application.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.File;

public interface FileRepository extends JpaRepository<File, Long>{

}
