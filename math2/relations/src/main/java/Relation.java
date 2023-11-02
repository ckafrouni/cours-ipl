/**
 * Classe Relation héritant de RelationDeBase
 * Fournit des outils de manipulation des relations entre sous-ensembles de l'Univers
 */

public class Relation extends RelationDeBase {
	
	/**
	 * Valeur numérique de MAXELT
	 */
	private static final int MAX = Elt.MAXELT.val();
	
	/**
	 * Construit la Relation vide sur l'ensemble vide
	 */
	public Relation() {
		super();
	}
	
	/**
	 * Construit la Relation vide de d vers a
	 *
	 * @param d L'ensemble de départ
	 * @param a L'ensemble d'arrivée
	 * @throws IllegalArgumentException si d ou a est null
	 */
	public Relation(EnsembleAbstrait d, EnsembleAbstrait a) {
		super(d, a);
	}
	
	/**
	 * @return clone de la relation courante
	 */
	public Relation clone() {
		return (Relation) super.clone();
	}
	
	//Ex1
	
	/**
	 * @return Le domaine de la relation courante
	 */
	public EnsembleAbstrait domaine() {
		Ensemble e = new Ensemble();
		
		for (Couple c : this) {
			e.ajouter(c.getX());
		}
		
		return e;
	}
	
	/**
	 * @return L'image de la relation courante
	 */
	public EnsembleAbstrait image() {
		Ensemble e = new Ensemble();
		
		for (Couple c : this) {
			e.ajouter(c.getY());
		}
		
		return e;
	}
	
	// EX 2
	
	/**
	 * @return La relation complémentaire à la relation courante
	 */
	public Relation complementaire() {
		Relation r = new Relation(this.depart(), this.arrivee());
		
		for (Elt x : this.depart()) {
			for (Elt y : this.arrivee()) {
				if (!this.contient(x, y)) {
					r.ajouter(x, y);
				}
			}
		}
		
		return r;
	}
	
	/**
	 * @return La relation réciproque
	 */
	public Relation reciproque() {
		Relation r = new Relation(this.arrivee(), this.depart());
		
		for (Couple c : this) {
			r.ajouter(c.reciproque());
		}
		
		return r;
	}
	
	/**
	 * Remplace la relation courante par son union avec r
	 *
	 * @param r La relation avec laquelle unir
	 * @throws IllegalArgumentException si r est null ou si les ensembles de départ ou d'arrivée ne sont pas égaux
	 */
	public void ajouter(RelationInterface r) {
		if (r == null || !this.depart().equals(r.depart()) || !this.arrivee().equals(r.arrivee())) {
			throw new IllegalArgumentException();
		}
		
		for (Couple c : r) {
			this.ajouter(c);
		}
	}
	
	/**
	 * Remplace this par sa différence avec r
	 *
	 * @param r La relation avec laquelle soustraire
	 * @throws IllegalArgumentException si r est null ou si les ensembles de départ ou d'arrivée ne sont pas égaux
	 */
	public void enlever(RelationInterface r) {
		if (r == null || !this.depart().equals(r.depart()) || !this.arrivee().equals(r.arrivee())) {
			throw new IllegalArgumentException();
		}
		
		for (Couple c : r) {
			// Inutile de vérifier si 'this' contient 'c', puisque enlever ne fait rien si 'c' n'est pas présent
			this.enlever(c);
		}
	}
	
	/**
	 * Remplace la relation courante par son intersection avec 'r'
	 *
	 * @param r La relation avec laquelle intersecter
	 * @throws IllegalArgumentException si r est null ou si les ensembles de départ ou d'arrivée ne sont pas égaux
	 */
	public void intersecter(RelationInterface r) {
		if (r == null || !this.depart().equals(r.depart()) || !this.arrivee().equals(r.arrivee())) {
			throw new IllegalArgumentException();
		}
		
		if (this.equals(r)) return;
		
		for (Couple c : this.clone()) {
			if (!r.contient(c)) {
				this.enlever(c);
			}
		}
	}
	
