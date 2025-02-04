/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.yelp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.yelp.model.LocaleMigliore;
import it.polito.tdp.yelp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnLocaleMigliore"
    private Button btnLocaleMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCitta"
    private ComboBox<String> cmbCitta; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML // fx:id="cmbAnno"
    private ComboBox<Integer> cmbAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLocale"
    private ComboBox<?> cmbLocale; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	String citta= cmbCitta.getValue();
    	Integer anno= cmbAnno.getValue();
    	
    	if(citta==null || anno==null) {
    		txtResult.appendText("Riempire entrambi i campi: citta e anno");
    	}
    	
    	this.model.creaGrafo(citta, anno);
    	txtResult.appendText("GRAFO CREATO: \n");
    	txtResult.appendText("#VERTICI: "+this.model.getVertici()+"\n");
    	txtResult.appendText("#ARCHI: "+this.model.getArchi()+"\n");
    	

    }

    @FXML
    void doLocaleMigliore(ActionEvent event) {
    	txtResult.clear();
    	if(this.model.getGrafo()==null) {
    		txtResult.appendText("Riempi i campi dell'anno e della citta e crea il grafo ");
    	}
    	
    	this.model.setLocaleMigliore();
    	LocaleMigliore l = this.model.getLocaleMigliore();
    	txtResult.appendText("Il locale migliore è: "+l.getB1().getBusinessName());
        
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnLocaleMigliore != null : "fx:id=\"btnLocaleMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCitta != null : "fx:id=\"cmbCitta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLocale != null : "fx:id=\"cmbLocale\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	List<Integer> anno= new ArrayList<Integer>();
    	
    	for(int i=2005; i<2014; i++) {
    		anno.add(i);
    	}
    	
    	cmbAnno.getItems().addAll(anno);
    	cmbCitta.getItems().addAll(this.model.getAllCity());
    }
}
