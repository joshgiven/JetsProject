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
		setSpeed(speed);
		setRange(range);
		setPrice(price);
	}

	public double getMACH() {
		return speed * MPH2MACH;
	}
	
	public void display() {
		String pattern = "$###,###,###,###.###";
		java.text.DecimalFormat formatter = new java.text.DecimalFormat(pattern);
	    String fmtPrice = formatter.format(price);
		//                  =================================================
		System.out.println("*************************************************");
		System.out.println("* model: " + model);
		System.out.printf( "* speed: Mach %.3f\n", getMACH());
		System.out.printf( "* range: %.0f nmi\n", range);
		System.out.println("* price: " + fmtPrice);
		System.out.println("* pilot: " + pilot);
		System.out.println("*************************************************");
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
		if(speed >= 0)
			this.speed = speed;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		if(range >= 0)
			this.range = range;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(range >= 0)
			this.price = price;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
}
