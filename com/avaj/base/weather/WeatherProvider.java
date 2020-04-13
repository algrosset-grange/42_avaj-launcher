package com.avaj.base.weather;

import com.avaj.base.Coordinates;

/**
 * Created by victor on 18/05/2017.
 */
public class WeatherProvider
{
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {
            "RAIN",
            "FOG",
            "SUN",
            "SNOW"
    };

    private WeatherProvider()
    {
    }

    public static WeatherProvider getProvider()
    {
        return WeatherProvider.weatherProvider;
    }

    private int convertByteToInt(byte[] b)
    {
        int value= 0;
        for(int i=0; i<b.length; i++)
            value = (value << 8) | b[i];
        return value;
    }
    public String getCurrentWeather(Coordinates coordinates)
    {
        int seed = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();

        return weather[seed % 4];
    }
}