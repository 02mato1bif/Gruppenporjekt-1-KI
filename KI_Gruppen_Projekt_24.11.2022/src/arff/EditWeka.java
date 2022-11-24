package arff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;

public class EditWeka {

	//	holt die einzelnen String werte der Instances und schmeist sie in eine ArrayList für Strings
	//	ArrayList<String> reviewString = reader.arffAttributeStringValues(reviewAttr);

	//	holt sich den Attribute namens "token" der an postion 2 sitzt
	//	Attribute reviewAttr = reader.arffAttributeAt(sarcasmcorpus, 2);


	//	attrList.add(new Attribute("dot&comma&?&!"));
	//	sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("PUNCTUATION"));
	//	
	//	attrList.add(new Attribute("dot&comma&?&!&CAPSLOCK"));
	//	sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("ALL"));


	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\Datensatz\\sarcasmcorpus\\sarcasmcorpus\\sarcasm.arff";
		ReadWeka reader = new ReadWeka(path);
		WriteWeka writer = new WriteWeka();
		EditWeka editor = new EditWeka();

		//holt sich die Instances aus der im pfad angebenen arff datei
		Instances sarcasmcorpus = reader.arffInstance();
		
		//double [][] sarcasmcorpusArray = editor.valuesToDoubleArray(sarcasmcorpus);
		double [][] sarcasmcorpusArray = new double[0][];

		//holt die einzelnen Attribute des datenSets und schmeißt sie in eine ArrayList für Attribute
		//ArrayList<Attribute> attrList = reader.arffAttributeAll(sarcasmcorpus);
		ArrayList<Attribute> attrList = new ArrayList<>();
		
		//symbole Zählen aus den review strings
		Attribute reviewAttr = reader.arffAttributeAt(sarcasmcorpus, 2);
		ArrayList<String> reviewString = reader.arffAttributeStringValues(reviewAttr);
		Map<String, double[]> symbolMap = editor.symbolCountAll(reviewString);

