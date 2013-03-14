package nl.raymondvermaas.resultprocessor.models;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseCriterion {
	private ArrayList<Double> map;
	private String name;
	private int id;
	private ArrayList<Double> borders;
	private int indexOffset;
	
	public BaseCriterion(int id, String name, ArrayList<Double> map, ArrayList<Double> classborders, int indexOffset) {
		this.map = map;
		this.name = name;
		this.id = id;
		this.borders = classborders;
		this.indexOffset = indexOffset;
	}

	public BaseCriterion(int id, String name, ArrayList<Double> classborders, int indexOffset) {
		this.name = name;
		this.id = id;
		this.borders = classborders;
		this.indexOffset = indexOffset;
	}
	
	public BaseCriterion(){}

	public ArrayList<Double> getMap() {
		return map;
	}

	public void setMap(ArrayList<Double> map) {
		this.map = map;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getValue(int key) {
		return map.get(key);
	}
	
	public void setValue(int key, double value) {
		map.set(key, value);
	}

	public ArrayList<Double> getBorders() {
		return borders;
	}

	public void setBorders(ArrayList<Double> borders) {
		this.borders = borders;
	}

	public int getIndexOffset() {
		return indexOffset;
	}

	public void setIndexOffset(int indexOffset) {
		this.indexOffset = indexOffset;
	}
	
	public int getSize() {
		return map.size();
	}
	
}
