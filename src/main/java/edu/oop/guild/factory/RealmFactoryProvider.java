package edu.oop.guild.factory;

import edu.oop.guild.model.RealmType;

import java.util.Objects;

public class RealmFactoryProvider {

    public RealmFactory forRealm(RealmType realmType) {
        Objects.requireNonNull(realmType);

        return switch (realmType) {
            case SKY -> new SkyRealmFactory();
            case UNDERGROUND -> new UndergroundRealmFactory();
        };
    }
}