package passwordgeneratorgui;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class GUIController implements Initializable {

    Random random = new Random();
    final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    final String DIGITS = "0123456789";
    final String SPECIALS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    @FXML
    private Spinner<Integer> lengthSpinner;
    @FXML
    private TextField passwordTextField;
    @FXML
    private CheckBox uppercaseCheckBox, lowercaseCheckBox, digitsCheckBox, specialCheckBox;

    @FXML
    private void generatePassword(ActionEvent e) {
        // set from which to pick potential characters for password
        String charactersToUse = "";

        // add to set based on parameters passed
        if (uppercaseCheckBox.isSelected()) {
            charactersToUse += UPPERCASE;
        }
        if (lowercaseCheckBox.isSelected()) {
            charactersToUse += LOWERCASE;
        }
        if (digitsCheckBox.isSelected()) {
            charactersToUse += DIGITS;
        }
        if (specialCheckBox.isSelected()) {
            charactersToUse += SPECIALS;
        }

        // if all false, default to lowercase
        if (charactersToUse.equals("")) {
            charactersToUse += LOWERCASE;
        }

        // pick random character from character set iteratively given the desired length
        String passwordToReturn = "";
        for (int i = 0; i < (int) lengthSpinner.getValue(); i++) {
            int randomPos = random.nextInt(charactersToUse.length());
            passwordToReturn += charactersToUse.charAt(randomPos);
        }

        // return the password
        passwordTextField.setText(passwordToReturn);
    }
    
    @FXML
    private void copyPassword(ActionEvent e) {
        // copy password to clipboard
        ClipboardContent content = new ClipboardContent();
        content.putString(passwordTextField.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // set spinner minimum, maximum, and starting values
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 256);
        valueFactory.setValue(16);
        lengthSpinner.setValueFactory(valueFactory);
    }

}
