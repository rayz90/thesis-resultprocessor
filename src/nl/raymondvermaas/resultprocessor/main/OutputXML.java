package nl.raymondvermaas.resultprocessor.main;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import nl.raymondvermaas.resultprocessor.models.*;

public class OutputXML {

	private String inPath;
	private String outPath;
	
	public OutputXML(String inPath, String outPath) {
		this.inPath = inPath;
		this.outPath = outPath;
	}
	
	public void write(Scenario[] scenarios) {
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inPath);
			DecimalFormat decim = new DecimalFormat("0.00000000");
			
			
			Node measurements = doc.getElementsByTagName("measurements").item(0);
			
			for (int i=0;i<scenarios.length;i++) {
				Scenario scenario = scenarios[i];
				for (int j=0;j<scenario.countCriteria();j++) {
					Criterion crit = scenario.getCriterion(j);
					Element measurement = doc.createElement("measurement");
					
					Element criterion = doc.createElement("criterion");
					criterion.setAttribute("class", "outrankingCriterion");
					criterion.setAttribute("ref", ""+crit.getBase().getId());
					measurement.appendChild(criterion);
					
					Element value = doc.createElement("value");
					value.setAttribute("class", "fi.smaa.jsmaa.model.DiscreteMeasurement");
					
					Element points = doc.createElement("points");
					ArrayList<Criterion.Point2D> norm = crit.getNormalizedValues();
					for(int k=0;k<norm.size();k++) {
						Element point = doc.createElement("point");
						point.setAttribute("x", ""+norm.get(k).getX());
						point.setAttribute("y", ""+decim.format(norm.get(k).getY()));
						points.appendChild(point);
					}
					value.appendChild(points);
					measurement.appendChild(value);
					
					Element alternative = doc.createElement("alternative");
					alternative.setAttribute("ref", ""+i);
					measurement.appendChild(alternative);
					
					measurements.appendChild(measurement);
				}
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(outPath));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
