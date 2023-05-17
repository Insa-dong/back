package com.insadong.application.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

	Page<Notice> findByNoticeTitleContains(Pageable pageable, String searchKeyword);

	Page<Notice> findByNoticeContentContains(Pageable pageable, String searchKeyword);

	Page<Notice> findByNoticeWriter(Pageable pageable, Employee findEmpId);

}
