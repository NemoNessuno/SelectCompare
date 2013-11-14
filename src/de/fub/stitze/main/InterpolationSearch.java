package de.fub.stitze.main;

public class InterpolationSearch extends Search {

	public InterpolationSearch(int[] list, int lookFor, int range) {
		super(list, lookFor, range);
	}

	/**
	 * Search for the elment by using interpolation search
	 * 
	 * @param l
	 * @param r
	 * @return The position of the element or -1 if it can't be found
	 */
	@Override
	public int search(int l, int r) {
		if (l <= r) {
			_count++;
			int k = interpolatePosition(l, r);
			int locelem = getElement(k);
			if (locelem == _element) {
				_count++;
				return k;
			} else {
				_count += 2;
				if (locelem > _element) {
					return search(l, k - 1);
				} else {
					return search(k + 1, r);
				}
			}
		} else {
			_count++;
			return -1;
		}
	}

	@Override
	public String getName() {
		return "interpolation search";
	}

}
