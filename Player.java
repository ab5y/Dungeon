import java.util.Vector;


public class Player {
  private Vector<Item> Inventory;
	private Location currLocation = new Location();
	
	public Player() {
		this.currLocation = null;
		this.Inventory = new Vector<Item>();
	}
	
	public Player(Location current) {
		this.currLocation = current;
		this.Inventory = new Vector<Item>();
	}
	
	public void addtoInventory(Item item) {
		this.Inventory.add(item);
		item.setLocation(null);
		item.setinInventory(true);
		System.out.println(item.getDescription());
	}
	
	public void removefromInventory(Item item) {
		if(this.Inventory.contains(item))
			this.Inventory.remove(item);
	}
	
	public Vector<Item> getInventory(){
		return (Vector<Item>)this.Inventory.clone();
	}
	
	public void setLocation(Location curr) {
		this.currLocation = curr;
	}
	
	public Location getLocation() {
		return this.currLocation;
	}
	
	public void takeExit(Exit exit) {
		if(this.currLocation == exit.getCurrentLocation()) {
			if(exit.getIsItLocked()){
				if(this.Inventory.contains(exit.getKey())){
					this.currLocation = exit.getLeadsTo();
					if(!(currLocation.getHasEntered())) {
						System.out.println(currLocation.getDescription());
						currLocation.setHasEntered(true);
					}
					else
						System.out.println(currLocation.getshortDescription());
					exit.ExitTaken();
				}
				else 
					System.out.println("You do not have the key to unlock this exit.\n");
			}
			else {
				this.currLocation = exit.getLeadsTo();
				if(!(currLocation.getHasEntered())) {
					System.out.println(currLocation.getDescription());
					currLocation.setHasEntered(true);
				}
				else
					System.out.println(currLocation.getshortDescription());
				exit.ExitTaken();
			}
		}
	}
}
