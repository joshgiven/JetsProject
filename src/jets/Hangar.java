package jets;

import java.util.*;

public class Hangar {
	private List<Jet> jets;
	
	public Hangar() {
		jets = new ArrayList<>();
	}
	
	public void addJet(Jet jet) {
		if(jet == null)
			return;
		
		if(!containsJet(jet)) {
			jets.add(jet);
		}
	}

	private boolean containsJet(Jet jet) {
		return jets.contains(jet);
	}

	public Jet[] getJets() {
		return jets.toArray(new Jet[0]);
	}
}
