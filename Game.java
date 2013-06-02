import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Game {
  private Map<String, Location> rooms = new HashMap<String, Location>();
	Map <Exit, String> exitTo = new HashMap<Exit, String>();
	Map<String, Item> gameItems = new HashMap<String, Item>();
	Location curr = new Location();
	Location Start = new Location();
	String GameOverMessage = "";
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
	
	public void run(){
		this.readfromtextFile();
		playgame();
		System.out.println("Thank you for playing. Lord SauroVader requests you give this game a 100% AWESOME rating.");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::THE END:::::::::::::::::::::::::::::::::::::::::");
		
	}
	
	public void playgame() {
		System.out.println("Welcome to TheAwesomestGameEverCreated. This game contains extremely high definition visuals, \nthus it requires the most powerful graphics card ever created: your imagination. We hope you possess it.");
		Player Alot = new Player(Start);
		System.out.println();
		System.out.println(Start.getDescription());
		Start.setHasEntered(true);
		Boolean containerOpened = false;
		curr = Start;
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    Boolean winGame = false;
		    System.out.print(">");
		    String s = bufferRead.readLine();
		    while(!(s.equalsIgnoreCase("quit"))) {
		    	if(s.length() == 0) {
		    		System.out.print(">");
		    		s = bufferRead.readLine();
		    	}
		    	else {
		    		String command = s.toUpperCase();
		    		Boolean dir = false;
		    		String direction = null;
		    		if(command.contains("INV")) {
		    			System.out.println("::::::::Inventory::::::::");
		    			System.out.println(Alot.getInventory().toString());
		    		}
		    		if(command.contains("GO ")){
		    			String temp[] = command.split(" ");
		    			command = temp[1];
		    		}
		    		for(int i = 0; i < 12; i++) {
		    			if(((command.equals(Exit.dirName[i]))) || (command.equals(Exit.shortDirName[i]))) {
		    				dir = true;
		    				direction = Exit.dirName[i];
		    				break;
		    			}
		    		}
		    		if(dir) {
		    			Boolean found = false;
			    		for(Enumeration<Exit> e = curr.getExits().elements(); e.hasMoreElements();) {
			    			Exit an_exit = (Exit) e.nextElement();
			    			if((an_exit.getDirection().compareTo(direction) == 0)) {
			    				Alot.takeExit(an_exit);
			    				curr = Alot.getLocation();
			    				found = true;
			    				containerOpened = false;
			    			}
			    		}
			    		if(!found)
			    			System.out.println("Nothing to see there.");
		    		}
		    		else if(command.contains("TALK")){
		    			Boolean found = false;
		    			CharacterObject toTalkTo = null;
		    			if (!(curr.getCharacters().isEmpty())) {
		    				for(int i = 0; i < curr.getCharacters().size(); i++) {
		    					if(command.contains(curr.getCharacters().elementAt(i).toString().toUpperCase())) {
		    						toTalkTo = (CharacterObject) curr.getCharacters().elementAt(i);
		    						found = true;
		    						break;
		    					}
		    				}
		    			}
		    			if((found) && (!toTalkTo.equals(null))){
				    		System.out.println("You are now talking to "+toTalkTo.toString());
			    			System.out.println("Hint: To stop talking, just type stop!");
			    			int i = 0;
			    			String convo = "";
			    			while((!(convo.equalsIgnoreCase("stop"))) && (i < toTalkTo.getDialogues().size())) {
			    				System.out.println(toTalkTo.toString()+" says: " +toTalkTo.getDialogues().elementAt(i));
			    				if(toTalkTo.getDialogues().elementAt(i).contains("(y/n)")) {
			    		    		System.out.print(">");
			    					convo = bufferRead.readLine();
			    					if(convo.equalsIgnoreCase("y")){
			    						i++;
			    						System.out.println(toTalkTo.toString()+" says: " +toTalkTo.getDialogues().elementAt(i));
			    			    		System.out.print(">");
			    						convo = bufferRead.readLine();
			    						if(toTalkTo.answerLookingFor(convo)){
				    						Alot.addtoInventory(toTalkTo.getItems().elementAt(0));
				    						toTalkTo.removeItem(toTalkTo.getItems().elementAt(0));
				    						break;
			    						}
			    						else
			    							i--;
			    					}
			    					else if(convo.equalsIgnoreCase("n"))
			    						break;
			    				}
			    				else {
			    		    		System.out.print(">");
			    					convo = bufferRead.readLine();
			    					i++;
			    				}
			    			}
			    			System.out.println("You've now stopped talking to "+toTalkTo.toString());
		    			}
		    			else
		    				System.out.println("The person you wish to talk is not available. Please leave a message at the tone.");
		    		}
		    		else if (command.contains("OPEN")) {
		    			Boolean found = false;
		    			if (!(curr.getContainers().isEmpty())) {
		    				for(int i = 0; i < curr.getContainers().size(); i++) {
			    				if(command.contains(curr.getContainers().elementAt(i).toString().toUpperCase())) {
					    			Container toOpen = (Container) curr.getContainers().elementAt(i);
				    				System.out.println("You found: " +toOpen.getItems().toString());
				    				containerOpened = true;
				    				found = true;
				    				break;
			    				}
		    				}
		    				if(!found) {
		    					System.out.println("The container you mentioned doesn't exist.");
		    				}
		    			}
		    			else
		    				System.out.println("Nothing to open.");
		    		}
		    		else if ((command.contains("TAKE")) ||(command.contains("GET"))) {
		    			Boolean found = false;
		    			for(int i = 0; i < curr.getItems().size(); i++) {
		    				if(command.contains(curr.getItems().elementAt(i).toString().toUpperCase())) {
		    					if(((Item)curr.getItems().elementAt(i)).isitinaContainer()) {
		    						if(containerOpened) {
				    					Alot.addtoInventory((Item)curr.getItems().elementAt(i));
				    					System.out.println("You've added "+Alot.getInventory().lastElement().toString()+ " to your inventory.");
				    					((Item)curr.getItems().elementAt(i)).removefromContainer();
				    					curr.removeItem((Item)curr.getItems().elementAt(i));
				    					found = true;
		    						}
		    						else
		    							System.out.println("But where is it?");
		    					}
		    					else {
		    						Alot.addtoInventory((Item)curr.getItems().elementAt(i));
			    					System.out.println("You've added "+Alot.getInventory().lastElement().toString()+ " to your inventory.");
			    					((Item)curr.getItems().elementAt(i)).removefromContainer();
			    					curr.removeItem((Item)curr.getItems().elementAt(i));
			    					found = true;
		    					}
		    				}	
		    			}
		    			if(!found) {
		    				System.out.println("These are not the droids you are looking for.");
		    			}
		    		}
		    		
		    		else if (command.contains("SHAVE")) {
		    			Boolean found = false;
		    			CharacterObject toShave = null;
		    			if (!(curr.getCharacters().isEmpty())) {
		    				for(int i = 0; i < curr.getCharacters().size(); i++) {
		    					if(command.contains(curr.getCharacters().elementAt(i).toString().toUpperCase())) {
		    						toShave = (CharacterObject) curr.getCharacters().elementAt(i);
		    						found = true;
		    						break;
		    					}
		    				}
		    			}
		    			if(found) {
			    			if(toShave.toString().equals("Yak")){
			    				System.out.println("While shaving the Yak, you unearthed an item! \nYou found: "+(toShave.getItems().elementAt(0)).toString());
			    			}
			    			else if(toShave.toString().equals("Dustan")) {
			    				System.out.println("DUDE! WHAT ARE YOU DOING? NOT COOL MAN!");
			    			}
			    			else if(toShave.toString().equalsIgnoreCase("The Evil Mister Wizarderer Sith Lord Sarumon- the SauroVader")) {
			    				System.out.println("Have you reading my diary? I might have to change my mind about you...");
			    			}
		    			}
		    			else
		    				System.out.println("What do you want to shave?");
		    		}
		    		else if (command.contains("THROW")) {
		    			Boolean found = false;
		    			Item toThrow = null;
		    			if((curr.toString().equalsIgnoreCase("Amon Amarth"))) {
			    			for(int i = 0; i < Alot.getInventory().size(); i++) {
			    				if(command.contains(Alot.getInventory().elementAt(i).toString().toUpperCase())) {
			    					toThrow = (Item) Alot.getInventory().elementAt(i);
			    					found = true;
			    				}
			    			}
			    			
			    			if(found) {
			    				if(toThrow.toString().equalsIgnoreCase("The One Ring")){
			    					System.out.println("Good job. Here's a cookie.");
			    					Alot.removefromInventory(toThrow);
			    				}
			    				else if(toThrow.toString().equalsIgnoreCase("iPhone7")){
			    					winGame = true;
			    					Alot.removefromInventory(toThrow);
			    				}
			    				else {
			    					System.out.println("Why would you do such a thing?");
			    					Alot.removefromInventory(toThrow);
			    				}
			    			}
			    			else
			    				System.out.println("What are you trying to throw?");
		    			}
		    			else
		    				System.out.println("You can't throw anything at the moment, Alot. Behave.");
		    		}
		    		else if (command.contains("USE")) {
		    			if(command.contains("MAGIC ROCK")) {
		    				System.out.println("You have uncovered a hidden feature. You wint 50 points. The glow of the magic rock makes your irresistible to women and yak alike.");
		    			}
		    			if(command.contains("iPhone7")) {
		    				System.out.println("Tch tch tch.");
		    			}
		    			else {
		    				System.out.println("You can't use! You are supposed to be setting an example for kids!");
		    				System.out.println("From Urban Dictionary(TM): To do any form of narcotic, as in marijuana, crack, PCP, LSD, etc. The heat use this term when questioning a suspected drug user.\nCop: Hey kid are you using?\nKid: No officer, never.");
		    			}
		    		}
		    		else if (command.contains("LOOK")) {
		    			Boolean found = false;
		    			if(!curr.getContainers().isEmpty()) {
		    				System.out.println("This room contains: "+curr.getContainers().toString());
		    				found = true;
		    			}
		    			if(!curr.getCharacters().isEmpty()) {
		    				System.out.println("There are people you can talk to here: "+curr.getCharacters().toString());
		    				found = true;
		    			}
		    			if(!found)
		    				System.out.println("Nothing to view here... This place is deader than Alderaan.");
		    		}
		    		else if((command.contains("EXAMINE")) || (command.contains("INSPECT"))) {
		    			Boolean found = false;
		    			for(int i = 0; i < Alot.getInventory().size(); i++) {
		    				if(command.contains(((Item)Alot.getInventory().elementAt(i)).toString().toUpperCase())) {
		    					System.out.println(((Item)Alot.getInventory().elementAt(i)).getDescription());
		    					found = true;
		    					break;
		    				}
		    			}
		    			if(!found){
		    				System.out.println("You do not have this item to inspect.");
		    			}
		    		}
		    		else if(command.contains("HELP")) {
		    			System.out.println("Welcome to the Help Menu");
		    			System.out.println("Type GO followed by any direction (eg. north, ne, s, southwest etc) or just the direction, to move.");
		    			System.out.println("To take an object, type TAKE, followed by the object name. eg. take keys.");
		    			System.out.println("To take a sneak peak at your inventory, type INV or INVENTORY.");
		    			System.out.println("To take a look around your location, type LOOK.");
		    			System.out.println("To open a container, type OPEN, followed by name, eg. open desk.");
		    			System.out.println("It is recommended to TALK with as many characters as possible.");
		    			System.out.println("In case of an encounter with a large mammal, you might want to SHAVE it. You never know what you might find.");
		    			System.out.println("Type THROW in order to hurl tiny shiny jewels with dark power into firy, molten lava.");
		    			System.out.println("Type EXAMINE or INSPECT to find out details about items in your inventory.");
		    			System.out.println("Type QUIT to end game.");
		    			System.out.println("Thank you. Hope you enjoy playing!");
		    			
		    		}
		    		else {
		    			System.out.println("Not a valid command. Read the instructions. Type HELP.");
		    		}
		    		if(!winGame) {
			    		System.out.print(">");
				    	s = bufferRead.readLine();
		    		}
		    		else {
		    			System.out.println("ERMEHGERD! YOU HAS WON THE INTERWEBZZZZZZZ...");
		    			System.out.println(GameOverMessage);
		    			s = "quit";
		    		}
			    }
		    }
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void readfromtextFile() {
		File f = new File("CaveDesc.txt");	// Connects f to CaveDesc.txt
		try {
			Scanner s = new Scanner(f);
			try {
				while(s.hasNext()) {
					String line = s.nextLine();
					switch(line) {
					case "Locations:":
						String getLocs = s.nextLine();
						while(!(getLocs.equals("end"))) {
							Location l = new Location(getLocs);
							rooms.put(getLocs, l);
							getLocs = s.nextLine();
						}
						break;
					case "Location:":
						curr = rooms.get(s.nextLine());
						break;
					case "Long:":
						curr.setDescription(s.nextLine());
						break;
					case "Short:":
						curr.setShortDescription(s.nextLine());
						break;
					case "Items:":
						String itemIter = s.nextLine();
						while(!(itemIter.equals("end"))) {
							if(itemIter.charAt(0)!= '"') {
								Item i = new Item(itemIter.trim());
								itemIter = s.nextLine();
								if(itemIter.charAt(0)== '"') {
									i.setDescription(itemIter);
									itemIter = s.nextLine();
								}
								gameItems.put(i.toString(), i);
								curr.addItem(i);
							}
						}
						break;
					case "Container:":
						line = s.nextLine();
						String ar [] = line.split("-");
						Container cont = new Container(ar[0]);
						ar = ar[1].split(", ");
						int ln = ar.length;
						cont.setLocation(curr);
						for(int i = 0; i <ln; i++) {
							cont.addItem(gameItems.get(ar[i]));
							gameItems.get(ar[i]).putinContainer(cont);
						}
						curr.addContainer(cont);
						break;
					case "Exit:":
						String exitIter = s.nextLine();
						Exit e = new Exit();
						if(!(curr.equals(null))) {
							e.setCurrentLocation(curr);
							curr.addExit(e);
						}
						while(!(exitIter.equals("end"))){
							if(exitIter.contains("Dir")) {
								int p;
								p = Integer.parseInt(exitIter.substring(4));
								e.setDirection(p);
								exitIter = s.nextLine();
							}
							if(exitIter.contains("ToL")) {
								e.setLeadsTo(rooms.get(exitIter.substring(4)));
								exitTo.put(e, exitIter.substring(4));
								exitIter = s.nextLine();
							}
							if(exitIter.contains("Lok")) {
								String item = exitIter.substring(4);
								gameItems.get(item).setKeyTo(e);
							}	
							exitIter = s.nextLine();
						}
						break;
						
					case "Character:":
						String characterize = s.nextLine();
						String ar1[] = characterize.split(":");
						while(!(ar1[0].equals("end"))) {
							if(ar1[0].equals("Name")) {
								CharacterObject chr = new CharacterObject(ar1[1].trim());
								curr.addCharacter(chr);
							} else if(ar1[0].equals("Description")) {
								curr.returnlastChar().setDescription(ar1[1].trim());
							} else if (ar1[0].equals("Dialogue")) {
								curr.returnlastChar().addDialogue(ar1[1].trim());
							} else if(ar1[0].equals("Item")) {
								curr.returnlastChar().addItem(gameItems.get(ar1[1].trim()));
							} else if(ar1[0].equals("Solution")) {
								curr.returnlastChar().setSolution(ar1[1].trim());
							} else if(ar1[0].equals("Correct")) {
								curr.returnlastChar().setCorrectResponse(ar1[1]);
							} else if(ar1[0].equals("Incorrect")) {
								curr.returnlastChar().setInCorrectResponse(ar1[1]);
							}
							characterize = s.nextLine();
							ar1 = characterize.split(":");
						}
						break;
					case "Start Location:":
						Start= rooms.get(s.nextLine());
						break;
					case "Game Over:":
						GameOverMessage = s.nextLine();
						break;
					default:
							break;
						 
					}
				} 
			}

			finally {
					s.close();
			}
		} 
		catch (Exception e) {
			System.err.println("Could not open file");
		} 
	}
}
