import java.util.LinkedList;
import java.util.Queue;

public class BSTNonRecursive<Key extends Comparable<Key>, Value> extends BSTBookExample<Key, Value> {

    public void printByLevel() {
        printByLevel(this.root);
    }

    public void printByLevel(Node root) {
        Queue<Node> qe = new LinkedList<>();
        if (root == null) return;
        qe.add(root);
        int count = qe.size();
        while (count != 0) {
            assert qe.peek() != null;
            System.out.print(qe.peek().key);
            System.out.print("  ");
            assert qe.peek() != null;
            if (qe.peek().left != null) qe.add(qe.peek().left);
            if (qe.peek().right != null) qe.add(qe.peek().right);
            qe.remove();
            count = count - 1;
            if (count == 0) {
                System.out.println("  ");
                count = qe.size();
            }
        }
    }

    @Override
    protected Node min(Node x) {
        Node tempNode = x;
        while (tempNode.left != null) {
            tempNode = tempNode.left;
        }
        return tempNode;
    }

    @Override
    protected Node max(Node x) {
        Node tempNode = x;
        while (tempNode.right != null) {
            tempNode = tempNode.right;
        }
        return tempNode;
    }

    @Override
    protected Node floor(Node x, Key key) {
        if (x == null) return null;
        Node currNode = x;
        Node prevNode = null;
        while (currNode != null) {
            int cmp = key.compareTo(currNode.key);
            if (cmp == 0) return currNode;
            if (cmp < 0) {
                //prevNode = currNode;
                currNode = currNode.left;
            } else {
                prevNode = currNode;
                currNode = currNode.right;
            }
        }
        return prevNode;
    }

    @Override
    protected Node ceiling(Node x, Key key) {
        if (x == null) return null;
        Node currNode = x;
        Node prevNode = null;
        while (currNode != null) {
            int cmp = key.compareTo(currNode.key);
            if (cmp == 0) return currNode;
            if (cmp < 0) {
                prevNode = currNode;
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
        }
        return prevNode;
    }

    @Override
    protected int rank(Key key, Node x) {
        Node tempNode = x;
        int summator = 0;
        while (tempNode != null) {
            int cmp = key.compareTo(tempNode.key);
            if (cmp < 0) {
                tempNode = tempNode.left;
            } else if (cmp > 0) {
                summator += 1 + size(tempNode.left);
                tempNode = tempNode.right;
            } else {
                return size(tempNode.left) + summator;
            }
        }
        return 0;
    }

    @Override
    protected Node select(Node x, int k) {
        if (x == null) return null;
        Node tempNode = x;
        int tempRank = k;
        while (tempNode != null) {
            int t = size(tempNode.left);
            if (t > tempRank) {
                tempNode = tempNode.left;
            } else if (t < tempRank) {
                tempNode = tempNode.right;
                tempRank = tempRank - t - 1;
            } else {
                return tempNode;
            }
        }
        return null;
    }
}
