package uk.ac.cam.jc841.sv3;

public class OOPListTest {
  public static void main(String[] args) {
    OOPList testList = new OOPLinkedList<Double>();
    testList.addHead(3.0);
    testList.addHead(6.72);
    testList.addHead(-2.0003);

    System.out.println(testList.length());
    System.out.println(testList.getHead());
    System.out.println(testList.getNth(1));
    System.out.println(testList.getNth(-1));

    try {
      System.out.println(testList.getNth(5));
    } catch (OOPListException ole) {
      System.out.println(ole.getMessage());
    }

    testList.deleteHead();
    System.out.println(testList.getHead());

    testList.deleteHead();
    testList.deleteHead();

    try {
      System.out.println(testList.getHead());
    } catch (OOPListException ole) {
      System.out.println(ole.getMessage());
    }

    System.out.println(testList.length());
  }
}
