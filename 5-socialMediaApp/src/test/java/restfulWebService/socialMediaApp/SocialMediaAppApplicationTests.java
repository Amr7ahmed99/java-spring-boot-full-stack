package restfulWebService.socialMediaApp;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SocialMediaAppApplicationTests {

	List<String> users = Arrays.asList("Murad", "Amr", "Ahmed");
	
	@Test
	void testUserExistence() {
		boolean test = users.contains("Amr");//Result
		boolean test2 = users.contains("Saleh");//Result
		assertTrue(test);
		assertFalse(test2);
		assertArrayEquals(new int[] {1,2}, new int[] {1, 2});
		assertEquals(3, users.size());
		System.out.println("testUserExistence");
		
	}

	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach");
	}

	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() {
		System.out.println("test2");
	}

	@Test
	void test3() {
		System.out.println("test3");
	}

	@AfterEach
	void afterEach() {
		System.out.println("AfterEach");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("afterAll");
	}

}
