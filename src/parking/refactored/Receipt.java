package parking.refactored;

public record Receipt(String plate, String type, int hours, double amount, String method) {}