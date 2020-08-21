package cloudsim.ext.gui;
class Node{
    private int data;
    private Node left;
    private Node right;
    public Node(int element){
        data = element;
        left = null;
        right = null;
    }
    public void setRightChild(Node n)
    {
        right = n;
    }
    public void setLeftChild(Node n){
        left = n;
    }
    public Node getRightChild(){
        return right;
    }
    public Node getLeftChild(){
        return left;
    }
   public int getData(){
        return data;
    }
    public void setData(int x){
        data = x;
    }
}
public class Binary_Tree {
    public static Node search(int x, Node n){
        if (n==null || n.getData()==x) 
            return n;
        else if(n.getData()>x) 
            return search(x, n.getLeftChild()); 
        else 
            return search(x, n.getRightChild());
    }
    public static Node findMinimum(Node root){
        if(root==null)
            return null;
        else if(root.getLeftChild() != null) 
            return findMinimum(root.getLeftChild()); 
        return root;
    }
    public static Node insert(Node root, int x){
        if (root == null)
            return new Node(x);
        else if(x>root.getData())
            root.setRightChild(insert(root.getRightChild(),x));
        else 
            root.setLeftChild(insert(root.getLeftChild(),x));
        return root;
    }
    public static Node delete(Node root, int x){        
        if(root==null)
            return null;
        if (x>root.getData())
            root.setRightChild(delete(root.getRightChild(), x));
        else if(x<root.getData())
            root.setLeftChild(delete(root.getLeftChild(), x));
        else
        {
            if(root.getLeftChild()==null && root.getRightChild()==null)
            {
                root = null;
                return null;
            }

            else if(root.getLeftChild()==null || root.getRightChild()==null)
            {
                Node temp;
                if(root.getLeftChild()==null)
                    temp = root.getRightChild();
                else
                    temp = root.getLeftChild();
                root = null;
                return temp;
            }

            else
            {
                Node temp = findMinimum(root.getRightChild());
                root.setData(temp.getData());
                root.setRightChild(delete(root.getRightChild(), temp.getData()));
            }
        }
        return root;
    }

    public static void inorder(Node root){
        if(root!=null){ // checking if the root is not null
            inorder(root.getLeftChild()); // visiting left child
            System.out.print(" "+root.getData()+" "); // printing data at root
            inorder(root.getRightChild()); // visiting right child
        }
    }
    public static void main(String[] args){
        Node root;
        root = new Node(30);
        for(int i=1;i<+30;i++){
            insert(root,i);
        }
        inorder(root);
        System.out.println("");
        root = delete(root, 15);        
        root = delete(root, 20);
        root = delete(root, 25);      
        root = delete(root, 9);
        inorder(root);        
        System.out.println("");
    }
}
