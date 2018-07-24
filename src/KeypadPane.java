
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/** The panel for the keypad.
 * 
 */
public class KeypadPane extends JPanel {
	private DictionaryModel model;

	/** Constructor.
	 * Note that we have to associate
	 * the model with this class.
	 * @param model, the DictionaryModel
	 */
	public KeypadPane(DictionaryModel model) {
		this.model = model;
		setLayout(new GridLayout(4, 3));
		JButton key1 = new JButton("<html><center> 1 <p>  </center></html>");
		JButton key2 = new JButton("<html><center> 2 <p> abc </center></html>");
		JButton key3 = new JButton("<html><center> 3 <p> def </center></html>");
		JButton key4 = new JButton("<html><center> 4 <p> ghi </center></html>");
		JButton key5 = new JButton("<html><center> 5 <p> jkl </center></html>");
		JButton key6 = new JButton("<html><center> 6 <p> mno </center></html>");
		JButton key7 = new JButton("<html><center> 7 <p> pqrs </center></html>");
		JButton key8 = new JButton("<html><center> 8 <p> tuv </center></html>");
		JButton key9 = new JButton("<html><center> 9 <p> vwxy </center></html>");
		JButton keyBlank = new JButton("<html> C </html>");
		JButton key0 = new JButton("<html><center> 0 <p> _ </center></html>");
		JButton keyStar = new JButton(" * ");

		add(key1);
		add(key2);
		add(key3);
		add(key4);
		add(key5);
		add(key6);
		add(key7);
		add(key8);
		add(key9);
		add(keyStar);
		add(key0);
		add(keyBlank);

		key2.addActionListener(new KeypadListener(model, '2'));
		key3.addActionListener(new KeypadListener(model, '3'));
		key4.addActionListener(new KeypadListener(model, '4'));
		key5.addActionListener(new KeypadListener(model, '5'));
		key6.addActionListener(new KeypadListener(model, '6'));
		key7.addActionListener(new KeypadListener(model, '7'));
		key8.addActionListener(new KeypadListener(model, '8'));
		key9.addActionListener(new KeypadListener(model, '9'));
		keyStar.addActionListener(new KeypadListener(model, '*'));
		key0.addActionListener(new KeypadListener(model, '0'));
		keyBlank.addActionListener(new KeypadListener(model, 'C'));

	}
}
