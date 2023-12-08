
package Budget;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// Swing imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Unit test for swing application.
 * We can't test the GUI so we have to test methods that do the work.
 * Add more tests for any manipulations of values in your application.
 * Rewrite methods so that they are not void, but return values, and can be tested.
 */


public class BudgetBaseTest {

    @Test
    public void testCalculateTotalIncome(){
        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        
        BudgetBase.wagesField = new JTextField("300.00");
        BudgetBase.loansField = new JTextField("100.00");
        BudgetBase.salesField = new JTextField("150.00");
        BudgetBase.trustFundField = new JTextField("50.00");

        BudgetBase.groceriesField = new JTextField("100.00");
        BudgetBase.rentField = new JTextField("200.00");
        BudgetBase.electricField = new JTextField("50.00");
        BudgetBase.gasField = new JTextField("30.00");

        Double ExpectedTotal = ((300.00+100.00+150.00+50.00)-(100.00+200.00+50.00+30.00));

        assertEquals(ExpectedTotal,BudgetBase.calculatetotal(),0.001);

    }

    @Test 
    public void testEmptyFields(){
        
        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        BudgetBase.wagesField = new JTextField("");
        BudgetBase.loansField = new JTextField("");
        BudgetBase.salesField = new JTextField("");
        BudgetBase.trustFundField = new JTextField("");

        BudgetBase.groceriesField = new JTextField("");
        BudgetBase.rentField = new JTextField("");
        BudgetBase.electricField = new JTextField("");
        BudgetBase.gasField = new JTextField("");

        Double ExpectedTotal = 0.00;
        assertEquals(ExpectedTotal,BudgetBase.calculatetotal(),0.001);

    }
   
   @Test 
   public void testNonNumeric(){
        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);
        
        BudgetBase.wagesField = new JTextField("abc");
        BudgetBase.loansField = new JTextField("200");
        BudgetBase.salesField = new JTextField("*3?");
        BudgetBase.trustFundField = new JTextField("150");

        Double ExpectedTotal = 0.00;

        BudgetBase.calculateTotalIncome();
        BudgetBase.calculateTotalExpenditures();
        assertEquals(ExpectedTotal,BudgetBase.calculatetotal());
   }



   @Test
   public void testNegative(){
        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);
        
        BudgetBase.wagesField = new JTextField("-100.00");
        BudgetBase.loansField = new JTextField("200");
        BudgetBase.salesField = new JTextField("-300.00");
        BudgetBase.trustFundField = new JTextField("400.00");

        Double ExpectedTotal = 0.00;

        assertEquals(ExpectedTotal,BudgetBase.calculatetotal());

   }

    @Test 
    public void testFrequencyIncome(){
        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        BudgetBase.wagesField = new JTextField("100.00");
        BudgetBase.wagesFrequencyComboBox.setSelectedItem("Week");

        BudgetBase.loansField = new JTextField("200.00");
        BudgetBase.loansFrequencyComboBox.setSelectedItem("Year");

        BudgetBase.salesField = new JTextField("300.00");
        BudgetBase.salesFrequencyComboBox.setSelectedItem("Week");

        BudgetBase.trustFundField = new JTextField("400.00");
        BudgetBase.trustFundFrequencyComboBox.setSelectedItem("Month");

        double ExpectedTotal = ((100)+(200*4.3333333*12)+(300)+(400*4.3333333));

        assertEquals(ExpectedTotal, BudgetBase.calculateTotalIncome(),0.001);

    }

    @Test
    public void testFrequencyExpenditures(){

        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        BudgetBase.groceriesField = new JTextField("100.00");
        BudgetBase.groceriesFrequencyComboBox.setSelectedItem("Week");

        BudgetBase.rentField = new JTextField("200.00");
        BudgetBase.rentFrequencyComboBox.setSelectedItem("Year");

        BudgetBase.electricField = new JTextField("200.00");
        BudgetBase.electricFrequencyComboBox.setSelectedItem("Week");

        BudgetBase.gasField = new JTextField("100.00");
        BudgetBase.gasFrequencyComboBox.setSelectedItem("Month");

        double ExpectedTotal = ((100.00)+(200.00*4.3333333*12)+(200.00)+(100.00*4.3333333));

        assertEquals(ExpectedTotal, BudgetBase.calculateTotalExpenditures(),0.001);


    }

    @Test
    public void testUpdateText(){

        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        BudgetBase.wagesField = new JTextField("200.00");
        BudgetBase.wagesFrequencyComboBox.setSelectedItem("Week");

        double initialTotalIncome = BudgetBase.calculateTotalIncome();

        BudgetBase.wagesField.setText("250.00");

        double updateTotalIncome = BudgetBase.calculateTotalIncome();

        assertEquals(initialTotalIncome + 50.00,  updateTotalIncome,0.001);
    }

    @Test
    public void testUpdateComboBox(){

        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        BudgetBase.salesField = new JTextField("200.00");
        BudgetBase.salesFrequencyComboBox.setSelectedItem("Month");

        double initialTotalIncome = BudgetBase.calculateTotalIncome();

        BudgetBase.salesFrequencyComboBox.setSelectedItem("Year");

        double updateTotalIncome = BudgetBase.calculateTotalIncome();

        assertEquals(initialTotalIncome * 12, updateTotalIncome,0.001);


    }

    @Test
    public void testSingleUndo(){

        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        BudgetBase.wagesField.setText("100.00");
        BudgetBase.saveState();


        BudgetBase.wagesField.setText("200.00");
        BudgetBase.undoButton.doClick();

        assertEquals("100.00",BudgetBase.wagesField.getText());

    }



    @Test
    public void testMoreUndos(){

        JFrame frame = new JFrame();
        BudgetBase BudgetBase = new BudgetBase(frame);

        BudgetBase.wagesField.setText("100.00");
        BudgetBase.saveState();

        BudgetBase.wagesField.setText("200.00");
        BudgetBase.saveState();

        BudgetBase.wagesField.setText("300.00");
        BudgetBase.saveState();


        BudgetBase.undoButton.doClick();
        BudgetBase.undoButton.doClick();
        BudgetBase.undoButton.doClick();

        assertEquals("100.00",BudgetBase.wagesField.getText());

    }

}
