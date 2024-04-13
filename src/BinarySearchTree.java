import java.util.*;
import java.util.LinkedList;

public class BinarySearchTree {

//------------------------------------------------PART-1-------------------------------------------------------

    public static BinaryTreeNode<Integer> takeInputLevelWiseBTree(Scanner s){
        System.out.println("Enter the root data");
        int rootData = s.nextInt();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        Queue<BinaryTreeNode<Integer>> pendingNodes = new LinkedList<>();
        pendingNodes.add(root);

        while (!pendingNodes.isEmpty()){
            BinaryTreeNode<Integer> frontNode = pendingNodes.poll();
            System.out.println("Enter the left child of :"  + frontNode.data);
            int leftChild = s.nextInt();
            if(leftChild!=-1){
                BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<Integer>(leftChild);
                pendingNodes.add(leftNode);
                frontNode.left = leftNode;
            }

            System.out.println("Enter the right child of :"  + frontNode.data);
            int rightChild = s.nextInt();
            if(rightChild!=-1){
                BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<Integer>(rightChild);
                pendingNodes.add(rightNode);
                frontNode.right = rightNode;
            }
        }
        return root;
    }
    public static void printLevelWiseBTree(BinaryTreeNode<Integer> root){
        if (root==null){
            return;
        }
        Queue<BinaryTreeNode<Integer>> pendingNodes = new LinkedList<>();
        pendingNodes.add(root);
        while (!pendingNodes.isEmpty()){
            BinaryTreeNode<Integer> front = pendingNodes.poll();
            String s = front.data + ": ";
            if(front.left!=null){
                s+="L:" + front.left.data +",";
                pendingNodes.add(front.left);
            }
            if(front.right!=null){
                s+= "R:" + front.right.data ;
                pendingNodes.add(front.right);
            }
            System.out.println(s);
        }
    }
    /*
    Search node in Binary search tree return true if node exits in bst else false.
    */
    public static boolean seacrhBst(BinaryTreeNode<Integer> root , int k){
        if(root == null){
            return false;
        }
        if(root.data == k){
            return true;
        }
        if(k< root.data){
            boolean leftBst = seacrhBst(root.left , k);
            return leftBst;
        }
        if(k> root.data){
            boolean rightBst = seacrhBst(root.right , k);
            return rightBst;
        }

        return false;
    }

    /**
     * @param root , int k1 , int k2
     * Print element in a range in BST , given a binary tree and two integer k1 and k2, find and print
     * element which are range in k1 and k2.
     */

    public static void printElementInRange(BinaryTreeNode<Integer> root , int k1 , int k2){
        if(root ==null){
            return;
        }
        if(k1 < root.data){
        printElementInRange(root.left , k1,k2);
        }
        if(root.data>=k1 && root.data<=k2){
            System.out.print(root.data + " ,");
        }
        if(k2 > root.data){
            printElementInRange(root.right, k1, k2);
        }
    }

    /*
    Check if Binary Tree is BST (METHOD : 1) In this method order of complexity is O(n2)
     */

    public static boolean isBST(BinaryTreeNode<Integer> root){
        if(root==null){
            return true;
        }

        int max = maximumBST(root.left);
        int min =  minimumBST(root.right);
        boolean isLeftBst = isBST(root.left);
        boolean isRightBst = isBST(root.right);

        boolean output = (root.data>max) && (root.data<=min) && (isLeftBst && isRightBst);
        return output;
    }

    /*
    Check if Binary Tree is BST (METHOD : 2)
     */

    public static IsBSTReturn IsBST2(BinaryTreeNode<Integer> root){
        if(root ==null){
            IsBSTReturn output = new IsBSTReturn();
            output.isBSt = true;
            output.maximum = Integer.MIN_VALUE;
            output.minimum = Integer.MAX_VALUE;
            return output;
        }
        IsBSTReturn leftOutput  = IsBST2(root.left);
        IsBSTReturn rightOutput = IsBST2(root.right);

        int maximum = Math.max(root.data, Math.max(leftOutput.maximum , rightOutput.maximum));
        int minimum = Math.min(root.data , Math.min(leftOutput.minimum , rightOutput.minimum));

        boolean ans = (root.data > leftOutput.maximum) && (root.data<=rightOutput.minimum) && (leftOutput.isBSt && rightOutput.isBSt);
        IsBSTReturn answer = new IsBSTReturn();
        answer.isBSt  = ans;
        answer.maximum = maximum;
        answer.minimum = minimum;
        return answer;

    }

