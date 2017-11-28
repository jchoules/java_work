package uk.ac.cam.jc841.sv3;

public class OOPLinkedList<T> implements OOPList<T> {
  private class OOPLinkedListElement {
    private T contents;
    private OOPLinkedListElement next;

    public OOPLinkedListElement(T x) {
      contents = x;
    }

    public T getContents() {
      return contents;
    }

    public void setContents(T x) {
      contents = x;
    }

    public OOPLinkedListElement getNext() {
      return next;
    }

    public void setNext(OOPLinkedListElement n) {
      next = n;
    }
  }

  private OOPLinkedListElement head;

  public boolean isEmpty() {
    return (head == null);
  }

  public void addHead(T x) {
    OOPLinkedListElement newHead = new OOPLinkedListElement(x);
    newHead.setNext(head);
    head = newHead;
  }

  public void deleteHead() throws OOPListException {
    if (isEmpty()) {
      throw new OOPListException("nothing to delete");
    } else {
      head = head.getNext();
    }
  }

  public T getHead() throws OOPListException {
    if (isEmpty()) {
      throw new OOPListException("nothing to get");
    } else {
      return head.getContents();
    }
  }

  public T getNth(int n) throws OOPListException {
    if (n > length() - 1 || n < -length()) {
      throw new OOPListException("index out of bounds");
    } else if (n < 0) {
      n += length();
    }

    OOPLinkedListElement currentElement = head;
    for (int i = 0; i < n; i++) {
      currentElement = currentElement.getNext();
    }

    return currentElement.getContents();
  }

  public int length() {
    OOPLinkedListElement currentElement = head;
    int count = 0;
    while (currentElement != null) {
      currentElement = currentElement.getNext();
      count++;
    }
    return count;
  }

  public void reverse() {
    OOPLinkedListElement previousElement = null;
    OOPLinkedListElement currentElement = head;
    OOPLinkedListElement nextElement;
    while (currentElement != null) {
      nextElement = currentElement.getNext();
      currentElement.setNext(previousElement);
      previousElement = currentElement;
      currentElement = nextElement;
    }
    head = previousElement;
  }
}
