// base code for student budget assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!
//
// Your submission must be a maven project, and must be submitted via Codio, and run in Codio
//
// user can enter in wages and loans and calculate total income
//
// run in Codio 
// To see GUI, run with java and select Box Url from Codio top line menu
//
// Layout - Uses GridBag layout in a straightforward way, every component has a (column, row) position in the UI grid
// Not the prettiest layout, but relatively straightforward
// Students who use IntelliJ or Eclipse may want to use the UI designers in these IDEs , instead of GridBagLayout
package Budget;

// Swing imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// class definition
public class BudgetBase extends JPanel {    // based on Swing JPanel

    // high level UI stuff
    JFrame topLevelFrame;  // top-level JFrame
    GridBagConstraints layoutConstraints = new GridBagConstraints(); // used to control layout

    // widgets which may have listeners and/or values
    private JButton calculateButton;   // Calculate button
    private JButton exitButton; // Exit button

    private JTextField wagesField;     // Wages text field
    private JTextField loansField;     // Loans text field
    private JTextField salesField;     //Sales text field
    private JTextField trustFundField; //TrustFund text field

    private JTextField groceriesField; //Groceries text field
    private JTextField rentField;      //Rent text field
    private JTextField electricField; //Electric bill text field
    private JTextField gasField;      //Gas bill text field

    private JTextField totalIncomeField; // Total Income field
    private JTextField totalExpendituresField; //Total Expenditures field
    private JTextField totalField;      //Total overall field

    // constructor - create UI  (dont need to change this)
    public BudgetBase(JFrame frame) {
        topLevelFrame = frame; // keep track of top-level frame
        setLayout(new GridBagLayout());  // use GridBag layout
        initComponents();  // initalise components
    }

    // initialise componenents
    // Note that this method is quite long.  Can be shortened by putting Action Listener stuff in a separate method
    // will be generated automatically by IntelliJ, Eclipse, etc
    private void initComponents() { 

        // Top row (0) - "INCOME" label
        JLabel incomeLabel = new JLabel("INCOME");
        addComponent(incomeLabel, 0, 0);

/////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Row 1 - Wages label followed by wages textbox
        JLabel wagesLabel = new JLabel("Wages");
        addComponent(wagesLabel, 1, 0);

        // set up text field for entering wages
        // Could create method to do below (since this is done several times)
        wagesField = new JTextField("", 10);   // blank initially, with 10 columns
        wagesField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(wagesField, 1, 1);   

///////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Row 2 - Loans label followed by loans textbox
        JLabel loansLabel = new JLabel("Loans");
        addComponent(loansLabel, 2, 0);

        // set up text box for entering loans
        loansField = new JTextField("", 10);   // blank initially, with 10 columns
        loansField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        addComponent(loansField, 2, 1); 

////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Row 3 - Sales label followed by sales textbox
        JLabel salesLabel = new JLabel("Sales");
        addComponent(salesLabel, 3, 0);

        salesField = new JTextField("",10);
        salesField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(salesField, 3, 1);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel trustFundLabel = new JLabel("Trust Fund");
        addComponent(trustFundLabel,4,0);

        trustFundField = new JTextField("",10);
        trustFundField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(trustFundField,4,1);

////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Row 4 - Total Income label followed by total income field
        JLabel totalIncomeLabel = new JLabel("Total Income");
        addComponent(totalIncomeLabel, 5, 0);

        // set up text box for displaying total income.  Users cam view, but cannot directly edit it
        totalIncomeField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalIncomeField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalIncomeField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalIncomeField, 5, 1);  

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

         // Top row (0) - "Expenditures" label
        JLabel Expenditures = new JLabel("Expenditures");
        addComponent(Expenditures, 0, 2);

///////////////////////////////////////////////////////////////////////////////////////////////////////

        //Row 1 - Groceries label followed by groceries textbox
        JLabel groceriesLabel = new JLabel("Groceries");
        addComponent(groceriesLabel,1,2);

        groceriesField = new JTextField("",10);
        groceriesField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(groceriesField,1,3);

////////////////////////////////////////////////////////////////////////////////////////////////////////


        //Row 2 - Rent label followed by rent textbox
        JLabel rentLabel = new JLabel("Rent");
        addComponent(rentLabel,2,2);

        rentField = new JTextField("",10);
        rentField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(rentField,2,3);

//////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Row 3- ElectricBill label followed by electricBill textbox
        JLabel electricLabel = new JLabel("Electric Bill");
        addComponent(electricLabel,3,2);

