
import java.awt.event.*;
/** This is the listener class, 
 * which handles the button events.
 */
public class KeypadListener implements ActionListener {

	private DictionaryModel model;
	private char key;

	/** Constructor.
	 * 
	 * @param model
	 * @param key
	 */
	public KeypadListener(DictionaryModel model, char key) {
		this.model = model;
		this.key = key;
	}


	public void actionPerformed(ActionEvent e) {
		if (key == '0') {
			//previous word entered is fine
			model.acceptWord();
		} else if (key == '*') {
			// change the current word in the currentMatches
			model.nextMatch();  
		} 
		else if (key == 'C') {
			// remove the last character typed
			model.removeLastCharacter();
		} else {
			// add a new character to the current signature
			model.addCharacter(key);
		}
	}
}
