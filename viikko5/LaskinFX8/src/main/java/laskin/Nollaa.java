/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    private String edellinenTulos;
    private String edellinenSyote;
    
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.edellinenSyote = this.syotekentta.getText();
        this.edellinenTulos = this.tuloskentta.getText();
    }
    
    @Override
    public void suorita() {
        this.edellinenSyote = this.syotekentta.getText();
        this.edellinenTulos = this.tuloskentta.getText();
        sovellus.nollaa();
        this.syotekentta.setText("");
        this.tuloskentta.setText("" + 0);
        this.nollaa.disableProperty().set(true);
        this.undo.disableProperty().set(false);
    }
    
    @Override
    public void peru() {
        sovellus.nollaa();
        try {
            this.sovellus.plus(Integer.parseInt(this.edellinenTulos));

        } catch (Exception e) {
        }
        this.syotekentta.setText(this.edellinenSyote);
        this.tuloskentta.setText(this.edellinenTulos);
        
    }
    
}
