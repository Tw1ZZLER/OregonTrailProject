package oregonTrail;

public enum WeatherType {
	VERY_HOT (2, 90, 120),
	HOT (1, 70, 90), 
	COOL (0, 50, 70),
	WARM (0, 30, 50),
	COLD (2, 10, 30),
	VERY_COLD (4, -20, 10);

	WeatherType(int healthModifier, int minTemp, int maxTemp) {
	}
}
