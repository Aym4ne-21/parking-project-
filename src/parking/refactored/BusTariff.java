package parking.refactored;

public class BusTariff implements Tariff {
    public double fee(int hours) { return hours * 15.0; }
}
