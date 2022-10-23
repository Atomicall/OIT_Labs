public class RedBlackBSTWithCache<Key extends Comparable<Key>, Value> extends RedBlackBSTBookExample<Key, Value> {
    private Node previousUsedNode;

    private boolean checkKeyIsEqualsToPrevKey(Key key) {
        if (previousUsedNode != null && previousUsedNode.key.equals(key)) {
            System.out.println("key found in cache:" + key);
            return true;
        } else {
            System.out.println("Nothing found in cache");
            return false;
        }
    }

    private void clearPreviousNodeValue() {
        System.out.println("prevNode is null now (has been deleted)");
        previousUsedNode = null;
    }

    // тут (во всех get) возвращаемое значение - value.
    // таким образом Node инкапсулирована в классе BST
    // и без переписывания исходного класса не получится добавить получение из него Node
    // а для теста и вообще для задачи была поставлена цель минимально (ну только модификаторы доступа)
    // изменять исходный класс (чтобы были видны различия)
    @Override
    public Value get(Key key) {
        System.out.println("Looking in cache");
        if (checkKeyIsEqualsToPrevKey(key)) {
            System.out.println("Found in cache:" + previousUsedNode.val);
            return previousUsedNode.val;
        }
        return super.get(key);
    }

    @Override
    protected Value get(Node x, Key key) {
        if (x != null && x.equals(previousUsedNode)) {
            return previousUsedNode.val;
        }
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                previousUsedNode = x;
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value val) {
        System.out.println("Looking in cache");
        if (checkKeyIsEqualsToPrevKey(key)) {
            System.out.println("Element found in cache:" + previousUsedNode.val + "\n;Replacing");
            put(previousUsedNode, key, val);
            assert check();
        }
        super.put(key, val);
    }

    // с put тут то же самое, что и с методом get: Node не выходит за пределы класса
    // По идее и просто вернуть из него последнюю Node полностью нельзя, т.к метод put исходного класса
    // на протяжении рекурсии корректирует цвета веток дерева
    // а также его конечное возвращаемое значение (на самом выходе из метода) всегда равно root
    @Override
    protected Node put(Node h, Key key, Value val) {
        if (h == null) {
            previousUsedNode = new Node(key, val, RED, 1);
            return previousUsedNode;
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else {
            previousUsedNode = h;
            h.val = val;
        }
        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    @Override
    public void delete(Key key) {
        super.delete(key);
        if (checkKeyIsEqualsToPrevKey(key)) {
            clearPreviousNodeValue();
        }
    }

    @Override
    protected Node deleteMin(Node h) {
        if (h.left == null) {
            if (checkKeyIsEqualsToPrevKey(h.key)) {
                System.out.printf("Deleted Min %s from cache\n", h.val);
                clearPreviousNodeValue();
            }
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    @Override
    protected Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            if (checkKeyIsEqualsToPrevKey(h.key)) {
                System.out.printf("Deleted Max %s from cache\n", h.val);
                clearPreviousNodeValue();
            }
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }
}

