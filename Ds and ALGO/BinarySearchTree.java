import java.util.LinkedList;
import java.util.Queue;

class BST{
    int info;
    BST right,left,root;
    BST(){
        this.root=null;
    }
    BST(int info){
        this.info=info;
        this.left=null;
        this.right=null;
    }

    void insert(int info){
        BST n=new BST(info);
        if(root==null){
            root=n;
            return;
        }
        BST prev=null,ptr=root;
        while(ptr!=null){
            prev=ptr;
            if(ptr.info<n.info){
                ptr=ptr.right;
            }
            else{
                ptr=ptr.left;
            }
        }
        if(n.info>prev.info){
            prev.right=n;
        }
        else{
            prev.left=n;
        }
    }

    int search(int info){
        if(root==null){
            return -1;
        }
        BST ptr=root;
        while(ptr!=null && ptr.info!=info){
            if(ptr.info<info){
                ptr=ptr.right;
            }
            else{
                ptr=ptr.left;
            }
        }
        if(ptr==null){
            return -1;
        }
        return ptr.info;
    }

    void delete(int info){
        if(root==null){
            System.out.println("Underflow");
            return;
        }
        BST ptr=root;
        BST pre=null;
        while(ptr!=null && ptr.info!=info){
            pre=ptr;
            if(ptr.info<info){
                ptr=ptr.right;
            }
            else{
                ptr=ptr.left;
            }
        }
        if(ptr==null){
            System.out.println("No such Element");
            return;
        }
        if(ptr.right==null && ptr.left==null){  //leaf node
        if(ptr==pre.right){
            pre.right=null;
          }  
        else{
            pre.left=null;
          }     
        }
        else if(ptr.left!=null && ptr.right==null){ //one left child
            if(ptr==pre.right){
                pre.right=ptr.left;
            }
            else{
                pre.left=ptr.left;
            }
        }
        else if(ptr.left==null && ptr.right!=null){ //one right child
            if(ptr==pre.right){
                pre.right=ptr.right;
            }
            else{
                pre.left=ptr.right;
            }
        }
        else if(ptr.left!=null && ptr.right!=null){ //two childs  //change it to right
            pre=ptr;
            BST l=ptr.left; //here is the approach to find largest in left subtree ,other approach is to find smallest in right subtree
            ptr=ptr.left;
            while(l.right!=null){
                ptr=l;
                l=l.right;
            }
            if(l==ptr){
                pre.info=l.info;
                pre.left=l.left;
            }
            else{
                pre.info=l.info;
                ptr.right=null;
            }
        }
    }

    int height(BST n){
        if(n==null){
            return -1;
        }
        else{
            int lh=height(n.left);
            int rh=height(n.right);
            if(lh>rh){
                return lh+1;
            }
            else{
                return rh+1;
            }
        }
    }

    int depth(BST n){
        if(n==null){
            return 0;
        }
        else{
            int lh=depth(n.left);
            int rh=depth(n.right);
            if(lh>rh){
                return lh+1;
            }
            else{
                return rh+1;
            }
        }
    }
    
    int BalanceFactor(BST n){
        if(n==null)
              return -1;
        else{
                  int lh=height(n.left);
                  int rh=height(n.right);
                  return lh-rh;
            }
    }

    Boolean isBalanced(BST n){
        if(n==null){
            return true;
        }
        int lh=height(n.left);
        int rh=height(n.right);
        if((lh-rh>=-1) && (lh-rh<=1) && isBalanced(n.left) && isBalanced(n.right)){
            return true;
        } 
        else{
            return false;
        }
    }

    BST NCA(BST root,int n1,int n2){  //nearest/lowest common ancestor ie.nearest lowest common ancestor if one of node is ancestor include it
        if(root==null)
            return null;
        if(n1>root.info && n2>root.info){
            return NCA(root.right, n1, n2);
        }
        if(n1<root.info && n2<root.info){
            return NCA(root.left, n1, n2);
        }
        // if((root.info>n1 && root.info<n2) || (root.info<n1 && root.info>n1)){
        //     return root;
        // }
        // if(root.info==n1 || root.info==n2){
        //     return root;
        // }
        return root;
    }

    BST LCA(BST root,int n1,int n2){  //least common ancestor //practice on gfg
        if(root==null){
            return null;
        }
        if((root.info>n1 && root.info<n2) || (root.info<n1 && root.info>n1)){
            return root;
        }
        if(n1>root.info && n2>root.info){
            return root;
        }
        if(root.left.info!=n1 || root.left.info!=n2){
            return LCA(root.left, n1, n2);
        }
        else{
            return root;
        }
    }

