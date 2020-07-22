package com.pet.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*
 * 로그를 전문적으로 처리해주는 오픈소스 진영의 라이브러리
 * log4j (안드로이드에서도 사용)
 */
public class TestLog {
	//출력레벨 trace < debug < info < error < fatal
	Logger logger;
	FileInputStream fis;
	String path = "D:/web_app/javaee_workspace/PetShop/src/com/pet/log/log4j.properties";
	Properties properties;
	
	public TestLog() {
		logger = logger.getLogger(this.getClass().getName());
		//logger에 설정파일 인식시키기
		try {
			fis = new FileInputStream(path);
			properties = new Properties();
			properties.load(fis);
			PropertyConfigurator.configure(properties);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("파일 찾기 실패");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMessage () {
		logger.trace("출력");
		logger.debug("출력");
		logger.info("상태나 정보 출력");
		logger.error("에러");
		logger.fatal("치명적 에러");
	}
	
	public static void main(String[] args) {
		new TestLog();
	}
}
