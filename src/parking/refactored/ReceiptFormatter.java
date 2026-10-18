package parking.refactored;

import java.util.List;

public interface ReceiptFormatter {
    String formatReceipt(Receipt receipt);
    String formatReport(List<Receipt> receipts, double total);
}