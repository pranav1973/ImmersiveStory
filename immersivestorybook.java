import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;


public class immersivestorybook extends JFrame {

    private JLabel storyLabel;
    private JButton choiceButton1;
    private JButton choiceButton2;
    private JLabel imageLabel;
    private Clip backgroundMusic;
    private Clip soundEffect;
    private Clip voiceEffectClip;

    private int currentPage;

    public immersivestorybook() {
        setTitle("Immersive Storybook");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        imageLabel = new JLabel();

        setupUI();

        currentPage = 1;
        displayPage(currentPage);
        playBackgroundMusic("background.wav");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                stopBackgroundMusic();
            }
        });
    }

    private void playBackgroundMusic(String musicFile) {
        try {
            String backPath = "D:\\cse406_Project\\sound\\" + musicFile;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(backPath));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
            backgroundMusic.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private void playSoundEffect(String soundFile) {
        try {
            String soundPath = "D:\\cse406_Project\\sound\\" + soundFile;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath));
            soundEffect = AudioSystem.getClip();
            soundEffect.open(audioInputStream);
            soundEffect.start();
            Timer timer = new Timer(7 * 1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    stopSoundEffect();
                }
            });
            timer.setRepeats(false);
            timer.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    private void voiceEffect(String soundFile) {
        try {
            String voicePath = "D:\\cse406_Project\\sound\\" + soundFile;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(voicePath));
            voiceEffectClip = AudioSystem.getClip();
            voiceEffectClip.open(audioInputStream);
            voiceEffectClip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }

    private void stopSoundEffect() {
        if (soundEffect != null && soundEffect.isRunning()) {
            soundEffect.stop();
            soundEffect.close();
        }
    }
    private void stopVoiceEffect() {
        if (voiceEffectClip != null && voiceEffectClip.isRunning()) {
            voiceEffectClip.stop();
            voiceEffectClip.close();
        }
    }

    private void setupUI() {
        getContentPane().setLayout(null);
        storyLabel = new JLabel();
        storyLabel.setVerticalAlignment(SwingConstants.TOP);
        storyLabel.setFont(new Font("forte", Font.PLAIN, 18));

        choiceButton1 = createStyledButton();
        choiceButton2 = createStyledButton();

        choiceButton1.addActionListener(e -> navigateToNextPage(1));
        choiceButton2.addActionListener(e -> navigateToNextPage(2));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(choiceButton1);
        buttonPanel.add(choiceButton2);

        storyLabel.setBounds(10, 10, 580, 100);
        getContentPane().add(storyLabel);
    
        imageLabel = new JLabel();
        imageLabel.setBounds(5, 110, 600, 300);
        getContentPane().add(imageLabel);
    
        buttonPanel.setBounds(0, 413, 600, 50); 
        getContentPane().add(buttonPanel);
    }

    private JButton createStyledButton() {
        JButton button = new JButton();
        button.setBackground(new Color(135, 206, 250));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        return button;
    }

    private void displayPage(int page) {
        switch (page) {
            case 1:
                storyLabel.setText("<html><div style='text-align: center;'><b></b> Hello Adventurer...<br><br>You find yourself in a magical land. Choose your path.</div></html>");
                choiceButton1.setText("Explore the Enchanted Forest");
                choiceButton2.setText("Climb the Mystic Mountain");
                imageLabel.setIcon(new ImageIcon(getImage("case1.jpeg")));
                playSoundEffect("natural-thunder.wav");
                voiceEffect("voice1.wav");
                break;
            case 2:
                storyLabel.setText("<html><b></b> You chose to explore the Enchanted Forest.<br><br>You encounter a talking squirrel who guides you deeper into the woods.</html>");
                choiceButton1.setText("Follow the Squirrel");
                choiceButton2.setText("Go Your Own Way");
                imageLabel.setIcon(new ImageIcon(getImage("case2.png"))); 
                voiceEffect("voice2.wav");
                break;
            case 3:
                storyLabel.setText("<html><b></b> You chose to climb the Mystic Mountain.<br><br>A mystical bird appears and offers to guide you to a hidden cave.</html>");
                choiceButton1.setText("Follow the Bird to the Cave");
                choiceButton2.setText("Continue Climbing");
                imageLabel.setIcon(new ImageIcon(getImage("case3.jpg")));
                voiceEffect("voice3.wav");
                break;
            case 4:
                storyLabel.setText("<html><b></b> You followed the Squirrel.<br><br>The Squirrel leads you to a magical waterfall with a hidden cave behind it.</html>");
                choiceButton1.setText("Enter the Cave");
                choiceButton2.setText("Stay by the Waterfall");
                imageLabel.setIcon(new ImageIcon(getImage("case4.jpeg")));
                voiceEffect("voice4.wav");
                break;
            case 5:
                storyLabel.setText("<html><b></b> You went your own way but while walking you stumble upon a field of glowing mushrooms.While cursing when you look up to your surprise you encounter a wise old wizard who is willing to give you an advice for free.</html>");
                choiceButton1.setText("Listen to the Wizard's Advice");
                choiceButton2.setText("Ignore the Wizard and Move On");
                imageLabel.setIcon(new ImageIcon(getImage("case5.jpeg")));
                voiceEffect("voice5.wav");
                break;
            case 6:
                storyLabel.setText("<html><b></b> You followed the Bird to the Cave.<br><br>The cave is filled with glowing crystals and mysterious symbols.</html>");
                choiceButton1.setText("Study the Symbols");
                choiceButton2.setText("Leave the Cave");
                imageLabel.setIcon(new ImageIcon(getImage("case6.jpeg")));
                voiceEffect("voice6.wav");
                break;
            case 7:
                storyLabel.setText("<html><b></b> You continued climbing.<br><br>The mountain becomes steeper and steeper, and you saw a mystical creature from shadows.</html>");
                choiceButton1.setText("Befriend the Creature");
                choiceButton2.setText("Proceed with Caution");
                imageLabel.setIcon(new ImageIcon(getImage("case7.jpeg")));
                voiceEffect("voice7.wav");
                break;
            case 8:
                storyLabel.setText("<html><b></b> You entered the Cave.<br><br>You find a treasure chest filled with magical artifacts.</html>");
                choiceButton1.setText("Take the Artifacts");
                choiceButton2.setText("Leave the Treasure");
                imageLabel.setIcon(new ImageIcon(getImage("case8.jpeg")));
                voiceEffect("voice8.wav");
                break;
            case 9:
                storyLabel.setText("<html><b></b> You chose to stay by the Waterfall.<br><br> Unknowingly you stumbled and kept your hand on a finely carved brick <br><br>which opens a new hidden path.</html>");
                choiceButton1.setText("Follow Path");
                choiceButton2.setText("Return to the Forest");
                imageLabel.setIcon(new ImageIcon(getImage("case9.jpeg")));
                voiceEffect("voice9.wav");
                break;
            case 10:
                storyLabel.setText("<html><b></b> You decided to take the artifacts.<br><br> There is no calamity greater than lavish desires There is no greater guilt than discontentment And there is no greater disaster than greed.<br><br>You are not suitable to be great adventurer. You failed the test.</html>");
                choiceButton1.setText("Failed");
                choiceButton2.setText("Start Again");
                imageLabel.setIcon(new ImageIcon(getImage("case10.jpg")));
                voiceEffect("voice10.wav");
                break;
            case 11:
                storyLabel.setText("<html><b></b>Greed is a bottomless pit which exhausts the person in an endless effort to satisfy the need without ever reaching satisfaction.<br><br>You are suitable to be great adventurer. You passed the test. <br><br>Oh great adventurer, Please free this world from all miseries </html>");
                choiceButton1.setText("Passed");
                choiceButton2.setText("Start Again");
                imageLabel.setIcon(new ImageIcon(getImage("case11.jpeg")));
                voiceEffect("voice11.wav");
                break;
            case 12:
                storyLabel.setText("<html><b></b> As I expected,You are not upto the challenge adventurer.<br> There is only one thing that makes a dream impossible to achieve: the fear of failure.<br>You are not suitable for the greater heights. You failed the test. </html>");
                choiceButton1.setText("Failed");
                choiceButton2.setText("Start Again");
                imageLabel.setIcon(new ImageIcon(getImage("case12.jpg")));
                voiceEffect("voice12.wav");
                break;
            case 13:
                storyLabel.setText("<html><b></b> Hey adventutrer you are here for a greater purpose.<br><br> Kindly follow this dark path ahead. </html>");
                choiceButton1.setText("Follow the advice");
                choiceButton2.setText("Ignore the wizard advice");
                imageLabel.setIcon(new ImageIcon(getImage("case13.jpeg")));
                voiceEffect("voice13.wav");
                break;
            case 14:
                storyLabel.setText("<html><b></b>While walking you saw a alluring stone tablet.<br><br>When you touched the tablet suddenly a hidden path revealed. </html>");
                choiceButton1.setText("Follow the Path");
                choiceButton2.setText("Return to the forest");
                imageLabel.setIcon(new ImageIcon(getImage("case14.jpeg")));
                voiceEffect("voice14.wav");
                break;  
            case 15:
                storyLabel.setText("<html><b></b> While walking you saw a alluring stone tablet.<br><br>When you touched the tablet suddenly a hidden path revealed. </html>");
                choiceButton1.setText("Follow the Path");
                choiceButton2.setText("Return to the forest");
                imageLabel.setIcon(new ImageIcon(getImage("case14.jpeg")));
                voiceEffect("voice14.wav");
                break;
            case 16:
                storyLabel.setText("<html><b></b> You started studying the symbols.<br><br>You were able to crack the symbol.<br>Then suddenly a hidden path is revealed.</html>");
                choiceButton1.setText("Enter the Path");
                choiceButton2.setText("Leave the Cave");
                imageLabel.setIcon(new ImageIcon(getImage("case16.jpeg")));
                voiceEffect("voice16.wav");
                break;    
            case 17:
                storyLabel.setText("<html><b></b>While Leaving a mystical creature saw you.<br><br>The displeased creature attacked you and killed you on spot</html>");
                choiceButton1.setText("Failed");
                choiceButton2.setText("Start Again");
                imageLabel.setIcon(new ImageIcon(getImage("case17.jpeg")));
                voiceEffect("voice17.wav");
                break;
            case 18:
                storyLabel.setText("<html><b></b>You helped the injured creature<br><br>The joyful creature leads you towards the hidden cave</html>");
                choiceButton1.setText("Enter the Cave");
                choiceButton2.setText("Return to the Forest");
                imageLabel.setIcon(new ImageIcon(getImage("case18.jpeg")));
                voiceEffect("voice18.wav");
                break;    
            default:
                break;
        }
    }

    private Image getImage(String imageName) {
        String imagePath = "D:\\cse406_Project\\images\\" + imageName; 
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        return image.getScaledInstance(575, 315, Image.SCALE_SMOOTH); 
    }

    private void navigateToNextPage(int choice) {
        switch (currentPage) {
            case 1:
                if (choice == 1) {
                    currentPage = 2;
                } else if (choice == 2) {
                    currentPage = 3;
                }
                break;
            case 2:
                if (choice == 1) {
                    currentPage = 4;
                } else if (choice == 2) {
                    currentPage = 5;
                }
                break;
            case 3:
                if (choice == 1) {
                    currentPage = 6;
                } else if (choice == 2) {
                    currentPage = 7;
                }
                break;
            case 4:
                if (choice == 1) {
                    currentPage = 8;
                } else if (choice == 2) {
                    currentPage = 9;
                }
                break;
            case 5:
                if (choice == 1) {
                    currentPage = 13;
                } else if (choice == 2) {
                    currentPage = 14;
                }
                break;
            case 6:
                if (choice == 1) {
                    currentPage = 16;
                } else if (choice == 2) {
                    currentPage = 17;
                }
            break;
            case 7:
                if (choice == 1) {
                currentPage = 18;
            } else if (choice == 2) {
                currentPage = 17;
            }
            break;
            case 8:
                if (choice == 1) {
                    currentPage = 10;
                }else if (choice == 2) {
                    currentPage = 11;
                }
                break;
            case 9:
                if (choice == 1) {
                    currentPage = 8;
                } else if (choice == 2) {
                    currentPage = 12;
                }
                break;
            case 10:
                if (choice == 1) {
                    currentPage = 10;
                } else if (choice == 2) {
                    currentPage = 1;
                }
                break;
            case 11:
                if (choice == 1) {
                    currentPage = 11;
                } else if (choice == 2) {
                    currentPage = 1;
                }
                break;
            case 12:
                if (choice == 1) {
                    currentPage = 12;
                } else if (choice == 2) {
                    currentPage = 1;
                }
                break;
            case 13:
                if (choice == 1) {
                    currentPage = 14;
                } else if (choice == 2) {
                    currentPage = 15;
                }
                break;
            case 14:
                if (choice == 1) {
                    currentPage = 8;
                } else if (choice == 2) {
                    currentPage = 12;
                }
                break;
            case 15:
                if (choice == 1) {
                    currentPage = 8;
                } else if (choice == 2) {
                    currentPage = 12;
                }
                break;
            case 16:
                if (choice == 1) {
                    currentPage = 8;
                } else if (choice == 2) {
                    currentPage = 17;
                }
                break;
            case 17:
                if (choice == 1) {
                    currentPage = 17;
                } else if (choice == 2) {
                    currentPage = 1;
                }
                break;
            case 18:
                if (choice == 1) {
                    currentPage = 8;
                } else if (choice == 2) {
                    currentPage = 17;
                }
                break;    
            default:
                break;
        }
        stopSoundEffect();
        stopVoiceEffect();
        clearImage();
        displayPage(currentPage);
    }
    private void clearImage() {
        imageLabel.setIcon(null);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new immersivestorybook().setVisible(true);
            }
        });
    }
}

