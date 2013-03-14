package nl.raymondvermaas.resultprocessor.models;

import java.util.ArrayList;

public class Criterion {

	private BaseCriterion bc;
	private double[] values;
	
	public Criterion(BaseCriterion bc, int column) {
		this.bc = bc;
		values = new double[bc.getMap().size()];
	}
	
	public void add(int choice, double value) {	
			values[choice] +=  value;
	}
	
	public ArrayList<Point2D> getNormalizedValues() {
		double total = 0.0;
		ArrayList<Point2D> norm = new ArrayList<Point2D>();
		
		for(double val : values) total+=val;
		
		double newTotal = 0.0;
		int i=-1;
		for(double val : values) {
			i++;
			if (val == 0.0) continue;
			double normVal = Math.round((val/total)*10000.0)/10000.0;
			norm.add(new Point2D(bc.getValue(i),normVal));
			newTotal += normVal;
		}
		
		if(newTotal != 1.0) {
			int index = norm.size() -1;
			while(norm.get(index).getY() == 0.0 && index > -1) index--;		
			double lastval = norm.get(index-1).getY() + (1.0-newTotal);
			Point2D old = norm.remove(index-1);
			norm.add(new Point2D(old.getX(),lastval));
		}
		
		return norm;
	}
	
	public BaseCriterion getBase() {
		return bc;
	}
	
	public String toString() {
		ArrayList<Point2D> norm = getNormalizedValues();
		String rtrn = "\t" + bc.getName() + " \n\t\t";
		for(int i = 0; i<norm.size();i++) {
			double value = norm.get(i).getY();
			if (value == 0.0) continue;
			value = (Math.round(value*10000.0))/10000.0;
			rtrn += "[" + norm.get(i).getX() + ";" + value + "],";
		}
		return rtrn + "\n";
	}

	public class Point2D {
		private double x;
		private double y;

		public Point2D(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public Point2D deepCopy() {
			return new Point2D(this.x, this.y);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Point2D) {
				Point2D po = (Point2D) o;
				return po.x == x && po.y == y;
			}
			return false;
		}

		@Override
		public String toString() {
			return "[" + x + "," + y + "]";
		}
	}
}
