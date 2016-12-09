package jets;

public abstract class ArrayWrapper {
	public static final int DEFAULT_SIZE = 20;
	
	private Object[] items;
	private int numItems;
	
	ArrayWrapper() {
		this(DEFAULT_SIZE);
	}
	
	ArrayWrapper(int numItems) {
		this.numItems = numItems;
		items = new Object[this.numItems];
	}
	
	Object[] getItemArray() {
		return items;
	}
	
	Object getItemAt(int i) {
		if(i < 0 || i > numItems) {
			return null;
		}
		else {
			return items[i];
		}
	}
	
	
}
