package uk.ac.cam.jc841.sv3;
import java.util.*;

public class CarComparator implements Comparator<Car> {
  public int compare(Car c1, Car c2) {
    int manufacturerComparison = c1.getManufacturer().compareTo(c2.getManufacturer());
    if (manufacturerComparison == 0) {
      return (c1.getAge() - c2.getAge());
    } else {
      return manufacturerComparison;
    }
  }
}
