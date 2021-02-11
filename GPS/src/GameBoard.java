import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {
    public static final int TILE_SIDE_COUNT = 10;
    private Object[][] tileCollection;
    private Object selectedTile;
    private final Random random = new Random();
    private int NO_CROSSING_TILE = 0;
    private int WINNING_TILE = 0;
    private int STARTING_TILE = 0;
    public int CURRENT_ROW = 0;
    public int CURRENT_COL = 0;
    private int noMovementOption = 0;




    public GameBoard() {

        this.tileCollection = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        tileCreator();

        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);



    }



    public void tileCreator () {
        for(int row = 1; row < 9; ++row) {
            for(int col = 1; col < 9; ++col) {
                int randomColor = random.nextInt(4);
                Color color;
                int yellow = 0;
                int red = 1;
                int green = 2;
                int blue = 3;

                switch (randomColor) {
                    case 0 -> {
                        color = new Color(248, 206, 204);
                        this.tileCollection[row][col] = new GameTile(row, col, color, red);
                        if(STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            this.tileCollection[row][col] = new GameTile(row, col, color, yellow);
                            STARTING_TILE++;
                            CURRENT_ROW = row;
                            CURRENT_COL = col;

                        }
                    }
                    case 1 -> {

                        if (WINNING_TILE < 8) {
                            color = new Color(213, 232, 212);
                            this.tileCollection[row][col] = new GameTile(row, col, color, green);
                            WINNING_TILE++; } else {
                            color = new Color(248, 206, 204);
                            this.tileCollection[row][col] = new GameTile(row, col, color, red);
                        }
                        if(STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            this.tileCollection[row][col] = new GameTile(row, col, color, yellow);
                            STARTING_TILE++;
                            CURRENT_ROW = row;
                            CURRENT_COL = col;
                        }
                    }
                    case 2 -> {
                        if (NO_CROSSING_TILE < 5) {
                            color = new Color(0, 80, 239);
                            this.tileCollection[row][col] = new GameTile(row, col, color, blue);
                            NO_CROSSING_TILE++; } else {
                            color = new Color(248, 206, 204);
                            this.tileCollection[row][col] = new GameTile(row, col, color,red);
                        }
                        if(STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            this.tileCollection[row][col] = new GameTile(row, col, color,yellow);
                            STARTING_TILE++;
                            CURRENT_ROW = row;
                            CURRENT_COL = col;
                        }
                    }
                    case 3 -> {
                        if (STARTING_TILE < 1 && row == 1 && col == 1 || STARTING_TILE < 1 && row == 1 && col == 8 ||
                                STARTING_TILE < 1 && row == 8 && col == 1 || STARTING_TILE < 1 && row == 8 && col == 8) {
                            color = new Color(255, 242, 204);
                            this.tileCollection[row][col] = new GameTile(row, col, color,yellow);
                            STARTING_TILE++;
                            CURRENT_ROW = row;
                            CURRENT_COL = col;
                        } else {
                            color = new Color(248, 206, 204);
                            this.tileCollection[row][col] = new GameTile(row, col, color,red);
                        }
                    }
                }
            }
        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());

        if (this.selectedTile != null) {
            GameTile gameTile = (GameTile) this.selectedTile;

            if (!lost()) {
                if (isMoveValid(row, col)) {
                    switch (gameTile.getId()) {

                        case 1 -> clickedSquare(row, col);
                        case 2 -> {
                            Modal.render(this, "WINNER", "You won");
                            new Restart();
                        }
                        case 3 -> System.out.println("YOU SHALL NOT PASS");


                    }

                }
            } else {
                Modal.render(this, "LOSER", "You lost");
                System.exit(1);
            }
        }

        if(this.hasBoardTile(row, col)) {
            this.selectedTile = this.getBoardTile(row, col);
        }
        this.repaint();

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


    //You lost statement
    public boolean lost() {


        for(int y = -1; y < 2; y++) {
            if(y == 0) {y++;}
            GameTile gameTile = new GameTile(CURRENT_ROW, y);

            if(gameTile.getId() == 3) {
                noMovementOption++;
            }
        }
        for(int x = -1; x < 2; x++) {
            if(x == 0) {x++;}
            GameTile gameTile = new GameTile(x, CURRENT_COL);

            if(gameTile.getId() == 3) {
                noMovementOption++;
            }
        }
        if(noMovementOption == 2) {
            return true;
        } else {
            return false;
        }
    }

    //Checking if movement is legal
    public boolean isMoveValid(int row, int col) {
        if(row == CURRENT_ROW + 1 && col == CURRENT_COL || row == CURRENT_ROW - 1 && col == CURRENT_COL ||
                col == CURRENT_COL + 1 && row == CURRENT_ROW || col == CURRENT_COL - 1 && row == CURRENT_ROW) {
            return true;
        } else { return false;}
    }

    //It's working, but breaks everything else
//    private void walkingInCircle(int row, int col) {
//        CURRENT_ROW = row;
//        CURRENT_COL = col;
//    }

    //Coloring the next square on random
    private void clickedSquare(int row, int col) {
        int i = random.nextInt(5);
        Color color;
        int id;
        if (i == 0) {
            color = new Color(0, 80, 239);
            id = 3;
            this.tileCollection[row][col] = new GameTile(row, col, color, id);
        } else {
            color = new Color(255, 242, 204);
            id = 0;
            this.tileCollection[row][col] = new GameTile(row, col, color, 0);
            this.tileCollection[CURRENT_ROW][CURRENT_COL] = new GameTile(CURRENT_ROW, CURRENT_COL, color, id);
            CURRENT_ROW = row;
            CURRENT_COL = col;
        }
    }

    // Painting the game board
    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 1; row < 9; ++row) {
            for(int col = 1; col < 9; ++col) {
                if(this.hasBoardTile(row, col)) {
                    GameTile tile = (GameTile) this.getBoardTile(row, col);
                    tile.render(g);
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

