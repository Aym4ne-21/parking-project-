package parking.refactored;

public class WalletPayment implements RefundablePayment {
    private final String phone;
    public WalletPayment(String phone) { this.phone = phone; }
    public String name() { return "WALLET"; }
    public void pay(double amount)    { System.out.println("[WALLET " + phone + "] paid " + amount + " MAD"); }
    public void refund(double amount) { System.out.println("[WALLET " + phone + "] refunded " + amount + " MAD"); }
}