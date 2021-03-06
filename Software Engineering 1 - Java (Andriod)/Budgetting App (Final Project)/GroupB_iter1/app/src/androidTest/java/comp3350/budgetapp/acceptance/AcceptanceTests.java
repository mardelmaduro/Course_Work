package comp3350.budgetapp.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        loadFullTestSuite();
        return suite;
    }

    private static void loadFullTestSuite(){
        suite.addTestSuite(WishlistTest.class);
        suite.addTestSuite(ExpensesTest.class);
        suite.addTestSuite(IncomeSourcesTest.class);
        suite.addTestSuite(PurchaseHistoryTests.class);
        suite.addTestSuite(FinancialSummaryTests.class);
        // Add all the test suites here
    }
}
