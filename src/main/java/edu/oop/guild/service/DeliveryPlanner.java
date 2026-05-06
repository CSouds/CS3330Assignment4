package edu.oop.guild.service;

import edu.oop.guild.creature.Creature;
import edu.oop.guild.factory.RealmFactory;
import edu.oop.guild.log.GuildLog;
import edu.oop.guild.model.DeliveryPlan;
import edu.oop.guild.model.DeliveryRequest;
import edu.oop.guild.seal.PackageSeal;
import edu.oop.guild.strategy.DeliveryCostStrategy;

import java.util.Objects;

public class DeliveryPlanner {
    private final RealmFactory factory;
    private final DeliveryCostStrategy strategy;
    private final GuildLog log;

    public DeliveryPlanner(RealmFactory factory, DeliveryCostStrategy strategy, GuildLog log) {
        this.factory = Objects.requireNonNull(factory);
        this.strategy = Objects.requireNonNull(strategy);
        this.log = Objects.requireNonNull(log);
    }

    public DeliveryPlan plan(DeliveryRequest request) {
        Objects.requireNonNull(request);

        Creature courier = factory.createCourier();

        if (!courier.canCarry(request)) {
            throw new IllegalStateException("Courier cannot carry this delivery request");
        }

        PackageSeal seal = factory.createSeal();

        String label = request.getPackageType().label()
                + " to "
                + request.getDestinationRealm().displayName();

        String sealedLabel = seal.apply(label);

        int price = strategy.estimateCoins(request);

        DeliveryPlan plan = new DeliveryPlan(request, courier, sealedLabel, price);

        log.record(plan.summary());

        return plan;
    }
}