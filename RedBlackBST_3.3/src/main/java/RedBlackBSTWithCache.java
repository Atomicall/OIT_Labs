public class RedBlackBSTWithCache<Key extends Comparable<Key>, Value> extends RedBlackBSTBookExample<Key, Value>{
    private Node previousUsedNode;
    private boolean checkKeyIsEqualsToPrevKey(Key key){
        if (previousUsedNode!= null && previousUsedNode.key.equals(key)){
            System.out.println("key found in cache:" + key);
            return true;
        }
        else {
            System.out.println("Nothing found in cache");
            return false;
        }
    }

    @Override
    public Value get(Key key){
        System.out.println("Looking in cache");
        if (checkKeyIsEqualsToPrevKey(key)){
            System.out.println("Found in cache:" + previousUsedNode.val);
            return previousUsedNode.val;
        }
        return super.get(key);
    }

    @Override
    protected Value get(Node x, Key key){
        if (x!= null && x.equals(previousUsedNode)){
            return previousUsedNode.val;
        }
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                previousUsedNode = x;
                return x.val;
            }
        }
        return null;
    }

    @Override
    protected Node delete(Node h, Key key){
        assert contains(h, key);
        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
            {
                if (h.equals(previousUsedNode)){
                    System.out.println("prevNode is null now (has been deleted)");
                    previousUsedNode= null;
                }
                    return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

}

