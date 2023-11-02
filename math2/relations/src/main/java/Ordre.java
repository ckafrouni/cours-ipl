import java.util.Iterator;

public class Ordre extends RelationAbstraite {
	
	private Relation couples;
	
	/**
	 * Construit l'ordre sur l'ensemble e
	 *
	 * @param e L'ensemble sur lequel construire l'ordre
	 * @throws IllegalArgumentException param. invalide
	 */
	public Ordre(EnsembleAbstrait e) {
		//TODO Ordre(EnsembleAbstrait)
	}
	
	/**
	 * Construit le plus petit ordre contenant r
	 *
	 * @param r La relation à contenir
	 * @throws IllegalArgumentException si r est null ou si r n'est pas une relation d'ordre
	 */
	public Ordre(Relation r) {
		//TODO Ordre(Relation)
	}
	
	/**
	 * Construit un ordre à partir d'un autre ordre
	 *
	 * @param or L'ordre à copier
	 * @throws IllegalArgumentException si 'or' est null
	 */
	public Ordre(Ordre or) {
		//TODO Ordre(Ordre)
	}
	
	/**
	 * Ajoute un élément à l'ensemble sous-jacent de l'ordre
	 * Ne fait rien si l'élément est déjà dans l'ensemble sous-jacent
	 *
	 * @param x L'élément à ajouter
	 * @throws IllegalArgumentException si x est null
	 */
	public void ajouterAuSousJacent(Elt x) {
		//TODO ajouterAuSousJacent(Elt)
	}
	
	/**
	 * Enlève un élément de l'ensemble sous-jacent de l'ordre
	 * Ne fait rien si l'élément n'est pas dans l'ensemble sous-jacent
	 * Enlève également toutes les flêches liées à l'élément
	 *
	 * @param x L'élément à enlever
	 * @throws IllegalArgumentException si x est null
	 */
	public void enleverDuSousJacent(Elt x) {
		//TODO enleverDuSousJacent(Elt)
	}
	
	@Override
	public Iterator<Couple> iterator() {
		return couples.iterator();
	}
	
	@Override
	public boolean estVide() {
		return couples.estVide();
	}
	
	@Override
	public boolean contient(Couple c) {
		if (c == null) throw new IllegalArgumentException();
		if (!couples.depart().contient(c.getX()) || !couples.arrivee().contient(c.getY()))
			throw new IllegalArgumentException();
		return couples.contient(c.getX(), c.getY());
	}
	
	/**
	 * Crée le plus petit ordre contenant this et {c}
	 *
	 * @param c Le couple à ajouter
	 * @throws IllegalArgumentException si {c} est null ou si c n'est pas une arête de Hasse
	 */
	@Override
	public void ajouter(Couple c) {
		//TODO ajouter(Couple)
	}
	
	/**
	 * Enlève (si possible) l'arête de Hasse c de la relation d'ordre
	 *
	 * @param c Le couple à enlever
	 * @throws IllegalArgumentException si {@code c} est null ou si c n'est pas une arête de Hasse
	 */
	@Override
	public void enlever(Couple c) {
		if (c == null) throw new IllegalArgumentException();
		if (!this.depart().contient(c.getX())) throw new IllegalArgumentException();
		if (!this.depart().contient(c.getY())) throw new IllegalArgumentException();
		if (!this.contient(new Couple(c.getX(), c.getY()))) return;
		if (!estUneAreteDeHasse(c.getX(), c.getY())) throw new IllegalArgumentException();
		Ensemble plusPttX = this.plusPetitQue(c.getX());
		Ensemble plusGrdY = this.plusGrandQue(c.getY());
		for (Elt eX : plusPttX) {
			for (Elt eY : plusGrdY) {
				this.couples.enlever(eX, eY);
			}
		}
		this.couples.cloTrans();
	}
	
	private Ensemble plusPetitQue(Elt e) {
		Ensemble min = new Ensemble();
		for (Elt eC : couples.depart()) {
			if (couples.contient(eC, e)) min.ajouter(eC);
		}
		return min;
	}
	
	private Ensemble plusGrandQue(Elt e) {
		Ensemble maj = new Ensemble();
		for (Elt eC : couples.depart()) {
			if (couples.contient(e, eC)) maj.ajouter(eC);
		}
		return maj;
	}
	
	private boolean estUneAreteDeHasse(Elt x, Elt y) {
		if (!this.contient(new Couple(x, y))) return false;
		if (x.equals(y)) return false;
		EnsembleAbstrait aParcourir = this.depart();
		aParcourir.enlever(x);
		aParcourir.enlever(y);
		for (Elt e : aParcourir) {
			if (this.contient(new Couple(x, e)) && this.contient(new Couple(e, y))) return false;
		}
		return true;
	}
	
	public boolean estUneAreteDeHasse(Couple c) {
		if (c == null) throw new IllegalArgumentException();
		return estUneAreteDeHasse(c.getX(), c.getY());
	}
	
	@Override
	public EnsembleAbstrait depart() {
		return couples.depart();
	}
	
	@Override
	public EnsembleAbstrait arrivee() {
		return couples.arrivee();
	}
	
	//renvoie true ssi x et y sont comparables pour l'ordre courant
	//lance une IllegalArgumentException en cas de paramètre invalide
	public boolean comparables(Elt x, Elt y) {
		//TODO comparables(Elt, Elt)
		return false;
	}
	
	// Renvoie l'ensemble des éléments minimaux de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait minimaux(EnsembleAbstrait b) {
		//TODO minimaux(EnsembleAbstrait)
		return null;
	}
	
	// Renvoie l'ensemble des éléments maximaux de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait maximaux(EnsembleAbstrait b) {
		//TODO maximaux(EnsembleAbstrait)
		return null;
	}
	
	// Renvoie le minimum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt minimum(EnsembleAbstrait b) {
		//TODO minimum(EnsembleAbstrait)
		return null;
	}
	
	// Renvoie le maximum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt maximum(EnsembleAbstrait b) {
		//TODO maximum(EnsembleAbstrait)
		return null;
	}
	
	// Renvoie l'ensemble des minorants de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait minor(EnsembleAbstrait b) {
		//TODO minor(EnsembleAbstrait)
		return null;
	}
	
	// Renvoie l'ensemble des majorants de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait major(EnsembleAbstrait b) {
		//TODO major(EnsembleAbstrait)
		return null;
	}
	
	// Renvoie l'infimum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt infimum(EnsembleAbstrait b) {
		//TODO infimum(EnsembleAbstrait)
		return null;
	}
	
	// Renvoie le supremum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt supremum(EnsembleAbstrait b) {
		//TODO supremum(EnsembleAbstrait)
		return null;
	}
	
	//Renvoie true ssi this est un treillis
	//lance une IllegalArgumentException en cas de paramètre invalide
	public boolean treillis() {
		//TODO treillis()
		return false;
	}
	
	public String toString() {
		return couples.toString();
	}
	
}
