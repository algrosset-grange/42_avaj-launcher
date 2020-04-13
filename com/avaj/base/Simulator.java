package com.avaj.base;

import com.avaj.base.machins.AircraftFactory;

import java.io.*;

public class Simulator {

	public static PrintWriter writer;
	public static int cycles;

	public static void main(String[] args)
	{
		if (args.length < 1)
			return ;
		// scenario filename
		String filename = args[0];

		File simulationFile = new File("simulation.txt");
		try {
			writer = new PrintWriter(simulationFile);
		} catch (FileNotFoundException fne) {
			System.out.println("Error: " + fne.getMessage());
			return ;
		}
		if (simulationFile.exists() && !simulationFile.isDirectory())
			writer.print("");

		AircraftFactory aircraftFactory = new AircraftFactory();
		WeatherTower weatherTower = new WeatherTower();
		try {
			FileInputStream fstream = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int line = 1;
			String[] splitted;

			while ((strLine = br.readLine()) != null)
			{
				// on vérifie que la première ligne compte 1 seul paramètre integer.
				if (line == 1)
					try {
						cycles = Integer.parseInt(strLine);
						if (cycles < 0)
						{
							System.out.println("Error: first line of scenario file must be a POSITIVE integer.");
							return ;
						}
					} catch (NumberFormatException nfe) {
						br.close();
						System.out.println("Error: first line of scenario file must be an integer.");
						return ;
					}
				// Pour les autres lignes, on vérifie le format <String> <String> <Int> <Int> <Int>
				else
				{
					splitted = strLine.split(" ");
					if (splitted.length == 1 && splitted[0].isEmpty()) // on ignore les ligne vide
						continue;
					if (splitted.length != 5) { // vérification qu'il y a 5 parametres
						br.close();
						throw new Exception("Error: line " + line + ": there must be 5 parameters.");
					}
					try {
						aircraftFactory.newAircraft(
								splitted[0],
								splitted[1],
								Integer.parseInt(splitted[2]),
								Integer.parseInt(splitted[3]),
								Integer.parseInt(splitted[4])
						).registerTower(weatherTower);
					} catch (NumberFormatException nfe) {
						System.out.println("Error: line " + line + ": parameter 3 to 5 must be integers.");
						br.close();
						return ;
					} catch (Exception ex) {
						System.out.println("Error: " + ex.getMessage());
						br.close();
						return ;
					}
				}
//				System.out.println(strLine);
				line++;
			}

			br.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return ;
		}

		//WeatherProvider weatherProvider = WeatherProvider.getProvider();
		while (cycles > 0)
		{
			weatherTower.changeWeather();
			cycles--;
		}

		writer.close();
	}
}