package com.insadong.application.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.insadong.application.common.entity.StudyStu;
import com.insadong.application.study.dto.StudyStuDTO;

@Configuration
public class BeanConfig {

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();
	       
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

	       modelMapper.typeMap(StudyStu.class, StudyStuDTO.class)
	                  .addMapping(src -> src.getStudyStuPK().getStudyCode(), StudyStuDTO::setStudyCode);

	       return modelMapper;
	}
}
