package edu.oop.guild.notification;

import edu.oop.guild.creature.CloudDragon;
import edu.oop.guild.model.DeliveryPlan;
import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.model.PackageType;
import edu.oop.guild.model.RealmType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NotificationAdapterTest {
    @Test
    void legacyOwlScrollDispatchesValidScroll() {
        LegacyOwlScroll owlScroll = new LegacyOwlScroll();
        assertEquals("Owl scroll sent to Sky Kingdom: The muffins have landed",
                owlScroll.dispatchScroll("Sky Kingdom", "The muffins have landed"));
    }

    static Stream<Arguments> invalidScrolls() {
        return Stream.of(
                Arguments.of(null, "Hi"),
                Arguments.of("   ", "Hi"),
                Arguments.of("Sky", null),
                Arguments.of("Sky", "   ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidScrolls")
    void legacyOwlScrollRejectsBlankInputs(String recipient, String inscription) {
        assertThrows(IllegalArgumentException.class, () -> new LegacyOwlScroll().dispatchScroll(recipient, inscription));
    }

    @Test
    void adapterTurnsDeliveryPlanIntoLegacyScrollMessage() {
        DeliveryRequest request = new DeliveryRequest(PackageType.POTION, 3, 4, RealmType.SKY, true);
        DeliveryPlan plan = new DeliveryPlan(request, new CloudDragon(), "☁ Potion case to Sky Kingdom ☁", 26);
        NotificationChannel channel = new OwlScrollNotificationAdapter(new LegacyOwlScroll());

        assertEquals("Owl scroll sent to Sky Kingdom: Nimbus the Cloud Dragon delivers ☁ Potion case to Sky Kingdom ☁ for 26 coins",
                channel.send(plan));
    }

    @Test
    void adapterRejectsNullLegacyScroll() {
        assertThrows(NullPointerException.class, () -> new OwlScrollNotificationAdapter(null));
    }

    @Test
    void adapterRejectsNullPlan() {
        assertThrows(NullPointerException.class, () -> new OwlScrollNotificationAdapter(new LegacyOwlScroll()).send(null));
    }
}
