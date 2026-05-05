package edu.oop.guild.notification;

public class LegacyOwlScroll {

    public String dispatchScroll(String recipient, String inscription) {
        if (recipient == null || recipient.isBlank() || inscription == null || inscription.isBlank()) {
            throw new IllegalArgumentException();
        }
        return "Owl scroll sent to " + recipient + ": " + inscription;
    }
}
