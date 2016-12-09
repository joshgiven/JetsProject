package jets;

public class Jet {
	public static double MPH2MACH = 0.00130332;
	
	private String model;
	private double speed;
	private double range;
	private double price;
	private Pilot pilot;
	
	public Jet() {}
	
	public Jet(String model, double speed, double range, double price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}

	public double getMACH() {
		return speed * MPH2MACH;
	}
	
	public void display() {
		System.out.println("model: " + model);
		System.out.println("speed: MACH " + getMACH());
		System.out.println("range: " + range);
		System.out.println("price: " + price);
		System.out.println("pilot: \n" + price);
		//pilot.display();
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
	
}
