package ru.job4j.collection;

import java.util.List;
import java.util.Objects;

public class Analize {
    private int added = 0;
    private int changed = 0;
    private int deleted = 0;

    public Info diff(List<User> previous, List<User> current) {
        java.util.HashMap<Integer, String> map = new java.util.HashMap<>();
        for (User c : previous) {
            map.put(c.getId(), c.getName());
        }
        for (User c : current) {
            if (!map.containsKey(c.getId())) {
                this.added++;
            } else {
                if (!map.containsValue(c.getName())) {
                    this.changed++;
                }
                map.remove(c.getId());
            }
        }
        this.deleted = map.size();
        return new Info(this.added, this.changed, this.deleted);
}

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }
        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
        public int getAdded() {
            return added;
        }
        public int getChanged() {
            return changed;
        }
        public int getDeleted() {
            return deleted;
        }
    }
}
