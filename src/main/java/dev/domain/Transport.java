package dev.domain;

public enum Transport {

    COVOITURAGE("Covoiturage"), AVION("Avion"), TRAIN("Train"), VOITURE_DE_SERVICE("Voiture de service");

    private String type;

    Transport(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
