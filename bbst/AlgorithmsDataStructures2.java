package bbst;

import java.util.*;

public class AlgorithmsDataStructures2 {
	
	
	public static int[] GenerateBBSTArray(int[] a) {
		
		if (a.length == 0) return null; 
		
		Arrays.sort(a);
	
		int[] bst = new int[a.length];
		
		bst[0] = a[a.length / 2];

		configureBst(0, 0, a.length / 2 - 1, a.length / 2 + 1, a.length - 1, bst, a);
		
		return bst;
		
	}
	
	private static void configureBst(int parentBstIndex, int leftStart, int leftEnd, int rightStart, int rightEnd, int[] bst, int[] a) {

		if (leftStart != -1 && leftStart < a.length && leftEnd != - 1 && leftEnd < a.length &&  2 * parentBstIndex + 1 < a.length) {
			
			int nodeLeftIndex = leftEnd - (leftEnd - leftStart + 1) / 2;
			bst[2 * parentBstIndex + 1] = a[nodeLeftIndex];
			
			configureBst(2 * parentBstIndex + 1, leftStart, nodeLeftIndex - 1, nodeLeftIndex + 1, leftEnd, bst, a);
			
		}
		
		if (rightStart != -1 && rightStart < a.length && rightEnd != - 1 && rightEnd < a.length && 2 * parentBstIndex + 2 < a.length) {
			
			int nodeRightIndex = rightEnd - (rightEnd - rightStart + 1) / 2;
			bst[2 * parentBstIndex + 2] = a[nodeRightIndex];
			
			configureBst(2 * parentBstIndex + 2, rightStart, nodeRightIndex - 1, nodeRightIndex + 1, rightEnd, bst, a);
			
		}
		
	}
	
}
