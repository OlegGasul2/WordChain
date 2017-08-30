package com.sabre;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordChainProcessor {
    public Map<String, Set<Node>> possibilities = new HashMap<>();

    private void addPosibility(String key, Node value) {
        Set<Node> nodes = possibilities.get(key);
        if (nodes == null) {
            nodes = new HashSet<>();
            possibilities.put(key, nodes);
        }

        nodes.add(value);
    }

    public void addWord(String word) {
        Node node = new Node(word);

        for (int i = 0; i < word.length(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(word.substring(0, i));
            builder.append(word.substring(i + 1, word.length()));
            addPosibility(builder.toString(), node);
        }
    }
}
