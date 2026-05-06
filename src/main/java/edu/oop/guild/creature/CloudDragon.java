package edu.oop.guild.creature;

import edu.oop.guild.model.RealmType;

public class CloudDragon implements Creature {

    @Override
    public String name() {
        return "Nimbus the Cloud Dragon";
    }

    @Override
    public int carryingCapacityKg() {
        return 80;
    }

    @Override
    public RealmType nativeRealm() {
        return RealmType.SKY;
    }
}