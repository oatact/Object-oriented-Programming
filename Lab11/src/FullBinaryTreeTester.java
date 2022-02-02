import java.util.*;

public class FullBinaryTreeTester {
	
	public static void inOrderTraverse(Node root)
	{
		//YOUR CODE GOES HERE
		if(root == null){
			return;
		}
	    inOrderTraverse(root.left);
	    System.out.print(root.id+" ");
	    inOrderTraverse(root.right);
	
		
	}
	
	public static boolean isFullBinTree(Node root)
	{	//YOUR CODE GOES HERE
		if(root == null)
			return true;
		if(root.left == null && root.right == null)
			return true;
		if((root.left != null)&&(root.right != null))
			return (isFullBinTree(root.left)&&isFullBinTree(root.right));
		return false;
	}
	
	public static void normalTester()
	{
		Node[] ts = new Node[7];
		int count = 0;
		ts[count++] = null;
		ts[count++] = new Node(16, null, null);
		
		ts[count++] = new Node(16, new Node(14, null, null), null);
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, null, null), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), null), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		
		ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				null);
		
		for(int i = 0; i < ts.length; i++)
		{
			System.out.print("[T"+i+"] in-order: ");
			inOrderTraverse(ts[i]);
			System.out.println("\n[T"+i+"] is"+(isFullBinTree(ts[i])?" ":" NOT ")+"a full binary tree.\n");
		}
		
	}
	
	
	/**************BONUS STARTS***************/
	public static void printBinTree(Node root)
	{	//YOUR BONUS CODE GOES HERE
		ArrayList<Node> q1= new ArrayList<Node>();
		ArrayList<Node> q2= new ArrayList<Node>();
		ArrayList<String> show = new ArrayList<String>();
		String str1;
		int count;
		q1.add(root);
		while(true) 
		{
			if(q1!=null) 
			{
				count=q1.size()*2;
					show.add("");
					show.add("");
				for(int i=0;i<q1.size();i++) 
				{
					str1 = Integer.toString(q1.get(i).id);
					show.set(show.size()-2,show.get(show.size()-2)+str1+" ");
					
					if(i%2==0) 
					{
						show.set(show.size()-2,show.get(show.size()-2)+"    ");
					}
					if(q1.get(i).left!=null) 
					{
						q2.add(q1.get(i).left);
						show.set(show.size()-1,show.get(show.size()-1)+"/   ");
					}
					else 
					{
						count--;
					}
					if(q1.get(i).right!=null) 
					{
						q2.add(q1.get(i).right);
						show.set(show.size()-1,show.get(show.size()-1)+"\\ ");
					}
					else 
					{
						count--;
					}
				}
				q1.clear();
				if(count==0) {
					q2=null;	
				}
				
			}
			else {
				q2=null;
			}
			if(q2!=null) 
			{
				count=q2.size()*2;
					show.add("");
					show.add("");
				for(int i=0;i<q2.size();i++) 
				{
					str1 = Integer.toString(q2.get(i).id);
					show.set(show.size()-2,show.get(show.size()-2)+str1+"       ");
					
						if(q2.get(i).left!=null) 
						{
							q1.add(q2.get(i).left);
							show.set(+show.size()-1,show.get(show.size()-1)+"/   ");
						}
						else 
						{
							count--;
						}
						if(q2.get(i).right!=null) 
						{
							q1.add(q2.get(i).right);
							show.set(show.size()-1,show.get(show.size()-1)+"\\   ");
						}
						else 
						{
							count--;
						}
					}
					q2.clear();
					if(count==0) {
						q1=null;
					}
			}
			else {
				q1=null;
			}
			
			if(q1==null&&q2==null) {
				show.remove(show.size()-1);
				show.set(show.size()-1," "+show.get(show.size()-1));
				break;
			}
		}
		int k=0;
		for(int i=show.size();i>0;i--) {
			for(int j=0;j<i;j++) {
				show.set(k, "  "+show.get(k));
			}
			k++;
		}
		for(int i=0;i<show.size();i++) {
			System.out.println(show.get(i));
		}
	}
	
	
	public static Node getBinSearchTree(Node root)
	 { //YOUR BONUS CODE GOES HERE
	  binaryToArray(root);
	  Collections.sort(tree);
	  return ChangeNode(tree, root, 0, tree.size() - 1);
	 }
	 
	 static ArrayList<Integer> tree = new ArrayList<Integer>();
	 
	 private static void binaryToArray(Node node) 
	 {
	  if(node != null)
	  {
	   if(node.left != null) 
	   {
	    binaryToArray(node.left);
	   }
	   
	   tree.add(node.id);
	   
	   if(node.right != null) 
	   {
	    binaryToArray(node.right);
	   }
	  }
	 }
	 
	 private static Node ChangeNode(ArrayList <Integer> A, Node root, int child, int leaf)
	 {
	  if(root != null) 
	  {   
	   root.id = A.get((child + leaf) / 2);
	   ChangeNode(A, root.left,child,((child + leaf) / 2) - 1);
	   ChangeNode(A, root.right,((child + leaf) / 2) + 1,leaf);
	  }
	  return root;
	 }
	
	public static void bonusTester()
	{
		Node t = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)), 
				new Node(4, new Node(8, null, null), new Node(10, null, null)));
		System.out.println("Before Transforming: ");
		printBinTree(t);
		System.out.println("After Transforming: ");
		printBinTree(getBinSearchTree(t));
		
	}
	/**************BONUS ENDS***************/
	
	
	
	public static void main(String[] args)
	{
//		normalTester();
		
		//Uncomment for bonus
		bonusTester();
	}
}