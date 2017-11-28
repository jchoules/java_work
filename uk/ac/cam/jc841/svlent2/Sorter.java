package uk.ac.cam.jc841.svlent2;

/**
 * An interface used via the Strategy pattern to allow
 * demonstration of different sorting algorithms
 * @author rkh23
 *
 */
public interface Sorter {

	/**
	 * precondition: array is an array of integers in no specific order
	 * postcondition: array will be the same integers, but sorted by value from lowest to highest
	 *
	 * @param array Input array
	 */
	public void Sort(int[] array);

	/**
	 * Return an identifier for you (e.g. "rkh23")
	 * @return unique identifier string
	 */
	public String GetID();

}
