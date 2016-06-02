
/**
 * @author Chris Bell
 */

package edu.miamioh.linked;

public class Node<T> {

	private T data;
	private Node<T> next;
	
	public Node(T dataPortion) {
		this(dataPortion,null);
	}
	
	public Node(T dataPortion, Node<T> nextNode) {
		data = dataPortion;
		next = nextNode;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T newData) {
		data = newData;
	}
	
	public Node<T> getNextNode() {
		return next;
	}
	
	public void setNextNode(Node<T> nextNode) {
		next = nextNode;
	}
}
