package commercial;

public class Prodotti {
		private String nomeProdotto;
		private double prezzo;
		
		public Prodotti(String nomeProdotto, double prezzo) {
			this.nomeProdotto = nomeProdotto;
			this.prezzo = prezzo;
			
			//commerce.addCarrello(this);
		}
		
		/*public void addCarrello(Carrello carrello) {
			carrello.addCarrello(this);
		}*/

		public String getNomeProdotto() {
			return nomeProdotto;
		}

		public void setNomeProdotto(String nomeProdotto) {
			this.nomeProdotto = nomeProdotto;
		}

		public double getPrezzo() {
			return prezzo;
		}

		public void setPrezzo(double prezzo) {
			this.prezzo = prezzo;
		}
		
}
