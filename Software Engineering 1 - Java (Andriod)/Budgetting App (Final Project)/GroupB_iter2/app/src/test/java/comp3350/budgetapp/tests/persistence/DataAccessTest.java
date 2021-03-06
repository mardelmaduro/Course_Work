package comp3350.budgetapp.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;

//import comp3350.budgetapp.tests.TesterMain;
//import comp3350.budgetapp.application.Services;

import comp3350.budgetapp.application.*;
import comp3350.budgetapp.objects.*;
import comp3350.budgetapp.persistence.DataAccess;
import comp3350.budgetapp.tests.persistence.DataAccessStub;

public class DataAccessTest extends TestCase {

    private static String dbName = "stub";

    public static WishListItem wish1, wish2;
    public static Expense expense1, expense2;
    public static IncomeSource incomeSource1, incomeSource2;


    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        DataAccess dataAccess;

        Services.closeDataAccess();

        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        // Use the following statement to run with the stub database
        dataAccess = Services.createDataAccess(new DataAccessStub(dbName));

        dataAccessTest();

        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    public static void dataAccessTest() {
        DataAccess dataAccess;
        dataAccess = Services.getDataAccess(dbName);

        wishListItemTest(dataAccess);
        expenseTest(dataAccess);
        incomeSourceTest(dataAccess);

        Services.closeDataAccess();
    }

    private static void wishListItemTest(DataAccess dataAccess)
    {
        ArrayList<WishListItem> wishlist;
        WishListItem wish;

        String result;

        wishlist = new ArrayList<WishListItem>();
        result = dataAccess.getWishListItemSequential(wishlist);
        assertNull(result);
        assertNotNull(wishlist);
        assertEquals(4, wishlist.size());

        wish = wishlist.get(0);
        assertEquals(899.00, wish.getPrice());
        assertEquals("Google Pixel",wish.getItemName());
        wish = wishlist.get(1);
        assertEquals(130.00, wish.getPrice());
        assertEquals("Google Home",wish.getItemName());
        wish = wishlist.get(2);
        assertEquals(99.00, wish.getPrice());
        assertEquals("Daydream View",wish.getItemName());
        wish = wishlist.get(3);
        assertEquals(35.00, wish.getPrice());
        assertEquals("ChromeCast",wish.getItemName());

        wish1 = new WishListItem("Samsung S9", 499.99);
        dataAccess.addWishListItem(wish1);
        wish2 = new WishListItem("Samsung Watch", 200.99);
        dataAccess.addWishListItem(wish2);

        result = dataAccess.getWishListItemSequential(wishlist);
        assertEquals(6, wishlist.size());

        wish = wishlist.get(4);
        assertEquals(499.99, wish.getPrice());
        assertEquals("Samsung S9",wish.getItemName());
        wish = wishlist.get(5);
        assertEquals(200.99, wish.getPrice());
        assertEquals("Samsung Watch",wish.getItemName());

        dataAccess.deleteWishListItem(wish1);
        result = dataAccess.getWishListItemSequential(wishlist);
        assertEquals(5,wishlist.size());

        wish2 = new WishListItem("Samsung Watch", 150.99);
        dataAccess.updateWishListItem(wish2);
        assertTrue(wish2.equals(wishlist.get(4)));

    }

    private static void incomeSourceTest(DataAccess dataAccess)
    {
        ArrayList<IncomeSource> incomelist;
        IncomeSource income;

        String result;

        incomelist = new ArrayList<IncomeSource>();
        result = dataAccess.getIncomeSourceSequential(incomelist);
        assertNull(result);
        assertNotNull(incomelist);
        assertEquals(1, incomelist.size());

        income = incomelist.get(0);
        assertEquals("Starbucks", income.getName());
        assertEquals(800.00,income.getAmount());

        incomeSource1 = new IncomeSource("Web Developer",21.0);
        dataAccess.addIncomeSource(incomeSource1);
        incomeSource2 = new IncomeSource("Teaching Assistant",13.0);
        dataAccess.addIncomeSource(incomeSource2);

        dataAccess.getIncomeSourceSequential(incomelist);
        assertEquals(3,incomelist.size());

        income = incomelist.get(1);
        assertEquals(21.0,income.getAmount());
        assertEquals("Web Developer",income.getName());
        income = incomelist.get(2);
        assertEquals(13.0,income.getAmount());
        assertEquals("Teaching Assistant",income.getName());

        dataAccess.deleteIncomeSource(incomeSource1);
        dataAccess.getIncomeSourceSequential(incomelist);
        assertEquals(2,incomelist.size());

        incomeSource2 = new IncomeSource("Teaching Assistant",14.0);
        dataAccess.updateIncomeSource(incomeSource2);
        assertTrue(incomeSource2.equals(incomelist.get(1)));

    }

    private static void expenseTest(DataAccess dataAccess)
    {
        ArrayList<Expense> expenselist;
        Expense expense;

        String result;

        expenselist = new ArrayList<Expense>();
        result = dataAccess.getExpenseSequential(expenselist);
        assertNull(result);
        assertNotNull(expenselist);
        assertEquals(2, expenselist.size());

        expense = expenselist.get(0);
        assertEquals("Rent", expense.getName());
        assertEquals(500.00,expense.getAmount());
        expense = expenselist.get(1);
        assertEquals("Internet", expense.getName());
        assertEquals(120.00,expense.getAmount());

        expense1 = new Expense("Cellphone Bill",60.0);
        dataAccess.addExpense(expense1);
        expense2 = new Expense("Hydro",100.0);
        dataAccess.addExpense(expense2);

        dataAccess.getExpenseSequential(expenselist);
        assertEquals(4,expenselist.size());

        expense = expenselist.get(2);
        assertEquals(60.0,expense.getAmount());
        assertEquals("Cellphone Bill",expense.getName());
        expense = expenselist.get(3);
        assertEquals(100.0,expense.getAmount());
        assertEquals("Hydro",expense.getName());

        dataAccess.deleteExpense(expense1);
        dataAccess.getExpenseSequential(expenselist);
        assertEquals(3,expenselist.size());

        expense2 = new Expense("Hydro",150.00);
        dataAccess.updateExpense(expense2);
        assertTrue(expense2.equals(expenselist.get(2)));

    }
}
