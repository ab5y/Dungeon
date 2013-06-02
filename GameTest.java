import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;



public class GameTest {

  String longDesc = "You are in a dark dingy cave. There is a large machine, a computer cluster, and the noise of the whirring fans fills the air. Whores, whores everywhere.";
	String shortDesc = "A dark cave.";
	String l1Name = "the Cave";
	Location l1 = new Location(l1Name, longDesc, shortDesc);
	String longDesc2 = "You are now in a forest. Dense with trees, you can barely see the sunlight through the leaves. Whores, whores everywhere.";
	String shortDesc2 = "A dense forest.";
	String l2Name = "the forest";
	Location l2 = new Location(l2Name, longDesc2, shortDesc2);
	Exit exit1 = new Exit(l2, Exit.EAST);
	Exit exit2 = new Exit();
	Vector<Exit> testing = new Vector<Exit>();
	Vector<Item> testingItems = new Vector<Item>();
	Player Dustan = new Player(l1);
	Item cellphone = new Item("a cell phone", "a crapphy iFone", l2);
	Container deskdrawer = new Container();
	
	@Test
	public void testLocation() {
		l1.addExit(exit1);
		testing.addElement(exit1);
		l1.addItem(cellphone);
		testingItems.addElement(cellphone);
		assertEquals(l1.getItems(), testingItems);
		assertEquals(l1.getExits(), testing);
		assertEquals(l1.getDescription(), longDesc);
		assertEquals(l1.getshortDescription(), shortDesc);
		assertEquals(l2.getDescription(), longDesc2);
		assertEquals(l2.getshortDescription(), shortDesc2);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testExit(){
		exit1.setCurrentLocation(l1);
		assertEquals(Dustan.getLocation(), l1);
		Dustan.takeExit(exit1);
		assertEquals(Dustan.getLocation().toString(), l2.toString());
	}
	
	@Test
	public void testItems() {
		assertEquals(cellphone.getDescription(), "a crapphy iFone");
		assertEquals(cellphone.getLocation(), l2);
		assertEquals(cellphone.getName(), "a cell phone");
		assertEquals(cellphone.isitintheInventory(), false);
		Item carkeys = new Item("bunch of keys", "hmm, a bunch of keys. I wonder what they unlock.", l1, exit1);
		exit1.setCurrentLocation(l1);
		assertEquals(exit1.getIsItLocked(), true);
		Dustan.takeExit(exit1);
		assertEquals(Dustan.getLocation(), l1);
		Dustan.addtoInventory(carkeys);
		assertEquals(carkeys.isitintheInventory(), true);
		Dustan.takeExit(exit1);
		assertEquals(Dustan.getLocation(), l2);
	}
	
	@Test
	public void testContainers() {
		Container jewelrybox = new Container();
		jewelrybox.setLocation(l2);
		deskdrawer.addContainer(jewelrybox);
		assertEquals(deskdrawer.getContainers(), new Vector<Container>());
		jewelrybox.setLocation(l1);
		deskdrawer.addContainer(jewelrybox);
		assertEquals(deskdrawer.getContainers(), new Vector<Container>());
		deskdrawer.setLocation(l1);
		cellphone.putinContainer(deskdrawer);
		assertEquals(deskdrawer.getItems(), new Vector<Item>());
		assertEquals(cellphone.getLocation(), l2);
	}
}
