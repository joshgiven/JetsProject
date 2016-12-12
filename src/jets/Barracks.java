package jets;

public class Barracks {
	//
	// array holds assigned, then unassigned, then buffer
	//
	//                   numPilots
	//     numAssigned     (5)
	//         (2)          |
	//          |           |
	//  p1  p2  p3  p4  p5  null null null ...
	//
	//  (where p1 and p2 are assigned)
	//
	
	private Pilot[] pilots;
	private int numPilots;
	private int numAssigned;
	
	public Barracks() {
		// chose 3 so that we exercise expandArray()
		pilots = new Pilot[3];
		numPilots = 0;
		numAssigned = 0;
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
		if(numPilots == numAssigned) {
			hirePilot(null);
		}
		
		return pilots[numAssigned];
	}

	private void addPilot(Pilot pilot) {
		if(containsPilot(pilot))
			return;
		
		if(pilotArrayFull())
			expandArray();
		
		pilots[numPilots] = pilot;
		numPilots++;
	}
	
	private boolean pilotArrayFull() {
		return numPilots == pilots.length;
	}

	public void unassignPilot(Pilot pilot, Jet jet) {
		if(jet == null || pilot ==null)
			return;
		
		if(pilot != jet.getPilot()) {
			return;
		}
		
		int index = indexOf(pilot);
		// not found   || not in assigned pool (?)
		if(index == -1 || index >= numAssigned) {
			// do nothing
		}
		// in assigned pool
		else {
			// add old pilot to back to unassigned pool
			Pilot tmp = pilots[numAssigned-1];
			pilots[numAssigned-1] = pilots[index];
			pilots[index] = tmp;
			numAssigned--;
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
		int index = indexOf(pilot);

		// if pilot not found, let's go ahead and add him
		// (could just return here)
		if(index == -1) {
			//return;
			addPilot(pilot);
			index = indexOf(pilot);
		}
		// if pilot already assigned
		else if(index < numAssigned) {
			return;
		}
		
		// juggle array
		// stick our pilot in next available "assigned" spot
		Pilot tmp = pilots[numAssigned];
		pilots[numAssigned] = pilots[index];
		pilots[index] = tmp;
		numAssigned++;

		// stick the pilot into the jet
		jet.setPilot(pilot);
	}
	
	public Pilot[] getAllPilots() {
		Pilot[] arr = new Pilot[numPilots];
		System.arraycopy(pilots, 0, arr, 0, numPilots);
		return arr;
	}
	
	public Pilot[] getAssignedPilots() {
		Pilot[] arr = new Pilot[numAssigned];
		System.arraycopy(pilots, 0, arr, 0, numAssigned);
		return arr;
	}

	public Pilot[] getUnassignedPilots() {
		int len = numPilots - numAssigned;
		Pilot[] arr = new Pilot[len];
		
		System.arraycopy(pilots, numAssigned, arr, 0, len);
		return arr;
	}

	public boolean containsPilot(Pilot pilot) {
		return !(indexOf(pilot) == -1);
	}
	
	private int indexOf(Pilot pilot) {
		int index = -1;
		for(int i=0; i < numPilots; i++) {
			if(pilot.equals(pilots[i]))
				return i;
		}
		
		return index;
	}

	private void expandArray() {
		Pilot[] newArray = new Pilot[pilots.length*2];
		System.arraycopy(pilots, 0, newArray, 0, numPilots);
		pilots = newArray;
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


