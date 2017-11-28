package uk.ac.cam.jc841.tick7;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.net.URL;
import java.net.URLConnection;

public class PatternLoader {
  public static List<Pattern> load(Reader r) throws IOException {
    BufferedReader buff = new BufferedReader(r);
    List<Pattern> resultList = new LinkedList<Pattern>();
    String currentLine;

    while ((currentLine = buff.readLine()) != null) {
      try {
        Pattern p = new Pattern(currentLine);
        resultList.add(p);
      } catch (PatternFormatException error) {
        // No need to do anything, just move on to the next line
      }
    }

    return resultList;
  }

  public static List<Pattern> loadFromURL(String url) throws IOException {
    URL destination = new URL(url);
    URLConnection conn = destination.openConnection();
    return load(new InputStreamReader(conn.getInputStream()));
  }

  public static List<Pattern> loadFromDisk(String filename) throws IOException {
    return load(new FileReader(filename));
  }
}
