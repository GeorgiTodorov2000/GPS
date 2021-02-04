import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {
    public static final int TILE_SIDE_COUNT = 10;
    private Object[][] tileCollection;
    private Object selectedTile;
    private Random random = new Random();
    private int NO_CROSSING_TILE = 0;
    private int WINNING_TILE = 0;
    private int STARTING_TILE = 0;
    public GameBoard() {

        this.tileCollection = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];


        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);


    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Painting the game board
    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 1; row < 9; ++row) {
            for(int col = 1; col < 9; ++col) {
                int randomColor = random.nextInt(4);
                Color color;
                GameTile tile;
                switch (randomColor) {
                    case 0 -> {
                        color = new Color(248, 206, 204);
                        this.tileCollection[row][col] = new GameTile(row, col, color);
                        tile = new GameTile(row, col, color);
                        tile.render(g);
                        if(STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                            this.tileCollection[row][col] = new GameTile(row, col, color);
                            STARTING_TILE++;
                        }
                    }
                    case 1 -> {

                        if (WINNING_TILE < 8) {
                            color = new Color(213, 232, 212);;
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                            this.tileCollection[row][col] = new GameTile(row, col, color);
                            WINNING_TILE++; } else {
                            color = new Color(248, 206, 204);
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                        }
                        if(STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                            this.tileCollection[row][col] = new GameTile(row, col, color);
                            STARTING_TILE++;
                        }
                    }
                    case 2 -> {
                        if (NO_CROSSING_TILE < 5) {
                        color = new Color(0, 80, 239);
                        tile = new GameTile(row, col, color);
                        this.tileCollection[row][col] = new GameTile(row, col, color);
                        tile.render(g);
                            NO_CROSSING_TILE++; } else {
                            color = new Color(248, 206, 204);
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                        }
                        if(STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                            this.tileCollection[row][col] = new GameTile(row, col, color);
                            STARTING_TILE++;
                        }
                    }
                    case 3 -> {
                        if (STARTING_TILE < 1 && row == 1 && col == 1 || STARTING_TILE < 1 && row == 1 && col == 8 ||
                                STARTING_TILE < 1 && row == 8 && col == 1 || STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                            this.tileCollection[row][col] = new GameTile(row, col, color);
                            STARTING_TILE++; } else {
                            color = new Color(248, 206, 204);
                            tile = new GameTile(row, col, color);
                            tile.render(g);
                        }
                    }
                }
            }
        }
    }




    private Object getBoardTile(int row, int col) {
        return this.tileCollection[row][col];
    }
    private boolean hasBoardTile(int row, int col) {
        return this.getBoardTile(row, col) != null;
    }
    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / 50;
    }
}

