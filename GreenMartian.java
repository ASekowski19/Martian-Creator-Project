package prob1;

public class GreenMartian extends Martian implements Teleporter {

	public GreenMartian(int id, int volume) {
		super(id, volume);
	}
	
	public GreenMartian(int id) {
		super(id);
		id = 1;
	}
	
	@Override
	public String Speak() {
		String msg = String.format("%d", getID());
		msg += "Grobldy Grock";
		return msg;
	}
	
	public String teleport(String dest) {
		String msg = String.format("id= %d teleporting to %s", getID(), dest);
		msg += dest;
		return msg;
		
	}
	@Override
	public String toString() {
	String msg = String.format("Green Martian - , id=%d, vol=%d", getID(), getVolume());
	return msg;
   }
}

	
