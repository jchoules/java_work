package uk.ac.cam.jc841.sv1;

public class OOPQueueTest {
  public static void main(String[] args) {
    OOPQueue testQueue = new OOPArrayQueue();

    System.out.println(testQueue.isEmpty());

    testQueue.enqueue(3);
    testQueue.enqueue(6);
    testQueue.enqueue(-2);

    System.out.println(testQueue.isEmpty());
    System.out.println(testQueue.getHead());

    testQueue.dequeue();

    System.out.println(testQueue.getHead());

    testQueue.dequeue();
    testQueue.enqueue(4);

    System.out.println(testQueue.getHead());

    testQueue.dequeue();
    testQueue.dequeue();

    try {
      System.out.println(testQueue.getHead());
    } catch (OOPQueueException oqe) {
      System.out.println(oqe.getMessage());
    }

    try {
      testQueue.dequeue();
    } catch (OOPQueueException oqe) {
      System.out.println(oqe.getMessage());
    }
  }
}
