
public class Exit {
  
	public static final int UNDEFINED = 0;
	public static final int NORTH = 1;
	public static final int SOUTH = 2;
	public static final int EAST  = 3;
	public static final int WEST  = 4;
	public static final int UP    = 5;
	public static final int DOWN  = 6;
	public static final int NORTHEAST = 7;
	public static final int NORTHWEST = 8;
	public static final int SOUTHEAST = 9;
	public static final int SOUTHWEST = 10;
	public static final int IN = 11;
	public static final int OUT = 12;
	
	public static final String[] dirName = 
	{ 
		"UNDEFINED",
		"NORTH",
		"SOUTH",
		"EAST",
		"WEST",
		"UP",
		"DOWN",
		"NORTHEAST",
		"NORTHWEST",
		"SOUTHEAST",
		"SOUTHWEST",
		"IN",
		"OUT"
	};

	public static final String[] shortDirName = 
	{
		"NULL",
		"N",
		"S",
		"E",
		"W",
		"U",
		"D",
		"NE",
		"NW",
		"SE",
		"SW",
		"I",
		"O"		
	};

	private Location to = null;
	private Location curr = new Location();
	private String Direction;
	private int dirCode;
	private String shortDirection;
	private Boolean isLocked = false;
	private Item key = null;
	
	public Exit() {
		this.to = new Location();
		this.Direction = new String();
		this.shortDirection = new String();
	}

	public Exit(Location to, int Direction) {
		this.to = to;
		dirCode = Direction;
		if(Direction <= dirName.length)
			this.Direction = dirName[Direction];
		if(Direction <= shortDirName.length)
			this.shortDirection = shortDirName[Direction];
	}
	
	public Exit(Location to, int Direction, Boolean isLocked){
		this.isLocked = isLocked;
		this.to = to;
		dirCode = Direction;
		if(Direction <= dirName.length)
			this.Direction = dirName[Direction];
		if(Direction <= shortDirName.length)
			this.shortDirection = shortDirName[Direction];
	}
	
	public Exit(Location to, int Direction, Boolean isLocked, Item Key){
		this.key = Key;
		this.isLocked = isLocked;
		this.to = to;
		dirCode = Direction;
		if(Direction <= dirName.length)
			this.Direction = dirName[Direction];
		if(Direction <= shortDirName.length)
			this.shortDirection = shortDirName[Direction];
	}

	public String toString() {
		return Direction;
	}
	
	public void setLeadsTo(Location to) {
		this.to = to;
	}
	
	public Location getLeadsTo() {
		return this.to;
	}
	
	public void setCurrentLocation(Location at) {
		this.curr = at;
	}
	
	public Location getCurrentLocation() {
		return this.curr;
	}
	
	public void setDirection(String Direction) {
		this.Direction = Direction;
	}
	
	public void setDirection(int Direction) {
		dirCode = Direction;
		if(Direction <= dirName.length)
			this.Direction = dirName[Direction];
		if(Direction <= shortDirName.length)
			this.shortDirection = shortDirName[Direction];
	}
	
	public String getDirection() {
		return this.Direction;
	}
	
	public void setShortDirection(String ShortDirection) {
		this.shortDirection = ShortDirection;
	}
	
	public String getShortDirection() {
		return this.shortDirection;
	}
	
	public Boolean getIsItLocked(){
		return this.isLocked;
	}
	
	public void setIsLocked(Boolean isLocked) {	
		this.isLocked = isLocked;
	}
	
	public String getKeyDescription() {
		return this.key.getDescription();
	}
	
	public void setKey(Item isKey) {
		this.key = isKey;
	}
	
	public Item getKey() {
		return this.key;
	}
	
	public void ExitTaken() {
		Location temp = new Location();
		temp = this.curr;
		this.curr = this.to;
		this.to = temp;
		if(!(this.curr.getExits().contains(this)))
			this.curr.addExit(this);
		switch(dirCode) {
			case 0: break;
			case 1:
				this.Direction = dirName[2];
				this.shortDirection = shortDirName[2];
				dirCode = 2;
				break;
			case 2:
				this.Direction = dirName[1];
				this.shortDirection = shortDirName[1];
				dirCode = 1;
				break;
			case 3:
				this.Direction = dirName[4];
				this.shortDirection = shortDirName[4];
				dirCode = 4;
				break;
			case 4:
				this.Direction = dirName[3];
				this.shortDirection = shortDirName[3];
				dirCode = 3;
				break;
			case 5:
				this.Direction = dirName[6];
				this.shortDirection = shortDirName[6];
				dirCode = 6;
				break;
			case 6:
				this.Direction = dirName[5];
				this.shortDirection = shortDirName[5];
				dirCode = 5;
				break;
			case 7:
				this.Direction = dirName[10];
				this.shortDirection = shortDirName[10];
				dirCode = 10;
				break;
			case 8:
				this.Direction = dirName[9];
				this.shortDirection = shortDirName[9];
				dirCode = 9;
				break;
			case 9:
				this.Direction = dirName[8];
				this.shortDirection = shortDirName[8];
				dirCode = 8;
				break;
			case 10:
				this.Direction = dirName[7];
				this.shortDirection = shortDirName[7];
				dirCode = 7;
				break;
			case 11:
				this.Direction = dirName[12];
				this.shortDirection = shortDirName[12];
				dirCode = 12;
				break;
			case 12:
				this.Direction = dirName[11];
				this.shortDirection = shortDirName[11];
				dirCode = 11;
				break;
		}
	}
}
