package uk.ac.cam.jc841.sv1;

public interface OOPList {
  public void addHead(int x);
  public void deleteHead() throws OOPListException;
  public int getHead() throws OOPListException;
  public int getNth(int n) throws OOPListException;
  public int length();
}
