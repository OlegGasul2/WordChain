package com.sabre;

import java.util.HashSet;
import java.util.Set;

public class Node {
    String value;
    Set<Node> children = new HashSet<>();

    Node(String value) {
        this.value = value;
    }
}
