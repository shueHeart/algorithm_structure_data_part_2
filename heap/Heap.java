
import java.util.*;

public class Heap {
	
	public int[] HeapArray;

	public Heap() {
		HeapArray = null;
	}

	public void MakeHeap(int[] a, int depth) {

		int tree_size = 0;

		for (int i = 0; i <= depth; ++i) {
			tree_size += Math.pow(2, i);
		}

		HeapArray = new int[tree_size];

		for (int i = 0; i < a.length; ++i) {
			HeapArray[i] = -1;
			Add(a[i]);
		}
		
	}

	public int GetMax() {
		
		int smallIndex = -1;
		
		int rootValue = -1;
		
		for (int i = HeapArray.length - 1; i >= 0; --i) {
			if (HeapArray[i] != -1) {
				rootValue = HeapArray[0];
				smallIndex = i;
				HeapArray[0] = HeapArray[i];
				HeapArray[i] = -1;
				break;
			}
		}
		
		if (smallIndex == -1) {
			return -1;	
		}
		 
		transformHeap(HeapArray[0], new int[] {0});
		
		return rootValue;
	}
	
	private void transformHeap (int smallIndex, int[] parents) {
		
		int[] childs = new int[(int) 2 * parents.length];
		
		int maxFromChildsIndex = -1;
		
		for (int i = 0; i < parents.length; ++i) {
			
			if (parents[i] == -1) {
				continue;
			}
			
			childs[i * 2] = 2 * parents[i] + 1;
			childs[i * 2 + 1] = 2 * parents[i] + 2;
			
			if ( childs[i * 2] < HeapArray.length && (maxFromChildsIndex == -1 || HeapArray[maxFromChildsIndex] < HeapArray[childs[i * 2]])) {
				maxFromChildsIndex = childs[i * 2];
			}
			
			if ( childs[i * 2 + 1] < HeapArray.length && (maxFromChildsIndex == -1 || HeapArray[maxFromChildsIndex] < HeapArray[childs[i * 2 + 1]])) {
				maxFromChildsIndex = childs[i * 2 + 1];
			}
		}
		
		if (maxFromChildsIndex == -1) {
			return;
		}
		
		int buf = HeapArray[maxFromChildsIndex];
		HeapArray[maxFromChildsIndex] = HeapArray[smallIndex];
		HeapArray[smallIndex] = buf;
				
		transformHeap(maxFromChildsIndex, childs);
		
	}

	public boolean Add(int key) {
		
		int newKeyIndex = -1;
		
		for (int i = 0; i < HeapArray.length; ++i) {
			
			if  (HeapArray[i] == -1) {
				newKeyIndex = i;
				HeapArray[i] = key;
				break;
			}
			
		}
		
		if (newKeyIndex == -1) {
			return false;
		}
		
		if (newKeyIndex == 0) {

			return true;
		}
		
		return setNewKey(newKeyIndex);
		
	}
	
	private boolean setNewKey(int newKeyIndex) {
		
		
		int parentIndex = -1;
		
		if (newKeyIndex % 2 == 0) {
			parentIndex = (newKeyIndex - 2) / 2;
		}
		else if (newKeyIndex % 2 == 1) {
			parentIndex = (newKeyIndex - 1) / 2;
		}
		
		if (HeapArray[newKeyIndex] < HeapArray[parentIndex]) {
			return true;
		}

		int buf = HeapArray[newKeyIndex];
		HeapArray[newKeyIndex] = HeapArray[parentIndex];
		HeapArray[parentIndex] = buf;
		
		if (parentIndex == 0) {
			return true;
		}
		
		return setNewKey(parentIndex);
		
	}

}
