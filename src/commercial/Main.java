package commercial;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		// DEFAULT DI ISTANZA INTERFACCIA UTENTI PER FARE IL TEST
		InterfacciaUtentiGUI interfaccia = new InterfacciaUtentiGUI();
		
		Scanner scanner = new Scanner(System.in);
		ArrayList<Utenti> utentiRegistrati =new ArrayList<>();
		// METTERE INSIEME LIST, MAP, ITERAZIONE, GENERICE.
		/* STRUTTURA: 
		 * HASHMAP<STRING, DOUBLE> DOVE -> CHIAVE = NOME PRODOTTO, VALORE = PREZZO
		 * STAMPARE TUTTI I PRODOTTI
		 * STAMPARE I PRODOTTI CON PREZZO SOPRA UNA CERTA SOGLIA
		 * CALCOLARE IL PREZZO MEDIO*/
		
		/* C	REARE UNA SEZIONE UTENTI CON ATTIBUTI:
		 * CAMPI : [ ID, NOME, CONGNOME, EMAIL, INIDIRIZZO ] 
		 * FARE IL PER UTENTE LOGIN/LOGOUT
		 * CON METODI ORDINI ATTIVI (CIOE' CARRELLO), 
		 * E  DOVE INSERIRE TUTTI I CAMPI FILE.TEXT export on SQL */
		
		//UTENTE DI DEAFULT
		Utenti utente = new Utenti("Paperino Rossi", "paperino@gmail.com", "Via Papero, n5");
		utente.addProdottiCarrello(new Prodotti("Laptop", 1220.99), new Prodotti("Mouse", 25.50));
		utentiRegistrati.add(utente);
		
		boolean statoMenu = true;
		System.out.println("Benvenuto nell\'area Utenti\n[1] Log-in\t[2] Registrati");
		int scelta = scanner.nextInt();
		scanner.nextLine();
		
		outloop:
		do {
			//LOG-IN
			if(scelta == 1) {
				System.out.print("Inserisci E-Mail: ");
				String email = scanner.nextLine();
				for(Utenti utenteRegistrato : utentiRegistrati) {
					if(utenteRegistrato.getEmail().equals(email)) {
						System.out.println("Benventuo "+ utenteRegistrato.getFullName());
						
						// INTERFACCIA MENU
						while(statoMenu) {
							System.out.println("[1] > Visualizza Dati\t Modifica Dati < [2]\n[3] > Carrello\t Log-Out < [4]");
							scelta = scanner.nextInt();
							scanner.nextLine();
							switch(scelta) {
							//VISUALIZZI I DATI
							case 1:
							System.out.print("\n**********************\n");
							System.out.print(utenteRegistrato);
							System.out.print("**********************\n\n");
								break;
							// MODIFICA DELLE CREDENZIALI
							case 2:
								System.out.println("\nModifica:\n[1] > Full Name \t E-Mail < [2]\n[3] > Indirizzo\t Torna Indietro < [4]\n");
								scelta = scanner.nextInt();
								scanner.nextLine();
								//SWITCH MODIFICA CREDENZIALI
									switch(scelta) {
									// MODIFICA FULL NAME
									case 1:
										System.out.print("Inserisci Full Name -> ");
										String newFullName = scanner.nextLine();
										utenteRegistrato.setFullName(newFullName);
										break;
									// MODIFICA E-MAIL	
									case 2:
										System.out.print("Inserisci E-Mail -> ");
										String newEmail = scanner.nextLine();
										utenteRegistrato.setEmail(newEmail);
										break;
									// MODIFICA INDIRIZZO
									case 3:
										System.out.print("Inserisci Indirizzo -> ");
										String newIndirizzo = scanner.nextLine();
										utenteRegistrato.setEmail(newIndirizzo);
										break;
									default:
										System.out.println("...");
									}
								break;
							// CARRELLO
							case 3:
								System.out.println("[1] > Aggiungi Prodotto in Carrello\\i\tVisualizza Carrello < [2]\n[3] > Totale Carrello\t Torna indietro <[4]");
								scelta = scanner.nextInt();
								scanner.nextLine();
									switch(scelta) {
									// AGGIUNTA DI PRODOTTO/I
									case 1:
										// SI POSSONO INSERIRE DA UNO A PIU ISTANZE PRODOTTI POICHE E UN VAR-ARGS METHOD
										System.out.print("Inserisci numero di prodotti da inserire: ");
										int iterazioni = scanner.nextInt();
										scanner.nextLine();
										for(int i = 0; i < iterazioni; i++) {
											System.out.print("Inserisci nome prodotto: ");
											String prodotto = scanner.nextLine();
											System.out.print("Inserisci Prezzo: ");
											double prezzo = scanner.nextInt();
											scanner.nextLine();
											utenteRegistrato.addProdottiCarrello(new Prodotti(prodotto, prezzo));
										}
										break;
									// VISUALIZZA CARRELLO
									case 2:
										utenteRegistrato.getCarrello().stampaCarrello(utenteRegistrato);
										break;
									// TOTALE CARRELLO
									case 3:
										System.out.println(utenteRegistrato.getCarrello().totaleCarrello());
										;
										break;
									default:
										System.out.println("...");
									}
								break;
							// LOG-OUT
							case 4:
								System.out.println("Grazie "+utenteRegistrato.getFullName()+", alla prossima!!!\n...Log-Out...");
								break outloop;
							default:
								System.out.println("Opzione non valida");
							}
						 // CHIUSURA WHILE (STATOMENU)
						}
					}
				}
			
			
			// REGISTRAZIONE
			}else {
				System.out.print("Inserisci full Name: ");
				String fullname = scanner.nextLine();
				System.out.print("Inserisci E-Mail: ");
				String email = scanner.nextLine();
				System.out.print("Inserisci Indirizzo: ");
				String indirizzo = scanner.nextLine();
				Utenti user = new Utenti(fullname, email, indirizzo);
				utentiRegistrati.add(user);
				System.out.printf("************************\nBenvenuto\\a %s\n",user.getFullName());
				break;
			}
		}while(statoMenu);
		
		scanner.close();
	}

}
