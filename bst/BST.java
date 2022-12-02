class BSTNode<T> {
	public int NodeKey;
	public T NodeValue;
	public BSTNode<T> Parent;
	public BSTNode<T> LeftChild;
	public BSTNode<T> RightChild;

	public BSTNode(int key, T val, BSTNode<T> parent) {
		NodeKey = key;
		NodeValue = val;
		Parent = parent;
		LeftChild = null;
		RightChild = null;
	}
}

class BSTFind<T> {

	public BSTNode<T> Node;

	public boolean NodeHasKey;

	public boolean ToLeft;

	public BSTFind() {
		Node = null;
	}

	public static <T> BSTFind<T> fromHasKey(BSTNode<?> node) {
		return new BSTFind(node);
	}

	private BSTFind(BSTNode<T> node) {

		this.Node = node;
		this.NodeHasKey = true;

	}

	public static <T> BSTFind<T> fromNotHasKey(BSTNode<?> Node, boolean ToLeft) {
		return new BSTFind(Node, ToLeft);
	}

	private BSTFind(BSTNode<T> Node, boolean ToLeft) {

		this.Node = Node;
		this.NodeHasKey = false;
		this.ToLeft = ToLeft;

	}
}


class BST<T> {
	public BSTNode<T> Root;

	public BST(BSTNode<T> node) {
		Root = node;
	}

	public BSTFind<T> FindNodeByKey(int key) {

		if (Root == null) return null;
		

		
		BSTNode<T> findNode = FindNodeByKey(key, Root);
		
		if (findNode.NodeKey == key ) {
			return BSTFind.fromHasKey(findNode); 
		}
		
		if (key < findNode.NodeKey) {
			
			return BSTFind.fromNotHasKey(findNode, true);
		}
		
		return BSTFind.fromNotHasKey(findNode, false);
		
	}

	private BSTNode<T> FindNodeByKey(int key, BSTNode<T> node) {

		if (key == node.NodeKey) {
			return node;
		}
		
		if (key < node.NodeKey && node.LeftChild != null) {
			 return FindNodeByKey(key, node.LeftChild);
		}

		if (key > node.NodeKey && node.RightChild != null) {
			return FindNodeByKey(key, node.RightChild);
		}
		
		return node;
		
	}

	public boolean AddKeyValue(int key, T val) {
		
		if (Root == null) {
			Root = new BSTNode<T>(key, val, null);
			return true;
		}
		
		BSTFind<T> find = FindNodeByKey(key);

		if (find.NodeHasKey) {
			return false;
		}
		
		BSTNode<T> node = new BSTNode<T>(key, val, find.Node);
		
		if (find.ToLeft) {
			find.Node.LeftChild = node;
			return true;
		}
		
		find.Node.RightChild = node;

		return true; 
		
	}

	public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
		
		if (FromNode == null) {
			FromNode = Root;
		}
		
		if (FindMax) {
			return FindMax(FromNode);
		}
		
		return FindMin(FromNode);
		
	}
	private BSTNode<T> FindMax(BSTNode<T> Node) {
		
		if (Node.RightChild != null) {
			return FindMax(Node.RightChild);
		}
		
		return Node;
				
	}
	private BSTNode<T> FindMin(BSTNode<T> Node) {
		
		if (Node.LeftChild != null) {
			return FindMin(Node.LeftChild);
		}
		
		return Node;
				
	}


	public boolean DeleteNodeByKey(int key) {
		
		BSTFind<T> find = FindNodeByKey(key);

		if (!find.NodeHasKey) return false;
		
		boolean deletableNodeFromRight = find.Node.Parent != null && find.Node.Parent.RightChild != null && find.Node.Parent.RightChild.equals(find.Node);
		boolean deletableNodeFromLeft = find.Node.Parent != null && find.Node.Parent.LeftChild != null && find.Node.Parent.LeftChild.equals(find.Node);
				
		if (find.Node.RightChild == null && find.Node.LeftChild == null) {
			
			if (deletableNodeFromLeft) find.Node.Parent.LeftChild = null;
			else if (deletableNodeFromRight) find.Node.Parent.RightChild = null;
			else Root = null;
			
			find.Node.Parent = null;
			
			return true;

		}

		if(find.Node.RightChild == null) {
			find.Node.LeftChild.Parent = find.Node.Parent;

			if (deletableNodeFromLeft) find.Node.Parent.LeftChild = find.Node.LeftChild;
			else if (deletableNodeFromRight) find.Node.Parent.RightChild = find.Node.LeftChild;
			else Root = find.Node.LeftChild;
			
			find.Node.LeftChild = null;
			find.Node.Parent = null;

			return true;
		}

		BSTNode<T> substituteNode = FindSubstituteNode(find.Node.RightChild);

		if (substituteNode.RightChild != null) {
			substituteNode.Parent.LeftChild = substituteNode.RightChild;
			substituteNode.RightChild.Parent = substituteNode.Parent;
			substituteNode.RightChild = null;
		}else if(find.Node.NodeKey != substituteNode.Parent.NodeKey) {
			substituteNode.Parent.LeftChild = null;	
		}
		
		substituteNode.Parent = find.Node.Parent;

		if (deletableNodeFromRight)  {
			find.Node.Parent.RightChild = substituteNode;
		}
		else if (deletableNodeFromLeft)  {
			find.Node.Parent.LeftChild = substituteNode;
		}
		else {
			Root = substituteNode;
			substituteNode.Parent = null;
		}
		
		substituteNode.LeftChild = find.Node.LeftChild;
		substituteNode.LeftChild.Parent = substituteNode;

		if(substituteNode.NodeKey != find.Node.RightChild.NodeKey) {
			substituteNode.RightChild = find.Node.RightChild;
			if (substituteNode.RightChild != null) {
				substituteNode.RightChild.Parent = substituteNode;
			}
		}

		if(substituteNode.LeftChild != null) {

			substituteNode.LeftChild.Parent = substituteNode;

		}
		
		find.Node.Parent = null;
		find.Node.LeftChild = null;
		find.Node.RightChild = null;
		
		return true;
		
	} 
	private BSTNode<T> FindSubstituteNode(BSTNode<T> node) {

		if (node.LeftChild == null) {
			return node;
		}

		return FindSubstituteNode(node.LeftChild);
		
	}

	public int Count() {

		if (Root == null) return 0;

		return Count(Root);
		
	}
	private int Count(BSTNode<T> node) {
				
		if (node.LeftChild != null && node.RightChild != null) {
			return 1 + Count(node.LeftChild) + Count(node.RightChild);
		}else if (node.LeftChild != null) {
			return 1 + Count(node.LeftChild);
		}else if (node.RightChild != null) {
			return 1 + Count(node.RightChild);
		}
		
		return 1;

	}

}