    /*
   Check if Binary Tree is BST (METHOD : 3)
    */

    public static boolean IsBST3(BinaryTreeNode<Integer> root){
        return IsBST3Helper(root ,Integer.MIN_VALUE , Integer.MAX_VALUE);
    }

    public static boolean IsBST3Helper(BinaryTreeNode<Integer> root ,  int minimum , int maximum){
        if(root==null){
            return true;
        }
        if(root.data <minimum || root.data>maximum){
            return false;
        }
        boolean isleftBst = IsBST3Helper(root.left ,minimum , root.data -1 );
        boolean isrightBst = IsBST3Helper(root.right ,root.data , maximum);

        return isleftBst && isrightBst;
    }

    public static int maximumBST(BinaryTreeNode<Integer> root){
        if(root ==null){
            return Integer.MIN_VALUE;
        }
        return Math.max(root.data ,Math.max( maximumBST(root.left) , maximumBST(root.right)));
    }

    public static int minimumBST(BinaryTreeNode<Integer> root){
        if(root==null){
            return Integer.MAX_VALUE;
        }
        return Math.min(root.data, Math.min(minimumBST(root.left) , minimumBST(root.right)));
    }

    /*
    Construct a BST from a sorted Array
     */
    public static BinaryTreeNode<Integer> sortedArrayToBst(int arr[]){
        return sortedArrayToBstHelper(arr , 0 , arr.length-1);
    }
    public static BinaryTreeNode<Integer> sortedArrayToBstHelper(int arr[] , int startIndex , int endIndex){
        if(startIndex > endIndex){
            return null;
        }
        int mid = (startIndex+ endIndex)/2;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(mid);
        root.left = sortedArrayToBstHelper(arr,startIndex , mid-1);
        root.right = sortedArrayToBstHelper(arr , mid+1 , endIndex);

        return root;
    }

    /*
    Construct a BST from a Linked List
     */

//    Pending on me

    /*

//-----------------------------------------------------PART-2-----------------------------------------------------------/
    Given a Binary Search tree, we have to return the path of the root
    node to the given node.(Root to the node path)
     */
    public static ArrayList<Integer> getPath(BinaryTreeNode<Integer> root, int data){
        if(root ==null){
            return null;
        }
        if(root.data == data){
            ArrayList<Integer> output = new ArrayList<>();
            output.add(root.data);
            return output;
        }

        if(data>root.data){
            ArrayList<Integer> rightsubtree = getPath(root.right, data);
            if(rightsubtree!=null){
                rightsubtree.add(root.data);
                return rightsubtree;
            }
        }

        if(data < root.data){
            ArrayList<Integer> leftsubtree = getPath(root.left, data);
            if(leftsubtree!=null){
                leftsubtree.add(root.data);
                return leftsubtree;
            }
        }

        return null;
    }
    /*
    Given a Binary Search tree, we have to return the path of the root
    node to the given node.
     */
    public static ArrayList<Integer> getPathInBT(BinaryTreeNode<Integer> root, int data){
        if(root ==null){
            return null;
        }
        if(root.data == data){
            ArrayList<Integer> output = new ArrayList<>();
            output.add(root.data);
            return output;
        }
            ArrayList<Integer> rightsubtree = getPathInBT(root.right, data);
            if(rightsubtree!=null){
                rightsubtree.add(root.data);
                return rightsubtree;
            }
            ArrayList<Integer> leftsubtree = getPathInBT(root.left, data);
            if(leftsubtree!=null){
                leftsubtree.add(root.data);
                return leftsubtree;
            }

        return null;
    }

    /*BST class performed search , inertion and deletion*/
    public static boolean searchNodeHelper(BinaryTreeNode<Integer> root , int data){
        if(root == null){
            return false;
        }

        if(root.data==data){
            return true;
        }
        if(data< root.data){
           boolean left = searchNodeHelper(root.left , data);
           return left;
        }
        if(data> root.data){
          boolean right = searchNodeHelper(root.right , data);
          return right;
        }
        return false;
    }

    public static BinaryTreeNode<Integer>  insertionHelper(BinaryTreeNode<Integer> root , int data){
        if(root == null){
            BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<>(data);
            return newRoot;
        }
        if( data > root.data){
            root.right = insertionHelper(root.right , data);
        }
        if( data < root.data){
            root.left = insertionHelper(root.left , data);
        }
        return root;
    }

