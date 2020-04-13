package com.avaj.base.machins;

import com.avaj.base.Coordinates;
import com.avaj.base.Simulator;
import com.avaj.base.WeatherTower;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
	
	
	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather = weatherTower.getWeather(this.coordinates);
		HashMap<String, String> messages = new HashMap<String, String>() {{
			put("SUN", "Flap, Flap, Flap... Oh, a sun !");
			put("RAIN", "Flap, Flap, Flap... damm, it's rainning, here.");
			put("FOG", "Flap, Flap, Flap... hum... it's pretty grey, here.");
			put("SNOW", "Flap, Flap, Flap... it's christmas day ?");
		}};

		if (weather.equals("SUN"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 10,
					coordinates.getLatitude() + 2,
					coordinates.getHeight() + 0
			);
		else if (weather.equals("RAIN"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 5,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() + 0
			);
		else if (weather.equals("FOG"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 1,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() + 0
			);
		else if (weather.equals("SNOW"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 12
			);

		Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + messages.get(weather));
		if (this.coordinates.getHeight() == 0)
		{
			Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
			this.weatherTower.unregister(this);
			Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");

	}

	
}