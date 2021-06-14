package it.polito.tdp.yelp.model;

public class LocaleMigliore {
Business b1;
Integer peso;
public LocaleMigliore(Business b1, Integer peso) {
	super();
	this.b1 = b1;
	this.peso = peso;
}
public Business getB1() {
	return b1;
}
public void setB1(Business b1) {
	this.b1 = b1;
}
public Integer getPeso() {
	return peso;
}
public void setPeso(Integer peso) {
	this.peso = peso;
}


}
