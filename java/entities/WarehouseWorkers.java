package entities;
/*
 * entity class that: views orders, edit orders, picks orders, updates inventory
 */

public class WarehouseWorkers {
    //private attributes
    private int Employee_ID;
    private String E_Name;
    private String E_Username;
    private String E_Password;
//setters and getter functions----------------
    public void setEmployee_ID(int i)
    {
        this.Employee_ID=i;
    }

    public void setE_Name(String e)
    {
        this.E_Name=e;
    }

    public void setUsername(String u)
    {
        this.E_Username=u;
    }

    public void setPassword(String p)
    {
        this.E_Password=p;
    }

    public int getEmployee_ID()
    {
        return this.Employee_ID;
    }

    public String getE_Name()
    {
        return this.E_Name;
    }

    public String getUsername()
    {
        return this.E_Username;
    }

    public String getPassword()
    {
        return this.E_Password;
    }
//end of setter and getter functions------------------


//constructor
public WarehouseWorkers(int ID, String name, String user, String pass)
{
    this.Employee_ID=ID;
    this.E_Name=name;
    this.E_Username=user;
    this.E_Password=pass;
}

}//end of entity
