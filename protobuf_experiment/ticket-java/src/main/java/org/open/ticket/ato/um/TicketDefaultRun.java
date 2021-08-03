package org.open.ticket.ato.um;

import org.open.ticket.Ticket;

/**
 * Visualizando os valores default's
 * @author lucas
 */
public class TicketDefaultRun {
	public static void main(String... strings) {
		var ticketDefault = Ticket.newBuilder().build();

		System.out.println("Quando é nulo ? \n" + ticketDefault.getWhen() == null);
		System.out.println("Número é nulo ? \n" + ticketDefault.getNumber() == null);
		System.out.println("Ticket default:" + ticketDefault);
		System.out.println("Ticket descrição:" + ticketDefault.getDescription());
		System.out.println("Ticket tipo:" + ticketDefault.getEventType());
		System.out.println("Ticket local:" + ticketDefault.getWhere());
		System.out
				.println("Ticket quando:" + ticketDefault.getWhen().getNanos() + ticketDefault.getWhen().getSeconds());
		System.out.println("Ticket preço:" + ticketDefault.getPrice());
		System.out.println("Ticket número:" + ticketDefault.getNumber());
	}
}
