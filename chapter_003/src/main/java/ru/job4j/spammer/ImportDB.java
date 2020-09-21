package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            users = rd.lines().map(line -> line.split(";")).map(splitStr -> new User(splitStr[0], splitStr[1])).collect(Collectors.toList());
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values(?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                    ResultSet rs = ps.getGeneratedKeys();
                    while (rs.next()) {
                        System.out.println(String.format("%s %s", rs.getString("name"), rs.getString("email")));
                    }
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        String propertiesPath = "./chapter_003/src/main/java/ru/job4j/spammer/data/app.properties";
        String dataPath = "./chapter_003/src/main/java/ru/job4j/spammer/data/dump.txt";
        try (FileInputStream in = new FileInputStream(propertiesPath)) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, dataPath);
        db.save(db.load());
    }
}