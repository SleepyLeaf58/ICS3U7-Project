import java.util.*;
import java.io.*;

public class Attack extends Animation {
    private Entity p;
    private int start_active;
    private int end_active;
    private ArrayList<Hitbox> hitboxes;

    public Attack(Entity p, int start_active, int end_active, int frames, String imgPath, String dataPath) {
        super(imgPath, frames);
        this.p = p;
        this.start_active = start_active;
        this.end_active = end_active;
    }

    private void hitboxReader(String path) {
        Scanner sc = null;

        try {
            sc = new Scanner(new File(path));
        } catch (Exception e) {
            System.out.println("Error opening " + path);
        }

        while (sc.hasNextLine()) {
            ArrayList<Hitbox> frame = new ArrayList<Hitbox>();
            while (sc.hasNext()) {
                ArrayList<Integer> hb_data = new ArrayList<Integer>();
                while (!sc.next().equals("|"))
                    hb_data.add(Integer.parseInt(sc.next()));
            }
        }
    }
}