package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.model.PackageType;

public class CarefulDeliveryStrategy implements DeliveryCostStrategy {

    @Override
    public int estimateCoins(DeliveryRequest request) {
        if (request == null) {
            throw new NullPointerException();
        }

        int base = request.getWeightKg() * request.getDistanceLeagues() * 4;

        int surcharge;
        if (request.getPackageType() == PackageType.ARTIFACT) {
            surcharge = 29;
        } else if (request.getPackageType() == PackageType.POTION) {
            surcharge = 17;
        } else {
            surcharge = 12;
        }

        return base + surcharge;
    }
}