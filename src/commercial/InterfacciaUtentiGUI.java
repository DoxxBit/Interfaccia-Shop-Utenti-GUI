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
	    		super("Gestione Utenti - GUI Moderna");
	        layout = new CardLayout();
	        setLayout(layout);
	        add(pannelloHome, "home");
	        add(pannelloLogin, "log-in");
	        add(pannelloRegistrazione, "registrazione");
	        add(pannelloUtente, "utente");
	        add(pannelloCarrello, "carrello");
	
	        layout.show(getContentPane(), "home");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(200, 200);
	        setVisible(true);
	    }
	    
	    private void initPannelloHome() {
	        pannelloHome = new JPanel();
	        pannelloHome.setLayout(new GridLayout(2, 1));

	        JButton loginButton = new JButton("Login");
	        loginButton.addActionListener(e -> layout.show(getContentPane(), "login"));

	        JButton registraButton = new JButton("Registrati");
	        registraButton.addActionListener(e -> layout.show(getContentPane(), "registrazione"));

	        pannelloHome.add(loginButton);
	        pannelloHome.add(registraButton);
	    }
}
