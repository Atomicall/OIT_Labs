import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class BSTNonRecursive<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

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

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of key-value pairs in BST
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /***********************************************************************
     *  Search BST for given key, and return associated value if found,
     *  return null if not found
     ***********************************************************************/
    // does there exist a key-value pair with given key?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with the given key, or null if no such key exists
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    /***********************************************************************
     *  Insert key-value pair into BST
     *  If key already exists, update with new value
     ***********************************************************************/
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        assert check();
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /***********************************************************************
     *  Delete
     ***********************************************************************/

    public void deleteMin() {
        if (isEmpty()) throw new RuntimeException("Symbol table underflow");
        root = deleteMin(root);
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new RuntimeException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /***********************************************************************
     *  Min, max, floor, and ceiling
     ***********************************************************************/
    public Key min() {
        return min(root).key;
    }

    public Node min(Node x) {
        if (isEmpty()) return null;
        Node tempNode = x;
        while (tempNode.left != null) {
            tempNode = tempNode.left;
        }
        return tempNode;
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        Node tempNode = x;
        while (tempNode.right != null) {
            tempNode = tempNode.right;
        }
        return tempNode;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
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
//    private Node floor(Node x, Key key) {
//        if (x == null) return null;
//        Node currNode = x;
//        Node prevNode = null;
//        while (currNode!= null){
//            int cmp = key.compareTo(currNode.key);
//            if (cmp == 0) return currNode;
//            if (cmp <  0) {
//                prevNode = currNode;
//                currNode = currNode.left;
//            }
//            else{
//                if (currNode.right != null){
//                    if (currNode.right.key.compareTo(key)<=0){
//                        currNode = currNode.right;
//                        continue;
//                    }
//                    else {
//                        currNode = currNode.right;
//                    }
//                }
//                cur;
//            }
//        }
//        return prevNode;
//    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
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

    /***********************************************************************
     *  Rank and selection
     ***********************************************************************/
    public Key select(int k) {
        if (k < 0 || k >= size()) return null;
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k.
    private Node select(Node x, int k) {
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

    public int rank(Key key) {
        return rank(key, root);
    }

    // Number of keys in the subtree less than x.key.
    private int rank(Key key, Node x) {
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

    /***********************************************************************
     *  Range count and range search.
     ***********************************************************************/
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new ArrayDeque<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    // height of this BST (one-node tree has height 0)
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /*************************************************************************
     *  Check integrity of BST data structure
     *************************************************************************/
    private boolean check() {
        if (!isBST()) System.out.println("Not in symmetric order");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) System.out.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    private class Node {
        private final Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
}

