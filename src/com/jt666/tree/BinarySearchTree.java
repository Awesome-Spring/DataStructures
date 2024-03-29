package com.jt666.tree;

import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        public E e;
        Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * 二分搜索树添加元素
     * 小于根结点，添加到左边，大于则添加到右边，等于根节点，则不作任何改变，二分搜索树不包含重复元素
     *
     */
    public void add(E e) {
        root = add(root, e);
    }

    //向以root为根的二分搜索树中插入元素E，递归算法
    private Node add(Node node, E e) {

        //递归的出口，找到子树为null,则必然添加,完成操作
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            //如果左子树为null,则node.left = new Node(e);如果不为空，继续递归
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            ////如果右子树为null,则node.right = new Node(e);如果不为空，继续递归
            node.right = add(node.right, e);
        }
        //其他情况，比如元素相等，则返回传进来的根节点，不做操作
        return node;
    }

    //查询二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //看以node为根的二分搜索树中是否含有元素e,递归实现
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /*
     * 二分搜索树的前序遍历(先访问结点，再访问左,右子树),最自然，最常用的遍历方式
     *
     * */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        //递归终止
        if (node == null) {
            return;
        }
        //递归调用
        System.out.print(node.e + " ");//首先打印根节点
        preOrder(node.left);//然后递归left,直到left为空，回溯，打印left由深到浅
        preOrder(node.right);//最后递归完了left,递归right,right打印由浅到深
    }

    /*
     * 非递归的前序遍历,巧妙使用栈来实现打印结点的顺序
     * 将根节点入栈，定义cur接收出栈结点
     * 当栈不为null,打印cur.e，依次入栈cur的右子树，左子树,出栈时，依次打印栈的左子树，右子树
     */
    public void inOrderNR() {
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //遍历的展示
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        BSTString(root, 0, res);
        return res.toString();
    }

    //生成以node为根节点，深度为depth描述的字符串
    private void BSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(DepthString(depth) + "null\n");
            return;
        }

        res.append(DepthString(depth) + node.e + "\n");
        BSTString(node.left, depth + 1, res);
        BSTString(node.right, depth + 1, res);
    }

    private String DepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    /*
     * 二分搜索树的中序遍历(访问左子树,结点,右子树),顺序由小到大，最自然，最常用的遍历方式
     * */
    public void inOrder() {
        inOrder(root);
    }

    //中序遍历以node为根的二分搜索树,递归算法
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);//由深到浅打印left
        System.out.print(node.e + " ");//每递归一次，打印当前根节点
        inOrder(node.right);//由浅到深打印right
    }

    /*
     * 二分搜索树的后序遍历(访问右子树,左子树,结点),最自然，最常用的遍历方式
     * */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        //递归的终点
        if (node == null) {
            return;
        }

        postOrder(node.left);//打印right由深到浅
        postOrder(node.right);//打印left由深到浅
        System.out.print(node.e + " ");//最后打印根节点
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.e + " ");
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public E minElement() {
        if (size == 0) {
            throw new RuntimeException("the node is empty");
        }
        return minElement(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minElement(Node node) {

        if (node.left == null) {
            return node;
        }
        return minElement(node.left);

    }

    // 寻找二分搜索树的最大元素
    public E maxElement() {
        if (size == 0) {
            throw new RuntimeException("the node is empty");
        }
        return maxElement(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maxElement(Node node) {

        if (node.right == null) {
            return node;
        }
        return maxElement(node.right);

    }

    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMinElement() {
        E ret = minElement();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        //递归终止条件(向左走走不动，即左子树为空)
        if (node.left == null) {
            //如果有右子树，将这个右子树返回，成为根节点
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;

    }

    // 从二分搜索树中删除最大值所在节点, 返回最大值
    public E removeMaxElement() {
        E ret = maxElement();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {

        //递归终止条件(向右走走不动，即右子树为空)
        if (node.right == null) {
            //如果有左子树，将这个左子树返回，成为根节点
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;

    }

    //从二分搜索树中删除指定值
    public void removeElement(E e) {
        if (!contains(e)) {
            throw new IllegalArgumentException("IllegalArgument!");
        }
        root = removeElement(root, e);
    }

    // 删除掉以node为根的二分搜索树中的指定值
    // 返回删除节点后新的二分搜索树的根
    private Node removeElement(Node node, E e) {

        if (node == null) {
            return null;
        }

        //如果要删除元素小于当前节点元素，则去左子树中递归删除
        if (e.compareTo(node.e) < 0) {  //如果要删除元素大于当前节点元素，则去右子树中递归删除
            node.left = removeElement(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = removeElement(node.right, e);
            return node;
        } else { //要删除元素就是当前节点元素

            if (node.left == null) { //要删除的节点上有右子树，将这个右子树返回成为新节点
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) { //要删除的节点上有左子树，将这个左子树返回成为新节点
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //左右子树都不为空的情况  找到该节点的后继节点 即该节点右子树的最小节点
            Node successor = minElement(node.right);
            //将该节点顶替为要删除的节点
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.right = node.left = null;//删除该节点
            return successor;

//
//            //左右子树都不为空的情况  找到该节点的前续节点 即该节点左子树的最大节点
//            Node Preceding  = maxElement(node.left);
//            //将该节点顶替为要删除的节点
//            Preceding .left =removeMax(node.left);
//            Preceding .right = node.right;
//            node.right = node.left = null;//删除该节点
//            return Preceding ;

        }

    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {5, 3, 6, 8, 4, 2};
        for (int i : arr) {
            bst.add(i);
        }

        System.out.println(bst);
        bst.removeElement(3);
        bst.levelOrder();
        System.out.println();
        System.out.println(bst.getSize());
    }
}

