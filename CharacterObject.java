import java.util.Vector;


public class CharacterObject {
  private String Name;
	private Vector<Item> items = new Vector<Item>();
	private String Description;
	private Vector<String> Dialogue = new Vector<String>();
	String solution = "", correctResponse = "", incorrectResponse = "";
	Boolean givesStuff = false;
	
	private Location existHere = new Location();
	
	public CharacterObject() {
		this.Name = new String();
		this.Description = new String();
		this.Dialogue.clear();
	}
	
	public CharacterObject(String Name) {
		this.Name = Name;
		this.Description = new String();
		this.Dialogue.clear();
	}
	
	public CharacterObject(String Name, String Description) {
		this.Name = Name;
		this.Description = Description;
		this.Dialogue.clear();
	}
	
	public String toString() {
		return this.Name;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public void setLocation(Location l){
		existHere = l;
	}
	
	public Location getLocation(){
		return existHere;
	}
	
	public String getDescription(){
		return this.Description;
	}
	
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	public Vector<String> getDialogues() {
		return (Vector<String>)this.Dialogue.clone();
	}
	
	public void addDialogue(String dialogue) {
		this.Dialogue.addElement(dialogue);
	}
	
	public void removeDialogue(String dialogue) {
		if(this.Dialogue.contains(dialogue))
			this.Dialogue.removeElement(dialogue);
	}
	
	public Vector<Item> getItems() {
		return (Vector<Item>)this.items.clone();
	}
	
	public void addItem(Item item) {
		this.items.addElement(item);
	}
	
	public void removeItem(Item item) {
		if(this.items.contains(item))
			this.items.removeElement(item);
	}
	public void setSolution(String sol){
		this.solution = sol;
	}
	public void setCorrectResponse(String corr){
		this.correctResponse = corr;
	}
	public void setInCorrectResponse(String wrong) {
		this.incorrectResponse = wrong;
	}
	public Boolean answerLookingFor(String answer) {
		if(answer.contains(solution)) {
			System.out.println(correctResponse);
			if(!(this.items.isEmpty())) 
				System.out.println("You have received " +items.elementAt(0).toString());
			return true;
		}
		else {
			System.out.println(incorrectResponse);
			return false;
		}
	}
}
