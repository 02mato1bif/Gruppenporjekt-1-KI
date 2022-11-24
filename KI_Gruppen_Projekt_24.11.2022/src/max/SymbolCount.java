package max;

import java.io.IOException;


import java.util.ArrayList;

import arff.ReadWeka;
import weka.core.Attribute;
import weka.core.Instances;

public class SymbolCount {

	public static void main(String[] args) throws IOException {
		int countPunkt = 0;
		int countKomma = 0;
		int countFragezeichen = 0;
		int countAusrufezeichen = 0;
		int countGroß = 0;
		int[] anzahlPunkte = new int[12534];
		int[] anzahlKomma = new int[12534];
		int[] anzahlFragezeichen = new int[12534];
		int[] anzahlAusrufezeichen = new int[12534];
		int[] reviewLength = new int[12534];
		int[] anzahlGroßbuchstaben = new int[12534];
		String path = "C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\Datensatz\\sarcasmcorpus\\sarcasmcorpus\\sarcasm.arff";
		ReadWeka read = new ReadWeka(path) ;
		Instances sarcasmcorpus = read.arffInstance();
		Attribute reviewAttr = read.arffAttributeAt(sarcasmcorpus, 2);
		ArrayList<String> reviewString = read.arffAttributeStringValues(reviewAttr);
		System.out.println(reviewString.size());
		
		for (int i = 0; i < 1254; i++) {
			countPunkt = 0;
			countKomma = 0;
			countFragezeichen = 0;
			countAusrufezeichen = 0;
			countGroß = 0;
			reviewLength[i] = reviewString.get(i).length();
			
			
			for(int j = 0; j<reviewString.get(i).length(); j++) {
				
				char a = reviewString.get(i).charAt(j);
				char c = reviewString.get(i).charAt(j++);
				String b = String.valueOf(a);
				String d = String.valueOf(c);
				
				if(b.equals(b.toUpperCase()) && d.equals(d.toUpperCase()) ) {
					countGroß++;
				}
				
				if(b.equals(".")) {
					countPunkt++;
					
				}
				
				if(b.equals(",")) {
					countKomma++;
					
				}
				
				if(b.equals("?")) {
					countFragezeichen++;
					
				}
				
				if(b.equals("!")) {
					countAusrufezeichen++;
					
				}
			}
			anzahlPunkte[i] = countPunkt;
			anzahlKomma[i] = countKomma;
			anzahlFragezeichen[i] = countFragezeichen;
			anzahlAusrufezeichen[i] = countAusrufezeichen;
			anzahlGroßbuchstaben[i] = countGroß;
		}
		for (int i = 0; i < 1254; i++) {
			System.out.println(i+" "+anzahlGroßbuchstaben[i]);
		}
		
	}

}
