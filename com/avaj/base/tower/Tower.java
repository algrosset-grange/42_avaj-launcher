package com.avaj.base.tower;
import com.avaj.base.machins.Flyable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 18/05/2017.
 */
public class Tower {

	private List<Flyable> observers = new ArrayList<Flyable>();

	public void register(Flyable flyable)
	{
		if (observers.contains(flyable))
			return;
		observers.add(flyable);
	}

	public void unregister(Flyable flyable)
	{
		observers.remove(flyable);
	}

	protected void conditionChanged()
	{
		for (int i = 0; i < observers.size(); i++)
		{
			observers.get(i).updateConditions();
		}
	}

}