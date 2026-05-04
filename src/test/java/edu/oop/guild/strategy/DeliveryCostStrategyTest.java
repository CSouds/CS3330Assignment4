package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.model.PackageType;
import edu.oop.guild.model.RealmType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCostStrategyTest {
    static Stream<Arguments> pricedRequests() {
        return Stream.of(
                Arguments.of(new StandardDeliveryStrategy(), new DeliveryRequest(PackageType.FOOD, 3, 4, RealmType.SKY, false), 21),
                Arguments.of(new StandardDeliveryStrategy(), new DeliveryRequest(PackageType.FOOD, 3, 4, RealmType.SKY, true), 26),
                Arguments.of(new ExpressDeliveryStrategy(), new DeliveryRequest(PackageType.POTION, 3, 4, RealmType.SKY, false), 47),
                Arguments.of(new ExpressDeliveryStrategy(), new DeliveryRequest(PackageType.POTION, 3, 4, RealmType.SKY, true), 57),
                Arguments.of(new CarefulDeliveryStrategy(), new DeliveryRequest(PackageType.FOOD, 2, 2, RealmType.SKY, false), 28),
                Arguments.of(new CarefulDeliveryStrategy(), new DeliveryRequest(PackageType.POTION, 2, 2, RealmType.SKY, false), 33),
                Arguments.of(new CarefulDeliveryStrategy(), new DeliveryRequest(PackageType.ARTIFACT, 2, 2, RealmType.SKY, false), 45)
        );
    }

    @ParameterizedTest
    @MethodSource("pricedRequests")
    void estimatesDeliveryCost(DeliveryCostStrategy strategy, DeliveryRequest request, int expectedCoins) {
        assertEquals(expectedCoins, strategy.estimateCoins(request));
    }

    static Stream<DeliveryCostStrategy> strategies() {
        return Stream.of(new StandardDeliveryStrategy(), new ExpressDeliveryStrategy(), new CarefulDeliveryStrategy());
    }

    @ParameterizedTest
    @MethodSource("strategies")
    void rejectsNullRequest(DeliveryCostStrategy strategy) {
        assertThrows(NullPointerException.class, () -> strategy.estimateCoins(null));
    }
}
