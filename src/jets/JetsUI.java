package jets;

import java.util.Scanner;

public class JetsUI {
	private Hangar fleet;
	private Menu menu;
	private Scanner kb;
	
	public JetsUI() {
		menu = new Menu();
		kb = new Scanner(System.in);
		
		fleet = new Hangar();
		initializeFleet();
	}
	
	private void initializeFleet() {
		Jet[] jets = { 
			new Jet("Lockheed F-22A Raptor", 800, 3001, 19_999_999.95), 
			new Jet("Boeing 777", 500, 4000, 2_999_999.95), 
			new Jet("Namath Broadway-12", 400, 2000, 199_999.95), 
			new Jet("Stark Quinjet mk3", 2000, 24000, 1_999_999_999.95), 
			new Jet("Jetstar J-7", 700, 3000, 1_999_999.95), 
		};

		for(Jet jet : jets) {
			fleet.addJet(jet);
		}
	}
	
	public static void main(String[] args) {
		JetsUI ui = new JetsUI();
		ui.execute();
	}
	
	public void execute() {
		boolean keepRunning = true;
		JetsUIChoice[] choices =  JetsUIChoice.values();

		do {
			switch((JetsUIChoice)menu.getUserChoice(choices, "enter: ")) {
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
				System.out.println("quitting...");
				keepRunning = false;
				break;
			}
		} while(keepRunning);
	}
	
	private void displayFleet() {
		// TODO Auto-generated method stub
		for(Jet j : fleet.getJets()) {
			j.display();
		}
	}

	private void displayFastestJet() {
		// TODO Auto-generated method stub
		
	}

	private void addJetToFleet() {
		// TODO Auto-generated method stub
		
	}

	private void displayLongestRange() {
		// TODO Auto-generated method stub
		
	}

	enum JetsUIChoice implements Menu.Choosable {
		LIST ("(1) List Fleet",                 "1"),
		FAST ("(2) View Fastest Jet",           "2"),
		RANGE("(3) View Jet w/ Longest Range",  "3"),
		ADD  ("(4) Add a Jet to Fleet",         "4"),
		QUIT ("(5) Quit",                       "5")
		;
		
		private String label;
		private String keyOption;

		private JetsUIChoice(String label, String keyOption) {
			this.label = label;
			this.keyOption = keyOption;
		}
		
		public String label() { return label; }
		public String keyOption() { return keyOption; }
	}
}
