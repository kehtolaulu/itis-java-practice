package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements Observer, Observable {
    private Socket socket;

    public Client() throws IOException {
        socket = new Socket("localhost", 1234);
        new Thread(this::run).start();
    }

    void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = br.readLine();
            while (s != null) {
                notifyObservers(s);
                s = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    //observer
    @Override
    public void onNext(String msg) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(msg);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
