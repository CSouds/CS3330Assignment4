package edu.oop.guild.strategy;

import edu.oop.guild.model.DeliveryRequest;

public interface DeliveryCostStrategy {
    int estimateCoins(DeliveryRequest request); // throws NullPointerException if null
}
