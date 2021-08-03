package org.open.ticket.ato.um;

import java.time.Instant;

import org.open.ticket.EventType;
import org.open.ticket.Ticket;

import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;

/**
 * Preenchendo os valores do stub.
 * @author lucas
 *
 */
public class TicketRun {
	public static void main(String... strings) {
		var when = Instant.now();
		var price = 500.0d;
		var ticket = Ticket.newBuilder().setDescription("Filme do Pelé").setEventType(EventType.LIVE_ACTION)
				.setWhen(Timestamp.newBuilder().setSeconds(when.getEpochSecond()).setNanos(when.getNano()).build())
				.setNumber(Int64Value.newBuilder().setValue(when.toEpochMilli()).build()).setPrice(price).build();

		
		System.out.printf("\nTicket do pelé: %s", ticket);

	}
}
