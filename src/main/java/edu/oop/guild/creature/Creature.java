package edu.oop.guild.creature;

import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.model.RealmType;

import java.util.Objects;

public interface Creature {

    String name();

    int carryingCapacityKg();

    RealmType nativeRealm();

    default boolean canCarry(DeliveryRequest request) {
        Objects.requireNonNull(request);

        return request.getWeightKg() <= carryingCapacityKg()
                && request.getDestinationRealm() == nativeRealm();
    }
}