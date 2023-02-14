package entities;

import java.util.List;

//Supplier entity class
public class Supplier{

//private class variables
        private String firstName;
        private String lastName;
        private String description;
        private String username;
        private String password;
        private List<Product> products;


        /*
        Supplier Constructor
        Inputs: first name, last name, description, username, and password (all String types)
        Outputs: None - instantiates supplier object w/ inputted values
        */
        public Supplier (String fname, String lname, String desc, String user, String pswd) {

        this.firstName=fname;
        this.lastName=lname;
        this.description=desc;
        this.username=user;
        this.password = pswd;
        

        }

        //______________________________________________________________
        //Setter functions:
        //______________________________________________________________
        
        public void setFirstName(String fname){
            this.firstName=fname;
        }

        public void setLastName(String lname){
            this.lastName=lname;
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
        public String getFirstName(){
            return this.firstName;
        }
        
        public String getLastName(){
            return this.lastName;
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

