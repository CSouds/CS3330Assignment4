package edu.oop.guild.model;

public enum PackageType {
	FOOD("Snack crate"),
    POTION("Potion case"),
    ARTIFACT("Ancient artifact");

    private final String label;

    PackageType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
