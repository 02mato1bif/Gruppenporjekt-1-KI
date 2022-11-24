package anthony;

import java.io.IOException;
import java.util.ArrayList;

import arff.ReadWeka;
import weka.core.Attribute;
import weka.core.Instances;


public class CapsLengthCount {
	
	public static void main(String[] args) throws IOException {

		int[] reviewLength = new int[1254];

		int[] anzahlCapslock = new int[1254];
		int[] anzahlLowerCase = new int[1254];
		int[] anzahlLetter = new int[1254];
		int countCapslock = 0;
		int countLowerCase = 0;
		int countLetter = 0;

		String path = "C:\\Users\\studi\\Desktop\\sarcasmcorpus\\sarcasm.arff";
		ReadWeka read = new ReadWeka(path);
		Instances sarcasmcorpus = read.arffInstance();
		Attribute reviewAttr = read.arffAttributeAt(sarcasmcorpus, 2);
		ArrayList<String> reviewString = read.arffAttributeStringValues(reviewAttr);
		System.out.println(reviewString.size());

		for (int i = 0; i < 1254; i++) {

			countCapslock = 0;
			countLowerCase= 0;
			countLetter= 0;
			
			reviewLength[i] = reviewString.get(i).length();
			

			for (int j = 0; j < reviewString.get(i).length(); j++) {

				char a = reviewString.get(i).charAt(j);
			
				if (Character.isUpperCase(a)) {
					countCapslock++;	
				}
				
				if(Character.isLowerCase(a)) {
				countLowerCase++;
				}
				
				if(String.valueOf(a) != null) {
					countLetter++;
				}
				
			}
			anzahlCapslock[i] = countCapslock;
			anzahlLowerCase[i] = countLowerCase;
			anzahlLetter[i] = countLetter;
		}
		
		for (int i = 0; i < 1254; i++) {
			System.out.println(i + " " + anzahlCapslock[i] + " " + anzahlLowerCase[i] + " " + anzahlLetter[i] );
		}
		
	}
}