        electricField = new JTextField("",10);
        electricField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(electricField,3,3);

///////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel gasLabel = new JLabel("Gas Bill");
        addComponent(gasLabel,4,2);

        gasField = new JTextField("",10);
        gasField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(gasField,4,3);


///////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Row 4- Show total Expenditures
        JLabel totalExpendituresLabel = new JLabel("Total Expenditures");
        addComponent(totalExpendituresLabel, 5, 2);

        // set up text box for displaying total Expenditures.  Users cam view, but cannot directly edit it
        totalExpendituresField = new JTextField("0", 10);   // 0 initially, with 10 columns
        totalExpendituresField.setHorizontalAlignment(JTextField.RIGHT) ;    // number is at right end of field
        totalExpendituresField.setEditable(false);    // user cannot directly edit this field (ie, it is read-only)
        addComponent(totalExpendituresField, 5, 3);  

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Row 5 - Calculate Button
        calculateButton = new JButton("Calculate");
        addComponent(calculateButton, 6, 0);  

////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Row 5 - Exit Button
        exitButton = new JButton("Exit");
        addComponent(exitButton, 7, 0);  
////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel totalLabel = new JLabel("Total");
        addComponent(totalLabel,8,0);

        totalField = new JTextField("0",10);
        totalField.setHorizontalAlignment(JTextField.RIGHT);
        totalField.setEditable(false);
        addComponent(totalField,8,1);

        // set up  listeners (in a spearate method)
        initListeners();
    }

    // set up listeners
    // initially just for buttons, can add listeners for text fields
    private void initListeners() {

        // exitButton - exit program when pressed
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // calculateButton - call calculateTotalIncome() when pressed
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome();
                calculateTotalExpenditures();
                calculatetotal();
            }
        });

    }

    // add a component at specified row and column in UI.  (0,0) is top-left corner
    private void addComponent(Component component, int gridrow, int gridcol) {
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;   // always use horixontsl filll
        layoutConstraints.gridx = gridcol;
        layoutConstraints.gridy = gridrow;
        add(component, layoutConstraints);

    }

    // update totalIncomeField (eg, when Calculate is pressed)
    // use double to hold numbers, so user can type fractional amounts such as 134.50
    public double calculateTotalIncome() {

        // get values from income text fields.  valie is NaN if an error occurs
        double wages = getTextFieldValue(wagesField);
        double loans = getTextFieldValue(loansField);
        double sales = getTextFieldValue(salesField);
        
        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(wages) || Double.isNaN(loans)|| Double.isNaN(sales)) {
            totalIncomeField.setText("");  // clear total income field
            wages = 0.0;
            return wages;   // exit method and do nothing
        }
        // otherwise calculate total income and update text field
        double totalIncome = (wages + loans + sales);
        totalIncomeField.setText(String.format("%.2f",totalIncome));  // format with 2 digits after the .
        return totalIncome;

    }

    public double calculateTotalExpenditures(){

        double groceries = getTextFieldValue(groceriesField);
        double rent = getTextFieldValue(rentField);
        double electricBill = getTextFieldValue(electricField);
        double gasBill = getTextFieldValue(gasField);

        double totalExpenditures = (groceries+rent+electricBill+gasBill);
        totalExpendituresField.setText(String.format("%.2f",totalExpenditures));
        return totalExpenditures;
    }
  
    public double calculatetotal(){

        double income = calculateTotalIncome();
        double expenditures = calculateTotalExpenditures();

        double total = income - expenditures;
        totalField.setText(String.format("%.2f",total));
        return total;
    }
   
    private double getTextFieldValue(JTextField field) {

        // get value as String from field
        String fieldString = field.getText();  // get text from text field

        if (fieldString.isBlank()) {   // if text field is blank, return 0
            return 0;
        }

        else {  // if text field is not blank, parse it into a double
            try {
                return Double.parseDouble(fieldString);  // parse field number into a double
             } catch (java.lang.NumberFormatException ex) {  // catch invalid number exception
                JOptionPane.showMessageDialog(topLevelFrame, "Please enter a valid number");  // show error message
                return Double.NaN;  // return NaN to show that field is not a number
            }
        }
    }


// below is standard code to set up Swing, which students shouldnt need to edit much
    // standard mathod to show UI
    private static void createAndShowGUI() {
 
        //Create and set up the window.
        JFrame frame = new JFrame("Budget Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        BudgetBase newContentPane = new BudgetBase(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    // standard main class to set up Swing UI
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }


}