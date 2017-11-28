package uk.ac.cam.jc841.sv1;

public class OOPListTest {
  public static void main(String[] args) {
    OOPList testList = new OOPArrayList();
    testList.addHead(3);
    testList.addHead(6);
    testList.addHead(-2);

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
