
import java.util.*;

class BSTNode {
	public int NodeKey;
	public BSTNode Parent;
	public BSTNode LeftChild;
	public BSTNode RightChild;
	public int Level;

	public BSTNode(int key, BSTNode parent) {
		NodeKey = key;
		Parent = parent;
		LeftChild = null;
		RightChild = null;
	}
}

class BalancedBST {
	public BSTNode Root;

	public BalancedBST() {
		Root = null;
	}

	public void GenerateTree(int[] a) {

		if (a.length == 0)
			return;

		Arrays.sort(a);

		Root = new BSTNode(a[a.length / 2], null);
		
		Root.Level = 0;
		
		configureBst(0, 0, a.length / 2 - 1, a.length / 2 + 1, a.length - 1, Root, a);


	}

	private void configureBst(int parentBstIndex, int leftStart, int leftEnd, int rightStart, int rightEnd,
			BSTNode parentNode, int[] a) {
		
		if (leftStart != -1 && leftStart < a.length && leftEnd != -1 && leftEnd < a.length
				&& 2 * parentBstIndex + 1 < a.length) {

			int nodeLeftIndex = leftEnd - (leftEnd - leftStart + 1) / 2;			
			parentNode.LeftChild = new BSTNode(a[nodeLeftIndex], parentNode);
			parentNode.LeftChild.Level = parentNode.Level + 1;

			configureBst(2 * parentBstIndex + 1, leftStart, nodeLeftIndex - 1, nodeLeftIndex + 1, leftEnd, parentNode.LeftChild, a);

		}

		if (rightStart != -1 && rightStart < a.length && rightEnd != -1 && rightEnd < a.length
				&& 2 * parentBstIndex + 2 < a.length) {

			int nodeRightIndex = rightEnd - (rightEnd - rightStart + 1) / 2;
			parentNode.RightChild = new BSTNode(a[nodeRightIndex], parentNode);
			parentNode.RightChild.Level = parentNode.Level + 1;

			configureBst(2 * parentBstIndex + 2, rightStart, nodeRightIndex - 1, nodeRightIndex + 1, rightEnd, parentNode.RightChild, a);

		}

	}
	
	public boolean IsBalanced(BSTNode node) {
		return IsBalanced(node, 0);
	}
	
	private boolean IsBalanced(BSTNode node, int lonelyNodesCount) {
		
		if (node.LeftChild != null && node.RightChild != null) {
			IsBalanced(node.LeftChild, lonelyNodesCount);
			IsBalanced(node.RightChild, lonelyNodesCount);
		}else if (node.LeftChild != null) {
			IsBalanced(node.LeftChild, ++lonelyNodesCount);
		}else if (node.RightChild != null) {
			IsBalanced(node.RightChild, ++lonelyNodesCount);
		}
		
		if (lonelyNodesCount > 1) {
			return false;
		}
		
		return true;

	}

}