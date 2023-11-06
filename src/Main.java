import java.util.Scanner;

class BinarySearchTree {
    static class Node {
        int element;
        Node leftChild;
        Node rightChild;

        Node(int value) {
            this.element = value;
            leftChild = null;
            rightChild = null;
        }
    }

    private Node root;

    public Node addNode(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.element) {
            current.leftChild = addNode(current.leftChild, value);
        } else if (value > current.element) {
            current.rightChild = addNode(current.rightChild, value);
        }
        return current;
    }

    public void displayInOrder(Node current) {
        if (current != null) {
            displayInOrder(current.leftChild);
            System.out.print(current.element + " ");
            displayInOrder(current.rightChild);
        }
    }

    public Node removeNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.element) {
            if (current.leftChild == null) {
                return current.rightChild;
            } else if (current.rightChild == null) {
                return current.leftChild;
            }

            current.element = findSmallestValue(current.rightChild);
            current.rightChild = removeNode(current.rightChild, current.element);
        } else if (value < current.element) {
            current.leftChild = removeNode(current.leftChild, value);
        } else {
            current.rightChild = removeNode(current.rightChild, value);
        }

        return current;
    }

    private int findSmallestValue(Node root) {
        return root.leftChild == null ? root.element : findSmallestValue(root.leftChild);
    }

    public boolean searchNode(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.element) {
            return true;
        }
        return value < current.element
                ? searchNode(current.leftChild, value)
                : searchNode(current.rightChild, value);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        // Inserting some numbers into the tree
        bst.root = bst.addNode(bst.root, 123);
        bst.root = bst.addNode(bst.root, 456);
        bst.root = bst.addNode(bst.root, 789);
        bst.root = bst.addNode(bst.root, 222);
        bst.root = bst.addNode(bst.root, 333);
        bst.root = bst.addNode(bst.root, 101010);
        bst.root = bst.addNode(bst.root, 121314);

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Current tree: ");
            bst.displayInOrder(bst.root);
            System.out.println("\nChoose a number to remove/0 to leave code: ");
            int userChoice = scanner.nextInt();

            if (userChoice == 0) {
                break;
            }

            if (!bst.searchNode(bst.root, userChoice)) {
                System.out.println("That number isn't in the tree.");
            } else {
                bst.root = bst.removeNode(bst.root, userChoice);
                System.out.println("Removed " + userChoice + " from the tree.");
            }
        } while (true);

        scanner.close();
        System.out.println("Program terminated.");
    }
}
