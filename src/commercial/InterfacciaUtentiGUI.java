package commercial;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class InterfacciaUtentiGUI extends JFrame {
	
	 	private ArrayList<Utenti> utentiRegistrati = new ArrayList<>();
	    private Utenti utenteLoggato = null;

	    private JPanel pannelloHome, pannelloLogin, pannelloRegistrazione, pannelloUtente, pannelloCarrello; // VARS DEI VARI PANNEL
	    private CardLayout layout;
	    private JTextField loginEmailField; // VARS PER IL LOG DELLE EMAIL UTENTI PRESENTI IN ARRAYLIST UTENTI
	    private JTextField regFullNameField, regEmailField, regIndirizzoField; // VARS PER LA REGISTRAZIONE
	    private JTextArea areaUtente, areaCarrello;
	    private JTextField prodNomeField, prodPrezzoField; // VARS PER L'INSERIMENTO PRODOTTO IN VAR MAP IN CARRELLO
	    
	    public InterfacciaUtentiGUI() {
	    		super("Gestione Utenti - GUI Moderna");
	        layout = new CardLayout();
	        setLayout(layout);
	        
	        // AGGIUNTA DEL METODO PRIVATO HOME
	        initPannelloHome();
	        initPannelloLogin();
	        initPannelloRegistrazione();
	        initPannelloUtente();
	        
	        add(pannelloHome, "Home");
	        add(pannelloLogin, "log-in");
	        add(pannelloRegistrazione, "registrazione");
	        add(pannelloUtente, "utente");
	        add(pannelloCarrello, "carrello");
	
	        layout.show(getContentPane(), "Home");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(200, 200);
	        setVisible(true);
	    }
	    
	    // METODO DI SERVIZIO HOME
	    private void initPannelloHome() {
	        pannelloHome = new JPanel();
	        pannelloHome.setLayout(new GridLayout(2, 1));

	        JButton loginButton = new JButton("Login");
	        loginButton.addActionListener(e -> layout.show(getContentPane(), "Login"));

	        JButton registraButton = new JButton("Registrati");
	        registraButton.addActionListener(e -> layout.show(getContentPane(), "Registrazione"));

	        pannelloHome.add(loginButton);
	        pannelloHome.add(registraButton);
	    }
	    
	    // METODO DI SERVIZIO LOG-IN
	    private void initPannelloLogin() {
	        pannelloLogin = new JPanel(new GridLayout(3, 1));
	
	        loginEmailField = new JTextField();
	        JButton loginBtn = new JButton("Accedi");
	
	        loginBtn.addActionListener(e -> {
	            String email = loginEmailField.getText();
	            for (Utenti utente : utentiRegistrati) {
	                if (utente.getEmail().equalsIgnoreCase(email)) {
	                    utenteLoggato = utente;
	                    aggiornaAreaUtente();
	                    layout.show(getContentPane(), "Utente");
	                    return;
	                }
	            }
	            JOptionPane.showMessageDialog(this, "Email non trovata.");
	        });
	
	        pannelloLogin.add(new JLabel("Inserisci Email:"));
	        pannelloLogin.add(loginEmailField);
	        pannelloLogin.add(loginBtn);
	    }
	    
	    // METODO DI SERVIZIO REGISTRAZIONE
	    private void initPannelloRegistrazione() {
	        pannelloRegistrazione = new JPanel(new GridLayout(4, 2));
	        regFullNameField = new JTextField();
	        regEmailField = new JTextField();
	        regIndirizzoField = new JTextField();
	        JButton registraBtn = new JButton("Registrati");
	        registraBtn.addActionListener(e -> {
	            Utenti nuovo = new Utenti(
	            								regFullNameField.getText(),
	            								regEmailField.getText(),
	            								regIndirizzoField.getText()
	            							);
	            utentiRegistrati.add(nuovo);
	            JOptionPane.showMessageDialog(this, "Registrazione completata!");
	            layout.show(getContentPane(), "Home");
	        });
	
	        pannelloRegistrazione.add(new JLabel("Full Name:"));
	        pannelloRegistrazione.add(regFullNameField);
	        pannelloRegistrazione.add(new JLabel("Email:"));
	        pannelloRegistrazione.add(regEmailField);
	        pannelloRegistrazione.add(new JLabel("Indirizzo:"));
	        pannelloRegistrazione.add(regIndirizzoField);
	        pannelloRegistrazione.add(new JLabel());
	        pannelloRegistrazione.add(registraBtn);
	    }
	    
	    // METODO DI SERVIZIO UTENTE
	    private void initPannelloUtente() {
	        pannelloUtente = new JPanel(new BorderLayout());
	        areaUtente = new JTextArea();
	        areaUtente.setEditable(false);
	
	        JPanel bottoniPanel = new JPanel(new GridLayout(1, 4));
	        JButton visualizzaBtn = new JButton("Visualizza Dati");
	        JButton modificaBtn = new JButton("Modifica Dati");
	        JButton carrelloBtn = new JButton("Carrello");
	        JButton logoutBtn = new JButton("Logout");
	
	        visualizzaBtn.addActionListener(e -> aggiornaAreaUtente());
	        modificaBtn.addActionListener(e -> {
	            String nome = JOptionPane.showInputDialog("Nuovo Full Name:");
	            String email = JOptionPane.showInputDialog("Nuova Email:");
	            String indirizzo = JOptionPane.showInputDialog("Nuovo Indirizzo:");
	            utenteLoggato.setFullName(nome);
	            utenteLoggato.setEmail(email);
	            utenteLoggato.setIndirizzo(indirizzo);
	            aggiornaAreaUtente();
	        });
	        carrelloBtn.addActionListener(e -> {
	            layout.show(getContentPane(), "carrello");
	        });
	        logoutBtn.addActionListener(e -> {
	            utenteLoggato = null;
	            layout.show(getContentPane(), "home");
	        });
	
	        bottoniPanel.add(visualizzaBtn);
	        bottoniPanel.add(modificaBtn);
	        bottoniPanel.add(carrelloBtn);
	        bottoniPanel.add(logoutBtn);
	
	        pannelloUtente.add(new JScrollPane(areaUtente), BorderLayout.CENTER);
	        pannelloUtente.add(bottoniPanel, BorderLayout.SOUTH);
	    }
	    
	    // METODO DI SERVIZIO PER AGGIORNARE AREA UTENTI
	    private void aggiornaAreaUtente() {
	        if (utenteLoggato != null)
	            areaUtente.setText(utenteLoggato.toString());
	    }
	    
	    // METODO DI SERVIZIO CARRELLO
	    
}
