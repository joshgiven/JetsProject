package jets;

import java.util.*;

public class JetsUI {
	private Hangar fleet;
	private Barracks crew;
	private InputPrompter prompter;
	private Scanner kb;
	
	public JetsUI() {
		kb = new Scanner(System.in);
		prompter = new InputPrompter(kb);
		fleet = new Hangar();
		crew = new Barracks();
	}
	
	private void initializeFleet() {
		Jet[] jets = { 
			new Jet("Lockheed F-22A Raptor", 1498, 1839, 361_000_000.00), 
			new Jet("Boeing 777", 590, 8500, 300_000_000.00), 
			new Jet("Cessna Citation X", 700, 3700, 21_999_999.95), 
			new Jet("Lockheed SR-71 Blackbird", 2200, 2900, 34_000_000.95), 
			new Jet("Namath BroadwayJoe-12", 400, 2000, 199_999.95), 
		};

		// assign a pilot to each jet
		// add each jet to our fleet
		for(Jet jet : jets) {
			Pilot pilot = crew.nextUnassignedPilot();
			crew.assignPilot(pilot, jet);
			
			fleet.addJet(jet);
		}
	}	
	
	public static void main(String[] args) {
		JetsUI ui = new JetsUI();
		ui.initializeFleet();
		ui.displaySplash();
		ui.execute();
	}

	enum JetsMenuChoice implements InputPrompter.Choosable {
		LIST  ("(1) List Fleet",                 "1"),
		FAST  ("(2) View Fastest Jet",           "2"),
		RANGE ("(3) View Jet w/ Longest Range",  "3"),
		ADD   ("(4) Add a Jet to Fleet",         "4"),
		PILOTS("(5) List Pilots",                "5"),
		HIRE  ("(6) Hire a Pilot",               "6"),
		QUIT  ("(7) Quit",                       "7")
		;
		
		private String label;
		private String keyOption;

		private JetsMenuChoice(String label, String keyOption) {
			this.label = label;
			this.keyOption = keyOption;
		}
		
		public String label() { return label; }
		public String keyOption() { return keyOption; }
	}

	public void execute() {
		boolean keepRunning = true;
		JetsMenuChoice[] choices =  JetsMenuChoice.values();
		
		prompter.setVerticalSeperator("--------------------------------------\n");
		do {
			switch((JetsMenuChoice)prompter.getUserMenuChoice(choices, "> ")) {
			case LIST:
				displayFleet();
				break;
			case FAST:
				displayFastestJet();
				break;
			case RANGE:
				displayLongestRange();
				break;
			case ADD:
				addJetToFleet();
				break;
			case PILOTS:
				displayPilots();
				break;
			case HIRE:
				hirePilot();
				break;
			case QUIT:
			default:
				System.out.println("\nGoodbye.");
				keepRunning = false;
				break;
			}
		} while(keepRunning);
	}
	
	private void displayFleet() {
		System.out.println("\nCurrent Fleet:");
		for(Jet j : fleet.getJets()) {
			j.display();
			System.out.println();
		}
	}

	private void displayFastestJet() {
		List<Jet> allJets = fleet.getJetsList();

		System.out.println();
		if(allJets == null || allJets.size() == 0) {
			System.out.println("(No jets in fleet)");
			return;
		}
		
		Jet fastestJet = allJets.get(0);

		for(Jet j : allJets) {
			if(j.getSpeed() > fastestJet.getSpeed())
				fastestJet = j;
		}
		
		System.out.println("Fastest jet: ");
		fastestJet.display();
		System.out.println();
	}

	private void displayLongestRange() {
		List<Jet> allJets = fleet.getJetsList();

		System.out.println();
		if(allJets == null || allJets.size() == 0) {
			System.out.println("(No jets in fleet)");
			return;
		}
		
		Jet rangiestJet = allJets.get(0);

		for(Jet j : allJets) {
			if(j.getRange() > rangiestJet.getRange())
				rangiestJet = j;
		}
		
		System.out.println("Longest range jet: ");
		rangiestJet.display();
		System.out.println();
	}

	private void addJetToFleet() {
		System.out.println("\nAdding a jet... ");
		
		String model = prompter.getUserString("Enter Model: ");
		double speed = prompter.getUserDouble("Enter speed (mph): ");
		double range = prompter.getUserDouble("Enter range (miles): ");
		double price = prompter.getUserDouble("Enter price ($): ");
		
		Jet jet = new Jet(model, speed, range, price);
		
		Pilot pilot = crew.nextUnassignedPilot();
		crew.assignPilot(pilot, jet);
		jet.display();
		
		String choice = null;
		do {
			choice = prompter.getUserString("Add new jet to fleet (y/n)? ");
			if(choice.equalsIgnoreCase("Y")) {
				fleet.addJet(jet);
				System.out.println("New jet added.");
				break;
			}
			else if(choice.equalsIgnoreCase("N")) {
				crew.unassignPilot(pilot, jet);
				System.out.println("New jet discarded.");
				break;
			}
		} while(true);
		System.out.println();
	}
	
	private void displayPilots() {
		System.out.println("\nPilot Roster: ");
		List<Pilot> roster = crew.getPilotsList();
		if(roster == null || roster.size() == 0) {
			System.out.println("(No pilots on roster)\n");
			return;
		}
		
		roster = crew.getAssignedPilotsList();
		if(roster != null && roster.size() > 0) {
			System.out.println("  - w/ assigned jets:");
			for(Pilot p : roster) {
				System.out.println("     * " + p);
			}
			System.out.println();
		}
		
		roster = crew.getUnassignedPilotsList();
		if(roster != null && roster.size() > 0) {
			System.out.println("  - w/o assigned jets:");
			for(Pilot p : roster) {
				System.out.println("     * " + p);
			}
			System.out.println();
		}
	}
	
	private void hirePilot() {
		System.out.println("\nHiring a pilot... ");

		String fname  = prompter.getUserString("Enter first name: ");
		String lname  = prompter.getUserString("Enter last name:  ");
		String nname  = prompter.getUserString("Enter nickname:   ");
		String name = String.format("%s \"%s\" %s", fname, nname, lname);
		
		String gender = null;
		do {
			gender = prompter.getUserString("Enter gender (M/F): ");
			gender = gender.toUpperCase();
			if(gender.equals("M") || gender.equals("F")) {
				break;
			}
		} while(true);
		
		int age = prompter.getUserInt("Enter age: ");
		
		Pilot pilot = new Pilot(name, gender, age);
		System.out.println("\n  * " + pilot + "\n");
		
		String choice = null;
		do {
			choice = prompter.getUserString("Add new pilot to roster (y/n)? ");
			if(choice.equalsIgnoreCase("Y")) {
				crew.hirePilot(pilot);
				System.out.println("New pilot added.");
				break;
			}
			else if(choice.equalsIgnoreCase("N")) {
				System.out.println("New pilot discarded.");
				break;
			}
		} while(true);
		System.out.println();
	}

	private void displaySplash() {
		String s = "";
		s += "=================================================\n";
		s += "=                                               =\n";
		s += "=               ##  ######  ######  ######      =\n";
		s += "=              ##  ##        ##    ##           =\n";
		s += "=             ##  ####      ##    ######        =\n";
		s += "=        ##  ##  ##        ##        ##         =\n";
		s += "=       ##  ##  ##        ##        ##          =\n";
		s += "=      ######  ###### #  ## #  ###### #         =\n";
		s += "=                                               =\n";
		s += "=================================================\n";
		s += "=              JEt Tracking System              =\n";
		s += "=================================================\n";

		System.out.println(s);
	}
}