    public static BinaryTreeNode<Integer> deletionHelper(BinaryTreeNode<Integer> root , int data){
        if(root == null){
            return null;
        }
        else if (data > root.data){
            root.right = deletionHelper(root.right , data);
            return root;



        }
        else if(data < root.data){
            root.left = deletionHelper(root.left , data);
            return root;
        }
        else {
            if(root.left == null && root.right == null){
                return null;
            }
            else if(root.left == null ){
               return root.right;
            }
            else if(root.right == null){
                return root.left ;
            }
            else {
                BinaryTreeNode<Integer> minNode = root.right;
                while (minNode.left!=null){
                    minNode = minNode.left;
                }
                root.data = minNode.data;
                deletionHelper(root.right , minNode.data);
                return root;

            }
        }
    }

    /**
     * Create and insert a Duplicate node  duplicate every node of the tree and attach it to the left of itself.
     * * @param root
     */
    public static BinaryTreeNode<Integer> insertDuplicateNode(BinaryTreeNode<Integer> root){
        if(root==null){
            return null;
        }
        BinaryTreeNode<Integer> duplicateRoot = new BinaryTreeNode<>(root.data);
        duplicateRoot.left = root.left;
        root.left = duplicateRoot;
        insertDuplicateNode(duplicateRoot.left);
        insertDuplicateNode(root.right);
        return root;
    }

    /**
     * Given a binary tree and data of two nodes, find 'LCA' (Lowest Common Ancestor) of the given two nodes in the binary tree.
     * @param root , node1 , node 2
     */

    public static int LCAofBinaryTree(BinaryTreeNode<Integer> root , int node1, int node2){
        if(root == null){
            return -1;
        }
        if(root.data == node1 || root.data == node2){
            return root.data;
        }
        int leftnode = LCAofBinaryTree(root.left , node1 , node2);
        int rightnode = LCAofBinaryTree(root.right , node1 , node2);
        if(leftnode == -1 &&  rightnode == -1){
            return -1;
        }
        else if(leftnode == -1 ){
            return leftnode;
        }
        else if(rightnode == -1){
            return rightnode;
        }
        else{
            return root.data;
        }
    }

    public static int LCAofBinarySearchTree(BinaryTreeNode<Integer> root , int node1 , int node2){
        if(root == null){
            return -1;
        }
        if(root.data == node1 || root.data == node2){
            return root.data;
        }
        if(node1<= root.data && node2 >= root.data || node2<= root.data && node1>= root.data){
            return root.data;
        }
        if(node1 < root.data && node2 < root.data){
            int left = LCAofBinarySearchTree(root.left , node1 , node2);
            if(left!=-1){
                return left;
            }else{
                return -1;
            }
        }
        if(node2 > root.data && node1 > root.data){
            int right = LCAofBinarySearchTree(root.right , node1 , node2);
            if(right!= -1){
                return right;
            }else{
                return -1;
            }
        }

        return -1;
    }

    /*
    * Approach : 1
    Print pair of given sum in  binary tree which contain a unique element the below approach is o(n2) complexity
     */
    public static void printPairSum(BinaryTreeNode<Integer> root , int sum , BinaryTreeNode<Integer> currentNode){
        if(root == null){
            return;
        }
        // make helper function to find a sum and call here
        if(root.data!=Integer.MIN_VALUE){
            boolean ans =  findNum(currentNode , sum - root.data , root.data);
        }
        printPairSum(root.left , sum , currentNode);
        printPairSum(root.right , sum , currentNode);
    }
    public static boolean findNum(BinaryTreeNode<Integer> root , int num ,int data){
        if(root==null){
            return false;
        }
        if(num== root.data){
            root.data = Integer.MIN_VALUE;
            System.out.println(data + "," + num);
            return true;
        }
        findNum(root.left , num , data);
        findNum(root.right , num , data);
       return false;
    }

    /*
    * Approach : 2
    * Given approach
     */
    public static int[] treeToArray (BinaryTreeNode<Integer> root){
        if(root==null){
            int arr[] =new int[0];
            return arr;
        }
        int firstelement = root.data;
       int leftArray[] = treeToArray(root.left);
       int righArray[] = treeToArray(root.left);
       int finalArray[] = new int[leftArray.length + righArray.length + 1];
       finalArray[0] = firstelement;

       return  finalArray;
    }

