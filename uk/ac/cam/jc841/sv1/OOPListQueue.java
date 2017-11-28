package uk.ac.cam.jc841.sv1;

class OOPListQueue implements OOPQueue {
  private OOPLinkedList front = new OOPLinkedList();
  private OOPLinkedList rear = new OOPLinkedList();

  private void normalise() {
    if (front.isEmpty() && !rear.isEmpty()) {
      front = rear;
      front.reverse();
      rear = new OOPLinkedList();
    }
  }

  public boolean isEmpty() {
    return (front.isEmpty() && rear.isEmpty());
  }

  public int getHead() throws OOPQueueException {
    if (isEmpty()) {
      throw new OOPQueueException("nothing to get");
    } else {
      return front.getHead();
    }
  }

  public void dequeue() throws OOPQueueException {
    if (isEmpty()) {
      throw new OOPQueueException("nothing to dequeue");
    } else {
      front.deleteHead();
      normalise();
    }
  }

  public void enqueue(int x) {
    rear.addHead(x);
    normalise();
  }
}
