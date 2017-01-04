package jets;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import jets.JetsLoader.Pair;

public class JetsLoader {
	private List<Jet> jets;
	private List<Pilot> pilots;

	public JetsLoader(Hangar hangar, Barracks barracks) {
		if(hangar == null) {
			hangar = new Hangar();
		}
		
		if(barracks == null) {
			barracks = new Barracks();
		}

		jets = hangar.getJetsList();
		pilots = barracks.getPilotsList();
	}

	String pilotToString(Pilot pilot, int pid) {
		if(pilot == null)
			return "";
		
		// PILOT:<ID>:<NAME>:<GENDER>:<AGE>
		return String.format("PILOT:%s:%s:%s:%d", 
				pid, pilot.getName(), pilot.getGender(), pilot.getAge());
	}

	static class Pair<A,B> {
		private A first;
		private B second;
		
		public Pair(A first, B second) {
			this.first  = first;
			this.second = second;
		}
		
		public A first() { return first; }
		public B second() { return second; }
	}
	
	// private Pilot pilotFromString(String pilotString) {
	Pair<Pilot, Integer> pilotFromString(String pilotString) {
		Pair<Pilot, Integer> retVal = null;
		
		String[] items = pilotString.split(":");
		if(items.length >= 5 && items[0].equals("PILOT")) {
			try {
				String name = items[2];
				String gender = items[3];
				int id;
				int age;
			
				id = Integer.parseInt(items[1]);
				age = Integer.parseInt(items[4]);
				
				retVal = new Pair<>(new Pilot(name, gender, age), id);
			}
			catch (NumberFormatException e) {}
		}
		
		return retVal;
	}
	
	Pair<Jet, Integer> jetFromString(String inString) {
		// TODO Auto-generated method stub
		return null;
	}
	
	String jetToString(Jet jet, int jetID, int pilotID) {
		if(jet == null)
			return "";
		
		// JET:<ID>:<MODEL>:<SPEED>:<RANGE>:<PRICE>:<PILOT_ID>
		return String.format("PILOT:%d:%s:%f:%f:%f:%d", 
				jetID, jet.getModel(), jet.getSpeed(), 
				jet.getRange(), jet.getPrice(), pilotID);
	}
	
	public boolean saveJetsToFile(String outputFile, List<Jet> jets) {
		return false;
	}

	public boolean loadJetsFromFile(String inputFile, List<Jet> jets) {
		return false;
	}

	public boolean savePilotsToFile(String outputFile, List<Pilot> pilots) {
		return false;
	}

	public boolean loadPilotsFromFile(String inputFile, List<Pilot> pilots) {
		return false;
	}
}
