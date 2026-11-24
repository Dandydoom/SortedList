import javax.swing.*;
import java.awt.*;

/**
 * SortedListFrame.java
 * Swing GUI that lets the user add Strings to a SortedList and then search it.
 * Every operation is shown in the text area.
 *
 * @author Kirby Fortney
 */
public class SortedListFrame extends JFrame
{
    private SortedList sortedList = new SortedList();

    private JTextField addField;
    private JTextField searchField;
    private JTextArea displayArea;

    public SortedListFrame()
    {
        setTitle("Sorted List (Binary Search)");
        setLayout(new BorderLayout(5, 5));

        add(createInputPanel(), BorderLayout.NORTH);
        add(createDisplayPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createInputPanel()
    {
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add to List"));
        addField = new JTextField(25);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(ae -> addItem());
        addField.addActionListener(ae -> addItem()); // Enter key also adds
        addPanel.add(new JLabel("String to add:"));
        addPanel.add(addField);
        addPanel.add(addButton);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search the List"));
        searchField = new JTextField(25);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(ae -> searchItem());
        searchField.addActionListener(ae -> searchItem()); // Enter key also searches
        searchPanel.add(new JLabel("String to find: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        inputPanel.add(addPanel);
        inputPanel.add(searchPanel);
        return inputPanel;
    }

    private JPanel createDisplayPanel()
    {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        return displayPanel;
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton clearButton = new JButton("Clear List");
        JButton quitButton = new JButton("Quit");
        clearButton.addActionListener(ae -> clearList());
        quitButton.addActionListener(ae -> quit());
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);
        return buttonPanel;
    }

    private void addItem()
    {
        String value = addField.getText().trim();
        if (value.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please enter a string to add.",
                    "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int index = sortedList.add(value);
        displayArea.append("ADD \"" + value + "\" inserted at index " + index + "\n");
        displayArea.append("    List: " + sortedList + "\n\n");

        addField.setText("");
        addField.requestFocus();
    }

    private void searchItem()
    {
        String target = searchField.getText().trim();
        if (target.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please enter a string to search for.",
                    "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int result = sortedList.binarySearch(target);
        if (result >= 0)
        {
            displayArea.append("SEARCH \"" + target + "\": FOUND \"" + sortedList.get(result)
                    + "\" at index " + result + "\n\n");
        }
        else
        {
            int location = -(result + 1);
            displayArea.append("SEARCH \"" + target + "\": NOT FOUND. It would be at index "
                    + location + describeLocation(location) + "\n\n");
        }

        searchField.setText("");
        searchField.requestFocus();
    }

    /**
     * Describes where a missing item would go, e.g. between "apple" and "cherry".
     */
    private String describeLocation(int location)
    {
        if (sortedList.isEmpty())
        {
            return " (the list is empty)";
        }
        if (location == 0)
        {
            return " (before \"" + sortedList.get(0) + "\")";
        }
        if (location == sortedList.size())
        {
            return " (after \"" + sortedList.get(location - 1) + "\")";
        }
        return " (between \"" + sortedList.get(location - 1) + "\" and \""
                + sortedList.get(location) + "\")";
    }

    private void clearList()
    {
        sortedList.clear();
        displayArea.setText("");
    }

    private void quit()
    {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?",
                "Quit", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
}
