package edu.oop.guild.creature;

import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.model.PackageType;
import edu.oop.guild.model.RealmType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    static Stream<Arguments> creatureValues() {
        return Stream.of(
                Arguments.of(new CloudDragon(), (Function<Creature, Object>) Creature::name, "Nimbus the Cloud Dragon"),
                Arguments.of(new CloudDragon(), (Function<Creature, Object>) Creature::carryingCapacityKg, 80),
                Arguments.of(new CloudDragon(), (Function<Creature, Object>) Creature::nativeRealm, RealmType.SKY),
                Arguments.of(new TunnelMole(), (Function<Creature, Object>) Creature::name, "Grumble the Tunnel Mole"),
                Arguments.of(new TunnelMole(), (Function<Creature, Object>) Creature::carryingCapacityKg, 30),
                Arguments.of(new TunnelMole(), (Function<Creature, Object>) Creature::nativeRealm, RealmType.UNDERGROUND)
        );
    }

    @ParameterizedTest
    @MethodSource("creatureValues")
    void exposesCreatureValues(Creature creature, Function<Creature, Object> getter, Object expected) {
        assertEquals(expected, getter.apply(creature));
    }

    static Stream<Arguments> carryOutcomes() {
        return Stream.of(
                Arguments.of(new CloudDragon(), new DeliveryRequest(PackageType.FOOD, 80, 1, RealmType.SKY, false), true),
                Arguments.of(new CloudDragon(), new DeliveryRequest(PackageType.FOOD, 81, 1, RealmType.SKY, false), false),
                Arguments.of(new CloudDragon(), new DeliveryRequest(PackageType.FOOD, 1, 1, RealmType.UNDERGROUND, false), false),
                Arguments.of(new TunnelMole(), new DeliveryRequest(PackageType.FOOD, 30, 1, RealmType.UNDERGROUND, false), true),
                Arguments.of(new TunnelMole(), new DeliveryRequest(PackageType.FOOD, 31, 1, RealmType.UNDERGROUND, false), false)
        );
    }

    @ParameterizedTest
    @MethodSource("carryOutcomes")
    void determinesWhetherCreatureCanCarryRequest(Creature creature, DeliveryRequest request, boolean expected) {
        assertEquals(expected, creature.canCarry(request));
    }

    @ParameterizedTest
    @MethodSource("creatures")
    void rejectsNullCarryRequest(Creature creature) {
        assertThrows(NullPointerException.class, () -> creature.canCarry(null));
    }

    static Stream<Creature> creatures() {
        return Stream.of(new CloudDragon(), new TunnelMole());
    }
}
