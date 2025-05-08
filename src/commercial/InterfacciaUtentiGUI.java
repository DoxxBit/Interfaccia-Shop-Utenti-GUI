package commercial;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class InterfacciaUtentiGUI extends JFrame {
	
	 	private ArrayList<Utenti> utentiRegistrati = new ArrayList<>();
	    private Utenti utenteLoggato = null;

	    private JPanel pannelloHome, pannelloLogin, pannelloRegistrazione, pannelloUtente, pannelloCarrello;
	    private CardLayout layout;
	    private JTextField loginEmailField;
	    private JTextField regFullNameField, regEmailField, regIndirizzoField;
	    private JTextArea areaUtente, areaCarrello;
	    private JTextField prodNomeField, prodPrezzoField;
	    
	    public InterfacciaUtentiGUI() {
	    	
	    }
}
