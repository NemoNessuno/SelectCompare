package de.fub.stitze.main;

import java.util.Arrays;
import java.util.Random;

public class Main {

	private final static String USAGE_TEXT = "Usage: java -jar SearchCompare.jar "
			+ "[number of elements >= 0 - default = 1000000]"
			+ "[range >= 0 - default 1000000]"
			+ "[number of experiments - default 20]";

	private final static int DEFAULT_NUMBER = 1000000;
	private final static int DEFAULT_RANGE = 1000000;
	private final static int DEFAULT_EXPERIMENTS = 20;

	public static void main(String[] args) {

		int number = DEFAULT_NUMBER;
		int range = DEFAULT_RANGE;
		int experiments = DEFAULT_EXPERIMENTS;
		int lookFor = -1;
		int[] randomnumbers;

		// Read args
		try {
			if (args.length > 0)
				number = Integer.getInteger(args[0]);

			if (number < 0)
				throw new Exception("Number must be geq 0");

			if (args.length > 1)
				range = Integer.getInteger(args[0]);

			if (range < 0)
				throw new Exception("Range must be geq 0");

			if (args.length > 2)
				experiments = Integer.getInteger(args[2]);

			if (experiments <= 0)
				throw new Exception("Number of experiments must be greater 0");

		} catch (Exception e) {
			System.out.println("An error happened!\n" + "Message: "
					+ e.getMessage() + "\n" + USAGE_TEXT);
			return;
		}

		// Create random numbers in RANGE
		Random randomGenerator = new Random();
		randomnumbers = new int[number];
		for (int i = 0; i < number; i++) {
			randomnumbers[i] = randomGenerator.nextInt(range + 1);
		}

		// And sort it with a "tuned quicksort"
		Arrays.sort(randomnumbers);
		
		int i = 0;
		while (i < experiments) {
			// Choose element to look for
			lookFor = randomnumbers[randomGenerator.nextInt(randomnumbers.length)];			

			try {
				String result = "";
				result += runAndPrintSearch(new InterpolationSearch(
						randomnumbers, lookFor, 100001));
				result += runAndPrintSearch(new QuadraticBinarySearch(
						randomnumbers, lookFor, 100001));
				System.out.println(result);
				i++;
			} catch (Error e) {
				// Do nothing
			}
		}
	}

	private static String runAndPrintSearch(Search search) {
		Result result = search.run();
		StringBuilder sb = new StringBuilder();

		sb.append("A search was conducted using : " + search.getName() + "\n");
		sb.append("looked for: " + result.getLookedFor());
		if (result.getPosition() >= 0) {
			sb.append(" position: " + result.getPosition());
			sb.append(" actual element: " + result.getActual());
		} else {
			sb.append(" It was not found\n");
		}
		sb.append(" comparrisons: " + result.getComparissons());

		return sb.toString() + "\n";
	}

}
