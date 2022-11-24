package arff;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;

public class TestArffWrite {

	public static void main(String[] args) throws ParseException {

		//		ArrayList<String> my_nominal_values = new ArrayList<>();
		//		my_nominal_values.add("first");
		//		my_nominal_values.add("second");
		//		my_nominal_values.add("third");
		//		
		//		ArrayList<String> my_id_values = new ArrayList<>();
		//		my_id_values.add("1");
		//		my_id_values.add("2");
		//		my_id_values.add("3");
		//		
		//		Attribute position = new Attribute("position", my_nominal_values);
		//		Attribute id = new Attribute("id", my_id_values);
		//		
		//		ArrayList<Attribute> my_attributes = new ArrayList<>();
		//		my_attributes.add(position);
		//		my_attributes.add(id);
		//		
		//		Instances test = new  Instances("testSet", my_attributes, 3);

		FastVector      atts;
		FastVector      attsRel;
		FastVector      attVals;
		FastVector      attValsRel;
		Instances       data;
		Instances       dataRel;
		double[]        vals;
		double[]        valsRel;
		int             i;

		// 1. set up attributes
		atts = new FastVector();
		// - numeric
		atts.addElement(new Attribute("att1"));
		// - nominal
		attVals = new FastVector();
		for (i = 0; i < 5; i++)
			attVals.addElement("val" + (i+1));
		atts.addElement(new Attribute("att2", attVals));
		// - string
		atts.addElement(new Attribute("att3", (FastVector) null));
		// - date
		atts.addElement(new Attribute("att4", "yyyy-MM-dd"));
		// - relational
		attsRel = new FastVector();
		// -- numeric
		attsRel.addElement(new Attribute("att5.1"));
		// -- nominal
		attValsRel = new FastVector();
		for (i = 0; i < 5; i++)
			attValsRel.addElement("val5." + (i+1));
		attsRel.addElement(new Attribute("att5.2", attValsRel));
		dataRel = new Instances("att5", attsRel, 0);
		atts.addElement(new Attribute("att5", dataRel, 0));

		// 2. create Instances object
		data = new Instances("MyRelation", atts, 0);

		// 3. fill with data
		// first instance
		vals = new double[data.numAttributes()];
		// - numeric
		vals[0] = Math.PI;
		// - nominal
		vals[1] = attVals.indexOf("val3");
		// - string
		vals[2] = data.attribute(2).addStringValue("This is a string!");
		// - date
		vals[3] = data.attribute(3).parseDate("2001-11-09");
		// - relational
		dataRel = new Instances(data.attribute(4).relation(), 0);
		// -- first instance
		valsRel = new double[2];
		valsRel[0] = Math.PI + 1;
		valsRel[1] = attValsRel.indexOf("val5.3");
		dataRel.add(new DenseInstance(1.0, valsRel));
		// -- second instance
		valsRel = new double[2];
		valsRel[0] = Math.PI + 2;
		valsRel[1] = attValsRel.indexOf("val5.2");
		dataRel.add(new DenseInstance(1.0, valsRel));
		vals[4] = data.attribute(4).addRelation(dataRel);
		// add
		data.add(new DenseInstance(1.0, vals));

		// second instance
		vals = new double[data.numAttributes()];  // important: needs NEW array!
		// - numeric
		vals[0] = Math.E;
		// - nominal
		vals[1] = attVals.indexOf("val1");
		// - string
		vals[2] = data.attribute(2).addStringValue("And another one!");
		// - date
		vals[3] = data.attribute(3).parseDate("2000-12-01");
		// - relational
		dataRel = new Instances(data.attribute(4).relation(), 0);
		// -- first instance
		valsRel = new double[2];
		valsRel[0] = Math.E + 1;
		valsRel[1] = attValsRel.indexOf("val5.4");
		dataRel.add(new DenseInstance(1.0, valsRel));
		// -- second instance
		valsRel = new double[2];
		valsRel[0] = Math.E + 2;
		valsRel[1] = attValsRel.indexOf("val5.1");
		dataRel.add(new DenseInstance(1.0, valsRel));
		vals[4] = data.attribute(4).addRelation(dataRel);
		// add
		data.add(new DenseInstance(1.0, vals));
		
		// 4. output data
		System.out.println(data);

		try (FileWriter out = new FileWriter("arffTest.arff")){
			out.write(data.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Mein Scheiß
		double[][] valueArray = new double[data.numAttributes()][];
		Instances test = new Instances("MyRelation", atts, 0);
		
		for (int a = 0; a < data.numAttributes(); a++) {
			valueArray[a] = data.attributeToDoubleArray(a);
		}
		
		for (int b = 0; b < valueArray[0].length; b++) {
			double[] values = new double[data.numAttributes()];
			for (int c = 0; c < values.length; c++) {
				values[c] = valueArray[c][b];
			}
			test.add(new DenseInstance(1.0,values));
		}
		
		
		System.out.println(test);
	
	}

}
