package prob1;

import java.util.ArrayList;

public class MartianManager extends Object{

    protected ArrayList<Martian> martians = new ArrayList<Martian>(0);
    protected ArrayList<Teleporter> teleporters = new ArrayList<Teleporter>(0);

    public MartianManager() {
    }

    public boolean addMartian(Martian m) {
        if(!martians.contains(m)) {
            martians.add(m);
            if(m instanceof Teleporter) {
                Teleporter t = (Teleporter)m;
                teleporters.add(t);
            }
            return true;
        }
        return false;
    }
    
    public ArrayList<Martian> battle (ArrayList<Martian> invaders) {
        ArrayList<Martian> killed = new ArrayList<Martian>();
        for(Martian invader : invaders) {
            int powInvader = getPower(invader);
            for(int i = 0; i < martians.size(); i++) {
                int powMartian = getPower(getMartianAt(i));
                if(powInvader>powMartian) {
                    killed.add((martians.get(i)));
                    removeMartian(martians.get(i).getID());
                    break;
                }
            }
        }
        return killed;
    }

    public boolean contains(int id) {
    	Martian m = new RedMartian(id, 0);
        if (martians.contains(m)) {
            return true;
        	} 
        return false;
        }
    
    public Martian getMartianAt(int i) {
    	if (i >= 0 && i < martians.size()) {
    		return martians.get(i);
    	}
    	return null;
    }

	public Martian getMartianClosestTo(int id) {
		int diff = Integer.MAX_VALUE;
		Martian closest = martians.get(0);
		int diff2;
		for(Martian m : martians) {
			diff2 = Math.abs(id - m.getID());
			if(diff > diff2) {
				diff = diff2;
				closest = m;
			}
		}
		return closest;
	}
	
    public Martian getMartianClosestTo(Martian martian) {
        return getMartianClosestTo(martian.getID());
    }
    
    public Martian getMartianWithID(int id) {
    	Martian mKey = new RedMartian(id, 0);
    	if (martians.contains(mKey)) {
    		int loc = martians.indexOf(mKey);
    		return martians.get(loc);
    	}
    	return null;
    }
    
    public int getNumMartians() {
        return martians.size();
    }
    
    public int getNumTeleporters() {
        return teleporters.size();
    }
    
    public ArrayList<Martian> getSortedMartians(){
        ArrayList<Martian> sorted = new ArrayList<>(martians);
        martians.sort(null);
        return sorted;
    }
    
    public Teleporter getTeleporterAt(int i) {
    	if (i >= 0 && i < teleporters.size()) {
    		return teleporters.get(i);
    	}
    	return null;
    }
    
    public String groupSpeak() {
        String msg = "";
        for(Martian m : martians) {
            msg += m.Speak() + "\n";
        }
        return msg;
    }
    
    public String groupTeleport(String dest) {
        String msg = "";
        for (Teleporter t : teleporters) {
            msg += t.teleport(dest) + "\n";
        }
        return msg;
    }   
    
    public void obliterateTeleporters() {
    	for (Martian m : martians) {
    		if (m instanceof GreenMartian) {
    			GreenMartian gm = (GreenMartian)m;
    			int loc = martians.indexOf(gm);
    			martians.remove(loc);
    		}
    	}
    }
    
    public Martian removeMartian(int id) {
    	Martian remMartian = getMartianWithID(id);
        if(martians.contains(remMartian)) {
            if (remMartian instanceof Teleporter) {
            	Teleporter t = (Teleporter)remMartian;
            	teleporters.remove(teleporters.indexOf(t));
            }
        	martians.remove(martians.indexOf(remMartian));
        	return remMartian;
        }
        return null;
    }
    
    @Override
    public String toString() {
    	String msg = "Martians:\n";
    	for (Martian m : martians) {
    		msg += m.toString() + "\n";
    	}
    	msg += "\nTeleporters:\n";
    	for (Teleporter t : teleporters) {
    		msg += t.toString() + "\n";
    	}
    	return msg;
    }
    
    //Helper method
    public int getPower(Martian m) {
    	if (m instanceof GreenMartian) {
    		GreenMartian gm = (GreenMartian)m;
    		return gm.getVolume();
    	}
    	RedMartian rm = (RedMartian)m;
    	return m.getVolume() + rm.getTenacity();
    }
}