package edu.oop.guild.model;

import edu.oop.guild.creature.Creature;

public class DeliveryPlan {
	private DeliveryRequest request;
	private Creature courier;
	private String sealedLabel;
	private int priceInCoins;
	
	public DeliveryPlan(DeliveryRequest request, Creature courier, String sealedLabel, int priceInCoins)
	{
		if(request == null || courier == null || sealedLabel == null)
		{
			throw new NullPointerException();
		}
		
		if(priceInCoins < 0)
		{
			throw new IllegalArgumentException();
		}
		
		this.request = request;
		this.courier = courier;
		this.sealedLabel = sealedLabel;
		this.priceInCoins = priceInCoins;
	}
    public DeliveryRequest getRequest()
    {
    	return request;
    }
    
    public Creature getCourier()
    {
    	return courier;
    }
    
    public String getSealedLabel()
    {
    	return sealedLabel;
    }
    
    public int getPriceInCoins()
    {
    	return priceInCoins;
    }
    
    public String summary()
    {
    	return courier.name() + " delivers " + sealedLabel + " for " + priceInCoins + " coins";
    }
}
