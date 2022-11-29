package it.polito.oop.production;

public class BrandException extends Exception {
	private static final long serialVersionUID = 1L;
	public BrandException() {super("Brand system error");}
	public BrandException(String msg ) {super(msg);}
}
