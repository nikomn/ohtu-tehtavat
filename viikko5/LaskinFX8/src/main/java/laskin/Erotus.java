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

public class Erotus extends Komento {
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
        
    @Override
    public void suorita() {
        try {
            this.sovellus.miinus(Integer.parseInt(this.syotekentta.getText()));

        } catch (Exception e) {
        }

        int laskunTulos = sovellus.tulos();

        this.syotekentta.setText("");
        this.tuloskentta.setText("" + laskunTulos);

        if (laskunTulos == 0) {
            this.nollaa.disableProperty().set(true);
        } else {
            this.nollaa.disableProperty().set(false);
        }
        this.undo.disableProperty().set(false);
    }
    
    @Override
    public void peru() {
        
    }
    
}
