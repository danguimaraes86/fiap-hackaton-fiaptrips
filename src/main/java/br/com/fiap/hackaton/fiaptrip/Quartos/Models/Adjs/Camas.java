package br.com.fiap.hackaton.fiaptrip.Quartos.Models.Adjs;

public enum Camas {

    CAMAQUEEN(2),
    CAMAKING(2),
    CAMASOLTEIRO(1),
    BELICHE(2),
    TRILICHE(3);

    private int lugares;
    Camas(int lugares) {
        this.lugares = lugares;
    }
    public int getLugares() {
        return lugares;
    }
}
