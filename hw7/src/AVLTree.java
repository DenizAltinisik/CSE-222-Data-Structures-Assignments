class AVLTree {
    private class Node {
        Stock stock;
        Node left, right;
        int height;

        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;

    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public void insert(Stock stock) {
        root = insertRec(root, stock);
    }

    private Node insertRec(Node node, Stock stock) {
        if (node == null)
            return (new Node(stock));

        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0)
            node.left = insertRec(node.left, stock);
        else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0)
            node.right = insertRec(node.right, stock);
        else {
            node.stock = stock;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0)
            return rightRotate(node);

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0)
            return leftRotate(node);

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void delete(String symbol) {
        root = deleteRec(root, symbol);
    }

    private Node deleteRec(Node root, String symbol) {
        if (root == null)
            return root;

        if (symbol.compareTo(root.stock.getSymbol()) < 0)
            root.left = deleteRec(root.left, symbol);
        else if (symbol.compareTo(root.stock.getSymbol()) > 0)
            root.right = deleteRec(root.right, symbol);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.stock = temp.stock;
                root.right = deleteRec(root.right, temp.stock.getSymbol());
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;

        return current;
    }

    public Stock search(String symbol) {
        Node node = searchRec(root, symbol);
        return (node != null) ? node.stock : null;
    }

    private Node searchRec(Node root, String symbol) {
        if (root == null || root.stock.getSymbol().equals(symbol))
            return root;

        if (root.stock.getSymbol().compareTo(symbol) > 0)
            return searchRec(root.left, symbol);

        return searchRec(root.right, symbol);
    }
}