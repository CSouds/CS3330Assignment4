package edu.oop.guild.service;

import edu.oop.guild.creature.CloudDragon;
import edu.oop.guild.creature.TunnelMole;
import edu.oop.guild.factory.RealmFactory;
import edu.oop.guild.factory.SkyRealmFactory;
import edu.oop.guild.factory.UndergroundRealmFactory;
import edu.oop.guild.log.GuildLog;
import edu.oop.guild.model.DeliveryPlan;
import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.model.PackageType;
import edu.oop.guild.model.RealmType;
import edu.oop.guild.strategy.CarefulDeliveryStrategy;
import edu.oop.guild.strategy.DeliveryCostStrategy;
import edu.oop.guild.strategy.ExpressDeliveryStrategy;
import edu.oop.guild.strategy.StandardDeliveryStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPlannerTest {
    @AfterEach
    void cleanUp() {
        GuildLog.getInstance().clear();
    }

    static Stream<Arguments> skyPlanValues() {
        return Stream.of(
                Arguments.of((Function<DeliveryPlan, Object>) plan -> plan.getCourier().getClass(), CloudDragon.class),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getSealedLabel, "☁ Snack crate to Sky Kingdom ☁"),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getPriceInCoins, 22),
                Arguments.of((Function<DeliveryPlan, Object>) plan -> GuildLog.getInstance().size(), 1),
                Arguments.of((Function<DeliveryPlan, Object>) plan -> GuildLog.getInstance().entries().get(0),
                        "Nimbus the Cloud Dragon delivers ☁ Snack crate to Sky Kingdom ☁ for 22 coins")
        );
    }

    @ParameterizedTest
    @MethodSource("skyPlanValues")
    void plansSkyDelivery(Function<DeliveryPlan, Object> getter, Object expected) {
        DeliveryPlanner planner = new DeliveryPlanner(new SkyRealmFactory(), new StandardDeliveryStrategy(), GuildLog.getInstance());
        DeliveryRequest request = new DeliveryRequest(PackageType.FOOD, 2, 5, RealmType.SKY, false);

        assertEquals(expected, getter.apply(planner.plan(request)));
    }

    static Stream<Arguments> undergroundPlanValues() {
        return Stream.of(
                Arguments.of((Function<DeliveryPlan, Object>) plan -> plan.getCourier().getClass(), TunnelMole.class),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getSealedLabel, "◆ Ancient artifact to Underground Market ◆"),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getPriceInCoins, 67)
        );
    }

    @ParameterizedTest
    @MethodSource("undergroundPlanValues")
    void plansUndergroundDelivery(Function<DeliveryPlan, Object> getter, Object expected) {
        DeliveryPlanner planner = new DeliveryPlanner(new UndergroundRealmFactory(), new ExpressDeliveryStrategy(), GuildLog.getInstance());
        DeliveryRequest request = new DeliveryRequest(PackageType.ARTIFACT, 10, 3, RealmType.UNDERGROUND, true);

        assertEquals(expected, getter.apply(planner.plan(request)));
    }

    static Stream<Arguments> invalidPlannerConstructorArguments() {
        GuildLog log = GuildLog.getInstance();
        RealmFactory factory = new SkyRealmFactory();
        DeliveryCostStrategy strategy = new StandardDeliveryStrategy();
        return Stream.of(
                Arguments.of(null, strategy, log),
                Arguments.of(factory, null, log),
                Arguments.of(factory, strategy, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPlannerConstructorArguments")
    void rejectsNullConstructorArguments(RealmFactory factory, DeliveryCostStrategy strategy, GuildLog log) {
        assertThrows(NullPointerException.class, () -> new DeliveryPlanner(factory, strategy, log));
    }

    @Test
    void rejectsNullPlanRequest() {
        DeliveryPlanner planner = new DeliveryPlanner(new SkyRealmFactory(), new StandardDeliveryStrategy(), GuildLog.getInstance());
        assertThrows(NullPointerException.class, () -> planner.plan(null));
    }

    @Test
    void rejectsPlansWhenCourierCannotCarryPackage() {
        DeliveryPlanner planner = new DeliveryPlanner(new SkyRealmFactory(), new CarefulDeliveryStrategy(), GuildLog.getInstance());
        DeliveryRequest wrongRealm = new DeliveryRequest(PackageType.POTION, 3, 3, RealmType.UNDERGROUND, false);

        assertThrows(IllegalStateException.class, () -> planner.plan(wrongRealm));
    }

    @Test
    void failedPlanDoesNotWriteToLog() {
        DeliveryPlanner planner = new DeliveryPlanner(new SkyRealmFactory(), new CarefulDeliveryStrategy(), GuildLog.getInstance());
        DeliveryRequest wrongRealm = new DeliveryRequest(PackageType.POTION, 3, 3, RealmType.UNDERGROUND, false);

        try {
            planner.plan(wrongRealm);
        } catch (IllegalStateException ignored) {
        }
        assertEquals(0, GuildLog.getInstance().size());
    }
}
