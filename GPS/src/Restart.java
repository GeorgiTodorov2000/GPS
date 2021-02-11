import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Restart extends JFrame implements ActionListener{

    GameBoard game;
    JButton resetButton;

    Restart(){

        JPanel panel = new JPanel();
        JLabel label = new JLabel("You won");

        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.setSize(100, 50);
        resetButton.setLocation(0, 200);
        resetButton.addActionListener(this);


        this.add(resetButton);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton) {
            if(this.game != null) {
                this.remove(game);
            }
            game = new GameBoard();
            this.add(game);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
}
