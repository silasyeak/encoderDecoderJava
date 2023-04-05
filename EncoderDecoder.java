package leetCode;
public class EncoderDecoder {
        
    private static final String refTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    public static String encode(String plainText, char pivot) {
        //find index of pivot char in reference table, assign it to pivotIndex
        int pivotIndex = refTable.indexOf(pivot);
        if (pivotIndex == -1) {
            throw new IllegalArgumentException("Pivot char not found in reference table.");
        }
        //create a new char array to store the shifted characters
        char[] shiftedChars = new char[refTable.length()];
        for (int i = 0; i < refTable.length(); i++) {
            /*calculates the new index for this char after shifting
            % refTable.length() takes the result of the sum and performs a modulo operation with the length of the reference table. 
            This ensures that the result is always within the bounds of the table. */
            int newIndex = (i + pivotIndex) % refTable.length();
            shiftedChars[newIndex] = refTable.charAt(i);
        }
        //assigns the new shifted chars to a shifted table string
        String shiftedTable = new String(shiftedChars);
        //initializes empty string + the pivot at the start (per question)
        String encodedText = "";
        encodedText += pivot;
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            int index = refTable.indexOf(c);
            if (index == -1) {
                encodedText += c;
            } else {
            	//using the new shifted table, take the character at the index, and add it to the encoded text
                encodedText += shiftedTable.charAt(index);
            }
        }
        return encodedText;
    }
       
    public static String decode(String encodedText, char pivot) {
        //find index of pivot char in reference table
        int pivotIndex = refTable.indexOf(pivot);
        if (pivotIndex == -1) {
            throw new IllegalArgumentException("Pivot char not found in reference table.");
        }
        //create a new char array to store the shifted characters
        char[] shiftedChars = new char[refTable.length()];
        for (int i = 0; i < refTable.length(); i++) {
            int newIndex = (i + pivotIndex) % refTable.length();
            shiftedChars[newIndex] = refTable.charAt(i);
        }
        String shiftedTable = new String(shiftedChars);
        String plainText = "";
        //you start from index 1 because you ignore the original encoding portion of it, with the encodedText += pivot
        for (int i = 1; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            int index = shiftedTable.indexOf(c);
            if (index == -1) {
                plainText += c;
            } else {
                plainText += refTable.charAt(index);
            }
        }
        return plainText;
    }
  
    public static void main(String[] args) {
        String plainText = "HELLO WORLD";
        char key = 'B';
    	System.out.println(encode(plainText, key));
    	System.out.println(decode(encode(plainText, key), key));

        
    }
}

