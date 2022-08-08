package prob1;

public abstract class Martian extends Object implements Comparable<Martian>{
	
	private int id;
	private int volume;
	
	public Martian(int id, int volume) {
		this.id = id;
		this.volume = volume;
	}
	public Martian(int id) {
		this.id = id;
		this.volume = 0;
	}
	
	public int compareTo(Martian m) {
		return Integer.compare(this.id, m.getID());
		
	}
	
	
	public boolean equals(Object o) {
		if(o instanceof Martian) {
			Martian a = (Martian)o;
			return this.id == (a.id);
		}
		return false;
	}
	
	public int getID() {
		return id;
	}
	
	public int getVolume() {
		return volume;
		
	}
	public void setVolume() {
		this.volume = volume;
		
	}
	public abstract String Speak();
	
}	
	
