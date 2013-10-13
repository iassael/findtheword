package net.ic3man.findtheword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// Word Length
	static int num_letters = 9;
	// Word Letters
	static String letters = "δολειολαα";

	static String[] vowel = { "ά", "έ", "ή", "ί", "ό", "ύ", "ώ", "ς" };
	static String[] vowel_replace = { "α", "ε", "η", "ι", "ο", "υ", "ω", "σ" };

	public static void main(String[] args) {

		// Open the file
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("Greek.dic");
			// fstream = new FileInputStream("greek_open.dic");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					fstream));

			String strLine;

			int results = 0;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.length() == num_letters) {
					for (int v = 0; v < vowel.length; v++) {
						strLine = strLine.toLowerCase().replaceAll(vowel[v],
								vowel_replace[v]);
					}

					boolean[] usedLetters = new boolean[letters.length()];

					char[] charArr = strLine.toCharArray();
					int counter = 0;

					for (int i = 0; i < charArr.length; i++) {
						for (int j = 0; j < letters.length(); j++) {
							if (usedLetters[j] == false
									&& charArr[i] == Character
											.toLowerCase(letters.charAt(j))) {
								usedLetters[j] = true;
								counter++;
								break;
							}
						}
					}
					if (counter == num_letters) {
						System.out.println(strLine);
						results++;
					}
				}
			}
			System.out.println("Search ended with "+results+" results");

			// Close the input stream
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
