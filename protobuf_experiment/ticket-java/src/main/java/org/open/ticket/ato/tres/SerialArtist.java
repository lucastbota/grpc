package org.open.ticket.ato.tres;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.open.ticket.Artist;
import org.open.ticket.utils.FileUtils;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Ato 3: Compatibilidade
 * 
 * @author lucas
 */
public class SerialArtist {

	public static void main(String... strings) {
		doV1();
		doV2();
		doV5();
		//Só descomente o método abaixo após alterar o contrato p/ v3
		//doV3();
		//Só descomente o método abaixo após alterar o contrato p/ v3
		//doV4();
	}

	/**
	 * Serializa o objeto com o contrato v5 e exibe-o.
	 */
	private static void doV5() {
		Path path = Paths.get("serialization", "artist-v5.ser");
		// V5
		// setContact, setEmail e setGender desapareceram!
		// Artist artist =
		// Artist.newBuilder().setName("Domina
		// Noctis").setAgentName("Edera").setCost(300.0).build();

		// FileUtils.write(path, artist.toByteArray());
		System.out.println("V5");
		readSafe(path);
	}

	/**
	 * Le o que fora serializado com o contrato modificado - campo contact virou email.
	 * 
	 * Nesse ponto, exibirá:
	 * V4
       name: "Domina Noctis"
       cost: 300.0
       agentName: "Edera"
       email: "123456789"
       gender: "F"
	 *  
	 */
	private static void doV4() {
		Path path = Paths.get("serialization", "artist-v2.ser");
		System.out.println("V4");
		readSafe(path);
	}
	
	/**
	 * Le o que fora serializado com o contrato modificado.
	 * 
	 * Nesse ponto, exibirá:
	 * 
		// V3
		// name: "Domina Noctis"
		// cost: 300.0
		// 3: {// 8: 0x61726564}
		// 4: { 6: 0x3938373635343332 }
		// 5: "F"
	 */
	private static void doV3() {
		Path path = Paths.get("serialization", "artist-v2.ser");
		System.out.println("V3");
		readSafe(path);
	}

	/**
	 * Serializa o objeto com o contrato v2 c/ o campo 'gender' e exibe-o.
	 */
	private static void doV2() {
		Path path = Paths.get("serialization", "artist-v2.ser");
		// V2

		 /*Artist artist =
		  Artist.newBuilder().setName("Domina Noctis").setAgentName("Edera").setContact
		  ("Edera-809-Italy") .setCost(300.0).setGender("F").build(); FileUtils.write(path,
		  artist.toByteArray());
		FileUtils.write(path, artist.toByteArray());*/
		System.out.println("V2");
		readSafe(path);
	}

	/**
	 * Serializa o objeto com o contrato v1 e exibe-o.
	 */
	private static void doV1() {
		Path path = Paths.get("serialization", "artist-v1.ser");
		// V1
		
		/* Artist artist =
		 Artist.newBuilder().setName("Domina Noctis").setAgentName("Edera").setContact
		 ("edera-Italy-1711") .setCost(300.0).build();
		 
		FileUtils.write(path, artist.toByteArray());*/
		System.out.println("V1");
		readSafe(path);
	}

	private static void readSafe(Path path) {
		try {
			System.out.println(Artist.parseFrom(FileUtils.readBytes(path)));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}
}