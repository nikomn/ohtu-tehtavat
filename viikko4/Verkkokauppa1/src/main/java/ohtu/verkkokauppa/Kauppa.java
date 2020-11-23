package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private VarastoInterface varasto;
    private PankkiInterface pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriInterface viitegeneraattori;
    private String kaupanTili;

    @Autowired
    public Kauppa(VarastoInterface varastoIF, PankkiInterface pankkiIF
            , ViitegeneraattoriInterface viitegeneraattoriIF) {
        varasto = varastoIF;
        pankki = pankkiIF;
        viitegeneraattori = viitegeneraattoriIF;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
        ostoskori.poista(t);
    }

    public void lisaaKoriin(int id) {
//        System.out.println("Ollaanko täällä?");
//        System.out.println("varasto: " + varasto);
        
        if (varasto.saldo(id) > 0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
