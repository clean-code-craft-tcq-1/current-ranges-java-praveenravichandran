package currentRangeAnalyser;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentRangeAnalyser {

	public Map<String, Integer> findRangeInList(List<Integer> currentRangeSeries) {
		Map<String, Integer> countOfNumbersInDifferentRanges = new HashMap<>();
		if (currentRangeSeries == null || currentRangeSeries.isEmpty()) {
			throw new NullPointerException("Number series should not be null or empty");
		}
		if (currentRangeSeries.contains(null)) {
			throw new NullPointerException("Numbers provided in number series should not be null");
		}

		Collections.sort(currentRangeSeries);

		int rangeStartValue = currentRangeSeries.get(0);
		int rangeEndValue = currentRangeSeries.get(0);
		int numbersInGivenRange = 1;
		for (int i = 0; i < currentRangeSeries.size() - 1; i++) {
			int differenceBetweenSuccessiveValues = currentRangeSeries.get(i + 1) - currentRangeSeries.get(i);
			if (differenceBetweenSuccessiveValues <= 1) {
				numbersInGivenRange++;
				rangeEndValue = currentRangeSeries.get(i + 1);
			} else {
				buildRangeKeyAndInsertIntoMap(countOfNumbersInDifferentRanges, rangeStartValue, rangeEndValue,
						numbersInGivenRange);
				numbersInGivenRange = 1;
				rangeStartValue = currentRangeSeries.get(i + 1);
			}
			if (i == (currentRangeSeries.size() - 2)) {
				buildRangeKeyAndInsertIntoMap(countOfNumbersInDifferentRanges, rangeStartValue, rangeEndValue,
						numbersInGivenRange);
			}
		}
		printCurrentRanges(countOfNumbersInDifferentRanges);
		return countOfNumbersInDifferentRanges;
	}

	private void printCurrentRanges(Map<String, Integer> countOfNumbersInDifferentRanges) {
		for (String keys : countOfNumbersInDifferentRanges.keySet()) {
			System.out.println(keys + ", " + countOfNumbersInDifferentRanges.get(keys));
		}

	}

	private void buildRangeKeyAndInsertIntoMap(Map<String, Integer> countOfNumbersInDifferentRanges,
			int rangeStartValue, int rangeEndValue, int numbersInGivenRange) {
		StringBuilder rangeString = new StringBuilder();
		rangeString.append(rangeStartValue);
		rangeString.append('-');
		rangeString.append(rangeEndValue);
		countOfNumbersInDifferentRanges.put(rangeString.toString(), numbersInGivenRange);
	}
}
