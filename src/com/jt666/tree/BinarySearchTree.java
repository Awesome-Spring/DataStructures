package com.jt666.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    public int size() {
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

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {5, 3, 6, 8, 4, 2};
        for (int i : arr) {
            bst.add(i);
        }
        System.out.print("前序遍历");
        bst.preOrder();
        System.out.println();
        System.out.print("中序遍历");
        bst.inOrder();
        System.out.println();
        System.out.print("后序遍历");
        bst.postOrder();
        System.out.println();
        System.out.print("层序遍历");
        bst.levelOrder();

    }
}
