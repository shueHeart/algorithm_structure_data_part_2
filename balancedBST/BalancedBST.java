
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

		int arrayLength = a.length;
		
		if (leftStart != -1 && leftStart < arrayLength && leftEnd != -1 && leftEnd < arrayLength
				&& 2 * parentBstIndex + 1 < arrayLength) {

			int nodeLeftIndex = leftEnd - (leftEnd - leftStart + 1) / 2;
			
			parentNode.LeftChild = new BSTNode(a[nodeLeftIndex], parentNode);
			parentNode.LeftChild.Level = parentNode.Level + 1;
 
			configureBst(2 * parentBstIndex + 1, leftStart, nodeLeftIndex - 1, nodeLeftIndex + 1, leftEnd,
					parentNode.LeftChild, a);

		}

		if (rightStart != -1 && rightStart < arrayLength && rightEnd != -1 && rightEnd < arrayLength
				&& 2 * parentBstIndex + 2 < arrayLength) {

			int nodeRightIndex = rightEnd - (rightEnd - rightStart + 1) / 2;
			parentNode.RightChild = new BSTNode(a[nodeRightIndex], parentNode);
			parentNode.RightChild.Level = parentNode.Level + 1;

			configureBst(2 * parentBstIndex + 2, rightStart, nodeRightIndex - 1, nodeRightIndex + 1, rightEnd,
					parentNode.RightChild, a);

		}

	}

	public boolean IsBalanced(BSTNode node) {

		ArrayList<BSTNode> nodes = new ArrayList<BSTNode>();

		ArrayList<BSTNode> nodesRootLevel = new ArrayList<BSTNode>();

		if (node != null) {
			nodes.add(node);
			nodesRootLevel.add(node);
		}

		return IsBalanced(nodesRootLevel.size(), nodesRootLevel, false, node.Level);

	}

	private boolean IsBalanced(int nodesCount, ArrayList<BSTNode> nodesLastLevel, boolean mayBeNotBalanced, int level) {

		ArrayList<BSTNode> nodesNextLevel = new ArrayList<BSTNode>();
		
		int count = 0;

		for (BSTNode node : nodesLastLevel) {

			if (node.LeftChild != null) {
				nodesNextLevel.add(node.LeftChild);
				++count;
			}

			if (node.RightChild != null) {
				nodesNextLevel.add(node.RightChild);
				++count;
			}

		}
		
		
		if (nodesCount != Math.pow(2, level) && mayBeNotBalanced) {
			return false;
		}
		
		if (nodesCount != Math.pow(2, level)) {
			mayBeNotBalanced = true;
		}
		
		if (nodesNextLevel.size() == 0) {
			return true;
		}
		
		return IsBalanced(count, nodesNextLevel, mayBeNotBalanced, ++level);
		
	}
	
}
