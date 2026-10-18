package parking.refactored;

import java.util.List;

public class TextReceiptFormatter implements ReceiptFormatter {

    public String formatReceipt(Receipt r) {
        return r.plate() + " | " + r.type() + " | " + r.hours() + " h | "
                + r.amount() + " MAD | " + r.method();
    }

    public String formatReport(List<Receipt> receipts, double total) {
        StringBuilder sb = new StringBuilder("=== Daily report ===\n");
        for (Receipt r : receipts) {
            sb.append(formatReceipt(r)).append("\n");
        }
        return sb.append("Total: ").append(total).append(" MAD").toString();
    }
}