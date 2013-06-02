import java.util.Vector;


public class Location {
  private String roomTitle;
	private String roomDescription;
	private String shortroomDescription;
	private Vector<Exit> listExits;
	private Vector<Item> listItems = new Vector<Item>();
	private Vector<Container> listContainers = new Vector<Container>();
	private Vector<CharacterObject> listCharacters = new Vector<CharacterObject>();
	private Boolean hasEntered = false;
	
	public Location() {
		this.roomTitle = new String();
		this.roomDescription = new String();
		this.shortroomDescription = new String();
		this.listExits = new Vector<Exit>();
	}
	
	public Location(String Title) {
		this.roomTitle = Title;
		this.roomDescription = new String();
		this.shortroomDescription = new String();
		this.listExits = new Vector<Exit>();		
	}
	
	public Location(String Title, String Description) {
		this.roomTitle = Title;
		this.roomDescription = Description;
		this.shortroomDescription = new String();
		this.listExits = new Vector<Exit>();
	}
	
	public Location(String Title, String Description, String shortDescription) {
		this.roomTitle = Title;
		this.roomDescription = Description;
		this.shortroomDescription = shortDescription;
		this.listExits = new Vector<Exit>();
	}
	
	public String toString() {
		return this.roomTitle;
	}
	
	public String getTitle() {
		return this.roomTitle;
	}
	
	public void setTitle(String Title) {
		this.roomTitle = Title;
	}
	
	public String getDescription() {
		if(hasEntered) {
			return this.shortroomDescription;
		}
		else
			return this.roomDescription;
	}
	
	public void setDescription(String Description) {
		this.roomDescription = Description;
	}
	
	public String getshortDescription() {
		return this.shortroomDescription;
	}
	
	public void setShortDescription(String shortDesc) {
		this.shortroomDescription = shortDesc;
	}
	
	public Vector<Exit> getExits() {
		return (Vector<Exit>) this.listExits.clone();
	}
	
	public void addExit(Exit exit) {
		this.listExits.addElement(exit);
	}
	
	public void removeExit(Exit exit){
		if(this.listExits.contains(exit))
			listExits.removeElement(exit);
	}
	
	public Vector<Item> getItems() {
		return (Vector<Item>) this.listItems.clone();
	}
	
	public void addItem(Item item) {
		this.listItems.addElement(item);
		item.setLocation(this);
	}
	
	public void removeItem(Item item){
		if(this.listItems.contains(item))
			listItems.removeElement(item);
	}
	
	public Vector<Container> getContainers() {
		return (Vector<Container>) this.listContainers.clone();
	}
	
	public void addContainer(Container container) {
		this.listContainers.addElement(container);
	}
	
	public void removeContainer(Container container){
		if(this.listContainers.contains(container))
			listContainers.removeElement(container);
	}
	
	public Boolean getHasEntered() {
		return this.hasEntered;
	}
	
	public void setHasEntered(Boolean Entered){
		this.hasEntered = Entered;
	}
	
	public void addCharacter(CharacterObject person) {
		listCharacters.add(person);
	}
	
	public Vector<CharacterObject> getCharacters() {
		return (Vector<CharacterObject>) listCharacters;
	}
	
	public CharacterObject returnlastChar() {
		return listCharacters.lastElement();
	}
	
	public Exit getExitAt(String dir) {
		Exit toReturn = null;
		for(int i = 0; i < this.listExits.size(); i++) {
			if((listExits.elementAt(i).getDirection().equals(dir)) || (listExits.elementAt(i).getShortDirection().equals(dir)))
				toReturn = listExits.elementAt(i);
		}
		return toReturn;
	}
}
