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
	    
		System.out.println("*************************************************");
		System.out.println("* model: " + model);
		System.out.println("*************************************************");
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
		if(price >= 0)
			this.price = price;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(range);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Jet other = (Jet) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(range) != Double.doubleToLongBits(other.range))
			return false;
		if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed))
			return false;
		return true;
	}
	
}
