package com.springboot.testing.mockito_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.springboot.testing.mockito_demo.business.DataService;
import com.springboot.testing.mockito_demo.business.SomeBusinessImp;

@SpringBootTest
class MockitoDemoApplicationTests {

	@Mock
	private DataService dataServiceMock;

	@InjectMocks
	private SomeBusinessImp someBusinessImp;

	@Test
	void SomeBusinessImpTest_basicScenario(){
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24, 6, 15});
		assertEquals(24, someBusinessImp.findTheGreatestFromAllData());
	}

	@Test
	void someBusinessImpTest_emptyScenario(){
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, someBusinessImp.findTheGreatestFromAllData());
	}

	@Test
	void someBusinessImpTest_oneValueScenario(){
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {15});
		assertEquals(15, someBusinessImp.findTheGreatestFromAllData());
	}

	@Test
	void someBusinessImpTest_minimumsInTwoArrays() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {3, 5, 1}, new int[] {2, 4, 6});
		int[] result = someBusinessImp.findMinimumsInTwoArrays();
		assertEquals(1, result[0]);
		assertEquals(2, result[1]);
	}

}