    void LLR(BST root){    // Leaf nodes left to right
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            System.out.print(root.info+" ");
        }
        if(root.left!=null){
            LLR(root.left);
        }
        if(root.right!=null){
            LLR(root.right);
        }
    }

    void LRL(BST root){     // Leaf nodes right to left
        if(root!=null){
            if(root.right==null && root.left==null){
                System.out.print(root.info+" ");
            }
            if(root.right!=null){
                LRL(root.right);
            }
            if(root.left!=null){
                LRL(root.left);
            }
        }
    }

    BST mirror(BST root){
        if(root==null){
            return null;
        }
        BST l=mirror(root.left);
        BST r=mirror(root.right);
        root.left=r;
        root.right=l;
        return root;
    }
//////////////////////////////////////////////////////
    void printExterior(BST root){   //print boundary/exterior elements anticlockwise
        System.out.print(root.info+" ");
        leftarm(root.left);
        LLR(root);
        rightarm(root.right);
    }

    void leftarm(BST root){
        if(root==null){
            return;
        }
        if(root.right==null && root.left==null){
            return;
        }
        System.out.print(root.info+" ");
        if(root.left==null && root.right!=null){
            leftarm(root.right);
        }
        else{
            leftarm(root.left);
        }
    }

    void rightarm(BST root){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            return;     
        }
        if(root.left!=null && root.right==null){
            rightarm(root.left);
        }
        else{
            rightarm(root.right);
        }
        System.out.print(root.info+" ");
    }
 ////////////////////////////////////////////////////   
}

class BinarySearchTree {
    static void printLevelOrder(BST root){
        Queue<BST> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            BST curr=q.peek();
            q.remove();
            System.out.print(curr.info+" ");
            if(curr.left!=null){
                q.add(curr.left);
            }
            if(curr.right!=null){
                q.add(curr.right);
            }
        }
    }

    static void printInorder(BST root){ 
        if(root!=null){
            printInorder(root.left);
            System.out.print(root.info+" ");
            printInorder(root.right);
        }
    }

    static void printPreorder(BST root){
        if(root!=null){
            System.out.print(root.info+" ");
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    static void printPostorder(BST root){
        if(root!=null){
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(root.info+" ");
        }
    }

    public static void main(String[] args) {
        BST t=new BST();
        t.insert(5);
        t.insert(6);
        t.insert(12);
        t.insert(22);
        t.insert(3);
        t.insert(2);
        t.insert(45);
        t.insert(53);
        t.insert(122);
        t.insert(7);
        System.out.println("Height of tree is "+t.height(t.root));
        System.out.println("Depth of tree is "+t.depth(t.root));
        System.out.println("BalanceFactor of tree is "+t.BalanceFactor(t.root));
        System.out.println(t.search(7));
        System.out.println(t.isBalanced(t.root));
        System.out.println(t.search(2));
        System.out.println(t.search(122));
        System.out.println(t.search(18));
        System.out.println(t.search(45));
        printPreorder(t.root);
        System.out.println();
        printPostorder(t.root);
        System.out.println();
        printInorder(t.root);
        t.delete(45);
        // t.delete(5);  //problem is after deleting root node
        t.delete(22);
        System.out.println();
        printInorder(t.root);
        t.delete(2);
        t.delete(3);
        System.out.println();
        printInorder(t.root);
        System.out.println("Exterior Nodes");
        t.printExterior(t.root);
        BST x=new BST();
        x.insert(10);
        x.insert(9);
        x.insert(11);
        System.out.println(x.BalanceFactor(x.root));
        System.out.println(x.isBalanced(x.root));
        System.out.println((x.LCA(x.root, 9, 11)).info);
        x.insert(34);
        x.insert(54);
        x.insert(12);
        x.insert(22);
        x.insert(5);
        x.insert(6);
        x.LLR(x.root);
        System.out.println();
        x.LRL(x.root);
        System.out.println();
        printPreorder(x.root);
        BST mirrorx=x.mirror(x.root);
        System.out.println("Mirror image of x ");
        printPreorder(mirrorx);
        System.out.println();
        x.printExterior(x.root);
        BST y=new BST();
        y.insert(20);
        y.insert(10);
        y.insert(12);
        y.insert(8);
        y.insert(13);
        y.insert(22);
        y.insert(24);
        y.insert(26);
        System.out.println();
        y.printExterior(y.root);  
        System.out.println();
        BST test=new BST();
        test.insert(30);
        test.insert(15);
        test.insert(25);  
        test.insert(24);  
        test.insert(18);  
        test.insert(19);  
        test.insert(40);  
        test.insert(35);  
        test.insert(34);  
        test.insert(36);  
        test.insert(37);  
        test.printExterior(test.root); 
        System.out.println();
        printLevelOrder(test.root); 
    }    
}
