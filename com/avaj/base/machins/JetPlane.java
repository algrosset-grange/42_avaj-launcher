package com.avaj.base.machins;

import com.avaj.base.Coordinates;
import com.avaj.base.Simulator;
import com.avaj.base.WeatherTower;

import java.util.HashMap;


public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather = weatherTower.getWeather(this.coordinates);
		HashMap<String, String> messages = new HashMap<String, String>() {{
			put("SUN", "Gniiii Pan Onnnnn. - sun");
			put("RAIN", "Gniiii Onnnnn. - rain");
			put("FOG", "Gniiii ? - fog");
			put("SNOW", "Gniiii Onnnnn clac clac clac. - snow");
		}};

		if (weather.equals("SUN"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 10,
					coordinates.getHeight() + 2
			);
		else if (weather.equals("RAIN"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 5,
					coordinates.getHeight() + 0
			);
		else if (weather.equals("FOG"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 1,
					coordinates.getHeight() + 0
			);
		else if (weather.equals("SNOW"))
			this.coordinates = new Coordinates(
					coordinates.getLongitude() + 0,
					coordinates.getLatitude() + 0,
					coordinates.getHeight() - 7
			);

		Simulator.writer.println("Jetplane#" + this.name + "(" + this.id + "): " + messages.get(weather));
		if (this.coordinates.getHeight() == 0)
		{
			Simulator.writer.println("Jetplane#" + this.name + "(" + this.id + "): landing.");
			this.weatherTower.unregister(this);
			Simulator.writer.println("Tower says: Jetplane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}

	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");

	}

}