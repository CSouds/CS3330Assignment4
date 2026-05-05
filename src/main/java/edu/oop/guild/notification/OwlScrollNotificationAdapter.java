package edu.oop.guild.notification;

import edu.oop.guild.model.DeliveryPlan;

public class OwlScrollNotificationAdapter implements NotificationChannel {
    private LegacyOwlScroll owlScroll;

    public OwlScrollNotificationAdapter(LegacyOwlScroll owlScroll) {
        if (owlScroll == null) {
            throw new NullPointerException();
        }
        this.owlScroll = owlScroll;
    }

    @Override
    public String send(DeliveryPlan plan) {
        if (plan == null) {
            throw new NullPointerException();
        }
        String recipient = plan.getRequest().getDestinationRealm().displayName();
        String inscription = plan.summary();
        return owlScroll.dispatchScroll(recipient, inscription);
    }
}
