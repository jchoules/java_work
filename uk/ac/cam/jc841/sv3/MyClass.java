package uk.ac.cam.jc841.sv3;

public class MyClass implements Cloneable {
  private String mName;
  private int[] mData;

  public MyClass(MyClass toCopy) {
    // A 'normal' constructor for convenience when testing.
    this.mName = toCopy.mName;
    this.mData = toCopy.mData;
  }

  public MyClass(String name, int[] data) {
    // This constructor makes a shallow copy with respect to mData.
    // A deep copy could be achieved by using:
    //   mData = data.clone();
    // in place of:
    //   mData = data;
    // which in fact is what we do in MyClass.clone().
    mName = name;
    mData = data;
  }

  public Object clone() throws CloneNotSupportedException {
    // Makes a deep copy as detailed in the comment in the copy constructor.
    MyClass cloned = (MyClass)super.clone();
    cloned.mData = mData.clone();
    return cloned;
  }

  public void report() {
    // Prints out selected data about the object's members.
    System.out.println(mName);
    System.out.println(mData[0]);
  }

  public void tweak() {
    // Makes some alterations to the object's members to demonstrate how these
    // are shared (or not) between deep and shallow clones.
    mName = new String("Holly");
    mData[0] = 7;
  }

  public static void main(String[] args) throws CloneNotSupportedException {
    String name = new String("Jake");
    int[] data = new int[] {1,3,5};
    MyClass myObject = new MyClass(name, data);
    System.out.println("Original object:");
    myObject.report();          // prints Jake 1

    MyClass myConstructedCopy = new MyClass(myObject);
    System.out.println("Copy of original object using copy constructor:");
    myConstructedCopy.report(); // prints Jake 1
    MyClass myClonedCopy = (MyClass)myObject.clone();
    System.out.println("Copy of original object using clone():");
    myClonedCopy.report();      // prints Jake 1

    System.out.println("Now tweaking original object...");
    myObject.tweak();
    System.out.println("Original object:");
    myObject.report();          // prints Holly 7
    System.out.println("Copy of original object using copy constructor:");
    myConstructedCopy.report(); // prints Jake 7
    System.out.println("Copy of original object using clone():");
    myClonedCopy.report();      // prints Jake 1
  }
}
