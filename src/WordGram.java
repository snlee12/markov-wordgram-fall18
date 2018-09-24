/**
 * WordGram objects represent a k-gram of strings/words.
 *
 * @author Siyun Lee
 *
 */

import java.util.*;
public class WordGram {

	private String[] myWords;
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Creates a new WordGram from the given parameters
	 * @param source
	 * @param start
	 * @param size
	 */
	public WordGram(String[] source, int start, int size) {

		myWords = new String[size];

		for (int i = 0; i < size; i += 1) {
			myWords[i] = source[start];
			start += 1;
		}
		myToString = null;
		myHash = 0;
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string
	 * @return string at index
	 */
	public String wordAt(int index) {

		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Return the number of words in this WordGram
	 * @return the number of strings that make up the WordGram
	 */
	public int length(){

		return myWords.length;
	}


	@Override
	public boolean equals(Object o) {

		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram wg = (WordGram) o;
		return Arrays.equals(this.myWords, wg.myWords);
	}

	@Override
	public int hashCode(){

		if (myHash == 0) {
			myHash = this.toString().hashCode();
		}
		return myHash;
	}


	/**
	 * Creates a new WordGram using this WordGram as a frame. It places the String
	 * last at the last index, removes the String at the first index, and shifts
	 * everything over.
	 * @param last is last String of returned WordGram
	 * @return a new WordGram with last as the last string and all other strings
	 * shifted over one index
	 */
	public WordGram shiftAdd(String last) {

		String[] newMyWords = new String[myWords.length];

		for (int i = 0; i < myWords.length - 1; i += 1) {
			newMyWords[i] = myWords[i+1];
		}
		newMyWords[newMyWords.length - 1] = last;
		WordGram wg = new WordGram(newMyWords,0,newMyWords.length);
		return wg;
	}

	@Override
	public String toString(){

		if (myToString == null) {
			myToString = String.join(" ", myWords);
		}
		return myToString;
	}
}