	/**
	 * @param r Une relation
	 * @return La relation composée de 'this' après 'r'
	 * @throws IllegalArgumentException si r est null ou si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public Relation apres(RelationInterface r) {
		if (r == null || !this.depart().equals(r.arrivee())) {
			throw new IllegalArgumentException();
		}
		
		Relation res = new Relation(r.depart(), this.arrivee());
		
		for (Couple c1 : r) {
			for (Couple c2 : this) {
				if (c1.getY().equals(c2.getX())) {
					res.ajouter(c1.getX(), c2.getY());
				}
			}
		}
		
		return res;
	}
	
	
	/*
	 * Les exercices 4 et 5 ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	
	/* Ex 4 */
	
	/**
	 * Clôture la Relation courante pour la réflexivité
	 *
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public void cloReflex() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Elt x : this.depart()) {
			this.ajouter(x, x);
		}
	}
	
	/**
	 * Clôture la Relation courante pour la symétrie
	 *
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public void cloSym() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Couple c : this.clone()) {
			this.ajouter(c.getY(), c.getX());
		}
	}
	
	/**
	 * Clôture la Relation courante pour la transitivité (Warshall)
	 *
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public void cloTrans() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Elt e_int : this.depart()) {
			for (Couple c_avant : this.clone()) {
				if (c_avant.getY().equals(e_int)) {
					for (Couple c_apres : this.clone()) {
						if (c_apres.getX().equals(e_int)) {
							this.ajouter(c_avant.getX(), c_apres.getY());
						}
					}
				}
			}
		}
	}
	
	
	//Ex 5
	/*
	 * Les questions qui suivent ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	
	/**
	 * @return true si la relation est réflexive
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public boolean reflexive() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Elt elt : this.depart()) {
			if (!this.contient(elt, elt)) return false;
		}
		
		return true;
	}
	
	/**
	 * @return true si la relation est antiréflexive
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public boolean antireflexive() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Elt elt : this.depart()) {
			if (this.contient(elt, elt)) return false;
		}
		
		return true;
	}
	
	/**
	 * @return true si la relation est symétrique
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public boolean symetrique() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Couple c : this) {
			if (!this.contient(c.getY(), c.getX())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @return true si la relation est antisymétrique
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public boolean antisymetrique() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Couple c : this) {
			if (this.contient(c.getY(), c.getX()) && !c.getX().equals(c.getY())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @return true si la relation est transitive
	 * @throws MathException si les ensembles de départ et d'arrivée ne sont pas égaux
	 */
	public boolean transitive() {
		if (!this.depart().equals(this.arrivee())) {
			throw new MathException();
		}
		
		for (Couple c1 : this) {
			for (Couple c2 : this) {
				if (c1.getY().equals(c2.getX()) && !this.contient(c1.getX(), c2.getY())) {
					return false;
				}
			}
		}
		return true;
	}
	
	// Ex 6
	
	/**
	 * Construit une copie de la relation en paramètre
	 *
	 * @param r La relation à copier
	 * @throws IllegalArgumentException param. invalide
	 */
	public Relation(RelationInterface r) {
		if (r == null) {
			throw new IllegalArgumentException();
		}
		
		for (Elt e_dep : r.depart()) {
			this.ajouterDepart(e_dep);
		}
		
		for (Elt e_arr : r.arrivee()) {
			this.ajouterArrivee(e_arr);
		}
		
		for (Couple c : r) {
			this.ajouter(c.getX(), c.getY());
		}
	}
	
	/**
	 * @return l'identité sur e
	 * @throws IllegalArgumentException param. invalide
	 */
	public static Relation identite(EnsembleAbstrait e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		
		Relation r = new Relation(e, e);
		r.cloReflex();
		return r;
	}
	
	/**
	 * @return le produit cartésien de a et b (A x B)
	 * @throws IllegalArgumentException param. invalide
	 */
	public static Relation produitCartesien(EnsembleAbstrait a, EnsembleAbstrait b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException();
		}
		
		Relation r = new Relation(a, b);
		for (Elt e_dep : a) {
			for (Elt e_arr : b) {
				r.ajouter(e_dep, e_arr);
			}
		}
		return r;
	}
	
} // class Relation
