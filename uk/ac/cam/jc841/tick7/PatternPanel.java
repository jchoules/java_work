package uk.ac.cam.jc841.tick7;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public abstract class PatternPanel extends JPanel {

  private Pattern currentPattern;
  private List<Pattern> patternList;
  private JList guiList;

  public PatternPanel() {
    super();
    currentPattern = null;
    setLayout(new BorderLayout());

    guiList = new JList();

    guiList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && (patternList != null)) {
          int sel = guiList.getSelectedIndex();
          if (sel != -1) {
            currentPattern = patternList.get(sel);
            onPatternChange();
          }
        }
      }
    });

    add(new JScrollPane(guiList));
  }

  public Pattern getCurrentPattern() {
    return currentPattern;
  }

  public void setPatterns(List<Pattern> list) {
    patternList = list;

    if (list == null) {
      currentPattern = null;
      guiList.setListData(new String[]{});
      return;
    }

    ArrayList<String> names = new ArrayList<String>();

    for (Pattern p : list) {
      names.add(p.getName() + " (" + p.getAuthor() + ")");
    }

    guiList.setListData(names.toArray());
    currentPattern = list.get(0);
    guiList.setSelectedIndex(0);
  }

  protected abstract void onPatternChange();
}
