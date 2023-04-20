package com.ohm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//스케줄러
@EnableScheduling
@SpringBootApplication
public class OhmApplication {

	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(OhmApplication.class, args);
	}


	//배포시 Spring 환경에선 localdate가 배포환경 시간에 맞춰 localdate를 호출한다.때문에 서울로 Timezone을 지정해줘야한다.
	//@PostConstruct 어노테이션은 의존성 주입이 완료된후 실행된다. bean lifeCycle에서 한번만 실행된다.
	@PostConstruct
	public void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

}
