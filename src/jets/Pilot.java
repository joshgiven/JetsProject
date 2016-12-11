package jets;

public class Pilot {
	private String name;
	private String gender;
	private int age;
	
	public Pilot(String name, String gender, int age) {
		super();
		this.name = name;
		setGender(gender);
		setAge(age);
	}

	public String toString() {
		return String.format("%s (Sex:%s, Age:%d)", name, gender, age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		gender = gender.toUpperCase();
		if(gender.equals("F"))
			this.gender = "F";
		else
			this.gender = "M";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age >= 0)
			this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilot other = (Pilot) obj;
		if (age != other.age)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
