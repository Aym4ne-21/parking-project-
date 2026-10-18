package parking.refactored;



public class CashPayment implements PaymentMethod {

    public String name() { return "CASH"; }

    public void pay(double amount) {
        System.out.println("[CASH] received " + amount + " MAD");
    }

    public void refund(double amount) {
        throw new UnsupportedOperationException("Cash cannot be refunded by the machine");
    }
}
