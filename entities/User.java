package entities;

import java.util.ArrayList;
import java.util.List;

public class User{
    private String firstName;
    private String lastName;
    private String userAddress;
    private String userEmail;
    private String password;
    private int cardNumber;
    private List<Product> cartProducts;
    private List<Order> orders;  


    public User(String firstName, String lastName, String userAddress, String userEmail, String password,
            int cardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.password = password;
        this.cardNumber = cardNumber;
        this.cartProducts = new ArrayList<Product>();
        this.orders = new ArrayList<Order>();
    }
   
    // Getter and Setter methods
        
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName(){
	    return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public String getuserEmail(){
		return userEmail;
	}

	public String getPassword(){
		return password;
	}

    public int getcardNumber() {
        return cardNumber;
    }

    public List<Product> getCartProducts() {
        return cartProducts;
    }

    public void addProductToCart(Product product) {
        cartProducts.add(product);
    }

    public void removeProductFromCart(Product product) {
        cartProducts.remove(product);
    }
    
    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}

