package edu.oop.guild.creature;

import edu.oop.guild.model.RealmType;

public class TunnelMole implements Creature {

    @Override
    public String name() {
        return "Grumble the Tunnel Mole";
    }

    @Override
    public int carryingCapacityKg() {
        return 30;
    }

    @Override
    public RealmType nativeRealm() {
        return RealmType.UNDERGROUND;
    }
}