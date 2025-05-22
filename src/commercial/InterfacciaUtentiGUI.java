package commercial;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

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
	    		super("Interfaccia Utenti Shop");
	        layout = new CardLayout();
	        setLayout(layout);
	        
	        initPannelloHome();
	        initPannelloLogin();
	        initPannelloRegistrazione();
	        initPannelloUtente();
	        initPannelloCarrello();
	        
	        add(pannelloHome, "home");
	        add(pannelloLogin, "login");
	        add(pannelloRegistrazione, "registrazione");
	        add(pannelloUtente, "utente");
	        add(pannelloCarrello, "carrello");
	
	        layout.show(getContentPane(), "home");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(Constants.sizeWidth, Constants.sizeHeight);
	        setVisible(true);
	    }
	    
	    // METODO DI SERVIZIO HOME
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
	    
	    // METODO DI SERVIZIO LOG-IN
	    private void initPannelloLogin() {
	        pannelloLogin = new JPanel(new GridLayout(3, 1));
	
	        loginEmailField = new JTextField();
	        JButton loginBtn = new JButton("Accedi");
	
	        loginBtn.addActionListener(e -> {
	            String email = loginEmailField.getText();
	            try (Connection conn = DBconn.getConnection()) {
	                String sql = "SELECT * FROM utenti WHERE email = ?";
	                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	                    stmt.setString(1, email);
	                    try (ResultSet rs = stmt.executeQuery()) {
	                        if (rs.next()) {
	                            utenteLoggato = new Utenti(
	                                rs.getString("full_name"),
	                                rs.getString("email"),
	                                rs.getString("indirizzo")
	                            );
	                            aggiornaAreaUtente();
	                            layout.show(getContentPane(), "utente");
	                        } else {
	                            JOptionPane.showMessageDialog(this, "Email non trovata.");
	                        }
	                    }
	                }
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(this, "Errore durante il login: " + ex.getMessage());
	            }
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
	            String fullName = regFullNameField.getText();
	            String email = regEmailField.getText();
	            String indirizzo = regIndirizzoField.getText();

	            try (Connection conn = DBconn.getConnection()) {
	                String sql = "INSERT INTO utenti (full_name, email, indirizzo) VALUES (?, ?, ?)";
	                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	                    stmt.setString(1, fullName);
	                    stmt.setString(2, email);
	                    stmt.setString(3, indirizzo);
	                    stmt.executeUpdate();
	                }
	                JOptionPane.showMessageDialog(this, "Registrazione completata!");
	                layout.show(getContentPane(), "home");
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(this, "Errore durante la registrazione: " + ex.getMessage());
	            }
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
	        // QUERY PER UPDATE PER MODIFICA UTENTE 
	        modificaBtn.addActionListener(e -> {
	            String nome = JOptionPane.showInputDialog("Nuovo Full Name:");
	            String email = JOptionPane.showInputDialog("Nuova Email:");
	            String indirizzo = JOptionPane.showInputDialog("Nuovo Indirizzo:");

	            try (Connection conn = DBconn.getConnection()) {
	                String sql = "UPDATE utenti SET full_name = ?, email = ?, indirizzo = ? WHERE email = ?";
	                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	                    stmt.setString(1, nome);
	                    stmt.setString(2, email);
	                    stmt.setString(3, indirizzo);
	                    stmt.setString(4, utenteLoggato.getEmail());
	                    stmt.executeUpdate();

	                    utenteLoggato.setFullName(nome);
	                    utenteLoggato.setEmail(email);
	                    utenteLoggato.setIndirizzo(indirizzo);
	                    aggiornaAreaUtente();
	                }
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(this, "Errore durante aggiornamento: " + ex.getMessage());
	            }
	        });

	        carrelloBtn.addActionListener(e -> {
	        		aggiornaAreaCarrello();
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
	    private void initPannelloCarrello() {
	        pannelloCarrello = new JPanel(new BorderLayout());
	
	        areaCarrello = new JTextArea();
	        areaCarrello.setEditable(false);
	
	        JPanel aggiuntaPanel = new JPanel(new GridLayout(3, 2));
	        prodNomeField = new JTextField();
	        prodPrezzoField = new JTextField();
	        JButton aggiungiBtn = new JButton("Aggiungi");
	
	        aggiungiBtn.addActionListener(e -> {
	            String nome = prodNomeField.getText();
	            double prezzo;

	            try {
	                prezzo = Double.parseDouble(prodPrezzoField.getText());
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(this, "Prezzo non valido");
	                return;
	            }

	            try (Connection conn = DBconn.getConnection()) {
	                conn.setAutoCommit(false);

	                /* SET OR IL GET ID DEL PRODOTTO */
	                int prodottoId;
	                try (PreparedStatement ps = conn.prepareStatement(
	                        "INSERT INTO prodotti (nome, prezzo) VALUES (?, ?) ON CONFLICT DO NOTHING RETURNING id", 
	                        Statement.RETURN_GENERATED_KEYS)) {
	                    ps.setString(1, nome);
	                    ps.setDouble(2, prezzo);
	                    ps.executeUpdate();
	                    ResultSet rs = ps.getGeneratedKeys();
	                    if (rs.next()) {
	                        prodottoId = rs.getInt(1);
	                    } else {
	                        try (PreparedStatement ps2 = conn.prepareStatement("SELECT id FROM prodotti WHERE nome = ?")) {
	                            ps2.setString(1, nome);
	                            ResultSet rs2 = ps2.executeQuery();
	                            rs2.next();
	                            prodottoId = rs2.getInt("id");
	                        }
	                    }
	                }

	                /* VERIFICA O CREAZIONE DEL CARRELLO UTENTE */
	                int carrelloId;
	                try (PreparedStatement ps = conn.prepareStatement(
	                        "SELECT id FROM carrelli WHERE utente_email = ?")) {
	                    ps.setString(1, utenteLoggato.getEmail());
	                    ResultSet rs = ps.executeQuery();
	                    if (rs.next()) {
	                        carrelloId = rs.getInt("id");
	                    } else {
	                        try (PreparedStatement ps2 = conn.prepareStatement(
	                                "INSERT INTO carrelli (utente_email) VALUES (?) RETURNING id")) {
	                            ps2.setString(1, utenteLoggato.getEmail());
	                            ResultSet rs2 = ps2.executeQuery();
	                            rs2.next();
	                            carrelloId = rs2.getInt("id");
	                        }
	                    }
	                }

	                /* COLEGAMENTO DEL PRODOTTO AL  CARELLO */
	                try (PreparedStatement ps = conn.prepareStatement(
	                        "INSERT INTO carrello_prodotti (carrello_id, prodotto_id) VALUES (?, ?) ON CONFLICT DO NOTHING")) {
	                    ps.setInt(1, carrelloId);
	                    ps.setInt(2, prodottoId);
	                    ps.executeUpdate();
	                }

	                conn.commit();
	                aggiornaAreaCarrello();

	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Errore durante inserimento prodotto nel carrello: " + ex.getMessage());
	            }
	        });

	
	        aggiuntaPanel.add(new JLabel("Prodotto:"));
	        aggiuntaPanel.add(prodNomeField);
	        aggiuntaPanel.add(new JLabel("Prezzo:"));
	        aggiuntaPanel.add(prodPrezzoField);
	        aggiuntaPanel.add(new JLabel());
	        aggiuntaPanel.add(aggiungiBtn);
	
	        JButton totaleBtn = new JButton("Totale Carrello");
	        totaleBtn.addActionListener(e -> areaCarrello.append("\n" + utenteLoggato.getCarrello().totaleCarrello()));
	
	        JButton indietroBtn = new JButton("Indietro");
	        indietroBtn.addActionListener(e -> layout.show(getContentPane(), "utente"));
	
	        JPanel bottomPanel = new JPanel();
	        bottomPanel.add(totaleBtn);
	        bottomPanel.add(indietroBtn);
	
	        pannelloCarrello.add(new JScrollPane(areaCarrello), BorderLayout.CENTER);
	        pannelloCarrello.add(aggiuntaPanel, BorderLayout.NORTH);
	        pannelloCarrello.add(bottomPanel, BorderLayout.SOUTH);
	    }
	    // METODO DI SERVIZIO PER AGGIORNARE IL CARRELLO
	    private void aggiornaAreaCarrello() {
	        areaCarrello.setText("");

	        try (Connection conn = DBconn.getConnection()) {
	            String query = """
	                SELECT p.nome, p.prezzo FROM carrello_prodotti cp
	                JOIN carrelli c ON cp.carrello_id = c.id
	                JOIN prodotti p ON cp.prodotto_id = p.id
	                WHERE c.utente_email = ?
	            """;

	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                ps.setString(1, utenteLoggato.getEmail());
	                ResultSet rs = ps.executeQuery();

	                double totale = 0;
	                while (rs.next()) {
	                    String nome = rs.getString("nome");
	                    double prezzo = rs.getDouble("prezzo");
	                    totale += prezzo;
	                    areaCarrello.append("- " + nome + ": €" + String.format("%.2f", prezzo) + "\n");
	                }

	                areaCarrello.append("\nTotale: €" + String.format("%.2f", totale));
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            areaCarrello.setText("Errore durante caricamento carrello.");
	        }
	    }

	    
	    

}
