package asd2_tree;

import java.util.*;

public class SimpleTreeNode<T> {
	public T NodeValue; // �������� � ����
	public SimpleTreeNode<T> Parent; // �������� ��� null ��� �����
	public List<SimpleTreeNode<T>> Children; // ������ �������� ����� ��� null

	public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
		NodeValue = val;
		Parent = parent;
		Children = null;
	}
}

class SimpleTree<T> {
	public SimpleTreeNode<T> Root; // ������, ����� ���� null

	public SimpleTree(SimpleTreeNode<T> root) {
		Root = root;
	}

	public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
		ParentNode.Children.add(NewChild);
	}

	public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
		NodeToDelete.Parent.Children.addAll(NodeToDelete.Children);
		
		NodeToDelete.Parent = null;
		NodeToDelete.Children = null;
	}

	public List<SimpleTreeNode<T>> GetAllNodes() {
		
		List<SimpleTreeNode<T>> rootSimpleTreeNodes = new ArrayList<SimpleTreeNode<T>>();
		
		if (Root != null)
			rootSimpleTreeNodes.add(Root);
		
		rootSimpleTreeNodes.addAll(GetAllNodes(rootSimpleTreeNodes, 0));
		
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
		
		if (Root != null)
			rootSimpleTreeNodes.add(Root);
		
		rootSimpleTreeNodes.addAll(FindNodesByValue(rootSimpleTreeNodes, 0, val));
		
		return rootSimpleTreeNodes;
	}
	
	private List<SimpleTreeNode<T>> FindNodesByValue(List<SimpleTreeNode<T>> simpleTreeNodes, int index, T val) {
		
		List<SimpleTreeNode<T>> nodes = new ArrayList<SimpleTreeNode<T>>();
		
		if(simpleTreeNodes == null) return nodes;
		
		if(index > simpleTreeNodes.size()) return nodes;
		
		if (simpleTreeNodes.get(index).equals(val)) {
			nodes.add(simpleTreeNodes.get(index));
		}
		
		nodes.addAll(GetAllNodes(simpleTreeNodes.get(index).Children, 0));
		nodes.addAll(GetAllNodes(simpleTreeNodes, index + 1));
		
		return nodes;
		
	}

	public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
		// ��� ��� ����������� ���� ������ � ��� ���������� --
		// � �������� ��������� ��� ���� NewParent
	}

	public int Count() {
		// ���������� ���� ����� � ������
		return 0;
	}

	public int LeafCount() {
		// ���������� ������� � ������
		return 0;
	}
}