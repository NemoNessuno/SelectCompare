package de.fub.stitze.main;

public class QuadraticBinarySearch extends Search {

	public QuadraticBinarySearch(int[] list, int lookFor, int range) {
		super(list, lookFor, range);
	}

	/**
	 * Search for the element by using quadratic binary search
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
			int locelem = _list[k];
			if (locelem == _element) {
				_count++;
				return k;
			} else {
				int m = (int) Math.ceil(Math.sqrt(r - l + 1));
				int i = 1;
				_count += 2;
				if (locelem > _element) {
					do {
						int index = Math.max(0, k - i * m);
						locelem = _list[index];
						if (locelem <= _element)
							break;
						i++;
						_count++;
					} while (true);
					return search(k - i * m, k - (i - 1) * m);
				} else {
					do {
						int index = Math.min(_list.length - 1, k + i * m);
						locelem = _list[index];
						if (locelem >= _element)
							break;
						i++;
						_count++;
					} while (true);
					return search(k + (i - 1) * m, k + i * m);
				}
			}
		} else {
			_count++;
			return -1;
		}
	}

	@Override
	public String getName() {
		return "quadratic binary search";
	}

}
