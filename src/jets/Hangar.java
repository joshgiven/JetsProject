package jets;

public class Hangar {
	private Jet[] jets;
	private int numJets;

	public Hangar() {
		jets = new Jet[3];
		numJets = 0;
	}
	
	public void addJet(Jet jet) {
		if(numJets+1 == jets.length) {
			Jet[] newArray = new Jet[jets.length*2];
			System.arraycopy(jets, 0, newArray, 0, numJets);
			jets = newArray;
		}
		
		// Assumes jet isn't alreay in the hangar
		jets[numJets] = jet;
		numJets++;
	}

	public Jet[] getJets() {
		Jet[] jetList = new Jet[numJets];
		System.arraycopy(jets, 0, jetList, 0, numJets);
		
		return jetList;
	}
}
