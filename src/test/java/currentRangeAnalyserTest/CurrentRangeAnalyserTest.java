package currentRangeAnalyserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import currentRangeAnalyser.CurrentRangeAnalyser;

public class CurrentRangeAnalyserTest {

	@Test
	public void itShouldThrowNullPointerExceptionWhenReadingsListIsNull() {
		assertThrows(NullPointerException.class, () -> {
			new CurrentRangeAnalyser().findRangeInList(null);
		});
	}

	@Test
	public void itShouldThrowNullPointerExceptionWhenReadingsListIsEmpty() {
		assertThrows(NullPointerException.class, () -> {
			new CurrentRangeAnalyser().findRangeInList(new ArrayList<Integer>());
		});
	}

	@Test
	void shouldThrowNullPointerExceptionWhenOneOrMoreReadingIsNull() {
		List<Integer> numberSeries = new ArrayList<Integer>();
		numberSeries.add(10);
		numberSeries.add(null);
		assertThrows(NullPointerException.class, () -> {
			new CurrentRangeAnalyser().findRangeInList(new ArrayList<Integer>());
		});
	}

	@Test
	void findTheCountOfNumbersInEachRange() {
		Map<String, Integer> countOfNumbersInDifferentRanges = new CurrentRangeAnalyser()
				.findRangeInList(Arrays.asList(3, 3, 5, 4, 10, 11, 12));
		assertEquals(4, countOfNumbersInDifferentRanges.get("3-5"));
		assertEquals(3, countOfNumbersInDifferentRanges.get("10-12"));
	}
	
	@Test
	void findTheCountOfNumbersInEachRangeIfOneValueIsNull() {
		Map<String, Integer> countOfNumbersInDifferentRanges = new CurrentRangeAnalyser()
				.findRangeInList(Arrays.asList(3, 3, 5, 4, 10, 11, null));
		assertEquals(4, countOfNumbersInDifferentRanges.get("3-5"));
		assertEquals(3, countOfNumbersInDifferentRanges.get("10-12"));
	}
}
