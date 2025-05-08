package commercial;

public class Utenti {
		private static int count = 1;
		@SuppressWarnings("unused")
		private boolean logInOut;
		@SuppressWarnings("unused")
		private int id;
		private String fullName;
		private String email;
		private String indirizzo;
		private Carrello carrello;
		
		public Utenti(String fullName, String email, String indirizzo) {
			this.logInOut = true;
			this.id = count;
			this.fullName = fullName;
			this.email = email;
			this.indirizzo = indirizzo;
			this.carrello = new Carrello();
			count++;
		}
		// VAR-ARGS DI PRODOTTO NEL CASO SI VOGLIANO METTERE PIU DI UN PRODOTTO NEL CARRELLO UTENTE
		public void addProdottiCarrello(Prodotti... prodotti) {
			for(Prodotti prodotto : prodotti) {
				carrello.addCarrello(prodotto);
			}
		}
		
		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

		public void setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
		}

		public Carrello getCarrello() {
			return carrello;
		}
		
		// I METODI PER IL CECK DEL LOG IN OR OUT
		public boolean isLogInOut() {
			return logInOut;
		}
		public void setLogInOut(boolean logInOut) {
			this.logInOut = logInOut;
		}
		
		
		@Override
		public String toString() {
			String format = String.format("Ciao [%s]\nle tue credenziali utente sono:\n[Email: %s]\n[Address: %s]\n", this.getFullName(),this.getEmail(),this.getIndirizzo());
			return format;
		}
}
