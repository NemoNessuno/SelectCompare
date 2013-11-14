package de.fub.stitze.main;

public class Result {
	
	private int _comparissons;
	private int _position;
	private int _lookedFor;
	private int _actual;
	
	public Result(int position, int comparrisons, int lookedFor, int actual) {
		_lookedFor = lookedFor;
		_actual = actual;
		_position = position;
		_comparissons = comparrisons;		
	}
	
	public int getComparissons() {
		return _comparissons;
	}
		
	public int getPosition() {
		return _position;
	}

	public int getLookedFor() {
		return _lookedFor;
	}

	public int getActual() {
		return _actual;
	}
}
