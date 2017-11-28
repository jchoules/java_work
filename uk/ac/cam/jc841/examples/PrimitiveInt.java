package uk.ac.cam.jc841.examples;

class PrimitiveInt {
  public static void main(String[] args) {
    int i;
    i = 1;
    i = i + 1;
    System.out.println(i);
    System.out.println(i = 7);
    int j = 10;
    System.out.println(--j);
    System.out.println(j);
    System.out.println(0x55>>4);
    System.out.println(0x55<<7);
    System.out.println(3>>4&1);
    System.out.println(16>>4&1);
    System.out.println(24>>4&1);
    System.out.println(48>>4&1);
  }
}
