package commercial;
import java.util.*;
public class Carrello {
		Map<String, Double> carrello;
		
		public Carrello() {
			carrello = new HashMap<>();
		}
		
		public void addCarrello(Prodotti prodotti) {
			carrello.put(prodotti.getNomeProdotto(), prodotti.getPrezzo());
		}
		
		public void stampaCarrello(Utenti utente) {
			System.out.println("Ciao " + utente.getFullName()+ " nel carrello hai:");
			for(Map.Entry<String, Double> prodotto : carrello.entrySet()) {
				String format = String.format("%.2f", prodotto.getValue());
				System.out.println("- "+ prodotto.getKey() +": €"+ format);
			}
			System.out.println("\n");
		}
		public void stampaSopraSoglia(double prezzo) {
			System.out.printf("\nI prodotti sopra il prezzo €%.2f sono: \n",prezzo);
			for (Map.Entry<String, Double> prodotto : carrello.entrySet()) {
	            if (prodotto.getValue() >= prezzo) {
	            		String format = String.format("%.2f", prodotto.getValue());
	                System.out.println(prodotto.getKey() + ": €" + format);
	            }
			}
		}
		
		// PREZZO MEDIO -> to -> SPESA TOTALE
		public String totaleCarrello() {
			double tot = 0;
			for (Map.Entry<String, Double> prodotto : carrello.entrySet()) {
	            tot += prodotto.getValue();
			}
			String format = String.format("%.2f", tot);
			return "\nNel tuo carrello ce un totale di\nTot: €" + format;
		}
}
