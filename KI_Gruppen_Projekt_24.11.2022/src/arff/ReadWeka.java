package arff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;


public class ReadWeka {
	String path;

	public ReadWeka (String path) {
		this.path = path;
	}

	public Instances arffInstance() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		ArffReader arff = new ArffReader(reader);
		Instances data = arff.getData();

		return data;
	}

	public Attribute arffAttributeAt(Instances instance, int i) {

		return instance.attribute(i);
	}

	public ArrayList<Attribute> arffAttributeAll(Instances instance) {
		ArrayList<Attribute> allAttributes = new ArrayList<>();

		for (int i = 0 ; i < instance.numAttributes(); i++) {
			allAttributes.add(instance.attribute(i));
		}

		return allAttributes;

	}

	public ArrayList<Object> arffAttributeAllValues(Attribute attr) {
		ArrayList<Object> allValues = new ArrayList<>();
		
		if (attr.isNominal()||attr.isString()) {
			for (Enumeration<Object> e = attr.enumerateValues(); e.hasMoreElements();) {
				allValues.add(e.nextElement());
			}
		} 
		return allValues;
	}

	public ArrayList<String> arffAttributeStringValues(Attribute attr) {
		ArrayList<String> allValues = new ArrayList<>();

		if (attr.isString()) {
			for (Enumeration<Object> e = attr.enumerateValues(); e.hasMoreElements();) {
				allValues.add((String) e.nextElement());
			}
		}

		return allValues;
	}

	public double[] wordCountRead(String path ,int size) {
		double[] wordCount = new double[size];
		try  (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String zeile;
			int i = 0;
			while ((zeile = reader.readLine()) != null) {
				wordCount[i] = Double.parseDouble(zeile);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wordCount;
	}
}
