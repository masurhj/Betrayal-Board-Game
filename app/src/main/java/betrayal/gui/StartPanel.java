package betrayal.gui;

import betrayal.controllers.SuppressFBWarnings;
import betrayal.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ComponentObserver {

    private JButton enter, changeLanguage;
    private JRadioButton[] playerCounts =
            {
                    new JRadioButton("3", true),
                    new JRadioButton("4"),
                    new JRadioButton("5"),
                    new JRadioButton("6")
            };
    private ButtonGroup buttonGroup;
    private JLabel howManyPlayers;
    private GameState state;

    /**
     * StartPanel constructor.
     * @param frame
     * @param state
     */
    @SuppressFBWarnings
    public StartPanel(final MainUI frame, GameState state) {
        this.state = state;
        this.state.addObserver(this);

        buttonGroup = new ButtonGroup();
        for (int i = 0; i < playerCounts.length; i++) {
            buttonGroup.add(playerCounts[i]);
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 2;
        c.gridy = 0;

        howManyPlayers = new JLabel();
        this.add(howManyPlayers, c);

        for (int i = 0; i < playerCounts.length; i++) {
            c.gridy = i + 1;
            this.add(playerCounts[i], c);
        }

        c.gridy += 1;
        c.gridx = 2;
        enter = new JButton();
        this.add(enter, c);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                int playerCount = 0;
                for (int i = 0; i < playerCounts.length; i++) {
                    if (playerCounts[i].isSelected()) {
                        playerCount = i + 3;
                    }
                }
                frame.playerSelect(playerCount);
            }
        });
        c.gridy += 1;
        changeLanguage = new JButton();
        this.add(changeLanguage, c);
        changeLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new ChangeLanguagePane(state);
            }
        });
        update();
    }

    /**
     * Update the player count selector. Used for language changes.
     */
    @Override
    public void update() {
        howManyPlayers.setText(state.getMessageString("howManyPlayers"));
        enter.setText(state.getMessageString("enter"));
        changeLanguage.setText(state.getMessageString("changeLanguage"));
    }
}
