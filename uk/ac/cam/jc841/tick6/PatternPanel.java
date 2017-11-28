package uk.ac.cam.jc841.tick6;

//TODO:  specify the appropriate import statements
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class PatternPanel extends JPanel {

  private JList guiList;

  public PatternPanel() {
    super();
    setLayout(new BorderLayout());
    guiList = new JList();
    add(new JScrollPane(guiList));
  }

  public void setPatterns(List<Pattern> list) {
    ArrayList<String> names = new ArrayList<String>();

    for (Pattern p : list) {
      names.add(p.getName() + " (" + p.getAuthor() + ")");
    }

    guiList.setListData(names.toArray());
  }
}
