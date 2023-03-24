package betrayal.gui;

import betrayal.Token;
import betrayal.controllers.SuppressFBWarnings;
import betrayal.card.Card;
import betrayal.GameState;
import betrayal.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayerInfoPanel extends JPanel implements ComponentObserver {

    private Player currentPlayer;

    private GridBagConstraints constraints = new GridBagConstraints();

    private GameState state;

    private JLabel playerName, speedStat, mightStat, sanityStat,
            knowledgeStat, age, height, weight, itemLabel;
    private JButton endTurn, useItem, dropItem, changeLanguage, useToken;

    private JComboBox<String> playerItemList;
    private ArrayList<String> itemNames;
    private HashMap<String, Card> inventory;

    /**
     * PlayerInfoPanel constructor.
     * @param state
     */
    @SuppressFBWarnings
    public PlayerInfoPanel(final GameState state) {
        super(new GridBagLayout());
        this.state = state;
        this.state.addObserver(this);

        setupLabels();

        itemNames = new ArrayList<String>();

        update();
    }

    /**
     * Setup all the labels and buttons.
     */
    public void setupLabels() {
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridy = 0;
        constraints.gridx = 0;

        playerName = new JLabel();
        add(playerName, constraints);
        constraints.gridy = 1;
        speedStat = new JLabel();
        add(speedStat, constraints);
        constraints.gridy = 2;
        mightStat = new JLabel();
        add(mightStat, constraints);
        constraints.gridy = 3;
        sanityStat = new JLabel();
        add(sanityStat, constraints);
        constraints.gridy = 4;
        knowledgeStat = new JLabel();
        add(knowledgeStat, constraints);
        constraints.gridy = 1;
        constraints.gridx = 1;
        age = new JLabel();
        add(age, constraints);
        constraints.gridy = 2;
        height = new JLabel();
        add(height, constraints);
        constraints.gridy = 3;
        weight = new JLabel();
        add(weight, constraints);
        constraints.gridy = 2;
        constraints.gridx = 4;
        itemLabel = new JLabel();
        add(itemLabel, constraints);
        constraints.gridy = 3;
        playerItemList = new JComboBox();
        constraints.gridx = 4;
        useItem = new JButton();
        useItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInventory();
                String itemName = itemNames.get(Integer.parseInt(((String) playerItemList.getSelectedItem()).split(": ")[0]));
                inventory = state.getCurrentPlayer().getInventory();
                System.out.println(inventory.get(itemName).isUsed());
                if (!inventory.get(itemName).isUsed()) {
                    state.getCurrentPlayer().use(itemName, state);
                }
            }
        });
        add(useItem, constraints);
        constraints.gridx = 5;
        dropItem = new JButton();
        add(dropItem, constraints);
        dropItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] splitItem = ((String) playerItemList.getSelectedItem()).split(": ");
                if (splitItem[1].equals("OMEN")) {
                    System.out.println("Omen cannot be dropped.");
                } else {
                    state.getCurrentPlayer().drop(itemNames.get(Integer.parseInt(splitItem[0])), state);
                }
            }
        });
        constraints.gridx = 6;
        constraints.gridy = 2;
        useToken = new JButton();
        useToken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<Token> tokens = state.getCurrentPlayer().getCurrentRoom().getTokens();
                if (tokens.size() != 0) {
                    for (Token token : tokens) {
                        if (token != null) {
                            token.use(state);
                        }
                    }
                }
            }
        });
        add(useToken, constraints);
        constraints.gridx = 7;
        endTurn = new JButton();
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.nextPlayer();
            }
        });
        add(endTurn, constraints);

        constraints.gridx = 8;
        changeLanguage = new JButton();
        this.add(changeLanguage, constraints);
        changeLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new ChangeLanguagePane(state);
            }
        });
    }

    /**
     * Update the info panel.
     */
    @Override
    public void update() {
        currentPlayer = state.getCurrentPlayer();
        String playerNameText = java.text.MessageFormat.format("{0} {1, number}: {2}", state.getMessageString("player"),
                (state.getCurrentPlayerIndex() + 1), currentPlayer.getName());
        String speedText = java.text.MessageFormat.format("{0}: {1, number}", state.getMessageString("speed"),
                currentPlayer.getSpeedValue());
        String mightText = java.text.MessageFormat.format("{0}: {1, number}", state.getMessageString("might"),
                currentPlayer.getMightValue());
        String sanityText = java.text.MessageFormat.format("{0}: {1, number}", state.getMessageString("sanity"),
                currentPlayer.getSanityValue());
        String knowledgeText = java.text.MessageFormat.format("{0}: {1, number}", state.getMessageString("knowledge"),
                currentPlayer.getKnowledgeValue());
        String ageText = java.text.MessageFormat.format("{0}: {1, number}", state.getMessageString("age"),
                currentPlayer.getAge());
        String heightText = java.text.MessageFormat.format("{0}: {1}", state.getMessageString("height"),
                currentPlayer.getHeight());
        String weightText = java.text.MessageFormat.format("{0}: {1}", state.getMessageString("weight"),
                currentPlayer.getWeight());
        String itemText = java.text.MessageFormat.format("{0}:", state.getMessageString("items"));

        playerName.setText(playerNameText);
        speedStat.setText(speedText);
        mightStat.setText(mightText);
        sanityStat.setText(sanityText);
        knowledgeStat.setText(knowledgeText);
        age.setText(ageText);
        height.setText(heightText);
        weight.setText(weightText);
        itemLabel.setText(itemText);

        useItem.setText(state.getMessageString("useCard"));
        dropItem.setText(state.getMessageString("dropCard"));
        useToken.setText(state.getMessageString("useToken"));
        changeLanguage.setText(state.getMessageString("changeLanguage"));
        updateInventory();
        String[] items = new String[inventory.size()];
        int i = 0;
        for (Map.Entry<String, Card> card : inventory.entrySet()) {
            items[i] = i + ": " + card.getValue().getType() + ": " + state.getMessageString((card.getKey()));
            i++;
        }

        remove(playerItemList);
        playerItemList = new JComboBox<String>(items);

        endTurn.setText(state.getMessageString("nextPlayer"));
        constraints.gridy = 2;
        constraints.gridx = 5;
        add(playerItemList, constraints);
    }

    private void updateInventory() {
        itemNames.clear();
        this.inventory = currentPlayer.getInventory();
        for (String name : inventory.keySet()) {
            itemNames.add(name);
        }
    }
}