    /*
    * Path sum to root to leaf : Given a binary tree of type integer and number k , print out all the root to leaf path  where the sum of all node data
    * along the path is equal to k.
    * */
    public static void rootToLeafPathsSumToK(BinaryTreeNode<Integer> root, int k) {
        helper(root,k,"");
    }
    private static void helper(BinaryTreeNode<Integer> root,int k,String s){
        if(root==null)
            return;
        if(root.left==null && root.right==null && root.data==k)
        {
            System.out.println(s+root.data);
            return;
        }
        s=s+root.data+" ";

        helper(root.left,k-root.data,s);
        helper(root.right,k-root.data,s);

    }

    /*Moderate Questions*/

   /*Largest BST , In given binary tree find the largest BST subtree , You have to find the BST with maximum height in the given tree.
   * return the height of the largest bst.
    */
    public static int largestBSTSubtree(BinaryTreeNode<Integer> root){
        Pair ans = largestBSThelper(root);
        return ans.height;
    }
    public static Pair largestBSThelper(BinaryTreeNode<Integer>  root){
        if(root == null){
            Pair output = new Pair();
            output.max = Integer.MIN_VALUE;
            output.min = Integer.MAX_VALUE;
            output.isBST = true;
            output.height = 0;
            return output;
        }

        Pair left  = largestBSThelper(root.left);
        Pair right = largestBSThelper(root.right);

        Pair output = new Pair();

        int min = Math.min(root.data,Math.min(left.min,right.min));
        int max = Math.max(root.data,Math.max(left.max,right.max));

        output.min = min;
        output.max = max;

        output.isBST = left.max < root.data && right.min > root.data
                && left.isBST && right.isBST;

        if(output.isBST){
            output.height = Math.max(left.height,right.height) + 1;
        }else{
            output.height = Math.max(left.height,right.height);
        }

        return output;
    }

    /*
    Replace with Sum greater nodes
    In given BST ,replace each node's data with the sum of all nodes which are greater or equal than it.
    You need to include the current node's data also.
    */
    public static void replaceWithLargerNodesSum(BinaryTreeNode<Integer> root) {
        helper(root, 0);
    }
    public static int helper(BinaryTreeNode<Integer> root , int sum){
        if(root ==null){
            return sum;
        }
        sum = helper(root.right, sum);
        sum+=root.data;
        root.data = sum;
        sum = helper(root.left, sum);
        return sum;
    }

    /*
    Print nodes at distance at K from node
    You are given a Binary Tree of type integer, a integer value of target node's data, and an integer value K.
    Print the data of all nodes that have a distance K from the target node. The order in which they would be printed will not matter.
     */
    public static void nodesAtDistanceK(BinaryTreeNode<Integer> root, int node, int k) {
        print(root,k,node);
    }
    public static int print(BinaryTreeNode<Integer> root,int k,int elem){
        if(root == null){
            return -1;
        }
        if(root.data == elem){
            printAtDepthK(root,k);
            return 0;
        }

        int ld = print(root.left,k,elem);

        int rd;
        if(ld == -1){
            rd = print(root.right,k,elem);
            if(rd == -1){
                return -1;
            }else if(rd + 1 == k){
                System.out.println(root.data+" ");
                return k;
            }else{
                printAtDepthK(root.left,k-rd-2);
                return rd+1;
            }
        }else if(ld + 1 == k){
            System.out.println(root.data+" ");
            return k;
        }else{
            printAtDepthK(root.right,k-ld-2);
            return ld+1;
        }
    }

    public static void printAtDepthK(BinaryTreeNode<Integer> root,int depth){
        if(root == null){
            return;
        }
        if(depth == 0 && root != null){
            System.out.println(root.data+" ");
            return;
        }
        printAtDepthK(root.left,depth-1);
        printAtDepthK(root.right,depth-1);
    }

    /*
    Pair Sum in BST
    Given a binary search tree and an integer S, find pair of nodes in the BST which sum to S. You can use extra space of the order of O(log n).
    Note:
    1. Assume BST contains all unique elements.
    2. In a pair, print the smaller element first.
    */

