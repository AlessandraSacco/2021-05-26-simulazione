package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	SimpleDirectedWeightedGraph<Business,DefaultWeightedEdge> grafo;
	YelpDao dao;
	Map<String,Business> idMap;
	LocaleMigliore l=null;

	
	public Model() {
		dao= new YelpDao();
		idMap= new HashMap<String,Business>();
		dao.getAllBusiness(idMap);
	}
	
	public List<String> getAllCity(){
		return dao.getCity();
	}
	
	public void creaGrafo(String citta, Integer anno) {
	grafo= new SimpleDirectedWeightedGraph<Business,DefaultWeightedEdge>(DefaultWeightedEdge.class);	
	
	// creare i vertici del grafo
	Graphs.addAllVertices(this.grafo, dao.getAllVertici(idMap, citta,anno));
	
	// creare i vertici del grafo 
	for(Adiacenza a: dao.getAdiacenza(idMap,citta,anno)) {
		if(a.getPeso()<0) {
		if(grafo.containsVertex(a.getB1()) && grafo.containsVertex(a.getB2())) {
		Graphs.addEdgeWithVertices(this.grafo, a.getB1(), a.getB2(),((double)-1)*a.getPeso());	
		}
		}
		else if(a.getPeso()>0) {
		if(grafo.containsVertex(a.getB1()) && grafo.containsVertex(a.getB2())) {
		Graphs.addEdgeWithVertices(this.grafo, a.getB2(), a.getB1(),a.getPeso());	
		}
		}
	}
	}
	
	public int getVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Graph<Business,DefaultWeightedEdge> getGrafo(){
		return grafo;
	}
	
	public void setLocaleMigliore() {
		if(grafo==null) {
			return ;
		}
		
		
		Business best=null;
		int somma=0;
		int sommaMax = 0;
	
		
		for(Business b: this.grafo.vertexSet()) {
			int tmp1=0;
			for(DefaultWeightedEdge d: this.grafo.incomingEdgesOf(b)) {
		    tmp1+=grafo.getEdgeWeight(d);
		}
	    
			int tmp2=0;
		for(DefaultWeightedEdge d: this.grafo.outgoingEdgesOf(b)) {
		    tmp2+=grafo.getEdgeWeight(d);
		}
		
		
		 somma= tmp1-tmp2;
		 
		
		if(somma>sommaMax) {
		 sommaMax=somma;
		 best=b;
		}
		
		
		}
		
		
		
		 l = new LocaleMigliore(best,sommaMax);
		
	}
	
	public LocaleMigliore getLocaleMigliore() {
		return l;
	}
}
