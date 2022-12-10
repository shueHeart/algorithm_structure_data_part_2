package abst;

import java.util.*;

class aBST {
	
	public Integer Tree[];

	public aBST(int depth) {

		int tree_size = 0;
		
		for (int i = 0; i < depth; ++i) {
			tree_size += Math.pow(2, i);
		}
		
		Tree = new Integer[tree_size];
		for (int i = 0; i < tree_size; i++)
			Tree[i] = null;
		
	}

	public Integer FindKeyIndex(int key) {
		
		int index = 0;
		
		while (index < Tree.length) {
			
			if (key == Tree[index]) {
				return index;
			}
			
			if (key > Tree[index]) {
				index = 2 * index + 2;
				continue;
			}
			
			index = 2 * index + 1;

		}
		
		return null;
	}

	public int AddKey(int key) {

		int index = 0;
		
		while (index < Tree.length) {
			
			if (Tree[index] == null) {
				
				Tree[index] = key;
				
				return Tree[index];
			}
			
			if (key > Tree[index]) {
				index = 2 * index + 2;
				continue;
			}
			
			index = 2 * index + 1;

		}
		return -1;
		
	}

}