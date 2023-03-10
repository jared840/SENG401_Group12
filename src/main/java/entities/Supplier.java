package entities;

import java.util.List;
import java.util.ArrayList;

//Supplier entity class
public class Supplier{

//private class variables
        private int supplierID;
        private String name;
        private String description;
        private String username;
        private String password;
        private ArrayList<Product> products;


        /*
        Supplier Constructor
        Inputs: first name, last name, description, username, and password (all String types)
        Outputs: None - instantiates supplier object w/ inputted values
        */
        public Supplier (int supplierID, String name, String desc, String user, String pswd) {

        this.supplierID = supplierID;
        this.name = name;
        this.description=desc;
        this.username=user;
        this.password = pswd;
        products=new ArrayList<Product>();
        

        }

        //______________________________________________________________
        //Setter functions:
        //______________________________________________________________
        
        public void setSupplierID(int supplierID) {
            this.supplierID = supplierID;
        }

        public void setName(String name){
            this.name = name;
        }

        public void setDescription(String descripString){
            this.description=descripString;
        }

        public void setUsername(String user){
            this.username=user;
        }

        public void setPassword (String password){
            this.password=password;
        }

        public void addProduct(Product item){
            this.products.add(item);
        }

        public void removeProduct(Product item){
            this.products.remove(item);
        }


        //_____________________________________________________
        // Getter methods
        //______________________________________________________
        public int getSupplierID() {
            return this.supplierID;
        }

        public String getName(){
            return this.name;
        }

        public String getDescription(){
            return this.description;
        }

        public List<Product> getProducts(){
            return this.products;
        }

        public String getUsername(){
            return this.username;
        }

        public String getPassword(){
            return this.password;
        }
}

