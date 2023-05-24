package com.insadong.application.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.insadong.application.common.entity.Notice;
import com.insadong.application.common.entity.StudyStu;
import com.insadong.application.notice.dto.NoticeDTO;
import com.insadong.application.study.dto.StudyStuDTO;

@Configuration
public class BeanConfig {

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		modelMapper.typeMap(StudyStu.class, StudyStuDTO.class).addMapping(src -> src.getStudyStuPK().getStudyCode(),
				StudyStuDTO::setStudy);
		
		modelMapper.typeMap(Notice.class, NoticeDTO.class).addMappings(mapper -> mapper.skip(NoticeDTO::setNoticeFile));

		return modelMapper;
	}
}
