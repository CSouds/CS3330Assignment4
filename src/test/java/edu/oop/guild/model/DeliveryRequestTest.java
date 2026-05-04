package edu.oop.guild.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryRequestTest {
    private final DeliveryRequest request = new DeliveryRequest(PackageType.POTION, 4, 9, RealmType.SKY, true);

    static Stream<Arguments> requestValues() {
        return Stream.of(
                Arguments.of((Function<DeliveryRequest, Object>) DeliveryRequest::getPackageType, PackageType.POTION),
                Arguments.of((Function<DeliveryRequest, Object>) DeliveryRequest::getWeightKg, 4),
                Arguments.of((Function<DeliveryRequest, Object>) DeliveryRequest::getDistanceLeagues, 9),
                Arguments.of((Function<DeliveryRequest, Object>) DeliveryRequest::getDestinationRealm, RealmType.SKY),
                Arguments.of((Function<DeliveryRequest, Object>) DeliveryRequest::isFragile, true)
        );
    }

    @ParameterizedTest
    @MethodSource("requestValues")
    void exposesConstructorValues(Function<DeliveryRequest, Object> getter, Object expected) {
        assertEquals(expected, getter.apply(request));
    }

    static Stream<Arguments> invalidRequests() {
        return Stream.of(
                Arguments.of(NullPointerException.class, null, 1, 1, RealmType.SKY, false),
                Arguments.of(IllegalArgumentException.class, PackageType.FOOD, 0, 1, RealmType.SKY, false),
                Arguments.of(IllegalArgumentException.class, PackageType.FOOD, 1, 0, RealmType.SKY, false),
                Arguments.of(NullPointerException.class, PackageType.FOOD, 1, 1, null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidRequests")
    void rejectsInvalidConstructorValues(Class<? extends Throwable> expectedException, PackageType packageType,
                                         int weightKg, int distanceLeagues, RealmType destinationRealm,
                                         boolean fragile) {
        assertThrows(expectedException,
                () -> new DeliveryRequest(packageType, weightKg, distanceLeagues, destinationRealm, fragile));
    }
}
