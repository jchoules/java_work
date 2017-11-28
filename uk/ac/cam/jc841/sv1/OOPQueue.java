package uk.ac.cam.jc841.sv1;

public interface OOPQueue {
  public boolean isEmpty();
  public int getHead() throws OOPQueueException;
  public void dequeue() throws OOPQueueException;
  public void enqueue(int x);
}
