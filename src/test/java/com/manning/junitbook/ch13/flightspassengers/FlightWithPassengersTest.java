/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.manning.junitbook.ch13.flightspassengers;

import com.manning.junitbook.ch13.flights.Flight;
import com.manning.junitbook.ch13.passengers.Passenger;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

// integration test between the 'Passenger' and 'Flight' classes
public class FlightWithPassengersTest {

    private Flight flight = new Flight("AA123", 1);                    // create a flight with a correct flight number and one seat

    // TEST 1:
    @Test
    public void testPassengerJoinsFlight() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");    // creates a passenger
        passenger.joinFlight(flight);                                                                       // makes the passenger join the flight
        assertEquals(flight, passenger.getFlight());                                    // checks that the passenger has the previously defined flight assigned
        assertEquals(1, flight.getNumberOfPassengers());                        // checks that the number of passengers from the flight is now one
    }

    // TEST 2: creates a passenger to be added to and removed from e the flight.
    @Test
    public void testAddRemovePassengers() throws IOException {
        Passenger passenger = new Passenger("124-56-7890", "Michael Johnson", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());                        // We check if the operation is successful and the correct number of passengers are on the flight.
        assertEquals(flight, passenger.getFlight());                                    // After the flight has added a passenger, check whether the flight has been set on the passenger's.

        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());                        // We check if the operation is successful and the correct number of passengers are on the flight.
        assertEquals(null, passenger.getFlight());
    }

    // TEST 3: creates a passenger to be added to a flight, followed by another passenger that will exceed the number of seats on the flight.
    @Test
    public void testNumberOfSeats() {
        Passenger passenger1 = new Passenger("124-56-7890", "Michael Johnson", "US");
        flight.addPassenger(passenger1);
        assertEquals(1, flight.getNumberOfPassengers());                                                // We first check the correct number of passengers.

        Passenger passenger2 = new Passenger("127-23-7991", "John Smith", "GB");
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));                            // Then we check for the fact that an exception is thrown when the number of passengers exceeds the number of seats.

    }
}
