package ohtu.intjoukkosovellus;

public class IntJoukko {

    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this.lukujono = alustaTaulu(5);
        alkioidenLkm = 0;
        this.kasvatuskoko = 5;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        this.lukujono = alustaTaulu(kapasiteetti);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = 5;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {

        this.lukujono = alustaTaulu(kapasiteetti);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    private int[] alustaTaulu(int kapasiteetti) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetin on oltava nollaa suurempi luku.");
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kasvatuskoon on oltava nollaa suurempi luku.");
        }
        int[] taulu = new int[kapasiteetti];
        for (int i = 0; i < kapasiteetti; i++) {
            taulu[i] = 0;
        }
        return taulu;
    }

    public boolean lisaa(int luku) {

        if (this.alkioidenLkm == 0) {
            this.lukujono[0] = luku;
            this.alkioidenLkm++;
            return true;
        } else if (!onkoJoLukujonossa(luku)) {
            this.lukujono[alkioidenLkm] = luku;
            this.alkioidenLkm++;
            if (this.alkioidenLkm >= this.lukujono.length) {
                kopioiTaulukko();
            }
            return true;
        }

        return false;
    }

    public boolean onkoJoLukujonossa(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }

        return false;

    }

    public int etsiIndeksi(int luku) {
        for (int i = 0; i < this.alkioidenLkm; i++) {
            if (luku == this.lukujono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int kohta = etsiIndeksi(luku);

        if (kohta != -1) {
            int apu = -1;
            this.lukujono[kohta] = 0;
            for (int j = kohta; j < this.alkioidenLkm - 1; j++) {
                apu = this.lukujono[j];
                this.lukujono[j] = this.lukujono[j + 1];
                this.lukujono[j + 1] = apu;
            }
            this.alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko() {
        int[] taulukkoOld = this.lukujono;
        this.lukujono = new int[this.alkioidenLkm + this.kasvatuskoko];

        for (int i = 0; i < taulukkoOld.length; i++) {
            this.lukujono[i] = taulukkoOld[i];
        }

    }

    public int mahtavuus() {
        return this.alkioidenLkm;
    }

    @Override
    public String toString() {
        switch (this.alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + this.lukujono[0] + "}";
            default:
                String tuotos = "{";
                for (int i = 0; i < this.alkioidenLkm - 1; i++) {
                    tuotos += this.lukujono[i];
                    tuotos += ", ";
                }
                tuotos += this.lukujono[alkioidenLkm - 1];
                tuotos += "}";
                return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[this.alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = this.lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
