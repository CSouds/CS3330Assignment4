package edu.oop.guild.factory;

import edu.oop.guild.creature.CloudDragon;
import edu.oop.guild.creature.TunnelMole;
import edu.oop.guild.model.RealmType;
import edu.oop.guild.seal.GlowStoneSeal;
import edu.oop.guild.seal.SkyRibbonSeal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RealmFactoryTest {
    static Stream<Arguments> factoryProducts() {
        return Stream.of(
                Arguments.of(new SkyRealmFactory(), (Function<RealmFactory, Object>) RealmFactory::createCourier, CloudDragon.class),
                Arguments.of(new SkyRealmFactory(), (Function<RealmFactory, Object>) RealmFactory::createSeal, SkyRibbonSeal.class),
                Arguments.of(new UndergroundRealmFactory(), (Function<RealmFactory, Object>) RealmFactory::createCourier, TunnelMole.class),
                Arguments.of(new UndergroundRealmFactory(), (Function<RealmFactory, Object>) RealmFactory::createSeal, GlowStoneSeal.class)
        );
    }

    @ParameterizedTest
    @MethodSource("factoryProducts")
    void createsRealmFamilyObjects(RealmFactory factory, Function<RealmFactory, Object> operation, Class<?> expectedType) {
        assertInstanceOf(expectedType, operation.apply(factory));
    }

    static Stream<Arguments> providerSelections() {
        return Stream.of(
                Arguments.of(RealmType.SKY, SkyRealmFactory.class),
                Arguments.of(RealmType.UNDERGROUND, UndergroundRealmFactory.class)
        );
    }

    @ParameterizedTest
    @MethodSource("providerSelections")
    void providerSelectsFactoryByRealm(RealmType realmType, Class<?> expectedType) {
        assertInstanceOf(expectedType, new RealmFactoryProvider().forRealm(realmType));
    }

    @Test
    void providerRejectsNullRealm() {
        assertThrows(NullPointerException.class, () -> new RealmFactoryProvider().forRealm(null));
    }
}
