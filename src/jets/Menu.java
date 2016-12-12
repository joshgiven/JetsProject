package jets;

import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
	public interface Choosable {
		String label();
		String keyOption();

		default Choosable keyOptionChoice(String o) {
			Choosable mc = null;
			if(this instanceof Enum) {
				try {
					Class clazz = this.getClass();
					Method m = clazz.getMethod("values");
					Enum[] values = (Enum[])m.invoke(null);
					for(Enum e : values) {
						Choosable item = (Choosable) e;
						if(item.keyOption().equals(o))
							mc = item;				
					}
				}
				catch(Exception e) {
					// Eat the exception, return null
					//System.out.println("BOOM! " + e.getMessage());
				}
			}
			
			return mc;
		}
		
		default Choosable labelChoice(String label) {
			return null;
		}

	}
	
	Scanner kb;
	String selectionDelimiter = "\n";
	String verticalSeperator = "";
	String retryMessage = "invalid selection. try again.";
	
	public Menu(Scanner userInput) {
		this.kb = userInput;
	}
	
	public void displayMenu(Choosable[] choices){ //list menu options
		for (Choosable m : choices){
			System.out.print(m.label() + selectionDelimiter);
		}
	}
	
	public Choosable getUserChoice(Choosable[] choices, String prompt){
		if(choices == null || choices.length == 0) {
			return null;
		}
		
		System.out.print(verticalSeperator);
		displayMenu(choices);
		System.out.print(verticalSeperator);
		
		Choosable first = choices[0];
		Choosable m = null;
		String choice;
		do {
			System.out.print(prompt);
			choice = kb.next();
			m = first.keyOptionChoice(choice.toUpperCase());
			if(m == null) {
				System.out.println(retryMessage);
			}
			else {
				break;
			}
		} while(true);

		return m;
	}
	
	
	// TODO: split these user input methods off to different class
	//   They don't really belong here.
	public String getUserString(String prompt){
		Pattern p = kb.delimiter();
		kb.useDelimiter("\n");
		
		String name = null;
		while(name == null || name.equals("")) {
			System.out.print(prompt);
			name = kb.next();
		}
		
		kb.useDelimiter(p);
		return name;
	}
	
	public int getUserInt(String prompt) {
		do {
			System.out.print(prompt);
			if(kb.hasNextInt()) {
				return kb.nextInt();
			}
			else {
				kb.next(); // eat bad data
			}
		} while(true);
	}
	
	public double getUserDouble(String prompt) {
		do {
			System.out.print(prompt);
			if(kb.hasNextDouble()) {
				return kb.nextDouble();
			}
			else {
				kb.next(); // eat bad data
			}
		} while(true);
	}


	public String getSelectionDelimiter() {
		return selectionDelimiter;
	}

	public void setSelectionDelimiter(String delimiter) {
		this.selectionDelimiter = delimiter;
	}

	public String getVerticalSeperator() {
		return verticalSeperator;
	}

	public void setVerticalSeperator(String verticalSeperator) {
		this.verticalSeperator = verticalSeperator;
	}

	public String getRetryMessage() {
		return retryMessage;
	}

	public void setRetryMessage(String retryMessage) {
		this.retryMessage = retryMessage;
	}
	
//	public static void main(String[] args) {
//		Menu menu = new Menu();
//		
//		System.out.println("Menu:");
//		System.out.println(menu.getUserChoice(Choice.values(), "Pick one: "));
//	}

//public enum Choice implements Choosable {
//	LIST ("(1) List Fleet",                 "1"),
//	FAST ("(2) View Fastest Jet",           "2"),
//	RANGE("(3) View Jet w/ Longest Range",  "3"),
//	ADD  ("(4) Add a Jet to Fleet",         "4"),
//	QUIT ("(5) Quit",                       "5")
//	;
//	
//	private String label;
//	private String keyOption;
//
//	private Choice(String label, String keyOption) {
//		this.label = label;
//		this.keyOption = keyOption;
//	}
//	
//	public String label() { return label; }
//	public String keyOption() { return keyOption; }
//}
}

