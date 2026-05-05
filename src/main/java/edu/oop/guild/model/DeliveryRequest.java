package edu.oop.guild.model;

public class DeliveryRequest {
	private PackageType packageType;
	private int weightKg;
	private int distanceLeagues;
	private RealmType destinationRealm;
	private boolean fragile;
	
	public DeliveryRequest(PackageType packageType, int weightKg, int distanceLeagues, RealmType destinationRealm, boolean fragile)
	{
		if(packageType == null || destinationRealm == null)
		{
			throw new NullPointerException();
		}
		if(weightKg <= 0 || distanceLeagues <= 0)
		{
			throw new IllegalArgumentException();
		}
		
		this.packageType = packageType;
		this.weightKg = weightKg;
		this.distanceLeagues = distanceLeagues;
		this.destinationRealm = destinationRealm;
		this.fragile = fragile;
	}
	
    public PackageType getPackageType()
    {
    	return packageType;
    }
    
    public int getWeightKg()
    {
    	return weightKg;
    }
    
    public int getDistanceLeagues()
    {
    	return distanceLeagues;
    }
    
    public RealmType getDestinationRealm()
    {
    	return destinationRealm;
    }
    
    public boolean isFragile()
    {
    	return fragile;
    }
}
