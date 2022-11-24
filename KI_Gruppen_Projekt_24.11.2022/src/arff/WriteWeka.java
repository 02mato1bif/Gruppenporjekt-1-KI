package arff;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class WriteWeka {
	
//	ArrayList<ArrayList<Object>> valueList = attrValueList(attributeList);

//	if (attributeList.get(j).isNumeric()) {
//		System.out.println(Arrays.toString(instance.attributeToDoubleArray(j)));
//		value = instance.attributeToDoubleArray(j)[i];
//		
//	} else if (attributeList.get(j).isNominal()) {
//		value = attributeList.get(j).
//				//indexOf((String) valueList.get(j).get(i));
//		
//	} else if (attributeList.get(j).isString()) {
//		value = data.attribute(j).addStringValue((String) valueList.get(j).get(i));
//		
//	} 
//	
//	values[j] = value;
//}
	
//	private ArrayList<ArrayList<Object>> attrValueList(ArrayList<Attribute> attributeList) {
//		ArrayList<ArrayList<Object>> attrValueList = new ArrayList<>();
//		
//		for (int i = 0; i < attributeList.size(); i++) {
//			attrValueList.add(new ArrayList<>());
//			if (attributeList.get(i).isNominal()||attributeList.get(i).isString()) {
//				for (Enumeration<Object> e = attributeList.get(i).enumerateValues(); e.hasMoreElements();) {
//					attrValueList.get(i).add(e.nextElement());
//				}
//			}
//		}
//		
//		return attrValueList;
//		
//	}
	
	public Instances writeInstances(String name ,ArrayList<Attribute> attributeList, double [][] valueArray) {
		Instances data = new  Instances(name, attributeList, valueArray[0].length);
		
		for (int i = 0; i < valueArray[0].length; i++) {
			double[] values = new double[data.numAttributes()];
			for (int j = 0; j < values.length; j++) {
				values[j] = valueArray[j][i];
			}
			data.add(new DenseInstance(1.0, values));
		}

			return data; 
	}
	
	public void writeFile(Instances data) {
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd_MM;hh-mm");  
        String strDate = dateFormat.format(date);
		try (FileWriter out = new FileWriter("dataset "+strDate+".arff")){
			out.write(data.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
