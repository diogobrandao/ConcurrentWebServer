package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WebServer {


    public void start() {

        int port = 8080;
        try {
            ServerSocket serversocket = new ServerSocket(port);
            System.out.println("Listening on port: " + port + " || " + serversocket);

            ExecutorService fixedPool = Executors.newFixedThreadPool(4);
            while (true) {

                Socket socket = serversocket.accept();
                System.out.println("Connection accepted || " + socket);

                //Thread thread = new Thread(new ClienDispatcher(socket));
                //thread.start();


                fixedPool.submit(new ClienDispatcher(socket));

                fixedPool.shutdown();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}