package com.abeltran10.lista_compra.num;

public enum Tipo {
    ALIMENTACION("Alimentacion"),
    HIGIENE("Higiene"),
    LIMPIEZA("Limpieza"),
    OTROS("Otros");

    private final String tipo;

    private Tipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
