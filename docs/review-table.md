## Question 2: Review Table

| Principle | Class | Lines | Design Issue |
|---|---|---|---|
| SRP | ParkingGarage | 12–19, 21–31, 33–42, 44–51 | The class handles four separate different things: pricing, payment processing, receipt and report formatting, and data persistence. |
| OCP | ParkingGarage | 13–18 | Adding a vehicle type needs modifying the fee() switch statement. |
| OCP | ParkingGarage | 35–40 | Introducing another non-refundable payment method requires modifying the instanceof check in cancel(). |
| OCP | Vehicle | 5 | The vehicle type is stored as an unrestricted String,thus invalid values are detected only at runtime. |
| LSP | CashPayment | 11–13 | The refund() method throws UnsupportedOperationException, preventing CashPayment from safely fulfilling the full PaymentMethod contract. |
| LSP | ParkingGarage | 35 | The instanceof check indicates that the code cannot treat all payment methods same way. |
| ISP | PaymentMethod | 6 | The interface requires CashPayment to provide a refund() operation that it cannot support. |
| DIP | ParkingGarage | 9 | Instantiating FileLogger directly couples the high-level business class to a concrete storage implementation which doesnt respect DIP. |
| DIP | ParkingGarage | 22 | Exposing IOException in checkout() propagates a file-storage concern into the business logic and its callers, including Main. |
| DIP | ParkingGarage | 35 | Referencing the concrete CashPayment type instead of an abstraction introduces unnecessary coupling. |

Table 1: Identified SOLID violations in the original Parking Garage design.