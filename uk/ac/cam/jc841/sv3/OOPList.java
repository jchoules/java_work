package uk.ac.cam.jc841.sv3;

public interface OOPList<T> {
  public void addHead(T x);
  public void deleteHead() throws OOPListException;
  public T getHead() throws OOPListException;
  public T getNth(int n) throws OOPListException;
  public int length();
}
