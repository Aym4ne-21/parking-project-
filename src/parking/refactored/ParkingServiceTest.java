package parking.refactored;

import java.util.ArrayList;
import java.util.List;

public class ParkingServiceTest {

    // Test double: in-memory fake of ReceiptLog
    static class InMemoryReceiptLog implements ReceiptLog {
        final List<String> lines = new ArrayList<>();
        public void record(String line) { lines.add(line); }
    }

    static int failures = 0;

    static void check(String name, Object expected, Object actual) {
        boolean ok = expected.equals(actual);
        System.out.println((ok ? "PASS " : "FAIL ") + name
                + (ok ? "" : " expected=" + expected + " actual=" + actual));
        if (!ok) failures++;
    }

    public static void main(String[] args) {
        InMemoryReceiptLog log = new InMemoryReceiptLog();
        ParkingService service = new ParkingService(log, new TextReceiptFormatter());

        Vehicle car   = new Vehicle("12345-A-6", "CAR", new CarTariff());
        Vehicle bike  = new Vehicle("67890-B-1", "MOTORBIKE", new MotorbikeTariff());
        Vehicle truck = new Vehicle("24680-D-8", "TRUCK", new TruckTariff());

        check("car fee", 15.0, service.fee(car, 3));
        check("bike fee", 4.0, service.fee(bike, 2));
        check("truck fee", 60.0, service.fee(truck, 4));

        service.checkout(car, 3, new CardPayment("4242"));
        service.checkout(bike, 2, new CashPayment());
        service.checkout(truck, 4, new CardPayment("4242"));

        String expected = "=== Daily report ===\n"
                + "12345-A-6 | CAR | 3 h | 15.0 MAD | CARD\n"
                + "67890-B-1 | MOTORBIKE | 2 h | 4.0 MAD | CASH\n"
                + "24680-D-8 | TRUCK | 4 h | 60.0 MAD | CARD\n"
                + "Total: 79.0 MAD";
        check("daily report", expected, service.dailyReport());
        check("receipts logged", 3, log.lines.size());

        if (failures > 0) System.exit(1);
    }
}