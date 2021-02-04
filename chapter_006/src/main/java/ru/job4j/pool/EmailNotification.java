package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subject = "Notification {" + user.username + "} to email {" + user.email + "}";
        String body = "Add a new event to {" + user.username + "}";
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(subject, body, user.email);
            }
        });
    }

    public void send(String subject, String body, String email) {
        System.out.println(subject + "\n" + body);
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class User {
        String username;
        String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }

    public static void main(String[] args) {
        EmailNotification em = new EmailNotification();
        em.emailTo(new User("Ivan", "iv.@mail.ru"));
        em.emailTo(new User("Vasily", "vas.@mail.ru"));
        em.close();
    }
}
