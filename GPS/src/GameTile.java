
import java.awt.Color;
import java.awt.Graphics;

public class GameTile {

    public static final int TILE_SIZE = 50;


    private int row;
    private int col;
    private Color color;



    public GameTile(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public void render(Graphics g) {
        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

        g.setColor(color);


        g.fillRect(tileX, tileY, this.TILE_SIZE, this.TILE_SIZE);
    }
}