/**
 * Original code of the SampleDictionary by Uday Reddy, used for WS2-4.
 * @author mnb
 * @version 19 Feb 2018  |  09:56:03
 * @tags 
 */

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SampleDictionary implements Dictionary {
	private Set<String> words = new TreeSet<String>();
	private SampleDictionary[] children = new SampleDictionary[8];

	public SampleDictionary(String dict) throws IOException {
		Scanner fileScan = new Scanner(new File(dict));
		while (fileScan.hasNext()) {
			String word = fileScan.nextLine().toLowerCase();
			if (!PredictivePrototype.validWord(word))
				continue;
			this.insert(PredictivePrototype.wordToSignature(word), word);
		}
		fileScan.close();
	}

	public SampleDictionary[] getChildren() {
		return this.children;
	}

	public void setChildren(SampleDictionary[] children) {
		this.children = children;
	}

	private SampleDictionary() {
	}

	private void insert(String signature, String word) {
		if (signature.length() > 0) {
			this.insertHelper(0, signature, word);
		}
	}

	private void insertHelper(int n, String signature, String word) {
		if (n == signature.length()) {
			this.words.add(word);
		} else {
			char ch = signature.charAt(n);
			if (ch >= '2' && ch <= '9') {
				int num = Character.digit(ch, 10);
				int index = num - 2;
				if (this.children[index] == null) {
					this.children[index] = new SampleDictionary();
				}
				this.words.add(word);
				this.children[index].insertHelper(n + 1, signature, word);
			}
		}
	}

	@Override
	public Set<String> signatureToWords(String signature) {
		Set<String> result = this.find(signature);
		TreeSet<String> resultCopy = new TreeSet<String>();
		for (String word : result) {
			resultCopy.add(word.substring(0, signature.length()));
		}
		return resultCopy;
	}

	private Set<String> find(String signature) {
		return this.findHelper(0, signature);
	}

	private Set<String> findHelper(int n, String signature) {
		if (n == signature.length()) {
			return new TreeSet<String>(this.words);
		}
		char ch = signature.charAt(n);
		if (ch >= '2' && ch <= '9') {
			int num = Character.digit(ch, 10);
			int index = num - 2;
			if (this.children[index] == null) {
				return new TreeSet<String>();
			}
			return this.children[index].findHelper(n + 1, signature);
		}
		return new TreeSet<String>();
	}
}
