package ElectionModel;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Vector;

public class mySet implements Serializable, Comparable<Citizen> {
	private Vector<Citizen> allCitizens = new Vector<Citizen>();

	public mySet(Vector<Citizen> theLIst) {
		allCitizens = theLIst;
	}

	public boolean addCitizenToList(Citizen theCitizen) {
		for (int i = 0; i < allCitizens.size(); i++) {
			if (theCitizen.getId().equalsIgnoreCase(allCitizens.elementAt(i).getId())) {
				return true;
			}
		}
		allCitizens.add(theCitizen);
		return false;
	}

	public void deleteLastCitizen() {
		allCitizens.remove(allCitizens.size() - 1);
	}

	@Override
	public int compareTo(Citizen o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Vector<Citizen> getSetList(){
		return this.allCitizens;
	}

	@Override
	public String toString() {
		LinkedHashSet<Citizen> hashCitizen = new LinkedHashSet<Citizen>(allCitizens);
		allCitizens.clear();
		allCitizens.addAll(hashCitizen);
		StringBuffer temp = new StringBuffer();
		temp.append("The citizens:\n\n");
		for (int i = 0; i < allCitizens.size(); i++) {
			temp.append(allCitizens.elementAt(i).toString() + "\n");
		}
		return temp.toString();
	}

}
