package com.insadong.application.notice.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.File;
import com.insadong.application.common.entity.Notice;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.notice.dto.FileDTO;
import com.insadong.application.notice.dto.NoticeDTO;
import com.insadong.application.notice.repository.FileRepository;
import com.insadong.application.notice.repository.NoticeRepository;
import com.insadong.application.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final EmployeeRepository employeeRepository;
	private final FileRepository fileRepository;
	private final ModelMapper modelMapper;

	@Value("${image.image-url}")
	private String IMAGE_URL;
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	public NoticeService(NoticeRepository noticeRepository, EmployeeRepository employeeRepository,
			FileRepository fileRepository, ModelMapper modelMapper) {
		this.noticeRepository = noticeRepository;
		this.employeeRepository = employeeRepository;
		this.fileRepository = fileRepository;
		this.modelMapper = modelMapper;
	}

	/* 공지사항 전체목록 조회 */
	public Page<NoticeDTO> selectNoticeList(int page) {

		log.info("[NoticeService] selectNoticeList start ============================== ");

		Pageable pageable = PageRequest.of(page - 1, 4, Sort.by("noticeCode").descending());

		Page<Notice> noticeList = noticeRepository.findAll(pageable);

		Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

		log.info("[NoticeService] selectNoticeList.getContent() : {}", noticeDTOList.getContent());

		log.info("[NoticeService] selectEmpList end ============================== ");

		return noticeDTOList;
	}

	/* 공지사항 검색조건 조회 */
	public Page<NoticeDTO> searchNoticeByOption(int page, String searchOption, String searchKeyword) {

		log.info("[NoticeService] searchNoticeByOption start ==============================");

		Pageable pageable = PageRequest.of(page - 1, 4, Sort.by("noticeCode").descending());

		if (searchOption.equals("title")) {
			Page<Notice> noticeList = noticeRepository.findByNoticeTitleContains(pageable, searchKeyword);
			Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

			if (noticeDTOList.isEmpty()) {
				throw new IllegalArgumentException("검색조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			log.info("[NoticeService] searchNoticeByOption.getContent() : {}", noticeDTOList.getContent());

			return noticeDTOList;

		} else if (searchOption.equals("content")) {
			Page<Notice> noticeList = noticeRepository.findByNoticeContentContains(pageable, searchKeyword);
			Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

			if (noticeDTOList.isEmpty()) {
				throw new IllegalArgumentException("검색조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			log.info("[NoticeService] searchNoticeByOption.getContent() : {}", noticeDTOList.getContent());

			return noticeDTOList;

		} else if (searchOption.equals("writer")) {

			List<Employee> employeeList = employeeRepository.findByEmpNameContains(searchKeyword);

			if (employeeList.isEmpty()) {
				throw new IllegalArgumentException("검색 조건과 일치하는 공지사항이 존재하지 않습니다.");
			} else if (employeeList.size() > 1) {
				throw new IllegalArgumentException("검색 조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			Employee findEmployee = employeeList.get(0);

			Page<Notice> noticeList = noticeRepository.findByNoticeWriter(pageable, findEmployee);
			Page<NoticeDTO> noticeDTOList = noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));

			if (noticeDTOList.isEmpty()) {
				throw new IllegalArgumentException("검색조건과 일치하는 공지사항이 존재하지 않습니다.");
			}

			log.info("[NoticeService] searchNoticeByOption.getContent() : {}", noticeDTOList.getContent());

			return noticeDTOList;

		} else {
			throw new IllegalArgumentException("유효하지 않은 검색 옵션입니다.");
		}

	}

	/* 공지사항 등록 */
	@Transactional
	public void registNotice(NoticeDTO noticeDTO, Long empCode) throws IOException {
		Employee foundEmp = employeeRepository.findById(empCode)
				.orElseThrow(() -> new IllegalArgumentException("조회 실패"));
		noticeDTO.setNoticeWriter(modelMapper.map(foundEmp, EmployeeDTO.class));

		Notice notice = noticeRepository.save(modelMapper.map(noticeDTO, Notice.class));

		if (!(noticeDTO.getNoticeFile() == null)) {

			for (MultipartFile file : noticeDTO.getNoticeFile()) {

				FileDTO fileDTO = new FileDTO();

				String originFileName = file.getOriginalFilename();
				String saveFileName = UUID.randomUUID().toString().replace("-", "");
				String fileFath = IMAGE_DIR;
				Long fileSize = file.getSize();

				Path uploadPath = Paths.get(IMAGE_DIR);

				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}

				String replaceFileName = saveFileName + "." + FilenameUtils.getExtension(file.getOriginalFilename());

				try (InputStream inputStream = file.getInputStream()) {
					Path filePath = uploadPath.resolve(replaceFileName);
					Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					throw new IOException("파일을 저장하지 못하였습니다. saveFileName : " + saveFileName);
				}

				fileDTO.setOriginFileName(originFileName);
				fileDTO.setSaveFileName(replaceFileName);
				fileDTO.setFileFath(IMAGE_DIR);
				fileDTO.setFileSize(fileSize);
				fileDTO.setNoticeCode(notice.getNoticeCode());

				fileRepository.save(modelMapper.map(fileDTO, File.class));

			}
		}

	}

	/* 공지사항 상세 조회 */
	public NoticeDTO selectNoticeList(Long noticeCode) {

		log.info("[NoticeService] selectNoticeList start ============================== ");
		log.info("[NoticeService] noticeCode : {}", noticeCode);

		Notice notice = noticeRepository.findById(noticeCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 코드의 공지사항이 없습니다. noticeCode=" + noticeCode));

		NoticeDTO noticeDTO = modelMapper.map(notice, NoticeDTO.class);

		return noticeDTO;
	}

//	public byte[] downloadFile(String fileName) {
//		File file = fileRepository.findBySaveFileName(fileName)
//				.orElseThrow();
//		return null;
//	}

	/* 공지사항 수정 */
	@Transactional
	public void updateNotice(NoticeDTO noticeDTO, Long empCode) throws IOException {

		Notice notice = noticeRepository.findById(noticeDTO.getNoticeCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 코드의 공지사항이 없습니다."));
		
		log.info("[NoticeService] noticeDTO : {}", noticeDTO);

		/* 로그인 한 사용자와 게시글 작성자가 일치할 경우*/
		if (notice.getNoticeWriter().getEmpCode().equals(empCode)) {
			
			/* 수정 시 파일을 첨부했을 경우 - multipartfile은 파일이 없으면 null이 아니라 빈배열을 반환 따라서
			 * null이 아니라는 조건을 줘도 조건식에 걸리지 않음 
			 * 배열의 첫번째 요소가 비었는지를 확인해주면 조건식에 걸림*/
			if (noticeDTO.getNoticeFile() != null) {
				
				/* 기존 파일 삭제 */
//				List<File> beforefiles = fileRepository.findByNoticeCode(noticeDTO.getNoticeCode());
//				for (File beforefile : beforefiles) {
//					FileUploadUtils.deleteFile(IMAGE_DIR, beforefile.getSaveFileName());
//
//					fileRepository.delete(beforefile);
//
//				}

				for (MultipartFile file : noticeDTO.getNoticeFile()) {

					FileDTO fileDTO = new FileDTO();

					String originFileName = file.getOriginalFilename();
					String saveFileName = UUID.randomUUID().toString().replace("-", "");
					String fileFath = IMAGE_DIR;
					Long fileSize = file.getSize();

					Path uploadPath = Paths.get(IMAGE_DIR);

					if (!Files.exists(uploadPath)) {
						Files.createDirectories(uploadPath);
					}

					String replaceFileName = saveFileName + "."
							+ FilenameUtils.getExtension(file.getOriginalFilename());

					try (InputStream inputStream = file.getInputStream()) {
						Path filePath = uploadPath.resolve(replaceFileName);
						Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						throw new IOException("파일을 저장하지 못하였습니다. saveFileName : " + saveFileName);
					}

					fileDTO.setOriginFileName(originFileName);
					fileDTO.setSaveFileName(replaceFileName);
					fileDTO.setFileFath(IMAGE_DIR);
					fileDTO.setFileSize(fileSize);
					fileDTO.setNoticeCode(notice.getNoticeCode());

					fileRepository.save(modelMapper.map(fileDTO, File.class));
					
					notice.setNoticeTitle(noticeDTO.getNoticeTitle());
					notice.setNoticeContent(noticeDTO.getNoticeContent());
					notice.setNoticeModifyDate(new Date());

				}
				
			  /* 수정시 첨부파일 없을 경우*/
			} else {

				notice.setNoticeTitle(noticeDTO.getNoticeTitle());
				notice.setNoticeContent(noticeDTO.getNoticeContent());
				notice.setNoticeModifyDate(new Date());

			}

		} else {
			throw new IllegalArgumentException("수정할 수 있는 권한이 없습니다.");
		}
	}
	
	/* 파일 수정 - 기존 파일 삭제 */
	@Transactional
	public void deleteFile(String fileName) throws IOException {
		
		File file = fileRepository.findBySaveFileName(fileName);
		
		FileUploadUtils.deleteFile(IMAGE_DIR, file.getSaveFileName());
		
		fileRepository.delete(file);
		
	}

	public void deleteNotice(Long noticeCode, Long empCode) {
		
		Notice notice = noticeRepository.findById(noticeCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 코드의 공지사항이 없습니다. noticeCode=" + noticeCode));
		
		if(notice.getNoticeWriter().getEmpCode().equals(empCode)) {
			
			noticeRepository.delete(notice);
		} else {
			throw new IllegalArgumentException("삭제할 수 있는 권한이 없습니다.");
		}
		
		
	}

}
