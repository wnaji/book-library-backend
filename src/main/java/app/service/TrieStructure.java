package app.service;

import java.util.*;
import java.util.function.Function;

class Node<D> {
    TreeMap<Character, Node<D>> children;
    D data;

    public Node() {
        this.children = new TreeMap<>();
        this.data = null;
    }

    public boolean contain (char c){
        return this.children.containsKey(c);
    }

    public void add(char c){
        this.children.put(c, new Node<>());
    }

    public Node<D> get(char c){
        return this.children.get(c);
    }
}

public class TrieStructure {

    public static <D> void insert(String word, D object, Node<D> root) {
        insert(word, root, object);
    }

    private static <D> void insert(String word, Node<D> node, D object) {
        if (word == null || word.isEmpty()) {
            node.data = object;
            return;
        }
        char firstChar = word.charAt(0);
        if (!node.contain(firstChar)) {
            node.add(firstChar);
        }
        insert(word.substring(1), node.get(firstChar), object);
    }

    public static <D> void preOrder(Node<D> node, Set<D> listObject) {
        if (node.data != null) {
            listObject.add(node.data);
        }

        for (Map.Entry<Character, Node<D>> entry : node.children.entrySet()) {
            Node<D> child = entry.getValue();
            preOrder(child, listObject);
        }
    }

    public static <D> LinkedHashSet<D> sort(Set<D> listObjects, Function<D, String> function) {
        Node<D> root = new Node<>();

        for (D object : listObjects) {
            String result = function.apply(object);
            insert(result, object, root);
        }

        LinkedHashSet<D> sortedSet = new LinkedHashSet<>();
        System.out.println(sortedSet);
        preOrder(root, sortedSet);
        return sortedSet;
    }
}
