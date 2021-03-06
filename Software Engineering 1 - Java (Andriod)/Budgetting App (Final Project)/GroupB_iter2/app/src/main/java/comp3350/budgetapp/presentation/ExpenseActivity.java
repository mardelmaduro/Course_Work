package comp3350.budgetapp.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350.budgetapp.R;
import comp3350.budgetapp.business.Calculate;
import comp3350.budgetapp.objects.Expense;
import comp3350.budgetapp.business.AccessExpenses;

public class ExpenseActivity extends Activity {

    private Calculate totalPrice;
    static String total = "Updates on opening Expenses";
    private AccessExpenses accessExpenses;
    private ArrayList<Expense> expenseList;
    private ArrayAdapter<Expense> itemArrayAdapter;
    private int selectedExpensePosition = -1;
    private TextView viewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        accessExpenses = new AccessExpenses();
        totalPrice = new Calculate();

        expenseList = new ArrayList<Expense>();
        String result = accessExpenses.getExpenses(expenseList);
        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else
        {
            itemArrayAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, expenseList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(expenseList.get(position).getName());
                    text2.setText(String.format("%.2f", expenseList.get(position).getAmount()));

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.expenses);
            listView.setAdapter(itemArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Button updateButton = (Button)findViewById(R.id.buttonExpenseUpdate);
                    Button deleteButton = (Button)findViewById(R.id.buttonExpenseDelete);

                    if (position == selectedExpensePosition) {
                        listView.setItemChecked(position, false);
                        updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        selectedExpensePosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        updateButton.setEnabled(true);
                        deleteButton.setEnabled(true);
                        selectedExpensePosition = position;
                        selectItemAtPosition(position);
                    }
                }
            });

            //TextView viewTotal = (TextView)findViewById(R.id.viewTotal);
            viewTotal = (TextView)findViewById(R.id.viewExpenseTotal);
            //Fix Total Display
            //viewTotal.setText(accessWishListItems.getTotal());
            viewTotal.setText(totalPrice.expenseTotal(expenseList));
            total = totalPrice.expenseTotal(expenseList);
        }
    }

    public void selectItemAtPosition(int position)
    {
        Expense selected = itemArrayAdapter.getItem(position);

        EditText editName = (EditText)findViewById(R.id.editExpenseName);
        EditText editPrice = (EditText)findViewById(R.id.editExpenseAmount);

        editName.setText(selected.getName());
        editPrice.setText(String.valueOf(selected.getAmount()));
    }

    public void buttonExpenseAddOnClick(View v)
    {
        Expense item = createExpenseFromEditText();

        if(expenseList.contains(item) || item.getName().contains("'"))
        {
            return;
        }

        String result;

        result = validateExpenseData(item, true);
        if (result == null)
        {
            result = accessExpenses.addExpense(item);
            if (result == null)
            {
                accessExpenses.getExpenses(expenseList);
                itemArrayAdapter.notifyDataSetChanged();
                int pos = expenseList.indexOf(item);
                if (pos >= 0)
                {
                    ListView listView = (ListView) findViewById(R.id.expenses);
                    listView.setSelection(pos);
                }
            }
            else
            {
                Messages.fatalError(this, result);
            }
        }
        else
        {
            Messages.warning(this, result);
        }

        viewTotal.setText(totalPrice.expenseTotal(expenseList));
        total = totalPrice.expenseTotal(expenseList);
    }

    public void buttonExpenseDeleteOnClick(View v)
    {
        Expense item = createExpenseFromEditText();

        if(!expenseList.contains(item))
        {
            return;
        }

        String result;

        result = accessExpenses.deleteExpense(item);

        if(result == null)
        {
            int pos = expenseList.indexOf(item);
            if(pos >=0)
            {
                ListView listView = (ListView) findViewById(R.id.expenses);
                listView.setSelection(pos);
            }
            accessExpenses.getExpenses(expenseList);
            itemArrayAdapter.notifyDataSetChanged();
        }
        else
        {
            Messages.warning(this, result);
        }
        viewTotal.setText(totalPrice.expenseTotal(expenseList));
        total = totalPrice.expenseTotal(expenseList);
    }

    public void buttonExpenseUpdateOnClick(View v)
    {
        Expense item = createExpenseFromEditText();

        if(!expenseList.contains(item))
        {
            return;
        }

        String result;

        result = validateExpenseData(item, false);

        if(result == null)
        {
            result = accessExpenses.updateExpense(item);
            if(result == null )
            {
                accessExpenses.getExpenses(expenseList);
                itemArrayAdapter.notifyDataSetChanged();
                int pos = expenseList.indexOf(item);
                if(pos >= 0)
                {
                    ListView listView = (ListView) findViewById(R.id.expenses);
                    listView.setSelection(pos);
                }
            }
            else
            {
                Messages.fatalError(this, result);
            }
        }
        else
        {
            Messages.fatalError(this, result);
        }
        viewTotal.setText(totalPrice.expenseTotal(expenseList));
        total = totalPrice.expenseTotal(expenseList);
    }

    private Expense createExpenseFromEditText()
    {
        Double price = 0.0;
        EditText editItemName = (EditText) findViewById(R.id.editExpenseName);
        EditText editPrice = (EditText) findViewById(R.id.editExpenseAmount);
        String itemName = editItemName.getText().toString();
        String priceString = editPrice.getText().toString();

        if(itemName == null || itemName.equals(""))
        {
            itemName = "Misc";
        }

        if(priceString.length() == 0){
            priceString = "0.00";
        }



        price = Double.parseDouble(priceString);

        Expense item = new Expense(itemName, price);

        return item;
    }

    private String validateExpenseData(Expense item, boolean isNewItem)
    {

        if (item.getName().length() == 0)
        {
            return "Item Name required!";
        }



        if ((isNewItem) && (accessExpenses.getRandom(item.getName()) != null))
        {
            return "Item " + item.getName() + " already exists";
        }

        return null;
    }
}


