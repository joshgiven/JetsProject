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
	
	Pilot[] pilots;
	int numPilots;
	int numAssigned;
	
	public Barracks() {
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

	public void assignPilot(Pilot pilot, Jet jet) {
		if(pilot == null || jet == null)
			return;

		if(jet.getPilot() != null) {
			// add old pilot to back to unassigned pool
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
		if(index < numAssigned)
			return;
		
		// juggle array
		Pilot tmp = pilots[numAssigned];
		pilots[numAssigned] = pilots[index];
		pilots[index] = tmp;
		numAssigned++;

		// stick the pilot into the jet
		jet.setPilot(pilot);
	}
	
	public Pilot nextUnassignedPilot() {
		// if no pilots available, hire a new (random) one
		if(numPilots == numAssigned) {
			hirePilot(null);
		}
		
		return pilots[numAssigned];
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
		for(int i=0; i < 20; i++)
			System.out.println(RandomPilotGenerator.randomPilot());
	}
}

abstract class RandomPilotGenerator {
	static public Pilot randomPilot() {
		int    age    = 22 + (int)(Math.random()*(49 - 22));
		String gender = (Math.random() > 0.5) ? "M" : "F";
		
		String fname = (gender.equals("M")) ? randomName(firstNamesM) : randomName(firstNamesF);
		String nickname = randomName(nickNames);
		String lname    = randomName(lastNames);
		String name     = String.format("%s \"%s\" %s", fname, nickname, lname);

		return new Pilot(name, gender, age);
	}

	private static String randomName(String[] anArray) {
		return anArray[(int)(Math.random() * anArray.length)];
	}
	
	private static final String[] firstNamesM = {
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
	
	
	private static final String[] firstNamesF = {
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
	
	private static final String[] lastNames = {
			"Allman",
			"Beck",
			"Clapton",
			"Dale",
			"Earl",
			"Frehley",
			"Gilmour",
			"Hendrix",
			"Iommi",
			"Johnson",
			"Knopfler",
			"Lifeson",
			"Malmsteen",
			"Nugent",
			"Osborne",
			"Page",
			"Richards",
			"Schenker",
			"Taylor",
			"Van Halen",
			"Winter",
			"Young",
			"Zappa",
	};
	
	private static final String[] nickNames = {
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
			"Swamp-Thing",
			"Ishmael",
			"Sue",
			"Bandit",
			"Smokey",
			"Puggsley",
			"Fester",
			"Wednesday",
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


