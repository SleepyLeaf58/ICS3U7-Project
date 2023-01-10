import java.awt.*;
import java.util.*;
import java.io.*;

public class Attack extends Animation {
    private Player p;
    private int start_active;
    private int end_active;
    ArrayList<ArrayList<Hitbox>> hitboxes = new ArrayList<ArrayList<Hitbox>>();
    private boolean active;
    private int ticks = 0; 

    public Attack(Player p, int start_active, int end_active, int frames, String imgPath, String dataPath) {
        super(imgPath, frames);
        this.p = p;
        this.start_active = start_active;
        this.end_active = end_active;
        this.active = false;
        hitboxReader(dataPath);

    }

    private void hitboxReader (String path) {        
    	try {
            Scanner sc = new Scanner (new File (path));

            while (sc.hasNextLine()) {  
                String[] frame_data = sc.nextLine().split(",");
                ArrayList<Hitbox> frame_hitboxes = new ArrayList<Hitbox>();
            
                for (int i = 0; i < frame_data.length; i++) {
                    frame_hitboxes.add(new Hitbox(int_cast(frame_data[i])));
                }
    
                hitboxes.add(frame_hitboxes);
            
            }
            

        } catch (Exception e) {
            System.out.println (e);
        }
        
    }

    // part of hitbox reader, transforms string into int array
    private int[] int_cast (String s) {
        String[] arr = s.split(" ");
        int len = arr.length;

        int[] int_arr = new int[len];

        for (int i = 0; i < len; i++) 
            int_arr[i] = Integer.parseInt(arr[i]);
        
        return int_arr;
    }
    
    // update all hitbox pos based on orientation
    public void update_hitboxes () {
    	if (p.getOrientation() == 'l') 
    		update_all(hitboxes, '-');
    	else 
    		update_all(hitboxes, '+');
    }
    
    // part of update_hitboxes
    private void update_all(ArrayList<ArrayList<Hitbox>> hitboxes, char c) {
    	for (int i = 0; i < hitboxes.size(); i++) {
    		for (Hitbox h: hitboxes.get(i)) {
    			if (c == '+') 
    				h.incrementX(p.getX());
    			else 
    				h.incrementX(-p.getX());
    			
    			h.incrementY(p.getY());
    		}
    			
    	}
    	
    }


    // test function, hitboxes won't actually be drawn in final product
    public void draw (Graphics g) {
        for (int i = 0; i < hitboxes.size(); i++) {
    		for (Hitbox h: hitboxes.get(i)) {
    			h.draw(g);
    		}
    	}
    }
    
}
