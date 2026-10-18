
package parking.refactored;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {
    private final List<String> receipts = new ArrayList<>();
    private final FileLogger logger = new FileLogger("parking.log");
    private double total;

    public double fee(Vehicle vehicle, int hours) {
        return switch (vehicle.type()) {
            case "CAR" -> hours * 5.0;
            case "MOTORBIKE" -> hours * 2.0;
            case "TRUCK" -> 20 + hours * 10.0;
            default -> throw new IllegalArgumentException(vehicle.type());
        };
    }

    public void checkout(Vehicle vehicle, int hours, PaymentMethod method)
            throws IOException {
        double amount = fee(vehicle, hours);
        method.pay(amount);
        total += amount;

        String receipt = vehicle.plate() + " | " + vehicle.type() + " | "
                + hours + " h | " + amount + " MAD | " + method.name();

        receipts.add(receipt);
        logger.log(receipt);
    }

    public void cancel(Vehicle vehicle, int hours, RefundablePayment method) {
        double amount = fee(vehicle, hours);
        method.refund(amount);
        total -= amount;
    }

    // Cash is refunded by a human at the desk.
    public void cancelAtDesk(Vehicle vehicle, int hours) {
        double amount = fee(vehicle, hours);

        System.out.println("Refund " + amount + " MAD at the desk for "
                + vehicle.plate());

        total -= amount;
    }

    public String dailyReport() {
        StringBuilder sb = new StringBuilder("=== Daily report ===\n");

        for (String receipt : receipts) {
            sb.append(receipt).append("\n");
        }

        sb.append("Total: ").append(total).append(" MAD");
        return sb.toString();
    }
}
