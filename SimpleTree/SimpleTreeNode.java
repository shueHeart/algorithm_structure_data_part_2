package app;

import java.util.*;

public class SimpleTreeNode<T> {
	public T NodeValue;
	public SimpleTreeNode<T> Parent;
	public List<SimpleTreeNode<T>> Children;
	public int Level;

	public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
		NodeValue = val;
		Parent = parent;
		Children = new ArrayList<SimpleTreeNode<T>>();
		Level = 0;
	}
}

class SimpleTree<T> {
	public SimpleTreeNode<T> Root;

	public SimpleTree(SimpleTreeNode<T> root) {
		Root = root;
	}

	public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
		
		ParentNode.Children.add(NewChild);
		NewChild.Parent = ParentNode;
		
	}

	public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
		
		for (SimpleTreeNode<T> simpleTreeNode : NodeToDelete.Children) {
			simpleTreeNode.Parent = NodeToDelete.Parent;
			NodeToDelete.Parent.Children.add(simpleTreeNode);
		}
		
		NodeToDelete.Parent = null;
		NodeToDelete.Children = null;
		
	}

	public List<SimpleTreeNode<T>> GetAllNodes() {
		
		List<SimpleTreeNode<T>> rootSimpleTreeNodes = new ArrayList<SimpleTreeNode<T>>();
		
		if (Root == null) return rootSimpleTreeNodes;
		
		rootSimpleTreeNodes.add(Root);
		
		rootSimpleTreeNodes.addAll(GetAllNodes(Root.Children, 0));
		
		return rootSimpleTreeNodes; 
		
	}
	
	private List<SimpleTreeNode<T>> GetAllNodes (List<SimpleTreeNode<T>> simpleTreeNodes, int index) {
		
		List<SimpleTreeNode<T>> nodes = new ArrayList<SimpleTreeNode<T>>();
		
		if (simpleTreeNodes == null) return nodes;
		
		if(index < simpleTreeNodes.size()) {
			
			nodes.add(simpleTreeNodes.get(index));
			nodes.addAll(GetAllNodes(simpleTreeNodes.get(index).Children, 0));
			nodes.addAll(GetAllNodes(simpleTreeNodes, index + 1));
				
		}
		
		return nodes;
		
	}

	public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
		List<SimpleTreeNode<T>> rootSimpleTreeNodes = new ArrayList<SimpleTreeNode<T>>();
		
		if (Root == null) return rootSimpleTreeNodes;
		
		if (Root.NodeValue.equals(val))
			rootSimpleTreeNodes.add(Root);
		
		rootSimpleTreeNodes.addAll(FindNodesByValue(Root.Children, 0, val));
		
		return rootSimpleTreeNodes;
	}
	
	private List<SimpleTreeNode<T>> FindNodesByValue(List<SimpleTreeNode<T>> simpleTreeNodes, int index, T val) {
		
		List<SimpleTreeNode<T>> nodes = new ArrayList<SimpleTreeNode<T>>();
		

		if(simpleTreeNodes == null) return nodes;
		
		if(index >= simpleTreeNodes.size()) return nodes;
		
		if (simpleTreeNodes.get(index).NodeValue.equals(val)) {
			nodes.add(simpleTreeNodes.get(index));
		}

		nodes.addAll(FindNodesByValue(simpleTreeNodes.get(index).Children, 0, val));
		nodes.addAll(FindNodesByValue(simpleTreeNodes, index + 1, val));
		
		return nodes;
		
	}

	public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
		
		OriginalNode.Parent.Children.remove(OriginalNode);
		
		AddChild(NewParent, OriginalNode);
		
	}

	public int Count() {
		
		if (Root == null) return 0; 
		
		return 1 + Count(Root.Children, 0);
		
	}
	
	private int Count(List<SimpleTreeNode<T>> simpleTreeNodes, int index) {
		
		if (simpleTreeNodes == null || index >= simpleTreeNodes.size()) return 0;
		
		return 1 + Count(simpleTreeNodes, index + 1) + Count(simpleTreeNodes.get(index).Children, 0);
		
	}

	public int LeafCount() {
		
		if (Root == null) return 0; 
		
		return LeafCount(Root.Children, 0);
		
	}
	
	private int LeafCount(List<SimpleTreeNode<T>> simpleTreeNodes, int index) {
		
		if (simpleTreeNodes == null || simpleTreeNodes.size() == 0) return 1;
		
		if (index >= simpleTreeNodes.size()) return 0;
		
		return LeafCount(simpleTreeNodes, index + 1) + LeafCount(simpleTreeNodes.get(index).Children, 0);

	}
	
}
