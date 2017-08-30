package com.sabre;

import java.util.*;

public class WordChainProcessor {
    private Map<String, Set<Node>> possibilities = new HashMap<>();
    private Map<String, Node> nodesMap = new HashMap<>();

    private void addPossibility(String key, Node value) {
        Set<Node> nodes = possibilities.get(key);
        if (nodes == null) {
            nodes = new HashSet<>();
            possibilities.put(key, nodes);
        }

        nodes.add(value);
    }

    public void addWords(String... args) {
        for (String word : args) {
            this.addWord(word);
        }
    }

    public void addWord(String word) {
        Node node = new Node(word);

        for (int i = 0; i < word.length(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(word.substring(0, i));
            builder.append(word.substring(i + 1, word.length()));
            addPossibility(builder.toString(), node);
        }

        nodesMap.put(word, node);
    }

    private void addChildren(Node node) {
        for (int i = 0; i < node.value.length(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(node.value.substring(0, i));
            builder.append(node.value.substring(i + 1, node.value.length()));

            Set<Node> list = possibilities.get(builder.toString());
            if (list != null) {
                for (Node elem : list) {
                    if (!node.value.equals(elem.value)) {
                        node.children.add(elem);
                    }
                }
                node.children.addAll(list);
            }
        }
    }

    public void makeGraph() {
        Collection<Node> nodes = nodesMap.values();
        for (Node node : nodes) {
            addChildren(node);
        }
    }

    public List<Node> findRoute(String from, String to) {
        Node node = nodesMap.get(from);
        return new Router().findRoute(node, to);
    }
}

class Router {
    private Set<Node> passed = new HashSet<>();

    public List<Node> findRoute(Node node, String to) {
        passed.add(node);
        List<Node> result = new LinkedList<>();

        for (Node child : node.children) {
            if (passed.contains(child)) {
                continue;
            }

            if (child.value.equals(to)) {
                result.add(node);
                result.add(child);
                return result;
            }

            List found = findRoute(child, to);
            if (found != null) {
                result.add(node);
                result.addAll(found);
                return result;
            }
        }

        return null;
    }

}
