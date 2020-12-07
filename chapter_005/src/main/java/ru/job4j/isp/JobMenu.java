package ru.job4j.isp;

import java.util.*;

public class JobMenu implements CRUD, Printable {
    private Node root;

    public JobMenu(String key, Action action) {
        this.root = new Node(key, action);
    }

    public boolean add(String parent, String node, Action action) {
        Optional<Node> parentNode = find(parent);
        Optional<Node> childrenNode = find(node);
        if (parentNode.isEmpty() || childrenNode.isPresent()) {
            return false;
        }
        parentNode.get().children.add(new Node(parentNode.get(), node, action));
        return true;
    }

    @Override
    public Action get(String key) {
        Optional<Node> node = find(key);
        return node.isPresent() ? node.get().action : null;
//        return node.map(value -> value.action).orElse(null);
    }

    @Override
    public String unOrdered() {
        return unOrdered(root, 0, new StringBuilder()).toString();
    }

    private StringBuilder unOrdered(Node node, int level, StringBuilder out) {
        String prefix = "-".repeat(level);
        out.append(String.format("%s%s%n", (prefix + " ").stripLeading(), node.action.getName()));
        node.children.forEach(c -> unOrdered(c, level + 1, out));
        return out;
    }

    private Optional<JobMenu.Node> find(String key) {
        Optional<Node> found = Optional.empty();
        Queue<Node> queue = new LinkedList<>(List.of(root));
        while (!queue.isEmpty()) {
            Node el = queue.poll();
            if (Objects.equals(el.key, key)) {
                found = Optional.of(el);
                break;
            }
            queue.addAll(el.children);
        }
        return found;
    }

    private static class Node {
        private String key;
        private Action action;
        private List<Node> children = new LinkedList<>();
        private Node parent;

        public Node(String key, Action action) {
            this.key = key;
            this.action = action;
        }
        public Node(Node parent, String key, Action action) {
            this.parent = parent;
            this.key = key;
            this.action = action;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return key.equals(node.key)
                    && children.equals(node.children)
                    && parent.equals(node.parent);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, children, parent);
        }
    }
}
