package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;

public class ExpressDeliveryStrategy implements DeliveryCostStrategy {

    @Override
    public int estimateCoins(DeliveryRequest request) {
        if (request == null) {
            throw new NullPointerException();
        }

        int base = request.getWeightKg() * request.getDistanceLeagues() * 3 + 11;

        if (request.isFragile()) {
            base += 10;
        }

        return base;
    }
}