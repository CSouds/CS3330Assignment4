package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;

public class StandardDeliveryStrategy implements DeliveryCostStrategy {

    @Override
    public int estimateCoins(DeliveryRequest request) {
        if (request == null) {
            throw new NullPointerException();
        }

        int base = request.getWeightKg() * request.getDistanceLeagues() + 9;

        if (request.isFragile()) {
            base += 5;
        }

        return base;
    }
}