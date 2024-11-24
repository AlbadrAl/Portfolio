import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class HangmanGUI extends JFrame {
    private static final String WORD_TO_GUESS= "hangman";
    private JLabel[] slots; 
    private int wrongGuesses; 
    private JLabel wrongGLabel; 
    private JLabel triesLabel; 
    private int tries; 
    private JLabel topScoreLabel; 
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 200;

    public HangmanGUI() {
        wrongGuesses=0;
        tries= 0;
        createComponents();
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createComponents() {
        JPanel mainPanel= new JPanel(new BorderLayout());
        JPanel wordPanel = new JPanel();
       
        slots= new JLabel[WORD_TO_GUESS.length()];
        for (int i= 0; i< WORD_TO_GUESS.length();i++) {
            slots[i]= new JLabel("_ ");
            wordPanel.add(slots[i]);
        }

        wrongGLabel =new JLabel("Wrong guesses: 0");
        triesLabel =new JLabel("Total tries: 0");

        topScoreLabel= new JLabel("Top Score: Not set");
        mainPanel.add(wordPanel, BorderLayout.NORTH);
        mainPanel.add(wrongGLabel, BorderLayout.WEST);
        mainPanel.add(triesLabel, BorderLayout.CENTER);
        mainPanel.add(topScoreLabel, BorderLayout.SOUTH);

         add(mainPanel);

        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent event) {
                char guessedLetter= event.getKeyChar();
               
                guessedLetter=Character.toLowerCase(guessedLetter); 
                processGuess(guessedLetter);
            }
        });
    }

    private void processGuess(char guessedLetter) {
        boolean correctGuess = false;

        tries++;
        triesLabel.setText("Total tries: " + tries);

        for (int i = 0;i < WORD_TO_GUESS.length();i++) {
            if(WORD_TO_GUESS.charAt(i)== guessedLetter) {
                slots[i].setText(String.valueOf(guessedLetter) + " ");
                correctGuess= true;
            }
        }

        if(!correctGuess) {
            wrongGuesses++; 
            wrongGLabel.setText("Wrong guesses: " + wrongGuesses);
        } else {
      
            boolean wordComplete= true;
            for(JLabel slot : slots) {
                if(slot.getText().equals("_ ")) {
                    wordComplete=false;
                    break;
                }
            }
            if (wordComplete){
                topScoreLabel.setText("Top Score: " + tries);
            }
        }
    }

    public static void main(String[] args) {
        new HangmanGUI();
    }
}