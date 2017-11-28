package uk.ac.cam.jc841.sv1;

public class OOPArrayList implements OOPList {
  private int[] contents = new int[0];
  private int length = 0;
  private static int expansionAmount = 10;

  private void expand() {
    int[] newContents = new int[contents.length + expansionAmount];
    for (int i = 0; i < length; i++) {
      newContents[i] = contents[i];
    }
    contents = newContents;
  }

  public void addHead(int x) {
    if (!(length < contents.length)) {
      expand();
    }

    for (int i = length - 1; i >= 0; i--) {
      contents[i + 1] = contents[i];
    }

    contents[0] = x;
    length++;
  }

  public void deleteHead() throws OOPListException {
    if (length == 0) {
      throw new OOPListException("nothing to delete");
    } else {
      for (int i = 0; i < length - 1; i++) {
        contents[i] = contents[i + 1];
      }

      length--;
    }
  }

  public int getHead() throws OOPListException {
    if (length == 0) {
      throw new OOPListException("nothing to get");
    } else {
      return contents[0];
    }
  }

  public int getNth(int n) throws OOPListException {
    if (n > length - 1 || n < -length) {
      throw new OOPListException("index out of bounds");
    } else if (n < 0) {
      n += length;
    }

    return contents[n];
  }

  public int length() {
    return length;
  }
}
