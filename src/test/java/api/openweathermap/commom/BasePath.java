package api.openweathermap.commom;

public enum BasePath {
	
	WEATHER("/weather"),
	GROUP("/group");
	
	private String value;

	BasePath(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
