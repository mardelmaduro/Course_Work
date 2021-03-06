package comp3350.budgetapp.objects;

import java.util.Locale;

public class FinancialObjects {
    protected String name = "";
    protected double amount = 0.0;

    public FinancialObjects()
    {
        this.name = "";
        this.amount = 0.00;
    }

    public FinancialObjects(String name, double amount)
    {
        this.name = name;
        this.amount = amount;
    }

    public double getAmount()
    {
        return this.amount;
    }

    public String getName()
    {
        return this.name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format(Locale.getDefault(),"%s : $%.2f", name, amount);
    }

    public boolean equals(Object object)
    {
        boolean result;
        FinancialObjects obj;

        result = false;

        if (object instanceof FinancialObjects)
        {
            obj = (FinancialObjects) object;
            if( ((obj.name == null) && (name == null)) || (obj.name.equals(name)) )
            {
                result = true;
            }
        }
        return result;
    }
}

