package com.zaremba.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static ArrayList<Node> graph;
    public static void main(String[] args) throws FileNotFoundException {
        setupTree();
        BFS();
    }

    private static void BFS() {
        ArrayList<Node> queue = new ArrayList<>();

        Node rootNode = graph.get(0);

        System.out.println("On " + rootNode);
        for (int childNode : rootNode.getNodes()) {
            System.out.println("Added " + childNode + " to queue");
            queue.add(graph.get(childNode));
        }

        BFSHelper(queue);
    }

    private static void BFSHelper(ArrayList<Node> queue) {
        if (queue.size() == 0)
            return;

        ArrayList<Node> nextIterationQueue = new ArrayList<>();

        for (Node node : queue) {
            System.out.println("On " + node);
            for (int c : node.getNodes()) {
                Node childNode = graph.get(c-1);

                if (!childNode.isVisited()) {
                    System.out.println("Added " + childNode + " to queue");
                    nextIterationQueue.add(childNode);
                }

            }

            graph.get(node.getKey() - 1).setVisited(true);
        }

        BFSHelper(nextIterationQueue);
    }

    private static void setupTree() throws FileNotFoundException {
        graph = new ArrayList<>();
        Scanner scan = new Scanner(new File("tree.txt"));
        while(scan.hasNext()){
            String line = scan.nextLine();
            parseLine(line);
        }
    }

    private static void parseLine(String line) {
        String[] keys = line.split(" ");
        int key = Integer.parseInt(keys[0]);
        ArrayList<Integer> points = new ArrayList<>();
        for(int i = 1; i < keys.length;i++){
            points.add(Integer.parseInt(keys[i]));
        }
        graph.add(new Node(key, points));
    }
}
