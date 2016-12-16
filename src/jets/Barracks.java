package jets;

import java.util.*;

public class Barracks {
	private List<Pilot> assignedPilots;
	private List<Pilot> unassignedPilots;
	
	public Barracks() {
		assignedPilots   = new ArrayList<>();
		unassignedPilots = new ArrayList<>();
	}
	
	public void hirePilot(Pilot pilot) {
		if(pilot == null) {
			pilot = RandomPilotGenerator.randomPilot();
		}

		if(!containsPilot(pilot)) {
			addPilot(pilot);
		}
	}
	
	public Pilot nextUnassignedPilot() {
		// if no pilots available, hire a new (random) one
		if(unassignedPilots.size() == 0) {
			hirePilot(null);
		}
		
		return unassignedPilots.get(0);
	}

	private void addPilot(Pilot pilot) {
		if(containsPilot(pilot))
			return;
		
		unassignedPilots.add(pilot);
	}
	
	public void unassignPilot(Pilot pilot, Jet jet) {
		if(jet == null || pilot ==null)
			return;
		
		if(pilot != jet.getPilot()) {
			return;
		}
		
		int index = assignedPilots.indexOf(pilot);
		if(index != -1) {
			// add old pilot to back to unassigned pool
			Pilot p = assignedPilots.remove(index);
			unassignedPilots.add(p);
		}
		
		jet.setPilot(null);
	}

	public void assignPilot(Pilot pilot, Jet jet) {
		if(pilot == null || jet == null)
			return;

		if(jet.getPilot() != null) {
			unassignPilot(pilot, jet);
		}

		// find our pilot
		int index = unassignedPilots.indexOf(pilot);

		// if pilot not found, let's go ahead and add him
		// (could just return here)
		if(index == -1) {
			//return;
			addPilot(pilot);
			index = unassignedPilots.indexOf(pilot);
		}
		
		Pilot p = unassignedPilots.remove(index);
		assignedPilots.add(p);
		
		// stick the pilot into the jet
		jet.setPilot(pilot);
	}
	
	public Pilot[] getAllPilots() {
		List<Pilot> tmpList = new ArrayList<>(assignedPilots);
		tmpList.addAll(unassignedPilots);
		return tmpList.toArray(new Pilot[0]);
	}

	public List<Pilot> getPilotsList() {
		return new ArrayList<>(Arrays.asList(getAllPilots()));
	}
	
	public Pilot[] getAssignedPilots() {
		return assignedPilots.toArray(new Pilot[0]);
	}

	public List<Pilot> getAssignedPilotsList() {
		return new ArrayList<>(assignedPilots);
	}

	public Pilot[] getUnassignedPilots() {
		return unassignedPilots.toArray(new Pilot[0]);
	}

	public List<Pilot> getUnassignedPilotsList() {
		return new ArrayList<>(unassignedPilots);
	}

	public boolean containsPilot(Pilot pilot) {
		return (unassignedPilots.contains(pilot) || 
				assignedPilots.contains(pilot));
	}
	
	public static void main(String[] args) {
		Barracks barracks = new Barracks();
		
		for(int i=0; i < 20; i++) {
			//System.out.println(RandomPilotGenerator.randomPilot());
			barracks.hirePilot(RandomPilotGenerator.randomPilot());			
		}
		
		System.out.println("\nAll pilots (pre):");
		for(Pilot p : barracks.getAllPilots())
			System.out.println(p);
		
		Jet[] jets = new Jet[5];
		for(int i=0; i<jets.length; i++) {
			Jet j = new Jet();
			jets[i] = j;
			
			//Pilot p = barracks.nextUnassignedPilot();
			
			Pilot[] pool = barracks.getUnassignedPilots();
			Pilot p = pool[(int)(Math.random()*pool.length)];
			System.out.println("assigning: " + p);
			barracks.assignPilot(p, j);
		}
		
		System.out.println("-------------------------");
		System.out.println("\nAll pilots (post-assign):");
		for(Pilot p : barracks.getAllPilots())
			System.out.println(p);
		
		System.out.println("\nAssigned pilots:");
		for(Pilot p : barracks.getAssignedPilots())
			System.out.println(p);
		
		System.out.println("\nUnassigned pilots:");
		for(Pilot p : barracks.getUnassignedPilots())
			System.out.println(p);

		
		Jet rj = jets[(int)(Math.random()*jets.length)];
		System.out.println("\nunassigning: " + rj.getPilot() + "\n");
		barracks.unassignPilot(rj.getPilot(), rj);
		
		System.out.println("-------------------------");
		System.out.println("\nAll pilots (post-unassign):");
		for(Pilot p : barracks.getAllPilots())
			System.out.println(p);
		
		System.out.println("\nAssigned pilots:");
		for(Pilot p : barracks.getAssignedPilots())
			System.out.println(p);
		
		System.out.println("\nUnassigned pilots:");
		for(Pilot p : barracks.getUnassignedPilots())
			System.out.println(p);
	}
}

