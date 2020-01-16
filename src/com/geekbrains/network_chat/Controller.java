package com.geekbrains.network_chat;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {

    @FXML
    TextArea chat_ta;

    @FXML
    TextField msg_tf;

    public void sendMessage() {
        if (msg_tf.getText().trim().length() > 0) {
            chat_ta.appendText(msg_tf.getText() + "\n");
            msg_tf.clear();
            msg_tf.requestFocus();
        }
    }
}
