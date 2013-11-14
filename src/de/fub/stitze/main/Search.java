package de.fub.stitze.main;

public abstract class Search {
	
	protected int[] _list;
	protected int _element;	
	protected int _count;
	private int _range;
	
	public Search(int[] list, int lookFor, int range) {
		_list = list;
		_element = lookFor;
		_range = range;
	}
	
	/**
	 * Conduct the search starting by index 1 and length-2
	 * @return a Result object containing all neccessary information
	 * @throws Exception 
	 */
	public Result run(){		
		_count = 0;
		int position = search(0, _list.length-1);
		
		return new Result(position, _count, _element, position >= 0 ? _list[position] : -1);
	}
	
	/**
	 * Search for the element
	 * @param l
	 * @param r
	 * @return The position of the element or -1 if it can't be found
	 */
	public abstract int search(int l, int r);	

	/**
	 * Interpolate the position of the searched element
	 * 
	 * Please note that in order to account for 0 based
	 * arrays the indices (i.e. l) have been shifted by one
	 * @param l
	 * @param r
	 * @return interpolated position
	 */
	protected int interpolatePosition(int l, int r) {
		Double divisor = new Double(_element - getElement(l));
		Double dividend = new Double(getElement(r) - getElement(l));
		
		return Math.min(_list.length-1,
				l + (int) (divisor / dividend * (r-l)));
	}
	
	protected int getElement(int index){
		if (index < 0)
			return 0;
		else if (index >= _list.length)
			return _range;
		
		return _list[index];
	}

	public abstract String getName();
}