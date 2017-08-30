package com.sabre;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        WordChainProcessor processor = new WordChainProcessor();
        processor.addWords("cat", "can", "pan", "tan");
        processor.addWords("aaa", "aab", "abb", "bbb");
        processor.makeGraph();

        printlnResult("cat", "tan", processor.findRoute("cat", "tan"));
        printlnResult("aaa", "bbb", processor.findRoute("aaa", "bbb"));

    }

    public static void printlnResult(String from, String to, List<Node> nodes) {
        System.out.println(from + ".." + to + " =>");

        if (nodes == null) {
            System.out.println("Route not found");
            return;
        }

        for (Node node : nodes) {
            System.out.println(node.value);
        }
        System.out.println("---");
    }

}
