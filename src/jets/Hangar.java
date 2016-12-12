package jets;

public class Hangar {
	private Jet[] jets;
	private int numJets;

	public Hangar() {
		// chose 3 so that we exercise expandArray()
		jets = new Jet[3];
		numJets = 0;
	}
	
	public void addJet(Jet jet) {
		if(jet == null)
			return;
		
		if(numJets == jets.length) 
			expandArray();
		
		if(!containsJet(jet)) {
			jets[numJets] = jet;
			numJets++;
		}
	}

	private boolean containsJet(Jet jet) {
		if(jets.length == 0) {
			return false;
		}
		
		for(int i=0; i < numJets; i++) {
			if(jets[i].equals(jet))
				return true;
		}

		return false;
	}

	private void expandArray() {
		Jet[] newArray = new Jet[jets.length*2];
		System.arraycopy(jets, 0, newArray, 0, numJets);
		jets = newArray;
	}
	
	public Jet[] getJets() {
		Jet[] jetList = new Jet[numJets];
		System.arraycopy(jets, 0, jetList, 0, numJets);
		
		return jetList;
	}
}
