package uk.ac.cam.jc841.sv1;

public class OOPArrayQueue implements OOPQueue {
  private int[] contents = new int[0];
  private int length = 0;
  private int start = 0;
  private static int expansionAmount = 10;

  private void expand() {
    int[] newContents = new int[contents.length + expansionAmount];
    for (int i = 0; i < length; i++) {
      newContents[i] = contents[(start + i) % contents.length];
    }
    contents = newContents;
    start = 0;
  }

  public boolean isEmpty() {
    return (length == 0);
  }

  public int getHead() throws OOPQueueException {
    if (isEmpty()) {
      throw new OOPQueueException("nothing to get");
    } else {
      return contents[start];
    }
  }

  public void dequeue() throws OOPQueueException {
    if (isEmpty()) {
      throw new OOPQueueException("nothing to dequeue");
    } else {
      start++;
      length--;
    }
  }

  public void enqueue(int x) {
    if (!(length < contents.length)) {
      expand();
    }

    contents[(start + length) % contents.length] = x;
    length++;
  }
}
