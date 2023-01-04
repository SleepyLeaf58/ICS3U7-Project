public class Attack {
    private int start_active;
    private int end_active;
    private int duration;
    private int key;
    private Animation a;
    private Hitbox[] hitboxes;

    public Attack(int start_active, int end_active, int duration, int key, String filePath) {
        this.start_active = start_active;
        this.end_active = end_active;
        this.duration = duration;

    }
}