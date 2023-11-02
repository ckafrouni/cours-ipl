public class Ens1 extends EnsembleAbstrait {
	
	private boolean[] tabB; // e appartient � l'ensemble courant ssi tabE[e.val()] est � true.
	private int cardinal;
	
	public Ens1() {
		this.tabB = new boolean[EnsembleAbstrait.MAX + 1];
		this.cardinal = 0;
	}
	
	public Ens1(EnsembleInterface a) {
		this();
		this.ajouter(a);
	}
	
	public Ens1(Elt e) {
		this();
		this.ajouter(e);
	}
	
	public boolean estVide() {
		return this.cardinal == 0;
	}
	
	public Elt unElement() {
		if (this.estVide()) throw new MathException();
		for (int i = 1; i <= EnsembleAbstrait.MAX; i++) {
			if (this.tabB[i]) return new Elt(i);
		}
		return null;
	}
	
	public boolean contient(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		return this.tabB[e.val()];
	}
	
	public void ajouter(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		if (!this.contient(e)) {
			this.tabB[e.val()] = true;
			this.cardinal++;
		}
		
	}
	
	public void enlever(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		if (this.contient(e)) {
			this.tabB[e.val()] = false;
			this.cardinal--;
		}
		
	}
	
	public int cardinal() {
		return this.cardinal;
	}
	
	public void complementer() {
		for (int i = 1; i <= EnsembleAbstrait.MAX; i++) {
			this.tabB[i] = !this.tabB[i];
		}
		this.cardinal = EnsembleAbstrait.MAX - this.cardinal;
	}
	
	public String toString() {
		String ens = "";
		for (int i = 1; i <= EnsembleAbstrait.MAX; i++) {
			if (this.tabB[i]) {
				ens += String.format("%d,", i);
			}
		}
		return String.format("{%s}", ens);
	}
	
}
