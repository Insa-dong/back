package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Rest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestRepository  extends JpaRepository<Rest, Long> {

    Page<Rest> findByRestState(Pageable pageable, String searchKeyword);

}