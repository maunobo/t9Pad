
import java.util.List;
/** @author Uday Reddy
 * 
 * This is the interface for your solutions for Worksheet4.
 * Do not modify this file.
 * Submit it along with your solution.
 */

public interface  DictionaryModelInterface {

	public List<String> getMessage();

	public void addCharacter(char key);

	public void removeLastCharacter(); 

	public void nextMatch();

	public void acceptWord();
}
