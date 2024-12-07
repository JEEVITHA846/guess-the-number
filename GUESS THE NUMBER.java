import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI {
    private static int numberToGuess;
    private static int numberOfAttempts;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255, 182, 193)); // Light pink background
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Guess the Number");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Bold font for the title
        titleLabel.setForeground(Color.BLACK); // Black font color
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel userLabel = new JLabel("Enter your guess:");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField userText = new JTextField(10);
        userText.setFont(new Font("Serif", Font.PLAIN, 18));
        panel.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Serif", Font.PLAIN, 18));
        panel.add(guessButton, gbc);

        gbc.gridx = 1;
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        panel.add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Serif", Font.PLAIN, 18));
        playAgainButton.setVisible(false);
        panel.add(playAgainButton, gbc);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess;
                try {
                    guess = Integer.parseInt(userText.getText());
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                    return;
                }
                numberOfAttempts++;

                if (guess < numberToGuess) {
                    resultLabel.setText("Your guess is too low.");
                } else if (guess > numberToGuess) {
                    resultLabel.setText("Your guess is too high.");
                } else {
                    resultLabel.setText("Congratulations! You guessed the number in " + numberOfAttempts + " attempts.");
                    guessButton.setEnabled(false);
                    playAgainButton.setVisible(true);
                }
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberToGuess = new Random().nextInt(100) + 1;
                numberOfAttempts = 0;
                userText.setText("");
                resultLabel.setText("");
                guessButton.setEnabled(true);
                playAgainButton.setVisible(false);
            }
        });

        // Initialize the game
        numberToGuess = new Random().nextInt(100) + 1;
        numberOfAttempts = 0;
    }
}