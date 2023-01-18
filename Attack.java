/*
 * Extends animation. Used to animate attacks and receive hitbox data
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;

public class Attack extends Animation {
    private Warrior w;
    // hitbox active
    private int start_active;
    private int end_active;

    ArrayList<ArrayList<Hitbox>> hitboxes = new ArrayList<ArrayList<Hitbox>>();

    public Attack(Warrior w, int start_active, int end_active, int frames, String imgPath, String dataPath) {
        super(imgPath, frames);
        this.w = w;
        this.start_active = start_active;
        this.end_active = end_active;
        hitboxReader(dataPath);
    }

    // Reads hitbox data from files
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

    private void update(ArrayList<Hitbox> hitboxes) {
        for (Hitbox h : hitboxes) {
            h.setX(w.getX(), w.getOrientation());
            h.setDrawX(w.getDrawX(), w.getOrientation());
            h.setY(w.getY());
            h.setDrawY(w.getDrawY());
        }
    }

    // actual gameplay
    public ArrayList<Hitbox> getNextHitbox() {
        ArrayList<Hitbox> h = hitboxes.get(cnt);
        update(h);
        return h;
    }
}