		//char counts
		attrList.add(new Attribute("chars"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("CHARCOUNT"));

		attrList.add(new Attribute("UPPERCASE"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("UPPER"));

		attrList.add(new Attribute("LOWERCASE"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("LOWER"));

		//symbol counts
		attrList.add(new Attribute("dot"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("."));

		attrList.add(new Attribute("comma"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get(","));

		attrList.add(new Attribute("?"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("?"));

		attrList.add(new Attribute("!"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("!"));

		attrList.add(new Attribute("\'"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("\'"));

		attrList.add(new Attribute("`"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, symbolMap.get("`"));

		//word counts
		attrList.add(new Attribute("amount_adjective.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_adjective.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_adverb.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_adverb.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_article.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_article.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_comparative.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_comparative.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_conjunction.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_conjunction.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_interjection.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_interjection.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_nouns.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_nouns.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_numeral.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_numeral.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_superlative.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_superlative.txt", sarcasmcorpus.numInstances()));

		attrList.add(new Attribute("amount_verbs.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\amount_verbs.txt", sarcasmcorpus.numInstances()));

		//profanity counts
		attrList.add(new Attribute("profanity_prob.txt"));
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, reader.wordCountRead("C:\\Users\\Tobias\\OneDrive\\Studium OneDrive\\Semester 2_3 WS 22_23\\Künstliche Intelligenz\\Gruppenarbeit\\KI_Gruppen_Projekt_22.11.2022-20221122T190750Z-001\\KI_Gruppen_Projekt_22.11.2022\\src\\mo\\profanity_prob.txt", sarcasmcorpus.numInstances()));

		ArrayList<Attribute> originalAttr = reader.arffAttributeAll(sarcasmcorpus);
		attrList.add(originalAttr.get(originalAttr.size()-1));
		
		sarcasmcorpusArray = editor.addToArray(sarcasmcorpusArray, editor.valuesToDoubleArray(sarcasmcorpus)[originalAttr.size()-1]);
		
		//gibt Instances aus wenn man ihm eine Relation eine Attribute Liste und values in double gibt
		Instances testSarcasm = writer.writeInstances("KI_Test_sarcasm", attrList, sarcasmcorpusArray);

		//schreibt arff datei
		writer.writeFile(testSarcasm);
	}

	public double[][] valuesToDoubleArray(Instances instance) {
		double[][] valueArray = new double[instance.numAttributes()][];

		for (int i = 0; i < instance.numAttributes(); i++) {
			valueArray[i] = instance.attributeToDoubleArray(i);
		}
		return valueArray;
	}

	public double[][] addToArray(double[][] smallerArr, double[] attrVals) {
		double[][] biggerArr = new double[smallerArr.length+1][];

		for (int i = 0; i < smallerArr.length; i++) {
			biggerArr[i] = smallerArr[i];
		}
		biggerArr[biggerArr.length-1] = attrVals;

		return biggerArr;
	}

	public double[] stringToDoubleArray() {

		return null;
	}

	public Map<String, double[]> symbolCountAll (ArrayList<String> reviewString) {
		Map<String, double[]> symbolMap = new HashMap<>();
		int countPunkt = 0;
		int countKomma = 0;
		int countFragezeichen = 0;
		int countAusrufezeichen = 0;
		int countGroß = 0;
		int countLowerCase = 0;
		int countChicken = 0;
		int countChicken2 = 0;
		double[] anzahlPunkte = new double[reviewString.size()];
		double[] anzahlKomma = new double[reviewString.size()];
		double[] anzahlFragezeichen = new double[reviewString.size()];
		double[] anzahlAusrufezeichen = new double[reviewString.size()];
		double[] reviewLength = new double[reviewString.size()];
		double[] anzahlGroßbuchstaben = new double[reviewString.size()];
		double[] anzahlLowerCase = new double[reviewString.size()];

		double[] anzahlZeichen = new double[reviewString.size()];
		double[] anzahlAlle = new double[reviewString.size()];
		double[] anzahlChicken = new double[reviewString.size()];
		double[] anzahlChicken2 = new double[reviewString.size()];

		for (int i = 0; i < reviewString.size(); i++) {
			countPunkt = 0;
			countKomma = 0;
			countFragezeichen = 0;
			countAusrufezeichen = 0;
			countGroß = 0;
			countLowerCase= 0;
			countChicken = 0;
			countChicken2 = 0;
			reviewLength[i] = reviewString.get(i).length();


			for(int j = 0; j<reviewString.get(i).length(); j++) {

				char a = reviewString.get(i).charAt(j);
				String b = String.valueOf(a);

				if((Character.isUpperCase(a)) ) {
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

				if(Character.isLowerCase(a)) {
					countLowerCase++;
				}

				if(b.equals("\'")) {
					countChicken++;
				}

				if(b.equals("`")) {
					countChicken2++;
				}

			}
			anzahlPunkte[i] = countPunkt;
			anzahlKomma[i] = countKomma;
			anzahlFragezeichen[i] = countFragezeichen;
			anzahlAusrufezeichen[i] = countAusrufezeichen;

			anzahlGroßbuchstaben[i] = countGroß;
			anzahlLowerCase[i] = countLowerCase;
			anzahlZeichen[i] = countPunkt+countKomma+countFragezeichen+countAusrufezeichen;
			anzahlAlle[i] = countPunkt+countKomma+countFragezeichen+countAusrufezeichen+countGroß;
			anzahlChicken[i] = countChicken;
			anzahlChicken2[i] = countChicken2;
		}

		symbolMap.put(".", anzahlPunkte);
		symbolMap.put(",", anzahlKomma);
		symbolMap.put("?", anzahlFragezeichen);
		symbolMap.put("!", anzahlAusrufezeichen);
		symbolMap.put("UPPER", anzahlGroßbuchstaben);
		symbolMap.put("LOWER", anzahlLowerCase);

		symbolMap.put("PUNCTUATION", anzahlZeichen);
		symbolMap.put("ALL", anzahlAlle);

		symbolMap.put("CHARCOUNT", reviewLength);

		symbolMap.put("\'", anzahlChicken);

		symbolMap.put("`", anzahlChicken2);


		return symbolMap;
	}

}
