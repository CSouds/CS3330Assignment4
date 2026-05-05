package edu.oop.guild.notification;

import edu.oop.guild.model.DeliveryPlan;

public interface NotificationChannel {
	String send(DeliveryPlan plan);
}
