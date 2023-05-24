package com.insadong.application.notice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.File;

public interface FileRepository extends JpaRepository<File, Long>{

	List<File> findByNoticeCode(Long noticeCode);

}
