

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CommentEncoderDecoder {

    // Méthode pour encoder un commentaire
    public static byte[] encodeComment (String comment, int delay) {

        byte[] commentbytes = comment.getBytes();
        int commentLength = commentbytes.length;

        // Taille totale : 1 octet pour la longueur + taille du commentaire + 4 octets pour le délai
        ByteBuffer buffer = ByteBuffer.allocate(1 + commentLength + 4);
        buffer.order(ByteOrder.LITTLE_ENDIAN); 

        // Écriture dans le buffer
        buffer.put((byte) commentLength);       // Taille du commentaire (1 octet)
        buffer.put(commentbytes);               // Commentaire ASCII (commentLength octets)
        buffer.putInt(delay);                   // Délai (4 octets)

        return buffer.array();
    }

    // Méthode pour décoder le commentaire
    public static String decodeComment(byte[] encodedComment) {
        
        ByteBuffer buffer = ByteBuffer.wrap(encodedComment);
        buffer.order(ByteOrder.LITTLE_ENDIAN); // Little-Endian

        // Lecture de la longueur du commentaire
        int commentLength = Byte.toUnsignedInt(buffer.get()); // 1er octet
        

        // Lecture du commentaire
        byte[] commentBytes = new byte[commentLength];
        buffer.get(commentBytes);
        String comment = new String(commentBytes);

        // Lecture du délai
        int delay = buffer.getInt();
        System.out.println(delay);
        
        return comment;
    }

    public static void main(String[] args) {
        String comment = "Bonjour 123";
        byte[] encodedComment = encodeComment(comment, 42);
        
        System.out.println("Message encodé (hex) :");
            for (byte b : encodedComment) {
                System.out.printf("%02x ", b);
            }
            System.out.println();    
        String decodedComment = decodeComment(encodedComment);
        
        System.out.println("Message décodé (hex) :");
            
        System.out.println(decodedComment);
            
    }
}
