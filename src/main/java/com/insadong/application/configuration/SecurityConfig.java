package com.insadong.application.configuration;

import com.insadong.application.jwt.JwtAccessDeniedHandler;
import com.insadong.application.jwt.JwtAuthenticationEntryPoint;
import com.insadong.application.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	private final JwtFilter jwtFilter;

	public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
	                      JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtFilter jwtFilter) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
		this.jwtFilter = jwtFilter;
	}


	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().antMatchers("/productimgs/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http
				.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(
						"/insa/v1/myScheduleList",
						"/insa/v1/myPagingScheduleList",
						"/insa/v1/myScheduleUpdate",
						"/insa/v1/mySchedule/**"
				).hasRole("MEMBER")
				.antMatchers(
						"/insa/v1/student",
						"/insa/v1/studyInfoList/**",
						"/insa/v1/PetiteStudyInfo/**",
						"/insa/v1/studyInfo/**",
						"/insa/v1/studyInsert",
						"/insa/v1/studyInfo",
						"/insa/v1/trainingTitleList",
						"/insa/v1/trainingList/**",
						"/insa/v1/training/**",
						"/abs/abs-admin/**",
						"/off/adminOff/**"
				).hasRole("ADMIN")
				.antMatchers("/insa/v1/empTeacher", "/insa/v1/empTeacher/**").hasRole("TEACHER")
				.antMatchers("/off/mySignOff/**", "/off/teamOff/**").hasRole("LEADER")
				.antMatchers("/auth/**").permitAll()
				.and()
				.cors()
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}

