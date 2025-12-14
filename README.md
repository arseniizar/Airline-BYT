# Airline Management System (BYT)

---

## Setup

*   **JDK:** Amazon Corretto JDK 24
*   **Build Tool:** Gradle
*   **Virtual Environment:** python venv 
*   **Branch Naming Convention:** `YOUR_NAME/dev`
    *   *Example:* `arsenii/dev`

---

## Implemented Class Associations

### 1. Basic Association (1..*)

*   **Classes:** `Passenger` <-> `Booking`
*   **Description:** A `Passenger` can have multiple `Booking` objects, while each `Booking` must belong to exactly one `Passenger`. The relationship is managed through methods that synchronize the link on both sides.

### 2. Composition

*   **Classes:** `Booking` <-> `Ticket`
*   **Description:** A `Ticket` is an integral part of a `Booking`. It cannot exist independently and is created exclusively through a `Booking` instance's `createTicket()` method. Deleting the `Booking` implies the removal of its associated `Ticket` objects.

### 3. Aggregation

*   **Classes:** `Flight` <-> `CrewMember`
*   **Description:** A `Flight` is associated with a team of `CrewMember` objects. Both `Flight` and `CrewMember` entities can exist independently of one another.

### 4. Reflexive Association

*   **Classes:** `Flight` <-> `Flight`
*   **Description:** A `Flight` can be linked to another `Flight` as a `previousLeg` or `nextLeg`, creating a chain of connecting flights. The connection is managed bi-directionally.

### 5. Qualified Association

*   **Classes:** `Airport` <-> `Flight`
*   **Description:** An `Airport` can efficiently look up a specific departing `Flight` using its unique `flightNumber` as a qualifier. This is implemented using a `Map<String, Flight>` within the `Airport` class.

### 6. Association with Attribute

*   **Classes:** `Flight` <-> `Pilot`
*   **Description:** The relationship between a `Flight` and a `Pilot` includes an attribute: the `role` (e.g., "Captain", "First Officer"). This is implemented using a `Map<Pilot, String>` within the `Flight` class, linking each assigned pilot to their specific role for that flight.
