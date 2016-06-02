
/**
 * @author Chris Bell
 */

package edu.miamioh.linked;

public class LinkedList<T> implements ListInterface<T>{

	private Node<T> firstNode;
	private int numberOfNodes;
	
	public LinkedList(){
		clear();
	}

	/**
	 * Adds a new entry to the end of this list.
	 * Entries currently in the list are unaffected.
	 * The list's size is increased by 1.
	 * @param newEntry The object to be added is a new entry.
	 */
	public void add(T newEntry) {
	
		Node<T> newNode = new Node<T>(newEntry);
		
		if(isEmpty()) {
			firstNode = newNode;
		}else {
			Node<T> lastNode = getNodeAt(numberOfNodes);
			lastNode.setNextNode(newNode);
		}
		
		numberOfNodes++;
		
	}

	/**
	 * Adds a new entry at a specified position within this list.
	 * Entries originally at and above the specified position
	 * are at the next higher position within the list.
	 * @param newPosition An integer that specifies the desired
	 * position of the new entry.
	 * @param newEntry The object to be added as a new entry.
	 * @return Returns true if the addition is successful or
	 * false if newPosition < 1, or newPosition > getLength() + 1.
	 */
	public boolean add(int newPosition, T newEntry) {
		
		boolean isSuccessful = true;
		
		if((newPosition >= 1) && (newPosition <= numberOfNodes +1)) {
			
			Node<T> newNode = new Node<T>(newEntry);
			
			if(newPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}else {
				Node<T> nodeBefore = getNodeAt(newPosition - 1);
				Node<T> nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			
			numberOfNodes++;
		}else {
			isSuccessful = false;
		}
		
		return isSuccessful;
	}

	/**
	 * Removes the entry at a given position from this list.
	 * Entries originally at positions higher than the given 
	 * position are at the next lower position within the list,
	 * and the list's size is decreased by 1.
	 * @param givenPosition An integer that indicates the position of
	 * the entry to be removed.
	 * @return A reference to the removed entry or null, if either
	 * the list was empty, givenPosition < 1, or givenPosition > 
	 * getLength()
	 */
	public T remove(int givenPosition) {
		
		T result = null;
		
		if((givenPosition >= 1) && (givenPosition <= numberOfNodes)) {
			
			assert !isEmpty();
			if(givenPosition == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			}else {
				Node<T> nodeBefore = getNodeAt(givenPosition -1);
				Node<T> nodeToRemove = nodeBefore.getNextNode();
				Node<T> nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
				result = nodeToRemove.getData();
			}
			
			numberOfNodes--;
		}
		return result;
	}

	/**
	 * Removes all entries from this list.
	 */
	public void clear() {
		firstNode = null;
		numberOfNodes = 0;
	}

	/**
	 * Replaces the entry at a given position in this list.
	 * @param givenPosition An integer that indicates the position
	 * of the entry to be replaced.
	 * @param newEntry The object that will replace the entry at the
	 * position givenPosition.
	 * @return True if the replacement occurs, or false if either the
	 * list is empty, givenPosition < 1, or givenPosition >
	 * getLength()
	 */
	public boolean replace(int givenPosition, T newEntry) {
		
		boolean isSuccessful = true;
		
		if((givenPosition >= 1) && (givenPosition <= numberOfNodes)) {
			assert !isEmpty();
			Node<T> desiredNode = getNodeAt(givenPosition);
			desiredNode.setData(newEntry);
		}else {
			isSuccessful = false;
		}
		
		return isSuccessful;
	}

	/**
	 * Retrieves the entry at a given position in this list.
	 * @param anEntry An integer that indicates the position of the
	 * desired entry.
	 * @return A reference to the indicated entry or null, if either
	 * the list is empty, givenPosition < 1, or givenPosition >
	 * getLength()
	 */
	public T getEntry(int givenPosition) {
		
		T result = null;
		
		if((givenPosition >= 1) && (givenPosition <= numberOfNodes)) {
			assert !isEmpty();
			result = getNodeAt(givenPosition).getData();
		}
		
		return result;
	}

	/**
	 * Sees whether this list contains a given entry.
	 * @param anEntry The object that is the desired entry.
	 * @return True if the list contains anEntry or false if not.
	 */
	public boolean contains(T anEntry) {
		
		boolean found = false;
		Node<T> currentNode = firstNode;
		
		while(!found && (currentNode != null)) {
			
			if(anEntry.equals(currentNode.getData())) {
				found = true;
			}else {
				currentNode = currentNode.getNextNode();
			}
		}
		
		return found;
	}

	/**
	 * Gets the length of this list.
	 * @return The integer number of entries currently in the list.
	 */
	public int getLength() {
		return numberOfNodes;
	}

	/**
	 * Sees whether this list is empty.
	 * @return True if the list is empty, or false if not
	 */
	public boolean isEmpty() {
		
		boolean result;
		
		if(numberOfNodes == 0) {
			assert firstNode == null;
			result = true;
		}else {
			assert firstNode != null;
			result = false;
		}
		
		return result;
	}

	/**
	 * Retrieves all entries that are in this list in the order in 
	 * which they occur in the list.
	 * @return The array of objects that was created.
	 */
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfNodes];
		
		int index = 0;
		Node<T> currentNode = firstNode;
		while((index < numberOfNodes) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		
		return result;
	}
	
	/**
	 * Retrieve the node at a given position.
	 * @param givenPosition The given position of the node to be retrieved.
	 * @return The node at the given position.
	 */
	private Node<T> getNodeAt(int givenPosition) {
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfNodes);
		
		Node<T> currentNode = firstNode;
		
		for(int i=1;i<givenPosition;i++) {
			currentNode = currentNode.getNextNode();
		}
		
		assert currentNode != null;
		
		return currentNode;
	}
	
}

