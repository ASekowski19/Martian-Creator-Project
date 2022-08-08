package prob1;

public class RedMartian extends Martian {
	private int tenacity;
	
	public RedMartian(int id, int volume, int tenacity) {
		super(id, volume);
		this.tenacity = tenacity;
	}
	
	public RedMartian(int id, int tenacity) {
		super(id);
		id = 1;
		this.tenacity = 1;
	}
	
	public int getTenacity() {
		return tenacity;
	}

	@Override
	public String Speak() {
		String msg = String.format("%d", getID());
		msg += "Rubldy Rock";
		return msg;
	}
	
	@Override
	public String toString() {
	String msg = String.format("Red Martian - id=%d, vol=%d, ten=%d", getID(), getVolume(), getTenacity());
	return msg;
   }

		
}
