package Default;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class will take the specified file from the location and read it line by line.
 * <p>
 * Words that contain more than 0 letters by length will be accounted for and sorted per occurrence.
 * </p>
 * <p>
 * Word occurrences and their count will be displayed in the program console once its been outputted. 
 * </p>
 * 
 * @author Duncan Li
 *
 */

public class WordOccurrences {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Map<String, Integer> fileReaderMap = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\conta\\OneDrive\\Desktop\\poem.txt"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				String[] words = line.split(" ");
				for (int i = 0; i < words.length; i++) {
					if (!fileReaderMap.containsKey(words[i])) {
						fileReaderMap.put(words[i], 1);
					} else {
						int newValue = fileReaderMap.get(words[i]) + 1;
						fileReaderMap.put(words[i], newValue);
					}
				}
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
		}
		List<Entry<String, Integer>> sorted = new ArrayList<>(fileReaderMap.entrySet());
		Collections.sort(sorted, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				int comp = Integer.compare(o1.getValue(), o2.getValue());
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		});

		for (Entry<String, Integer> entry : sorted) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}