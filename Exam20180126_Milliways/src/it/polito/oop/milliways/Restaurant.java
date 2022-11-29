package it.polito.oop.milliways;

import java.util.List;
import java.util.Map;


public class Restaurant {

    public Restaurant() {
	}
	
	public Race defineRace(String name) throws MilliwaysException{
	    return null;
	}
	
	public Party createParty() {
	    return null;
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
	    return null;
	}

	public List<Hall> getHallList() {
		return null;
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
        return null;
	}

	public Hall seat(Party party) throws MilliwaysException {
        return null;
	}

	public Map<Race, Integer> statComposition() {
        return null;
	}

	public List<String> statFacility() {
        return null;
	}
	
	public Map<Integer,List<Integer>> statHalls() {
        return null;
	}

}
