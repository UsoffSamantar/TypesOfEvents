package TypesOfEvents;


public class MyDoublyLinkedList {
	Node head; // head of list
   Node tail;
   private int length;
	// Nodes //
	class Node {
		Object data;
		Node prev;
		Node next;
		// Constructor to create a new node //
		// next and prev is by default initialized as null //
		Node(Object d) { 
      this.data = d; }
	}
   
   public boolean areNodesEmpty() {
      // head == tail //
      return length ==0; 
   }
   
	// Adding a node at the front of the list //
	public void push(Object newData)
	{
		// Allocate the node //
		// Put in the data //
		Node newNode = new Node(newData);

		// Make next of new node as head and previous as null //
		newNode.next = head;
		newNode.prev = null;

		// Change prev of head node to new node //
		if (head != null)
			head.prev = newNode;

		// Move the head to point to the new node //
		head = newNode;
	}
   
   // Insert newData at the first node //
   public void InsertFirst(Object newData) {
      		Node newNode = new Node(newData);
            if (areNodesEmpty()) {
               tail = newNode;
            } else {
               head.prev = newNode;
            }
            newNode.next = head;
            head = newNode;
            length++;
   }

	// Add a node before the given node //
	public void InsertBefore(Node nextNode, Object newData)
	{
		// Check if the given nextNode is NULL //
		if (nextNode == null) {
			System.out.println(
				"The given next node can not be NULL");
			return;
		}

		// Allocate node, put in the data //
		Node newNode = new Node(newData);

		// Making prev of new node as prev of next node //
		newNode.prev = nextNode.prev;

		// Making prev of next node as new node //
		nextNode.prev = newNode;

		// Making next of new node as next node //
		newNode.next = nextNode;

		// Check if new node is added as head //
		if (newNode.prev != null)
			newNode.prev.next = newNode;
		else
			head = newNode;
	}
   
   public void InsertLast(Object newData) {
      Node newNode = new Node(newData);
      if (areNodesEmpty()) {
         head = newNode;
      } else {
         tail.next = newNode;
         newNode.prev = tail;
      }
      tail = newNode;
      length++;
   }

	// Given a node as prev_node, insert //
	// a new node after the given node  //
	public void InsertAfter(Node prevNode, Object newData)
	{

		// Check if the given prev_node is NULL //
		if (prevNode == null) {
			System.out.println(
				"The previous node cannot be null"); 
			return;
		}

		// Allocate node //
		// put in the data //
		Node newNode = new Node(newData);

		// Make next of new node as next of prev_node //
		newNode.next = prevNode.next;

		// Make the next of prev_node as newNode //
		prevNode.next = newNode;

		// Make prev_node as previous of newNode //
		newNode.prev = prevNode;

		// Change previous of newNode's next node //
		if (newNode.next != null)
			newNode.next.prev = newNode;
	}

	// Add a node at the end of the list //
	void append(Object newData)
	{
		// Allocate node //
		// Put in the data //
		Node newNode = new Node(newData);

		Node last = head;

		// This new node is going to be the last node, so //
		     // make next of it as NULL*/
		newNode.next = null;

		// If the Linked List is empty, then make the new //
		      // node as head //
		if (head == null) {
			newNode.prev = null;
			head = newNode;
			return;
		}

		// Else traverse till the last node //
		while (last.next != null)
			last = last.next;

		// Change the next of last node //
		last.next = newNode;

		// Make last node as previous of new node //
		newNode.prev = last;
	}
   
   public void displayForward(Node node) {
      // if the head = null return and print null //
      if (head == null) {
            return;
      }
      Node temp = head;
      while(temp != null) {
         System.out.print(temp.data + ",");
         temp = temp.next;
     }
     System.out.println("null");
   }
   
   public void displayBackward(Node node) {
      // if the tail = null return and print null //
      if (tail == null) {
         return;
      }
      Node temp = tail;
      while(temp != null) {
         System.out.print(temp.data + ",");
         temp = temp.prev;
      }
      System.out.print("null");
   }

}
