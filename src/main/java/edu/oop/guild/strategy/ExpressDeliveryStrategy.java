package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;

public class ExpressDeliveryStrategy implements DeliveryCostStrategy {

    @Override
    public int estimateCoins(DeliveryRequest request) {
        if (request == null) {
            throw new NullPointerException();
        }

        int base = 2 * request.getWeightKg() + 4 * request.getDistanceLeagues() + 25;

        if (request.isFragile()) {
            base += 10;
        }

        return base;
    }
}