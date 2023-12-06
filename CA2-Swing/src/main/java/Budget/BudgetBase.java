
package Budget;

// Swing imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Stack;

// class definition
public class BudgetBase extends JPanel { // based on Swing JPanel

    // high level UI stuff
    JFrame topLevelFrame; // top-level JFrame
    GridBagConstraints layoutConstraints = new GridBagConstraints(); // used to control layout

    // widgets which may have listeners and/or values
    private JButton undoButton;
    private JButton calculateButton; // Calculate button
    private JButton exitButton; // Exit button

    public JTextField wagesField; // Wages text field
    public JTextField loansField; // Loans text field
    public JTextField salesField; // Sales text field
    public JTextField trustFundField; // TrustFund text field

    public JTextField groceriesField; // Groceries text field
    public JTextField rentField; // Rent text field
    public JTextField electricField; // Electric bill text field
    public JTextField gasField; // Gas bill text field

    public JTextField totalIncomeField; // Total Income field
    public JTextField totalExpendituresField; // Total Expenditures field
    public JTextField totalField; // Total overall field

    public JComboBox<String> wagesFrequencyComboBox;
    public JComboBox<String> loansFrequencyComboBox;
    public JComboBox<String> salesFrequencyComboBox;
    public JComboBox<String> trustFundFrequencyComboBox;

    public JComboBox<String> groceriesFrequencyComboBox;
    public JComboBox<String> rentFrequencyComboBox;
    public JComboBox<String> electricFrequencyComboBox;
    public JComboBox<String> gasFrequencyComboBox;

    public Stack<Stack<String>> undoStack;

    // constructor - create UI (dont need to change this)
    public BudgetBase(JFrame frame) {
        topLevelFrame = frame; // keep track of top-level frame
        setLayout(new GridBagLayout()); // use GridBag layout
        initComponents(); // initalise components
        undoStack = new Stack<>();
        saveState();
    }



    // initialise componenents
    // Note that this method is quite long. Can be shortened by putting Action
    // Listener stuff in a separate method
    // will be generated automatically by IntelliJ, Eclipse, etc
    public void initComponents() { 

        // Top row (0) - "INCOME" label
        JLabel incomeLabel = new JLabel("INCOME");
        addComponent(incomeLabel, 0, 0);

        // Row 1 - Wages label followed by wages textbox
        JLabel wagesLabel = new JLabel("Wages");
        addComponent(wagesLabel, 1, 0);

        // set up text field for entering wages
        // Could create method to do below (since this is done several times)
        wagesField = new JTextField("", 10);   // blank initially, with 10 columns
        wagesField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(wagesField, 1, 1);   

        wagesFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
        addComponent(wagesFrequencyComboBox, 2,0);

        //wagesFrequencyComboBox.addActionListener(e -> handleFrequencyChange(wagesField, wagesFrequencyComboBox));
        wagesFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
        wagesField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });

        // Row 2 - Loans label followed by loans textbox
        JLabel loansLabel = new JLabel("Loans");
        addComponent(loansLabel, 3, 0);

        // set up text box for entering loans
        loansField = new JTextField("", 10);   // blank initially, with 10 columns
        loansField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(loansField, 3, 1); 

        loansFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
            addComponent(loansFrequencyComboBox, 4,0);

        //loansFrequencyComboBox.addActionListener(e -> handleFrequencyChange(loansField, loansFrequencyComboBox));
        loansFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
        loansField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });
        

        // Row 3 - Sales label followed by sales textbox
        JLabel salesLabel = new JLabel("Sales");
        addComponent(salesLabel, 5, 0);

        salesField = new JTextField("",10);
        salesField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(salesField, 5, 1);

         salesFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
            addComponent(salesFrequencyComboBox, 6,0);

        //salesFrequencyComboBox.addActionListener(e -> handleFrequencyChange(salesField, salesFrequencyComboBox));
        salesFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
          salesField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });
    

        JLabel trustFundLabel = new JLabel("Trust Fund");
        addComponent(trustFundLabel,7,0);

        trustFundField = new JTextField("",10);
        trustFundField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(trustFundField,7,1);

         trustFundFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
            addComponent(trustFundFrequencyComboBox, 8,0);

        //trustFundFrequencyComboBox.addActionListener(e -> handleFrequencyChange(trustFundField, trustFundFrequencyComboBox));
        trustFundFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
          trustFundField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });
    

        // Row 4 - Total Income label followed by total income field
        JLabel totalIncomeLabel = new JLabel("Total Income");
        addComponent(totalIncomeLabel, 10, 0);

        // set up text box for displaying total income.  Users cam view, but cannot directly edit it
        totalIncomeField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalIncomeField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalIncomeField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalIncomeField, 11, 1);  


         // Top row (0) - "Expenditures" label
        JLabel Expenditures = new JLabel("Expenditures");
        addComponent(Expenditures, 0, 2);


        //Row 1 - Groceries label followed by groceries textbox
        JLabel groceriesLabel = new JLabel("Groceries");
        addComponent(groceriesLabel,1,2);

        groceriesField = new JTextField("",10);
        groceriesField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(groceriesField,1,3);

            groceriesFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
            addComponent(groceriesFrequencyComboBox, 2,2);

        //groceriesFrequencyComboBox.addActionListener(e -> handleFrequencyChange(groceriesField, groceriesFrequencyComboBox));
        groceriesFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
           groceriesField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });

        //Row 2 - Rent label followed by rent textbox
        JLabel rentLabel = new JLabel("Rent");
        addComponent(rentLabel,3,2);

        rentField = new JTextField("",10);
        rentField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(rentField,3,3);


           rentFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
            addComponent(rentFrequencyComboBox, 4,2);

        //rentFrequencyComboBox.addActionListener(e -> handleFrequencyChange(rentField, rentFrequencyComboBox));
rentFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
   rentField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });

        //Row 3- ElectricBill label followed by electricBill textbox
        JLabel electricLabel = new JLabel("Electric Bill");
        addComponent(electricLabel,5,2);

        electricField = new JTextField("",10);
        electricField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(electricField,5,3);

          electricFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
            addComponent(electricFrequencyComboBox, 6,2);

        //electricFrequencyComboBox.addActionListener(e -> handleFrequencyChange(electricField, electricFrequencyComboBox));
        electricFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
           electricField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });

        JLabel gasLabel = new JLabel("Gas Bill");
        addComponent(gasLabel,7,2);

        gasField = new JTextField("",10);
        gasField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(gasField,7,3);

        gasFrequencyComboBox = new JComboBox<>(new String[]{"Week","Month","Year"});
        addComponent(gasFrequencyComboBox, 8,2);

        //gasFrequencyComboBox.addActionListener(e -> handleFrequencyChange(gasField, gasFrequencyComboBox));
        gasFrequencyComboBox.addActionListener(e -> calculateButton.doClick());
           gasField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateButton.doClick();
            }
        });

        //Row 4- Show total Expenditures
        JLabel totalExpendituresLabel = new JLabel("Total Expenditures");
        addComponent(totalExpendituresLabel, 10, 2);

        // set up text box for displaying total Expenditures.  Users cam view, but cannot directly edit it
        totalExpendituresField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalExpendituresField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalExpendituresField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalExpendituresField, 11, 3);  


        // Row 5 - Calculate Button
        calculateButton = new JButton("Calculate");
        addComponent(calculateButton, 0, 4);  

        // Row 5 - Exit Button
        exitButton = new JButton("Exit");
        addComponent(exitButton, 1, 4);  

        undoButton = new JButton("Undo");
        addComponent(undoButton, 2,4);



        JLabel totalLabel = new JLabel("Total");
        addComponent(totalLabel,3,4);

        totalField = new JTextField("0",10);
        totalField.setHorizontalAlignment(JTextField.RIGHT);
        totalField.setEditable(false);
        addComponent(totalField,4,4);

        // set up  listeners (in a spearate method)
        initListeners();
    }

    // set up listeners
    // initially just for buttons, can add listeners for text fields
    public void initListeners() {

        // exitButton - exit program when pressed
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restoreState();
                System.exit(0);
            }
        });

        // calculateButton - call calculateTotalIncome() when pressed
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveState();
                calculateTotalIncome();
                calculateTotalExpenditures();
                calculatetotal();
                updatetotalcolour();
            }
        });

        undoButton.addActionListener(new java.awt.event.ActionListener(){

            public void actionPerformed(ActionEvent e){

                restoreState();

            }
        });
    }

    // add a component at specified row and column in UI. (0,0) is top-left corner
    public void addComponent(Component component, int gridrow, int gridcol) {
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL; // always use horixontsl filll
        layoutConstraints.gridx = gridcol;
        layoutConstraints.gridy = gridrow;
        add(component, layoutConstraints);

    }

    // update totalIncomeField (eg, when Calculate is pressed)
    // use double to hold numbers, so user can type fractional amounts such as
    // 134.50
    public double calculateTotalIncome() {

        // get values from income text fields. valie is NaN if an error occurs
        double wages = getTextFieldValue(wagesField) * getMultiplier(wagesFrequencyComboBox);
        double loans = getTextFieldValue(loansField) * getMultiplier(loansFrequencyComboBox);
        double sales = getTextFieldValue(salesField) * getMultiplier(salesFrequencyComboBox); 
        double trustFund = getTextFieldValue(trustFundField) *getMultiplier(trustFundFrequencyComboBox);


        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(wages) || Double.isNaN(loans) || Double.isNaN(sales) || Double.isNaN(trustFund)) {
            totalIncomeField.setText(""); // clear total income field
            return 0.0; // exit method and do nothing
        }
        // otherwise calculate total income and update text field
        double totalIncome = (wages + loans + sales + trustFund);
        totalIncomeField.setText(String.format("%.2f", totalIncome)); // format with 2 digits after the .
        return totalIncome;

    }

    public double getMultiplier(JComboBox<String> dropDownBox) {
        if (dropDownBox.getSelectedItem().toString().equals("Month")) {
            return 4.3333333;
        } else if (dropDownBox.getSelectedItem().toString().equals("Year")) {
            return 4.3333333 * 12;
        }
        return 1;
    }

    public double calculateTotalExpenditures() {

        double groceries = getTextFieldValue(groceriesField) * getMultiplier(groceriesFrequencyComboBox);
        double rent = getTextFieldValue(rentField)*getMultiplier(rentFrequencyComboBox);
        double electricBill = getTextFieldValue(electricField)*getMultiplier(electricFrequencyComboBox);
        double gasBill = getTextFieldValue(gasField)*getMultiplier(gasFrequencyComboBox);

        if (Double.isNaN(groceries) || Double.isNaN(rent) || Double.isNaN(electricBill) || Double.isNaN(gasBill)) {
            totalExpendituresField.setText(""); // clear total income field
            return 0.0; // exit method and do nothing
        }

        double totalExpenditures = (groceries + rent + electricBill + gasBill);
        totalExpendituresField.setText(String.format("%.2f", totalExpenditures));
        return totalExpenditures;
    }

    public double calculatetotal() {

        double income = calculateTotalIncome();
        double expenditures = calculateTotalExpenditures();

        if (Double.isNaN(income) || Double.isNaN(expenditures)) {
            totalField.setText("Error"); // clear total income field
            return Double.NaN;
        }

        double total = income - expenditures;
        totalField.setText(String.format("%.2f", total));
        return (double) total;
    }

    public void updatetotalcolour() {

        double total = calculatetotal();

        if (total < 0) {

            totalField.setForeground(Color.BLACK);
            totalField.setBackground(Color.RED);
        } else {

            totalField.setForeground(Color.WHITE);
            totalField.setBackground(Color.BLACK);

        }
    }

    private double getTextFieldValue(JTextField field) {

        // get value as String from field
        String fieldString = field.getText(); // get text from text field

        if (fieldString.isBlank()) { // if text field is blank, return 0
            return 0;
        } else if (!fieldString.matches("^\\d*\\.?\\d+$")) {
            if (fieldString.startsWith("-")) {
                JOptionPane.showMessageDialog(topLevelFrame, "Please only enter positive values");
            } else {
                JOptionPane.showMessageDialog(topLevelFrame, "Please only enter numeric values");
            }
            return Double.NaN;
        } else {

            return Double.parseDouble(fieldString);

        }

    }

    public void saveState() {

        Stack<String> currentState = new Stack<>();

        currentState.push(wagesField.getText());
        currentState.push(loansField.getText());
        currentState.push(salesField.getText());
        currentState.push(trustFundField.getText());

        currentState.push(groceriesField.getText());
        currentState.push(rentField.getText());
        currentState.push(electricField.getText());
        currentState.push(gasField.getText());

        currentState.push(wagesFrequencyComboBox.getSelectedItem().toString());
        currentState.push(loansFrequencyComboBox.getSelectedItem().toString());
        currentState.push(salesFrequencyComboBox.getSelectedItem().toString());
        currentState.push(trustFundFrequencyComboBox.getSelectedItem().toString());

        currentState.push(groceriesFrequencyComboBox.getSelectedItem().toString());
        currentState.push(rentFrequencyComboBox.getSelectedItem().toString());
        currentState.push(electricFrequencyComboBox.getSelectedItem().toString());
        currentState.push(gasFrequencyComboBox.getSelectedItem().toString());

        undoStack.push(currentState);

    }


    public void restoreState(){

        if (!undoStack.isEmpty()){

            Stack<String> prevState = undoStack.pop();
            gasFrequencyComboBox.setSelectedItem(prevState.pop());
            electricFrequencyComboBox.setSelectedItem(prevState.pop());
            rentFrequencyComboBox.setSelectedItem(prevState.pop());
            groceriesFrequencyComboBox.setSelectedItem(prevState.pop());

            trustFundFrequencyComboBox.setSelectedItem(prevState.pop());
            salesFrequencyComboBox.setSelectedItem(prevState.pop());
            loansFrequencyComboBox.setSelectedItem(prevState.pop());
            wagesFrequencyComboBox.setSelectedItem(prevState.pop());
        
            gasField.setText(prevState.pop());
            electricField.setText(prevState.pop()); 
            rentField.setText(prevState.pop());
            groceriesField.setText(prevState.pop());

            trustFundField.setText(prevState.pop());
            salesField.setText(prevState.pop());
            loansField.setText(prevState.pop());
            wagesField.setText(prevState.pop());

            calculateTotalIncome();
            calculateTotalExpenditures();
            calculatetotal();
            updatetotalcolour();
        
        } else{

            System.out.println("No more undo's");

        }

    }

    // below is standard code to set up Swing, which students shouldnt need to edit
    // much
    // standard mathod to show UI
    private static void createAndShowGUI() {

        // Create and set up the window.
        JFrame frame = new JFrame("Budget Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        BudgetBase newContentPane = new BudgetBase(frame);
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // standard main class to set up Swing UI
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}