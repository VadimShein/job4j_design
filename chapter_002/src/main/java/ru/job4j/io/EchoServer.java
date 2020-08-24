package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    public static void main(String[] args) {
        final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String[] strSplit;
                    String[] paramSplit;
                    String answer = "Hello!";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("/?msg")) {
                            strSplit = str.split(" ");
                            for (String temp : strSplit) {
                                if (temp.contains("/?msg")) {
                                    paramSplit = temp.split("msg=", 2);
                                    if (paramSplit[1].equals("Bye")) {
                                        server.close();
                                    }
                                    if (paramSplit[1].equals("Hello")) {
                                        answer = "Hello, dear friend!";
                                    } else {
                                        answer = paramSplit[1];
                                    }
                                }
                            }
                        }
                    }
                    if (!server.isClosed()) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write((answer).getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Test exception", e);
        }
    }
}
