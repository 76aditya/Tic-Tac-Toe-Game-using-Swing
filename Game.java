import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game{
    private static boolean xTurn=true;
    public static void main(String[] args) {
        //Setboard
        //add buttons and a background image
        JFrame frame=new JFrame("Tic Tac Toe");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        Background bg=new Background();
        frame.setContentPane(bg);

        frame.setLayout(new GridLayout(3, 3, 20, 20));

        // Create 3x3 button array
        JButton[][] buttons = new JButton[3][3];

        // Load and resize image
        ImageIcon icon = new ImageIcon("C:/Aditya/DSA in JAVA/Tic Tac Toe game/wp4292444-wood-texture-wallpapers.jpg"); // ✅ your image file here
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // Resize to 100x100
        ImageIcon resizedIcon = new ImageIcon(img);

        // Fill buttons with resized image
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Font font=new Font("DialogInput", Font.BOLD, 60);
                buttons[row][col] = new JButton("");
                buttons[row][col].setIcon(resizedIcon);
                buttons[row][col].setFont(font);
                buttons[row][col].setForeground(new Color(88, 27, 9));
                buttons[row][col].setVerticalTextPosition(SwingConstants.CENTER);
                buttons[row][col].setHorizontalTextPosition(SwingConstants.CENTER);

                frame.add(buttons[row][col]);
            }
        }


        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++) {
                buttons[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JButton btnclicked=(JButton) e.getSource();
                        if (!btnclicked.getText().equals("")) {
                            return; // already clicked
                        }
                        if(xTurn)
                        {
                            btnclicked.setText("X");
                        }
                        else {
                            btnclicked.setText("O");
                        }
                        xTurn=!xTurn;
                        String winner = null;

                        // Check rows and columns
                        for (int i = 0; i < 3; i++) {
                            // Rows
                            if (!buttons[i][0].getText().equals("") &&
                                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                                winner = buttons[i][0].getText();
                            }

                            // Columns
                            if (!buttons[0][i].getText().equals("") &&
                                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                                    buttons[1][i].getText().equals(buttons[2][i].getText())) {
                                winner = buttons[0][i].getText();
                            }
                        }

                        // Diagonals
                        if (!buttons[0][0].getText().equals("") &&
                                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                                buttons[1][1].getText().equals(buttons[2][2].getText())) {
                            winner = buttons[0][0].getText();
                        }

                        if (!buttons[0][2].getText().equals("") &&
                                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                                buttons[1][1].getText().equals(buttons[2][0].getText())) {
                            winner = buttons[0][2].getText();
                        }
                        if(winner!=null)
                        {
                            JOptionPane.showMessageDialog(null, "Player: "+winner+" wins!");
                            reset(buttons);
                        }
                        else if(isDraw(buttons))
                        {
                            JOptionPane.showMessageDialog(null, "Draw!");
                            reset(buttons);
                        }
                    }
                });
            }

        }


        frame.setVisible(true);

        //board setted up
        //Now time for game logic

    }
    public static void reset(JButton[][] buttons)
    {
        xTurn=true;
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                buttons[i][j].setText("");
            }
        }
    }
    private static boolean isDraw(JButton[][] buttons) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals("")) return false;
        return true;
    }

}



class Background extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clear background

        Image img=Toolkit.getDefaultToolkit().getImage("C:/Aditya/DSA in JAVA/Tic Tac Toe game/wp4292422-wood-texture-wallpapers.jpg");
        g.drawImage(img, 0, 0, 400, 400, this);
    }
}


