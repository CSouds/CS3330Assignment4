package edu.oop.guild.seal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PackageSealTest {
    static Stream<Arguments> sealValues() {
        return Stream.of(
                Arguments.of(new SkyRibbonSeal(), (Function<PackageSeal, Object>) seal -> seal.apply("Cookies"), "☁ Cookies ☁"),
                Arguments.of(new SkyRibbonSeal(), (Function<PackageSeal, Object>) PackageSeal::durability, 7),
                Arguments.of(new GlowStoneSeal(), (Function<PackageSeal, Object>) seal -> seal.apply("Mushrooms"), "◆ Mushrooms ◆"),
                Arguments.of(new GlowStoneSeal(), (Function<PackageSeal, Object>) PackageSeal::durability, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("sealValues")
    void exposesSealValues(PackageSeal seal, Function<PackageSeal, Object> operation, Object expected) {
        assertEquals(expected, operation.apply(seal));
    }

    static Stream<PackageSeal> seals() {
        return Stream.of(new SkyRibbonSeal(), new GlowStoneSeal());
    }

    @ParameterizedTest
    @MethodSource("seals")
    void rejectsNullLabels(PackageSeal seal) {
        assertThrows(NullPointerException.class, () -> seal.apply(null));
    }
}
