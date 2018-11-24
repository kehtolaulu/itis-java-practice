package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class Controller implements Observer, Observable {
    @FXML
    private Button btnSend;
    @FXML
    private TextField input;
    @FXML
    private TextArea messages;

    void addMessage(String msg) {
        messages.appendText("\n" + msg);
        input.clear();
    }

    @FXML
    void initialize() {
        btnSend.setOnAction(event -> {
            notifyObservers(input.getText());
        });
    }

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(String msg) {
        observers.forEach(o -> o.onNext(msg));
    }

    @Override
    public void onNext(String msg) {
        addMessage(msg);
    }
}
