package com.sabre;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        WordChainProcessor processor = new WordChainProcessor();
        processor.addWords("cat", "can", "pan", "tan");
        processor.makeGraph();

        printlnResult(processor.findRoute("cat", "tan"));

    }

    public static void printlnResult(List<Node> nodes) {
        if (nodes == null) {
            System.out.println("Route not found");
            return;
        }

        for (Node node : nodes) {
            System.out.print(node.value + ",");
        }
        System.out.println();
    }

}
