package edu.oop.guild.model;

import edu.oop.guild.creature.CloudDragon;
import edu.oop.guild.creature.Creature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPlanTest {
    private final DeliveryRequest request = new DeliveryRequest(PackageType.FOOD, 2, 5, RealmType.SKY, false);
    private final Creature courier = new CloudDragon();
    private final DeliveryPlan plan = new DeliveryPlan(request, courier, "☁ Snack crate to Sky Kingdom ☁", 22);

    static Stream<Arguments> planValues() {
        return Stream.of(
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getRequest, PackageType.FOOD),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getCourier, "Nimbus the Cloud Dragon"),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getSealedLabel, "☁ Snack crate to Sky Kingdom ☁"),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::getPriceInCoins, 22),
                Arguments.of((Function<DeliveryPlan, Object>) DeliveryPlan::summary,
                        "Nimbus the Cloud Dragon delivers ☁ Snack crate to Sky Kingdom ☁ for 22 coins")
        );
    }

    @ParameterizedTest
    @MethodSource("planValues")
    void exposesPlanValues(Function<DeliveryPlan, Object> getter, Object expected) {
        Object actual = getter.apply(plan);
        if (actual instanceof DeliveryRequest actualRequest) {
            actual = actualRequest.getPackageType();
        }
        if (actual instanceof Creature actualCourier) {
            actual = actualCourier.name();
        }
        assertEquals(expected, actual);
    }

    static Stream<Arguments> invalidPlans() {
        DeliveryRequest request = new DeliveryRequest(PackageType.FOOD, 2, 5, RealmType.SKY, false);
        Creature courier = new CloudDragon();
        return Stream.of(
                Arguments.of(NullPointerException.class, null, courier, "label", 1),
                Arguments.of(NullPointerException.class, request, null, "label", 1),
                Arguments.of(NullPointerException.class, request, courier, null, 1),
                Arguments.of(IllegalArgumentException.class, request, courier, "label", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPlans")
    void rejectsInvalidConstructorValues(Class<? extends Throwable> expectedException, DeliveryRequest request,
                                         Creature courier, String label, int price) {
        assertThrows(expectedException, () -> new DeliveryPlan(request, courier, label, price));
    }
}
