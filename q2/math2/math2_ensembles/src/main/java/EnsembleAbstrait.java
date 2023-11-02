public abstract class EnsembleAbstrait implements EnsembleInterface {
	
	// renvoie true ssi this est inclus dans <a>
	// lance une IllegalArgumentException en cas de param�tre invalide
	public boolean inclusDans(EnsembleAbstrait a) {
		if (a == null) throw new IllegalArgumentException();
		
		if (this.estVide()) return true;
		
		for (int i = 1; i <= EnsembleAbstrait.MAX; i++) {
			if (this.contient(new Elt(i)) && !a.contient(new Elt(i))) return false;
		}
		
		return true;
	}
	
	// renvoie true ssi this est �gal � a o
	public final boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof EnsembleAbstrait other)) return false;
		
		if (this.estVide() && other.estVide()) return true;
		if (this.cardinal() != other.cardinal()) return false;
		
		for (int i = 1; i <= EnsembleAbstrait.MAX; i++) {
			if ((this.contient(new Elt(i)) && !other.contient(new Elt(i)))
					|| (!this.contient(new Elt(i)) && other.contient(new Elt(i)))) return false;
		}
		
		return true;
	}
	
	@Override
	public final int hashCode() {
		int result = 1;
		int prime = 31;
		for (int i = 1; i <= MAX; i++) {
			Elt ei = new Elt(i);
			if (this.contient(ei))
				result = result * prime + ei.hashCode();
		}
		return result;
	}
	
}
