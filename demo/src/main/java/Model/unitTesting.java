package Model;

public class unitTesting  {

    private String firstname;
    private double balance;

    public unitTesting(String firstname, double balance) {
        this.firstname = firstname;
        this.balance = balance;
    }
    public double deposit(double balance ,  double amount) {
        balance += amount;
        return balance;
    }
}
