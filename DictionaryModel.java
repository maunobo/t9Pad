import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * @author mnb
 * @version 15 Feb 2018  |  15:47:07
 * @tags 
 */

/**
 * This class will be given the keys typed on the keypad via the addCharacter
 * method. It should use the dictionary o predict the possible words the user is
 * trying to enter, and store them internally.
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

	private Dictionary dictionary;
	private String signature;
	private List<String> matching;
	private List<String> sentence;
	private int index;

	// The two constructors
	// The first constructor takes the path name of a dictionary file and
	// initialises the internal data structures.
	public DictionaryModel(String dictionaryFile) throws java.io.IOException {

		this.dictionary = new SampleDictionary(dictionaryFile);
		this.signature = "";
		this.matching = new ArrayList<String>();
		this.sentence = new ArrayList<String>();
		this.matching.add("");
		this.index = 0;

	}

	// The second constructor uses a default dictionary of our choice.
	public DictionaryModel() throws java.io.IOException {
		
		this("/usr/share/dict/words");

	}

	// Returns a list of the words typed in so far
	@Override
	public List<String> getMessage() {

		ArrayList<String> getMessage = new ArrayList<String>(this.sentence);

		getMessage.add(this.getCurrentWord());

		return getMessage;
	}

	// The last word (or prefix) that has not yet been accepted.
	private String getCurrentWord() {

		if (this.matching.size() > 0) {

			return this.matching.get(this.index);

		} else {

			return "";
		}
	}

	// Adds a numeric key that has been typed in by the user. Extends the current
	// word (or prefix) with possible matches for the new key.
	@Override
	public void addCharacter(char key) {

		// A new List of matching words dependent on the key pressed
		ArrayList<String> matchingWords = new ArrayList<String>(

				this.dictionary.signatureToWords(this.signature + key));

		if (matchingWords.size() > 0) {

			this.signature = this.signature + key;

			this.matching = matchingWords;

			if (this.index >= this.matching.size()) {

				this.index = 0;

			}

			this.setChanged();

			this.notifyObservers();

		}
	}

	// Removes the last character typed in. This should remove the last character
	// from the current match, but it would in general enlarge the possible matches
	// for the current word.
	@Override
	public void removeLastCharacter() {

		if (this.signature.length() > 0) {

			String matchedWord = this.matching.get(this.index);

			matchedWord = matchedWord.substring(0, this.signature.length() - 1);

			this.signature = this.signature.substring(0, this.signature.length() - 1);

			if (this.signature.length() > 0) {

				this.matching = new ArrayList<String>(this.dictionary.signatureToWords(this.signature));

				this.index = Collections.binarySearch(this.matching, matchedWord);

			} else {

				this.matching = new ArrayList<String>();

				this.matching.add("");

				this.index = 0;

			}

			this.setChanged();

			this.notifyObservers();

		}

	}

	// Cycles through the possible matches for the current word.
	@Override
	public void nextMatch() {

		// Cycling through complete words using the index
		this.index++;

		if (this.index == matching.size()) {

			this.index = 0;

		}

		this.setChanged();

		this.notifyObservers();
	}

	// Accepts the current word and adds sit to the composed message.
	@Override
	public void acceptWord() {

		// Adds to the sentence the current selected word
		this.sentence.add(getCurrentWord());

		// Values reset for next input
		this.matching = new ArrayList<String>();
		this.matching.add("");
		this.signature = "";
		this.index = 0;

		this.setChanged();

		this.notifyObservers();

	}

}
