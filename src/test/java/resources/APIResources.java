package resources;


public enum APIResources {
	

	getUnixTimeStampAPI("/UnixTime/fromunixtimestamp");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
		
	}
	
	
	


}
