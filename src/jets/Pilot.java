package jets;

public class Pilot {
	private String name;
	private String nickname;
	private String gender;
	private int age;
	private String quote;
	
	public Pilot(String name, String nickname, String gender, int age, String quote) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.gender = gender;
		this.age = age;
		this.quote = quote;
	}

	public String toString() {
		return "Pilot [name=" + name + ", nickname=" + nickname + ", gender=" + gender + ", age=" + age + ", quote="
				+ quote + "]";
	}

	public void display() {
		System.out.println(this.toString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
}
