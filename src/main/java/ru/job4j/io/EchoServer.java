package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String firstLine = input.readLine();
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (firstLine != null && firstLine.contains("msg=")) {
                        String msg = firstLine.split("=")[1].split(" ")[0];
                        if ("Exit".equals(msg)) {
                            output.write("The server is shutting down\r\n\r\n".getBytes());
                            output.flush();
                            server.close();
                            break;
                        } else if ("Hello".equals(msg)) {
                            output.write("Hello, dear friend.\r\n\r\n".getBytes());
                        } else if ("Any".equals(msg)) {
                            output.write("What\r\n\r\n".getBytes());
                        } else {
                            String answer = msg + "\r\n\r\n";
                            output.write(answer.getBytes());
                        }
                    }
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                } catch (IOException e) {
                    LOG.error("Error handling client request", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Server error", e);
        }
    }
}