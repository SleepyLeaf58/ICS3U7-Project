/*
 * David Zhai
 * 1/18/2023
 * For ICS3U7 Ms.Strelkovska
 * Extends animation. Used to animate attacks and receive hitbox data
 */

import java.util.*;
import java.io.*;

public class Attack extends Animation { // Extends animation as it just adds hitbox functionality
    private Warrior w;

    // Denotes frames where there are hitboxes
    private int start_active;
    private int end_active;

    // Arraylist of all hitboxes
    ArrayList<ArrayList<Hitbox>> hitboxes = new ArrayList<ArrayList<Hitbox>>();

    public Attack(Warrior w, int start_active, int end_active, int frames, String imgPath, String dataPath) {
        super(imgPath, frames);
        this.w = w;
        this.start_active = start_active; // First active frame
        this.end_active = end_active; // Last active frame
        hitboxReader(dataPath);
    }

    // Reads hitbox data from files in the Data folder
    private void hitboxReader(String path) {
        try {
            Scanner sc = new Scanner(new File(path));

            for (int i = 0; i < frames; i++) {
                if (in_range(i)) {
                    String[] frame_data = sc.nextLine().split(", ");
                    ArrayList<Hitbox> frame_hitboxes = new ArrayList<Hitbox>();

                    for (int j = 0; j < frame_data.length; j++) {
                        frame_hitboxes.add(new Hitbox(int_cast(frame_data[j])));
                    }

                    hitboxes.add(frame_hitboxes);
                } else
                    hitboxes.add(new ArrayList<Hitbox>());
            }
            sc.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Returns if the frame is an active frame
    public boolean in_range(int n) {
        return n >= start_active && n <= end_active;
    }

    // part of hitbox reader, transforms string into int array
    private int[] int_cast(String s) {
        String[] arr = s.split(" ");
        int len = arr.length;
        int[] int_arr = new int[len];

        for (int i = 0; i < len; i++)
            int_arr[i] = Integer.parseInt(arr[i]);

        return int_arr;
    }

    // Updates hitbox location to remain accurate with player models
    private void update(ArrayList<Hitbox> hitboxes) {
        for (Hitbox h : hitboxes) {
            h.setX(w.getX(), w.getOrientation());
            h.setDrawX(w.getDrawX(), w.getOrientation());
            h.setY(w.getY());
            h.setDrawY(w.getDrawY());
        }
    }

    // Returns the hitboxes that are used for the next frames
    public ArrayList<Hitbox> getNextHitbox() {
        ArrayList<Hitbox> h = hitboxes.get(cnt);
        update(h);
        return h;
    }
}
