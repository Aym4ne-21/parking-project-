package parking.refactored;

import java.util.List;

public class CsvReceiptFormatter implements ReceiptFormatter {
    public String formatReceipt(Receipt r) {
        return String.join(",", r.plate(), r.type(), String.valueOf(r.hours()),
                String.valueOf(r.amount()), r.method());
    }
    public String formatReport(List<Receipt> receipts, double total) {
        StringBuilder sb = new StringBuilder("plate,type,hours,amount,method\n");
        for (Receipt r : receipts) sb.append(formatReceipt(r)).append("\n");
        return sb.append("TOTAL,,,").append(total).append(",").toString();
    }
}