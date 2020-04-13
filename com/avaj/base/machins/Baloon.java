package com.avaj.base.machins;

import com.avaj.base.Coordinates;
import com.avaj.base.Simulator;
import com.avaj.base.WeatherTower;

import java.util.HashMap;


public class Baloon extends Aircraft implements Flyable 
{

	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates)
	{
		super(name, coordinates);
	}

	public void updateConditions()
	{
		String weather = weatherTower.getWeather(this.coordinates);
		HashMap<String, String> messages = new HashMap<String, String>() {{
			put("SUN", "Here is ballon-boy and it's sunny time !");
			put("RAIN", "I sing under the raiiiiiin... i'm the ballon");
			put("FOG", "Here is a ballon, I say FOG and frog !");
			put("SNOW", "Here is ballon, it's white and cold here...");
		}};

		if (weather.equals("SUN"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 2,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() + 4
			);
		else if (weather.equals("RAIN"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 5
			);
		else if (weather.equals("FOG"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 3
			);
		else if (weather.equals("SNOW"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 15
			);

		Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): " + messages.get(weather));
		if (this.coordinates.getHeight() == 0)
		{
			Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): landing.");
			this.weatherTower.unregister(this);
			Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
}

	public void registerTower(WeatherTower weatherTower)
	{
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

}