package org.open.ticket.ato.um;

import java.time.Instant;

import org.open.ticket.Buyer;
import org.open.ticket.EventManager;
import org.open.ticket.EventType;
import org.open.ticket.Ticket;

import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;

public class TicketShop {

	public static void main(String... strings) {
		var ticketPele = getTicketPele();
		var ticketSlayer = getTicketSlayer();

		// lidando com listas, usando o ".addInterests(...)"
		var roberval = Buyer.newBuilder().setAge(70).setDocument("111111111111").setName("João")
				.addInterests(EventType.LIVE_ACTION).addInterests(EventType.OPERA).build();
		var shagrath = Buyer.newBuilder().setAge(18).setDocument("222222222222").setName("Shagrath")
				.addInterests(EventType.HEAVY_METAL_CONCERT).build();

		// lidando com maps, usando o putBuyers
		var shopMap = EventManager.newBuilder().putBuyers(roberval.getDocument(), ticketPele)
				.putBuyers(shagrath.getDocument(), ticketSlayer).build();
		 
		System.out.printf("\nRoberval: %s",roberval);
		System.out.printf("\nShagrath: %s", shagrath);
		System.out.println("Documento x Bilhete");
		System.out.println(shopMap);
	}

	private static Ticket getTicketPele() {
		var when = Instant.now();
		var price = 500.0d;
		var ticket = Ticket.newBuilder().setDescription("Filme do Pelé").setEventType(EventType.LIVE_ACTION)
				.setWhere("Cinemark")
				.setWhen(Timestamp.newBuilder().setSeconds(when.getEpochSecond()).setNanos(when.getNano()).build())
				.setNumber(Int64Value.newBuilder().setValue(when.toEpochMilli()).build()).setPrice(price).build();
		return ticket;
	}

	private static Ticket getTicketSlayer() {
		var when = Instant.now();
		var price = 710.3d;
		var ticket = Ticket.newBuilder().setDescription("Show do Slayer").setEventType(EventType.HEAVY_METAL_CONCERT)
				.setWhere("Gates of hell")
				.setWhen(Timestamp.newBuilder().setSeconds(when.getEpochSecond()).setNanos(when.getNano()).build())
				.setNumber(Int64Value.newBuilder().setValue(when.toEpochMilli()).build()).setPrice(price).build();
		return ticket;
	}
}
