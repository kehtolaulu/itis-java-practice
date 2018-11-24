package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

    private ServerSocket serverSocket;
    private List<Socket> sockets;

    public Server() throws IOException {
        serverSocket = new ServerSocket(1234);
        sockets = new ArrayList<>();
        run();
    }

    void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                Thread thread = new Thread(() -> {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String s = br.readLine();
                        while (s != null) {
                            send(s);
                            s = br.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void send(String msg) {
        for (Socket s : sockets) {
            try {
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                pw.println(msg);
                pw.flush();
//                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
