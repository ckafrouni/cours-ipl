public class Ens2 extends EnsembleAbstrait {
	
	private Elt[] elements; // Contient les elements de l'ensemble. Il ne peut pas y avoir de doublon.
	private int cardinal;
	
	public Ens2() {
		this.elements = new Elt[EnsembleAbstrait.MAX];
		this.cardinal = 0;
	}
	
	public Ens2(EnsembleInterface a) {
		this();
		this.ajouter(a);
	}
	
	public Ens2(Elt e) {
		this();
		this.ajouter(e);
	}
	
	
	public boolean estVide() {
		return this.cardinal == 0;
	}
	
	public Elt unElement() {
		if (this.estVide()) throw new MathException();
		return this.elements[0];
	}
	
	public boolean contient(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		
		for (int i = 0; i < this.cardinal; i++) {
			if (this.elements[i].equals(e)) return true;
		}
		
		return false;
	}
	
	public void ajouter(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		
		if (!this.contient(e)) {
			this.elements[this.cardinal++] = e;
		}
	}
	
	public void enlever(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		
		for (int i = 0; i < this.cardinal; i++) {
			if (this.elements[i].equals(e)) {
				this.elements[i] = this.elements[this.cardinal - 1];
				this.elements[cardinal - 1] = null;
				this.cardinal--;
			}
		}
	}
	
	public int cardinal() {
		return this.cardinal;
	}
	
	public void complementer() {
		boolean[] tabB = new boolean[EnsembleAbstrait.MAX + 1];
		
		for (int i = 0; i < this.cardinal; i++) {
			tabB[this.elements[i].val()] = true;
		}
		
		this.elements = new Elt[EnsembleAbstrait.MAX + 1];
		this.cardinal = 0;
		
		for (int i = 1; i <= EnsembleAbstrait.MAX; i++) {
			if (!tabB[i]) {
				this.elements[this.cardinal++] = new Elt(i);
			}
		}
	}
	
	public String toString() {
		//TODO
		return null;
	}
	
}
