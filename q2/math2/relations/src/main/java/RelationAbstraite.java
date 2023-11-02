

public abstract class RelationAbstraite implements RelationInterface {
	
	// Ex 3
	
	/**
	 * @return true si 'this' est dans 'r'
	 */
	public boolean inclusDans(RelationAbstraite r) {
		if (!this.depart().equals(r.depart()) || !this.arrivee().equals(r.arrivee())) return false;
		
		for (Couple c : this) {
			if (!r.contient(c)) return false;
		}
		return true;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof RelationAbstraite r)) return false;
		
		if (!r.depart().equals(this.depart()) || !r.arrivee().equals(this.arrivee())) {
			return false;
		}
		
		for (Couple c1 : this) {
			if (!r.contient(c1)) return false;
		}
		
		for (Couple c2 : r) {
			if (!this.contient(c2)) return false;
		}
		
		return true;
	}
	
	public int hashCode() {
		int hash = this.depart().hashCode();
		hash = hash * 31 + this.arrivee().hashCode();
		for (int i = 1; i <= MAX; i++) {
			Elt d = new Elt(i);
			if (this.depart().contient(d)) {
				for (int j = 1; j <= MAX; j++) {
					Elt a = new Elt(j);
					Couple c = new Couple(d, a);
					if (this.contient(c)) hash = hash * 31 + c.hashCode();
				}
			}
		}
		return hash;
	}
}
