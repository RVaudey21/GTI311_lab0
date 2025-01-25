
public class CommentEncoderDecoder {

    // Méthode pour encoder un commentaire
    public static byte[] encodeComment (String comment, int delay) {

        byte[] commentbytes = comment.getBytes();


        return commentbytes;
    }

    // Méthode pour décoder le commentaire
    public static String decodeComment(byte[] encodedComment) {
        
        String comment = encodedComment.toString();

        return comment;
    }

    public static void main(String[] args) {
        String comment = "Bonjour 123";
        byte[] encodedComment = encodeComment(comment, 0);
        
        System.out.println("Message encodé (hex) :");
            for (byte b : encodedComment) {
                System.out.printf("%02x ", b);
            }
        
    }
}
