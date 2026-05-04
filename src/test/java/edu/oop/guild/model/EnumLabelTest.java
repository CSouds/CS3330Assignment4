package edu.oop.guild.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumLabelTest {
    @ParameterizedTest
    @CsvSource({
            "FOOD, Snack crate",
            "POTION, Potion case",
            "ARTIFACT, Ancient artifact"
    })
    void packageTypesHaveStudentFriendlyLabels(PackageType packageType, String expectedLabel) {
        assertEquals(expectedLabel, packageType.label());
    }

    @ParameterizedTest
    @CsvSource({
            "SKY, Sky Kingdom",
            "UNDERGROUND, Underground Market"
    })
    void realmsHaveDisplayNames(RealmType realmType, String expectedDisplayName) {
        assertEquals(expectedDisplayName, realmType.displayName());
    }
}
