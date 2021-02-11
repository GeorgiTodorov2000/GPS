
import java.awt.Color;
import java.awt.Graphics;

public class GameTile {

    public static final int TILE_SIZE = 50;


    private int row;
    private int col;
    private Color color;
    private int id;



    public GameTile(int row, int col, Color color, int id) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.id = id;
    }

    public GameTile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getId() {
        return id;
    }





    public void render(Graphics g) {
        int tileX = this.col * TILE_SIZE;
        int tileY = this.row * TILE_SIZE;

        g.setColor(color);

        g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
    }
}