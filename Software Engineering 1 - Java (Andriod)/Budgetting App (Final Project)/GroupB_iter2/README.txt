Personal Budgeting App
Group B

By: Mardel Maduro
    Neil Marzo
    Tina Hernando
    Wynand Badenhorst
    Hui Jin

README.txt:

BudgIt is an application that allows the user to track their total expenses, income,

and

the cost of items they want to purchase. This is done by allowing the user to input

items

in a list.

CONTENTS OF SUBMISSION:

----------------------

Manifest:

- AndroidManifeset.xml

Packages:

- comp3350.budgetapp

~ application: Main, Services

~ business:

AccessWishListItems - add/update/delete item from the databasee

AccessIncomeSourceItems - add/update/delete item from the databasee

AccessExpenseItems - add/update/delete item from the databasee

Calculate - finds totals of objects

~ objects:

FinancialObjects - parent class

WishListItems - class inherits parent

IncomeSource - class inherits parent

Expense - class inherits parent

~ persistence:

DataAccess - interface that uses HSQLDB

DataAccessObject - implements DataAccess

DataAccessFactory - used to switch between stub and real DB

~ presentation:

* all Activity class extend Activity class & are used for presentation functionality*

MainActivity

WishlistActivity

IncomeSourceActivity

ExpenseActivity

SummaryActivity

CLI - command line interface

Messages - used for error interaction within the application

- comp3350.budgetapp (test)

~ tests:

AccessWishListItemsTest (business)

AccessIncomeSourceTest (business)

AccessExpenseTest (business)

CalculateWishListTotalTest (business)

CalculateExpenseTotalTest (business)

CalculateIncomeSourceTotalTest (business)

WishListItemTest (object)

IncomeSourceTest (object)

ExpenseTest (object)

FinancialObjectsTest (object)

DataAccessFactoryTest (persistence)

DataAccessTest (persistence)

DataAccessStub (persistence)

~ AllTests

~ TesterMain - starts up main that calls stub database to test

Resource Files

Gradle Scripts