    /* Approach : 1*/
    public static void printNodesSumToS1(BinaryTreeNode<Integer> root, int s) {
        if (root == null) {
            return;
        }
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        arr1 = helper(root, arr1);
        int i = 0;
        int j = arr1.size() - 1;
        while (i < j) {
            int sum = arr1.get(i) + arr1.get(j);
            if (sum == s) {
                System.out.println(arr1.get(i) + " " + arr1.get(j));
                i++;
                j--;
            } else if (sum < s) {
                i++;
            } else {
                j--;
            }
        }
    }
    public static ArrayList<Integer> helper(BinaryTreeNode<Integer> root, ArrayList<Integer> arr) {
        if (root == null) {
            return arr;
        }
        helper(root.left, arr);
        arr.add(root.data);
        helper(root.right, arr);
        return arr;
    }

    /* Approach : 2*/
    public static void printNodesSumToS(BinaryTreeNode<Integer> root, int s) {
        if (root == null) {
            return;
        }
        Stack<BinaryTreeNode<Integer>> stack1 = new Stack<>();
        Stack<BinaryTreeNode<Integer>> stack2 = new Stack<>();
        pushNodeInStack(root, false, stack1);
        pushNodeInStack(root, true, stack2);
        int node1 = stackPopedValue(stack1, false);
        int node2 = stackPopedValue(stack2, true);

        while (node1 < node2) {
            int sum = node1 + node2;
            if (sum == s) {
                System.out.println(node1 + " " + node2);
                node1 = stackPopedValue(stack1, false);
                node2 = stackPopedValue(stack2, true);
            } else if (sum > s) {
                node2 = stackPopedValue(stack2, true);
            } else {
                node1 = stackPopedValue(stack1, false);

            }

        }
    }
    public static int stackPopedValue(Stack<BinaryTreeNode<Integer>> stack, Boolean isReverse) {
        BinaryTreeNode<Integer> poppedValue = stack.pop();
        if (isReverse) {
            pushNodeInStack(poppedValue.left, true, stack);
        } else {
            pushNodeInStack(poppedValue.right, false, stack);
        }
        return poppedValue.data;
    }
    public static void pushNodeInStack(BinaryTreeNode<Integer> root, Boolean isReverse,Stack<BinaryTreeNode<Integer>> stack) {
        while (root != null) {
            stack.push(root);
            if (isReverse) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }


    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

//----------------------PART-1--------------------------------------------------------------------
        System.out.println("Level wise Input and print");
        BinaryTreeNode<Integer> rootdatalevelwise = takeInputLevelWiseBTree(s);
        printLevelWiseBTree(rootdatalevelwise);
        boolean exists = seacrhBst(rootdatalevelwise , 20);
        System.out.print("Root exist or not : " + exists);
        printElementInRange(rootdatalevelwise , 7 , 11);
        System.out.println("Is bst or not :");
        isBST(rootdatalevelwise);
        IsBST2(rootdatalevelwise);
        IsBST3(rootdatalevelwise);
        int arr[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        sortedArrayToBst(arr);

//----------------------PART-2--------------------------------------------------------------------
        ArrayList<Integer> pathinbt = getPath(rootdatalevelwise , 12);
        System.out.println(pathinbt);
        ArrayList<Integer> path = getPathInBT(rootdatalevelwise , 12);
        System.out.println(path);
        boolean ans= seacrhBst(rootdatalevelwise , 12);
        System.out.println("Binary tree have this number :" + ans);
        BinaryTreeNode<Integer> newBT = insertionHelper(rootdatalevelwise , 13);
        printLevelWiseBTree(newBT);
        BinaryTreeNode<Integer> deleteNode = deletionHelper(rootdatalevelwise , 14);
        printLevelWiseBTree(deleteNode);
        System.out.println("Duplicate bst.");
        BinaryTreeNode<Integer> duplicateNode = insertDuplicateNode(rootdatalevelwise);
        printLevelWiseBTree(duplicateNode);

        LCAofBinaryTree(rootdatalevelwise , 12 , 11);
        LCAofBinarySearchTree(rootdatalevelwise , 12 , 11);
        printPairSum(rootdatalevelwise , 9 , rootdatalevelwise);
        treeToArray(rootdatalevelwise);
        rootToLeafPathsSumToK(rootdatalevelwise , 13 );

//----------------------Moderate Questions---------------------------------
       int heightoflargestSubtree =  largestBSTSubtree(rootdatalevelwise);
       System.out.println(heightoflargestSubtree);
        replaceWithLargerNodesSum(rootdatalevelwise);
        nodesAtDistanceK(rootdatalevelwise , 5 , 2);
        printNodesSumToS1(rootdatalevelwise ,12);
        printNodesSumToS(rootdatalevelwise , 12);

    }
}
