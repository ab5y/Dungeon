
public class Item {
  private String Name;
	private String Description;
	private Exit keyTo = new Exit();
	private Location currLocation = new Location();
	private Boolean inInventory = false;
	private Container inContainer = new Container();
	private Boolean inC = false; 
	
	public Item(){
		Name = new String();
		Description = new String();
		keyTo = null;
		currLocation = null;
	}
	
	public Item(String Name){
		this.Name = Name;
		this.Description = new String();
		keyTo = null;
		currLocation = null;
	}
	
	public Item(String Name, String Description){
		this.Name = Name;
		this.Description = Description;
		keyTo = null;
		currLocation = null;
	}
	
	public Item(String Name, Location isIn){
		this.Name = Name;
		this.currLocation = isIn;
		isIn.addItem(this);
		keyTo = null;
	}
	
	public Item(String Name, String Description, Location isIn){
		this.Name = Name;
		this.Description = Description;
		this.currLocation = isIn;
		isIn.addItem(this);
		keyTo = null;
	}
	
	public Item(String Name, Location isIn, Exit keyTo){
		this.Name = Name;
		this.currLocation = isIn;
		isIn.addItem(this);
		this.keyTo = keyTo;
	}
	
	public Item(String Name, String Description, Location isIn, Exit keyTo){
		this.Name = Name;
		this.Description = Description;
		this.currLocation = isIn;
		isIn.addItem(this);
		this.keyTo = keyTo;
		keyTo.setIsLocked(true);
		keyTo.setKey(this);
	}
	
	public String toString() {
		return this.Name;
	}
	
	public String getName(){
		return this.Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getDescription(){
		return this.Description;
	}
	
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	public Location getLocation(){
		return this.currLocation;
	}
	
	public void setLocation(Location isIn) {
		this.currLocation = isIn;
		//if(isIn != null)
			//isIn.addItem(this);
	}
	
	public Exit getIsKeyTo() {
		return this.keyTo;
	}
	
	public void setKeyTo(Exit isKeyTo) {
		this.keyTo = isKeyTo;
		isKeyTo.setIsLocked(true);
		isKeyTo.setKey(this);
	}
	
	public Boolean isitintheInventory(){
		return this.inInventory;
	}
	
	public void setinInventory(Boolean inInventory) {
		this.inInventory = inInventory;
		this.currLocation = null;
	}
	
	public void putinContainer(Container container){
		if(this.getLocation() != null) {
			if(container.getLocation() == this.getLocation()) {
				this.inContainer = container;
				//container.addItem(this);
			}
		}
		else {
			this.inContainer = container;
			this.setLocation(container.getLocation());
			container.addItem(this);
		}
		inC = true;
	}
	
	public void removefromContainer(){
		inContainer.removeItem(this);
		inC = false;
	}
	
	public Boolean isitinaContainer() {
		return inC;
	}
}
