package jets;

import java.util.Scanner;

public class JetsUI {
	private Hangar fleet;
	private Barracks crew;
	private Menu menu;
	private Scanner kb;
	
	public JetsUI() {
		kb = new Scanner(System.in);
		menu = new Menu(kb);
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

	enum JetsMenuChoice implements Menu.Choosable {
		LIST ("(1) List Fleet",                 "1"),
		FAST ("(2) View Fastest Jet",           "2"),
		RANGE("(3) View Jet w/ Longest Range",  "3"),
		ADD  ("(4) Add a Jet to Fleet",         "4"),
		QUIT ("(5) Quit",                       "5")
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
		
		menu.setVerticalSeperator("--------------------------------------\n");
		do {
			switch((JetsMenuChoice)menu.getUserChoice(choices, "> ")) {
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
		Jet[] allJets = fleet.getJets();

		System.out.println();
		if(allJets == null || allJets.length == 0) {
			System.out.println("No jets in fleet");
			return;
		}
		
		Jet fastestJet = allJets[0];

		for(Jet j : allJets) {
			if(j.getSpeed() > fastestJet.getSpeed())
				fastestJet = j;
		}
		
		System.out.println("Fastest jet: ");
		fastestJet.display();
		System.out.println();
	}

	private void displayLongestRange() {
		Jet[] allJets = fleet.getJets();

		System.out.println();
		if(allJets == null || allJets.length == 0) {
			System.out.println("No jets in fleet");
			return;
		}
		
		Jet rangiestJet = allJets[0];

		for(Jet j : allJets) {
			if(j.getRange() > rangiestJet.getRange())
				rangiestJet = j;
		}
		
		System.out.println("Longest range jet: ");
		rangiestJet.display();
		System.out.println();
	}

	private void addJetToFleet() {
		System.out.println("\nAdding a jet...");
		
		String model = menu.getUserString("Enter Model: ");
		double speed = menu.getUserDouble("Enter speed (mph): ");
		double range = menu.getUserDouble("Enter range (miles): ");
		double price = menu.getUserDouble("Enter price ($): ");
		
		Jet jet = new Jet(model, speed, range, price);
		
		Pilot pilot = crew.nextUnassignedPilot();
		crew.assignPilot(pilot, jet);
		jet.display();
		
		String choice = null;
		do {
			choice = menu.getUserString("Add new jet to fleet (y/n)? ");
			if(choice.equalsIgnoreCase("Y")) {
				fleet.addJet(jet);
				System.out.println("New jet added.");
				break;
			}
			else if(choice.equalsIgnoreCase("N")) {
				System.out.println("New jet discarded.");
				break;
			}
		} while(true);
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
