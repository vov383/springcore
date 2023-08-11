package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
/**
 * SpringBootTest는 실행하면 스플이 띄우고 해야되서 오래걸린다.
 * 얘 혼자 429ms
 * 그랳서 단위테스틐가 중요하다.
 * */
class CoreApplicationTests {

	@Test
	void contextLoads() {
	}

}
