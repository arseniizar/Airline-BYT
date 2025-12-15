# Airline Management System (BYT)

---

## Setup

*   **JDK:** Amazon Corretto JDK 21
*   **Framework:** Spring Boot 3
*   **Build Tool:** Gradle
*   **Branch Naming Convention:** `YOUR_NAME/dev`
    *   *Example:* `arsenii/dev`

---

## Implemented Class Associations

### 1. Basic Association (1..*)

*   **Classes:** `Passenger` ↔ `Booking`
*   **Implementation:** A `Passenger` can have multiple (`*`) `Booking` objects, while each `Booking` belongs to exactly one (`1`) `Passenger`.
*   **Details:** The `Passenger` class contains a `List<Booking>`. The association is managed bidirectionally through `addBooking()` and `removeBooking()` methods in the `Passenger` class and a `setPassenger()` method in the `Booking` class, ensuring that both sides of the relationship are always synchronized.

### 2. Composition

*   **Classes:** `Aircraft` → `Seat`
*   **Implementation:** A `Seat` is an integral part of an `Aircraft`. It cannot exist independently.
*   **Details:** The `Aircraft` class contains a `Set<Seat>`. `Seat` objects are created exclusively through the `Aircraft.addSeat()` method, which requires an instance of the `Aircraft` in its constructor, enforcing the composition relationship. A `Seat` cannot be transferred from one `Aircraft` to another.

### 3. Aggregation

*   **Classes:** `Booking` <>- `Ticket`
*   **Implementation:** A `Booking` is an aggregation of one or more `Ticket` objects.
*   **Details:** The `Booking` class contains a `Set<Ticket>`. While `Ticket` objects are created in the context of a `Booking` (via `booking.createTicket()`), their lifecycle is not strictly tied to it in the same way as composition. They represent a "whole-part" relationship where parts can be conceptually separated.

### 4. Reflexive Association

*   **Classes:** `Flight` ↔ `Flight`
*   **Implementation:** A `Flight` can be linked to another `Flight` as a `previousLeg` or `nextLeg`.
*   **Details:** This is implemented as a doubly-linked list. The `Flight` class has `previousLeg` and `nextLeg` attributes. The `setNextLeg()` method ensures that the connection is bidirectional, automatically setting the `previousLeg` on the other `Flight` object and breaking any old links.

### 5. Qualified Association

*   **Classes:** `Flight` ↔ `Ticket`
*   **Implementation:** A `Flight` can efficiently look up a specific `Ticket` using its unique `ticketNumber` as a qualifier.
*   **Details:** This is implemented using a `Map<String, Ticket>` within the `Flight` class, where the key is the `ticketNumber` string. This allows for direct access to a ticket on a flight without iterating through a list.

### 6. Association with Attribute

*   **Classes:** `Airport` ↔ `Flight` (via `OriginMetadata` / `DestinationMetadata`)
*   **Implementation:** The association between a `Flight` and its origin/destination `Airport` has attributes like `actualDepartureTime` and `actualArrivalTime`.
*   **Details:** This is modeled using association classes: `OriginMetadata` and `DestinationMetadata`. The `Flight` class holds references to these metadata objects, which in turn hold a reference to the `Airport` and the specific attributes of that connection (e.g., the actual time of departure from that specific airport).
