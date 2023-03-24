package betrayal.gui;

import betrayal.controllers.SuppressFBWarnings;
import betrayal.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PlayerPanel extends JPanel implements MouseListener, ComponentObserver {
    private JButton[] flipButtons;
    private int[] characterSide = new int[6];
    private JButton nextPlayer, startGame, changeLanguage;

    private Image[] characters;
    private int[] selectedByPlayer = new int[12];

    private int currPlayer = 1, maxCharacters;
    private boolean currPlayerHasSelected = false;
    private String selectCharacter;

    private static final String IMAGE_LIST_PATH = "/images/imageNames.txt";

    private GameState state;

    /**
     * PlayerPanel constructor.
     * @param frame
     * @param state
     */
    @SuppressFBWarnings
    public PlayerPanel(final MainUI frame, GameState state) {
        this.state = state;
        this.state.addObserver(this);

        loadCharImages();
        this.setLayout(null);

        setupButtons();

        nextPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (currPlayer < maxCharacters && currPlayerHasSelected) {
                    currPlayer++;
                    currPlayerHasSelected = false;
                }
                if (currPlayer >= maxCharacters) {
                    remove(nextPlayer);
                    add(startGame);
                }
                repaint();
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (currPlayerHasSelected) {
                    frame.startGame(getCharacterIndices());
                }
            }
        });

        update();
    }

    /**
     * Setup the JButtons.
     */
    public void setupButtons() {
        flipButtons = new JButton[6];
        int yOffset = 0;
        for (int i = 0; i < 6; i++) {
            flipButtons[i] = new JButton();
            flipButtons[i].setBounds(350 * (i % 3) + 150, 360 + yOffset,
                    150, 50);
            this.add(flipButtons[i]);
            flipButtons[i].addActionListener(new FlipSideListener(i));
            if (i == 2) {
                yOffset = 390;
            }
        }
        nextPlayer = new JButton();
        nextPlayer.setBounds(1100, 370, 150, 50);
        startGame = new JButton();
        startGame.setBounds(1100, 370, 150, 50);

        changeLanguage = new JButton();
        this.add(changeLanguage);
        changeLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new ChangeLanguagePane(state);
            }
        });
        changeLanguage.setBounds(1100, 450, 150, 50);
        this.add(nextPlayer);
    }

    /**
     * Update the player selection panel. Used when language changes.
     */
    @Override
    public void update() {
        for (int i = 0; i < 6; i++) {
            String flip = state.getMessageString("flipCard");
            flipButtons[i].setText(flip);
        }
        nextPlayer.setText(state.getMessageString("nextPlayer"));
        startGame.setText(state.getMessageString("startGame"));
        changeLanguage.setText(state.getMessageString("changeLanguage"));
        selectCharacter = java.text.MessageFormat.format(state.getMessageString("playerSelectMessage"), currPlayer);
        repaint();
    }

    /**
     * Get a HashMap of which player selected which character index.
     * @return characterIndices
     */
    public HashMap<Integer, Integer> getCharacterIndices() {
        HashMap<Integer, Integer> characterIndices = new HashMap<Integer, Integer>();
        for (int i = 0; i < 12; i++) {
            if (selectedByPlayer[i] != 0) {
                characterIndices.put(selectedByPlayer[i], i);
            }
        }
        return characterIndices;
    }

    /**
     * Set the max characters.
     * @param maxCharacters1
     */
    public void setMaxCharacters(final int maxCharacters1) {
        this.maxCharacters = maxCharacters1;
    }

    /**
     * Draw stuff.
     * @param graphics
     */
    public void paintComponent(final Graphics graphics) {
        // Fill background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 1300, 1000);

        drawCharacters(graphics);
        drawSelectedStrings(graphics);
    }

    /**
     * Draw the character images.
     * @param graphics
     */
    public void drawCharacters(final Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int yOffset = 0;
        for (int i = 0; i < 6; i++) {
            graphics.drawImage(characters[i + 6 * characterSide[i]],
                    75 + 350 * (i % 3), 50 + yOffset, null);
            if (i == 2) {
                yOffset = 390;
            }
        }
    }

    /**
     * Draw the strings for the selected characters.
     * @param graphics
     */
    public void drawSelectedStrings(final Graphics graphics) {
        graphics.setColor(Color.BLACK);
        selectCharacter = java.text.MessageFormat.format(state.getMessageString("playerSelectMessage"), currPlayer);
        graphics.drawString(selectCharacter, 500, 10);
        int yOffset = 0;
        for (int i = 0; i < 12; i++) {
            if (checkAlreadySelected(i)) {
                int offset = i <= 5 ? 0 : -6;
                int expectedSide = i <= 5 ? 0 : 1;
                // Draw "Selected by player" if the card is displaying the selected side
                if (checkAlreadySelectedOneSide(i)
                        && characterSide[i + offset] == expectedSide) {
                    String selectedText = java.text.MessageFormat.format(state.getMessageString("selectedByPlayer"),
                            selectedByPlayer[i]);
                    graphics.drawString(selectedText,
                            175 + 350 * (i % 3), 35 + yOffset);
                } else if (characterSide[i + offset] == expectedSide) {
                    // Draw "Other side has been selected" if the card is not displaying the selected side
                    graphics.drawString(state.getMessageString("otherSideSelected"),
                            150 + 350 * (i % 3), 35 + yOffset);
                }
            }
            if (i == 5) {
                yOffset = 0;
            } else if (i == 2 || i == 8) {
                yOffset = 400;
            }
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    /**
     * Player index selects a character.
     * @param index
     */
    public void selectCharacter(final int index) {
        if (!checkAlreadySelectedOneSide(index)
                && !checkAlreadySelectedOppositeSide(index)) {
            selectedByPlayer[index] = currPlayer;
            currPlayerHasSelected = true;
            for (int i = 0; i < 12; i++) {
                if (selectedByPlayer[i] == currPlayer && i != index) {
                    selectedByPlayer[i] = 0;
                }
            }
        }
    }

    /**
     * Mouse pressed. Used for selecting characters.
     * @param e
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= 75 && x <= 385) {
            // Mouse in first column
            if (y >= 50 && y <= 360) {
                // Mouse in first row
                selectCharacter(0 + 6 * characterSide[0]);
            } else if (y >= 440 && y <= 750) {
                // Mouse in second row
                selectCharacter(3 + 6 * characterSide[3]);
            }
        } else if (x >= 425 && x <= 735) {
            // Mouse in second column
            if (y >= 50 && y <= 360) {
                selectCharacter(1 + 6 * characterSide[1]);
                // Mouse in first row
            } else if (y >= 440 && y <= 750) {
                // Mouse in second row
                selectCharacter(4 + 6 * characterSide[4]);
            }
        } else if (x >= 775 && x <= 1085) {
            // Mouse in third column
            if (y >= 50 && y <= 360) {
                // Mouse in first row
                selectCharacter(2 + 6 * characterSide[2]);
            } else if (y >= 440 && y <= 750) {
                // Mouse in second row
                selectCharacter(5 + 6 * characterSide[5]);
            }
        }
        repaint();
    }

    private boolean checkAlreadySelectedOneSide(final int index) {
        if (selectedByPlayer[index] != 0) {
            return true;
        }
        return false;
    }

    private boolean checkAlreadySelectedOppositeSide(final int index) {
        int offset = index <= 5 ? 6 : -6;
        if (selectedByPlayer[index + offset] != 0
                && selectedByPlayer[index + offset] != currPlayer) {
            return true;
        }
        return false;
    }

    private boolean checkAlreadySelected(final int index) {
        int offset = index <= 5 ? 6 : -6;
        if (selectedByPlayer[index] != 0
                || selectedByPlayer[index + offset] != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }

    class FlipSideListener implements ActionListener {

        private int index;

        FlipSideListener(final int index1) {
            this.index = index1;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (characterSide[index] == 0) {
                characterSide[index] = 1;
            } else {
                characterSide[index] = 0;
            }
            repaint();
        }
    }

    /**
     * Loads the character images.
     */
    public void loadCharImages() {
        characters = new Image[12];
        File imageLists = new File(
                PlayerPanel.class.getResource(IMAGE_LIST_PATH).getPath());
        try {
            Scanner scanner = new Scanner(imageLists, "UTF-8");
            int i = 0;
            while (scanner.hasNextLine() && i < 12) {
                characters[i] = ImageIO.read(PlayerPanel.class.getResource(
                        "/images/" + scanner.nextLine()));
                i++;
            }
        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }
}