final class RandomPilotGenerator {
	static public Pilot randomPilot() {
		int    age    = 22 + (int)(Math.random()*(49 - 22));
		String gender = (Math.random() > 0.4) ? "M" : "F";
		
		String fname = (gender.equals("M")) ? randomName(FIRST_NAME_M) : randomName(FIRST_NAME_F);
		String nickname = randomName(NICKNAMES);
		String lname    = randomName(LAST_NAMES);
		String name     = String.format("%s \"%s\" %s", fname, nickname, lname);

		return new Pilot(name, gender, age);
	}

	private static String randomName(String[] anArray) {
		return anArray[(int)(Math.random() * anArray.length)];
	}
	
	private static final String[] FIRST_NAME_M = {
			"Aaron",
			"Bob",
			"Chuck",
			"Dave",
			"Erik",
			"Frank",
			"Gus",
			"Hank",
			"Ike",
			"John",
			"Karl",
			"Leon",
			"Miles",
			"Nathaniel",
			"Oscar",
			"Pablo",
			"Quinn",
			"Ralph",
			"Stuart",
			"Thelonious",
			"Upton",
			"Victor",
			"Wes",
			"Xavier",
			"Yorick",
			"Zed"
	};
	
	
	private static final String[] FIRST_NAME_F = {
			"Ariel",
			"Belle",
			"Candy",
			"Debbie",
			"Ella",
			"Frances",
			"Gwendolyn",
			"Helen",
			"Iris",
			"Jasmine",
			"Kandi",
			"Leia",
			"Merida",
			"Natalie",
			"Oprah",
			"Pocahontas",
			"Q'andee",
			"Rapunzel",
			"Suzanne",
			"Tiana",
			"Uvalda",
			"Wendy",
			"Xia",
			"Yolanda",
			"Zanzibara"
	};
	
	private static final String[] LAST_NAMES = {
			"Abercrombie",
			"Allman",
			"Alomar",
			"Beck",
			"Belew",
			"Burrell",
			"Clapton",
			"Coryell",
			"Dale",
			"DiMeola",
			"Earl",
			"Frehley",
			"Fripp",
			"Frisell",
			"Garcia",
			"Gilmour",
			"Ginn",
			"Green",
			"Hackett",
			"Harrison",
			"Hendrix",
			"Howe",
			"Hunter",
			"Iommi",
			"Johnson",
			"Jones",
			"Knopfler",
			"Lifeson",
			"Malmsteen",
			"Martino",
			"McLaughlin",
			"Manzanera",
			"Marr",
			"Metheny",
			"Montgomery",
			"Nugent",
			"Osborne",
			"Page",
			"Paul",
			"Ramone",
			"Reed",
			"Reid",
			"Reinhardt",
			"Ribot",
			"Ronson",
			"Rundgren",
			"Richards",
			"Sambora",
			"Schenker",
			"Scofield",
			"Sharrock",
			"Smith",
			"Summers",
			"Taylor",
			"Thompson",
			"Trower",
			"Ulmer",
			"Vai",
			"Van Halen",
			"Vaughan",
			"Ween",
			"Winter",
			"Wood",
			"Young",
			"Zappa",
	};
	
	private static final String[] NICKNAMES = {
			"Apollo",
			"Boom-Boom",
			"Charlie",
			"Cowboy",
			"Cougar",
			"Dogfight",
			"The Edge",
			"Fubar",
			"Goose",
			"Hieronymus",
			"Iceman",
			"Jester",
			"Klaxon",
			"Lucky",
			"Mayday",
			"Maverick",
			"Merlin",
			"Natty Light",
			"Ophelia",
			"Peanut",
			"Q'Bert",
			"Rocky",
			"Sparky",
			"T-Bone",
			"Uvula",
			"Viper",
			"Wizard",
			"YOLO",
			"Zeke",
			"Granny",
			"Ace",
			"Puffy",
			"Macho",
			"Nacho",
			"Gazpacho",
			"Chuey",
			"The Game",
			"Bunny",
			"El Guapo",
			"El Jefe",
			"Lucho Libre",
			"Chupacabra",
			"Flim-Flam",
			"Wild Thing",
			"Swamp-Thing",
			"Ishmael",
			"Sue",
			"Bandit",
			"Smokey",
			"Puggsley",
			"Fester",
			"Wednesday",
			"Cousin Itt",
			"Lurch",
			"Gomez",
			"Morticia",
			"Radar",
			"Hotlips",
			"Hawkeye",
			"Wolverine",
			"Bronco",
			"Buffy",
			"Muffy",
			"Skip",
			"Elvis",
			"The King",
			"The Fridge",
			"Icebox",
			"T.C.B.Y.",
			"Snakeeyes",
			"Snowjob",
			"Roadblock",
			"Cooter",
			"Duke",
			"Roscoe",
			"Boss Hogg",
			"Flash",
			"Cletus",
			"Jethro",
			"Krampus",
			"Buckethead",
			"Bootsy",
			"Moe",
			"Curly",
			"Shemp",
			"Buckwheat",
			"Alfalfa",
			"Froggy",
			"Darlene",
			"Malph",
			"Fonzie",
			"Potsie",
			"Opie",
			"Barney",
			"Goober",
			"Gomer",
			"Gozer",
			"Zuhl",
			"Slimer",
			"Diabeetus",
	};
	
}


