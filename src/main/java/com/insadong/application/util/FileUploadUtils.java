package com.insadong.application.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.createDirectories;

public class FileUploadUtils {

	/* 첫 번째 인자 : 경로
	 * 두 번째 인자 : 저장할 고유한 파일 명
	 * 세 번째 인자 : 저장할 파일의 정보
	 * */
	public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

		/* 전달 된 경로를 Path 라는 타입으로 변환 ( 파일 명은 제외. 경로만 있다 ) */
		Path uploadPath = Paths.get(uploadDir);

		/* 업로드 경로가 존재하지 않을 경우, 경로를 먼저 생성한다. */
		if (!Files.exists(uploadPath)) {
			createDirectories(uploadPath);
		}

		/* 파일명 리네임
		 * => 전달받은 multipartFile 의 originalFileName 에서 확장자만 추출한 후, 새로운 파일 이름에 붙여준다.
		 * => 파일 확장자가 변경되지 않도록 설정한다. getExtension() 은 파일 확장자를 추출하는 메소드.  */
		String replaceFileName = fileName + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());

		/* 파일 저장
		 * multipartFile 의 .getInputStream() 을 이용하면 inputStream 을 생성할 수 있다.
		 * uploadPath 와 replaceFileName 을 .resolve() 메소드를 통해서 합친거임. 경로 + 이름 이 생긴것 !
		 * 그리고 그 파일을 copy 해서 저장한다. 첫 번째 인자는 스트림, 두 번째 인자는 경로와 파일 이름, 세 번째 인자는 같은 이름의 파일이 있다면 덮어쓴다는 설정을 해준다. */
		try (InputStream inputStream = multipartFile.getInputStream()) {

			Path filePath = uploadPath.resolve(replaceFileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			throw new IOException("파일을 저장하지 못했습니다. 파일 이름 : " + fileName);
		}

		return replaceFileName;
	}

	/* 첫 번째 인자 : 저장 경로
	 * 두 번째 인자 : 파일 이름 */
	public static void deleteFile(String uploadDir, String productImageUrl) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		Path filePath = uploadPath.resolve(productImageUrl);

		try {
			Files.delete(filePath);
		} catch (IOException e) {
			throw new IOException("파일을 삭제하지 못했습니다. 파일 이름 : " + productImageUrl);
		}
	}
}
