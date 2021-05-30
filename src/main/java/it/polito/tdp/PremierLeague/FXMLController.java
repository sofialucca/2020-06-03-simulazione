/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.Player;
import it.polito.tdp.PremierLeague.model.PlayerNumero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML // fx:id="btnTopPlayer"
    private Button btnTopPlayer; // Value injected by FXMLLoader

    @FXML // fx:id="btnDreamTeam"
    private Button btnDreamTeam; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="txtGoals"
    private TextField txtGoals; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	
    	if(!isValid()) {
    		return;
    	}
    	
    	double x = Double.parseDouble(this.txtGoals.getText());
    	model.creaGrafo(x);
    	
    	txtResult.appendText("GRAFO CREATO:\n");
    	txtResult.appendText("# VERTICI: " + model.getVertexSize());
    	txtResult.appendText("\n# ARCHI: " + model.getEdgeSize());
    	
    	this.btnTopPlayer.setDisable(false);
    	this.txtK.setDisable(false);
    	this.btnDreamTeam.setDisable(false);
    }

    private boolean isValid() {
		String input = this.txtGoals.getText();
		boolean check = true;
		if(input.equals("")) {
			txtResult.appendText("ERRORE: inserire un valore nel campo x\n");
			check = false;
		}else {
			try {
				Double.parseDouble(input);
			}catch(NumberFormatException nfe) {
				txtResult.appendText("ERRORE: inserire un numero decimale o intero per x\n");
				check = false;
			}
		}
		if(!this.btnDreamTeam.isDisabled()) {
			input = this.txtK.getText();
			if(input.equals("")) {
				txtResult.appendText("ERRORE: inserire un valore nel campo x\n");
				check = false;
			}else {
				try {
					Integer.parseInt(input);
					
				}catch(NumberFormatException nfe) {
					txtResult.appendText("ERRORE: inserire un numero intero per k");
					check = false;
				}
			}
		}
		return check;
	}

	@FXML
    void doDreamTeam(ActionEvent event) {

		txtResult.clear();
		
		if(!isValid()) {
			return;
		}
		
		int k = Integer.parseInt(txtK.getText());
		List<Player> dreamTeam = model.getDreamTeam(k);
		
		txtResult.appendText("DREAMTEAM DI " + k + " GIOCATORI");
		txtResult.appendText("\n\nGRADO TITOLARITA' = " + model.getGradoTitolarita());
		txtResult.appendText("\n\nGIOCATORI:");
		
		for(Player p : dreamTeam) {
			txtResult.appendText("\n" + p.toString());
		}
    }

    @FXML
    void doTopPlayer(ActionEvent event) {

    	txtResult.clear();
    	Player bestP = model.getBestPlayer();
    	txtResult.appendText("TOP PLAYER: " + bestP.toString() + "\n\n");
    	txtResult.appendText("AVVERSARI BATTUTI:");
    	for (PlayerNumero pn : model.getListaBattuti(bestP)) {
    		txtResult.appendText("\n" + pn.toString());
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTopPlayer != null : "fx:id=\"btnTopPlayer\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGoals != null : "fx:id=\"txtGoals\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
