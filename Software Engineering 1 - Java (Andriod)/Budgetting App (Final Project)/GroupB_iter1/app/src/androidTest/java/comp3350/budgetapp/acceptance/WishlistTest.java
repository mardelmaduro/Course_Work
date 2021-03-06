package comp3350.budgetapp.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.budgetapp.presentation.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class WishlistTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public WishlistTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    public void testAddItem()
    {

        String price = "" + randomPrice();
        String name = randomString();

        getToActivity();

        solo.enterText(0,name);
        solo.enterText(1,price);
        solo.clickOnButton("Add");

        try {
            solo.wait();
        } catch(Exception e){
            System.out.println(e);
        }

        Assert.assertTrue(solo.searchText(name));
        Assert.assertTrue(solo.searchText(price));

        //cleanup
        solo.enterText(0,name);
        solo.clickOnButton("Delete");
    }

    public void testEditItem(){
        String price = "" + randomPrice();
        String name = randomString();
        String otherPrice = "" + randomPrice();

        getToActivity();

        solo.enterText(0,name);
        solo.enterText(1,price);
        solo.clickOnButton("Add");

        solo.enterText(0, name);
        solo.enterText(1,otherPrice);
        solo.clickOnButton("Update");

        Assert.assertFalse(solo.searchText(price,true));
        Assert.assertTrue(solo.searchText(otherPrice,true));

        //cleanup
        solo.enterText(0,name);
        solo.clickOnButton("Delete");
    }

    public void testDeleteItem(){
        String price = "" + randomPrice();
        String name = randomString();

        getToActivity();

        solo.enterText(0,name);
        solo.enterText(1,price);
        solo.clickOnButton("Add");

        //cleanup
        solo.enterText(0,name);
        solo.clickOnButton("Delete");
        Assert.assertFalse(solo.searchText(name,true)); // searching only visible text, for speed
        Assert.assertFalse(solo.searchText(price,true));
    }

    public void testAccess() // tests whether user can access and see relevent info
    {
        solo.waitForActivity("MainActivity");
        Assert.assertTrue(solo.searchButton("Wish List", true)); // only searches visible buttons
        solo.clickOnButton("Wish List");
        solo.assertCurrentActivity("Expected activity WishlistActivity", "WishlistActivity");

        Assert.assertTrue(solo.searchText("BudgIt: Wish List")); // may need to be more specific, an entry may be called that
        Assert.assertTrue(solo.searchButton("Delete"));
        Assert.assertTrue(solo.searchButton("Add"));
        Assert.assertTrue(solo.searchButton("Update"));
        Assert.assertTrue(solo.searchButton("Purchased"));

        Assert.assertTrue(solo.searchText("Total:"));
    }

    public void testInvalidAmount(){
        getToActivity();

        String name = randomString();
        String price = "100000000";

        solo.enterText(0,name);
        solo.enterText(1,price);

        solo.clickOnButton("Add");

        Assert.assertTrue(solo.waitForText("Warning",1,10000));
    }

    public void testInvalidPresses(){
        getToActivity();

        solo.clickOnButton("Delete");
        Assert.assertTrue(solo.waitForText("Warning",1,10000));
        solo.goBack();
        solo.clickOnButton("Update");
        Assert.assertTrue(solo.waitForText("Warning",1,10000));
        solo.goBack();
        solo.clickOnButton("Purchased");
        Assert.assertTrue(solo.waitForText("Warning",1,10000));

    }

    public void testDuplicateAdd(){
        getToActivity();
        String name = randomString();

        solo.enterText(0,name);
        solo.clickOnButton("Add");
        solo.enterText(0,name);
        solo.clickOnButton("Add");

        Assert.assertTrue(solo.waitForText("Warning",1,10000));

        solo.goBack();
        solo.enterText(0,name);
        solo.clickOnButton("Delete");
    }

    private void getToActivity(){
        solo.waitForActivity("MainActivity");
        solo.clickOnButton("Wish List");
    }

    private double randomPrice(){
        double price = Math.random()*1000;
        price = ((double)((int)(price*100)))/100;

        return price;
    }

    private String randomString(){
        String result = "";

        for(int i = 0; i < 12; i++){
            int key = (int)(Math.random()*52) + 'A';
            result += (char)key;
        }

        return result;
    }
}
