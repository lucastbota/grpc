package org.open.ticket.ato.dois;

import java.io.IOException;

import org.open.ticket.Ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Serializando e desserializando o objeto c/ tecnologias diferentes.
 * 
 * @author lucas
 *
 */
public class Performance {

	private static final String DESCRIPTION = "Filme do Peleh";
	private static final String WHERE = "Estadio de Futebol";

	public static void main(String... strings) {
		Runnable jsonRunnable = () -> writeAndReadJson();
		Runnable protobufRunnable = () -> writeAndReadProtobuf();

		System.out.printf("\nProto: %d", getTicketProtobuf().toByteArray().length);
		try {
			System.out.printf("\nJson: %d", new ObjectMapper().writeValueAsBytes(getTicketDto()).length);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		runPerformanceTest(jsonRunnable, "JSON");
		runPerformanceTest(protobufRunnable, "PROTOBUFF");

	}

	private static void writeAndReadProtobuf() {
		var ticket = getTicketProtobuf();
		byte[] bytes = ticket.toByteArray();

		try {
			Ticket.parseFrom(bytes);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}

	private static void writeAndReadJson() {
		var ticketDto = getTicketDto();
		var mapper = new ObjectMapper();
		try {
			byte[] bytes = mapper.writeValueAsBytes(ticketDto);
			mapper.readValue(bytes, TicketDTO.class);
		} catch (IOException io) {
			System.out.println(io);
		}
	}

	private static void runPerformanceTest(Runnable runnable, String method) {
		long initialTime = System.currentTimeMillis();
		int length = 1_000_000;
		for (int i = 0; i < length; i++) {
			runnable.run();
		}

		long elapsedTime = System.currentTimeMillis() - initialTime;
		System.out.printf("\nMethod: %s, ElapsedTime: %d ms", method, elapsedTime);
	}

	private static TicketDTO getTicketDto() {
		return new TicketDTO(DESCRIPTION, WHERE);
	}

	private static Ticket getTicketProtobuf() {
		return Ticket.newBuilder().setDescription(DESCRIPTION).setWhere(WHERE).build();
	}
}
