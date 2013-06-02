import java.util.Vector;


public class Container {
  private Location isHere;
	private Vector<Item> ItemsInHere = new Vector<Item>();;
	private Vector<Container> hasContainers = new Vector<Container>();
	private String Description = new String();;
	private String Name;
	
	public Container(){
		isHere = new Location();
		ItemsInHere = new Vector<Item>();
	}
	
	public Container(String Name) {
		this.Name = Name;
		isHere = new Location();
	}
	
	public Container(String Name, Location here) {
		this.Name = Name;
		this.isHere = here;
	}
	
	public String toString(){
		return this.Name;
	}
	
	public Location getLocation() {
		return isHere;
	}
	
	public void setLocation(Location here) {
		this.isHere = here;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	public Vector<Item> getItems() {
		return (Vector<Item>) this.ItemsInHere.clone();
	}
	
	public void addItem(Item item) {
		if(item != null) {
			this.ItemsInHere.addElement(item);
			//item.putinContainer(this);
		}
	}
	
	public void removeItem(Item item){
		if(this.ItemsInHere.contains(item))
			ItemsInHere.removeElement(item);
	}
	
	public Vector<Container> getContainers() {
		return (Vector<Container>) this.hasContainers.clone();
	}
	
	public void addContainer(Container container) {
		if((container.getLocation() == null) || (container.getLocation() == this.getLocation()))
			this.hasContainers.addElement(container);
	}
	
	public void removeContainer(Container container){
		if(this.hasContainers.contains(container))
			hasContainers.removeElement(container);
	}
	
	public Boolean hasSomething() {
		if((!(this.ItemsInHere.isEmpty())) || (!(this.hasContainers.isEmpty())))
			return true;
		else
			return false;
	}
}
