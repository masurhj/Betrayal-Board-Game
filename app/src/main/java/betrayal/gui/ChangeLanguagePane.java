package betrayal.gui;

import betrayal.GameState;

import javax.swing.*;

public class ChangeLanguagePane extends JOptionPane {
    /**
     * ChangeLanguagePane constructor. Used for changing the language.
     * @param state
     */
    public ChangeLanguagePane(GameState state) {
        Object[] options = {state.getMessageString("english"), state.getMessageString("german")};
        Object selectionObject = JOptionPane.showInputDialog(null, "", state.getMessageString("changeLanguage"),
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selectionObject == null) {
            return;
        } else if (selectionObject.toString().equals(state.getMessageString("english"))) {
            state.changeResourceBundle("en", "US");
        } else if (selectionObject.toString().equals(state.getMessageString("german"))) {
            state.changeResourceBundle("de", "DE");
        }
    }
}
