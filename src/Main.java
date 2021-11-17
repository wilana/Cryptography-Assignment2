import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Assignment 1    COMP 4476
 *  Author:         Wilana Matthews
 *  Last Modified:  Oct. 11, 2021
 */

public class Main {
    // let the alphabet and bitstrings be accessible within Main class
    private static final ArrayList<Character> alphabetChars = new ArrayList<>();
    private static final ArrayList<String> alphabetBits = new ArrayList<>();
    private static final int[][] s1 = {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
    private static final int[][] s2 = {
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

    public static void main(String[] args) {
        // fill the alphabet array list
        alphabetChars.addAll(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '.', ',', '?', '(', ')'));

        // fill the bitstring array list
        int n = 5;
        String[] bitArr = new String[n];
        findBitStrings(n, bitArr, 0);

        //Intro documentation
        System.out.println("\nAssignment 2\t\tCOMP 4476");
        System.out.println("Wilana Matthews\t\t1120464");
        System.out.println("----------------------------------------------------\n\n");

        // Problem 1.1:
        System.out.print("Problem 1.1: character to 5 bitstring and back\n");
        System.out.print("----------------------------------------------------\n");
        System.out.print("Table of characters and bitstrings:\n");
        System.out.print("\n\tCharacter\t\t  Bitstring\n");

        for (int i = 0; i < alphabetChars.size(); i++)
        {
            System.out.printf("\t\t%s\t\t\t\t%s\n", alphabetChars.get(i), alphabetBits.get(i));
        }

        System.out.println("->\tThere are " + alphabetBits.size() + " bitstrings and " + alphabetChars.size() + " characters.\n");

        System.out.printf("The character %s is equivalent to %s.\n", "A", getBitStringFromCharacter("A"));
        System.out.printf("The character %s is equivalent to %s.\n", "A to Z", getBitStringFromCharacter("A to Z"));
        System.out.printf("The character %s is equivalent to %s.\n", "(?)", getBitStringFromCharacter("(?)"));
        System.out.printf("The character %s is equivalent to %s.\n", "Wilana", getBitStringFromCharacter("Wilana"));

        System.out.printf("\nThe bitstring %s is equivalent to %s.\n", "00000", getCharacterFromBitString("00000"));
        System.out.printf("The bitstring %s is equivalent to %s.\n", "000000000100010", getCharacterFromBitString("000000000100010"));
        System.out.printf("The bitstring %s is equivalent to %s.\n", "000100000010011", getCharacterFromBitString("000100000010011"));
        System.out.printf("The bitstring %s is equivalent to %s.\n", "111101110111111", getCharacterFromBitString("111101110111111"));


        // Problem 1.2:
        System.out.print("\n\n----------------------------------------------------\n");
        System.out.print("Problem 1.2: implementing function f\n");
        System.out.print("----------------------------------------------------\n");

        executeF("10110101", "101101010010");

        // Problem 1.3:
        System.out.print("----------------------------------------------------\n");
        System.out.print("Problem 1.3: implementing encryption system\n");
        System.out.print("----------------------------------------------------\n");


        // Problem 1.4:
        System.out.print("\n\n----------------------------------------------------\n");
        System.out.print("Problem 1.4: Use 1.1 to 1.3 to compute ciphertext\n");
        System.out.print("----------------------------------------------------\n");
        String plainText = "how do you like computer science";
        String roundKey = "101101010010100101101011";
        System.out.printf("PlainText:\t\t\t\t%s\n", plainText);
        System.out.printf("RoundKey: \t\t\t\t%s\n", roundKey);
        String cipherText = encryptMDES(plainText, roundKey);
        System.out.printf("CipherText: \t\t\t%s\n", cipherText);


        System.out.print("\n\n----------------------------------------------------\n");
        System.out.print("Problem 2: Decrypt cipher from 1.4\n");
        System.out.print("----------------------------------------------------\n");
        System.out.printf("CipherText: \t\t\t%s\n", cipherText);
        String computedPlainText = decryptMDES(cipherText, roundKey);
        System.out.printf("Decrypted Text:\t\t\t%s\n", computedPlainText);



        System.out.print("\n\n----------------------------------------------------\n");
        System.out.print("Problem 3: Make MDES into CBC Mode\n");
        System.out.print("----------------------------------------------------\n");
        plainText = "cryptography is an important tool for network security. but there are other issues for network security.";
        roundKey = "101101010010100101101011";
        System.out.printf("PlainText:\t\t\t\t%s\n", plainText);
        System.out.printf("RoundKey: \t\t\t\t%s\n", roundKey);
        cipherText = encryptCBC(plainText, roundKey, "1010101001010101");
        System.out.printf("CipherText: \t\t\t%s\n", cipherText);

        System.out.print("\n\n----------------------------------------------------\n");
        System.out.print("Problem 4: Miller-Rabin Primality test\n");
        System.out.print("----------------------------------------------------\n");
        BigInteger largePrime = BigInteger.valueOf(223);
        System.out.println("Large prime:\t " + largePrime + "");
        System.out.println(" ->\t" + testPrimality(largePrime));

        largePrime = BigInteger.valueOf(6337);
        System.out.println("\nLarge prime:\t " + largePrime + "");
        System.out.println(" ->\t" + testPrimality(largePrime));

        largePrime = BigInteger.valueOf(7573);
        System.out.println("\nLarge prime:\t " + largePrime + "");
        System.out.println(" ->\t" + testPrimality(largePrime));
    }

    // Problem 1.1
    /**
     * Changes string from english characters to bitstring
     * @param englishText string of english characters A-Z as well as space, period, comma, question mark, open and close parentheses
     * @return the bitstring equivalent
     */
    public static String getBitStringFromCharacter(String englishText) {
        // make sure plaintext is in all uppercase to match with alphabet
        englishText = englishText.toUpperCase();

        // save the plaintext as individual characters
        char[] charArr = englishText.toCharArray();
        StringBuilder bitString = new StringBuilder(); // to save each bitstring
        int index;

        // add each bit to one large bitstring
        for (Character c : charArr) {
            index = alphabetChars.indexOf(c);
            bitString.append(alphabetBits.get(index));
        }

        return bitString.toString();
    }

    /**
     * gets the english character equivalent of a bitstring
     * @param bitString the bitstring to find the character equivalent of
     * @return the english character equivalent
     */
    public static String getCharacterFromBitString (String bitString) {
        StringBuilder englishText = new StringBuilder();
        int index;

        for (int i = 0; i < bitString.length(); i = i + 5) {
            index = alphabetBits.indexOf(bitString.substring(i, i+5));
            englishText.append(alphabetChars.get(index));
        }

        return englishText.toString();
    }

    // Problem 1.2
    /**
     * function f
     * @param b plaintext bitstring length 8
     * @param k round key bitstring length 12
     * @return 8 bitstring
     */
    public static String executeF (String b, String k) {
        // expand b to 12:
        char[] charArrB = b.toCharArray();
        int[] expansionKey = {1, 2, 3, 4, 3, 4, 5, 6, 5, 6, 7, 8};
        int index;
        StringBuilder bBuilder = new StringBuilder();
        for (int j : expansionKey) {
            index = j - 1;
            bBuilder.append(charArrB[index]);
        }
        b = bBuilder.toString();

        // XOR b and k
        String xor = getXor(b, k);

        // split xor result into b1 and b2, each with 6 bits
        char[] b1 = (xor.substring(0,6)).toCharArray();
        char[] b2 = (xor.substring(6,12)).toCharArray();

        // find rows and columns of s-box 1
        String r = "" + b1[0] + b1[5];
        int row = Integer.parseInt(r, 2);
        String c = "" + b1[1] + b1[2] + b1[3] + b1[4];
        int column = Integer.parseInt(c, 2);
        // find output for s1
        String s1output = Integer.toBinaryString(s1[row][column]);
        s1output = addBinaryPadding(s1output.length(), 4) + s1output;


        // find rows and columns of s-box 2
        r = "" + b2[0] + b2[5];
        row = Integer.parseInt(r, 2);
        c = "" + b2[1] + b2[2] + b2[3] + b2[4];
        column = Integer.parseInt(c, 2);
        // find output for s2
        String s2output = Integer.toBinaryString(s2[row][column]);
        s2output = addBinaryPadding(s2output.length(), 4) + s2output;

        // return the concatenation of the two s-box outputs
        return String.format("%s%s", s1output, s2output);
    }


    // Problem 1.3
    public static String encryptMDES (String plaintext, String roundKey) {
        // Step 1: translate English text to binary strings
        String plaintextBitString = getBitStringFromCharacter(plaintext);

        // padding
        String padding = addBinaryPadding(plaintextBitString.length(), 16);
        plaintextBitString = padding + plaintextBitString;

        // Step 2: divide plaintext into blocks of 16
        int numBlocks = plaintextBitString.length() / 16;
        String[] lBlocks = new String[numBlocks];
        String[] rBlocks = new String[numBlocks];

        // each block is divided into l and r
        for (int i = 0; i < numBlocks; i++) {
            lBlocks[i] = plaintextBitString.substring(16 * i, 16*(i+1)-8);      // first 8 bits
            rBlocks[i] = plaintextBitString.substring(16*(i+1)-8, 16*(i+1));    // last 8 bits
        }

        // Step 3: work on two iterations for the split key
        String[] key = {roundKey.substring(0,12), roundKey.substring(12)};  // split key

        // two rounds, one for each key
        for (int k = 0; k < 2; k++) {
            // work through each block
            for (int i = 1; i < numBlocks; i++) {
                lBlocks[i] = rBlocks[i-1];
                rBlocks[i] = getXor(lBlocks[i-1], executeF(rBlocks[i-1], key[k]));
            }
        }

        // gather all blocks back into one bitstring
        StringBuilder ciphertextBitString = new StringBuilder();
        for(int i = 0; i < numBlocks; i++) {
            ciphertextBitString.append(lBlocks[i]).append(rBlocks[i]);
        }

        ciphertextBitString.replace(0, padding.length(), "");
        return (getCharacterFromBitString(ciphertextBitString.toString()));
    }


    // Problem 2
    /**
     * Decrypt by reversing key and using the encryption algorithm again
     * @param ciphertext text to be decrypted in English characters
     * @param roundKey 24 bit key
     * @return the decrypted text
     */
    public static String decryptMDES(String ciphertext, String roundKey) {
        String reversedKey = "" + roundKey.substring(12) + roundKey.substring(0,12);
        System.out.println("Reverse Key: \t\t\t" + reversedKey);
        return encryptMDES(ciphertext, reversedKey);
    }


    // Problem 3:
    /**
     * Uses same encryption as MDES, but in CBC mode where the plaintext block is XOR with previous encrypted block
     * @param plaintext English plaintext
     * @param roundKey 24 bitstring
     * @param iv 16 bitstring initialization vector
     * @return Ciphertext as English characters
     */
    public static String encryptCBC (String plaintext, String roundKey, String iv) {
        // Step 1: translate English text to binary strings
        String plaintextBitString = getBitStringFromCharacter(plaintext);

        // padding
        String padding = addBinaryPadding(plaintextBitString.length(), 16);
        plaintextBitString = padding + plaintextBitString;


        // Initialized Vector:
        String lIV = iv.substring(0,8);
        String rIV = iv.substring(8);


        // Step 2: divide plaintext into blocks of 16
        int numBlocks = plaintextBitString.length() / 16;
        String[] lBlocks = new String[numBlocks];
        String[] rBlocks = new String[numBlocks];

        // each block is divided into l and r
        for (int i = 0; i < numBlocks; i++) {
            lBlocks[i] = plaintextBitString.substring(16 * i, 16*(i+1)-8);      // first 8 bits
            rBlocks[i] = plaintextBitString.substring(16*(i+1)-8, 16*(i+1));    // last 8 bits
        }

        // Step 3: work on two iterations for the split key
        String[] key = {roundKey.substring(0,12), roundKey.substring(12)};  // split key

        // two rounds, one for each key
        for (int k = 0; k < 2; k++) {
            // work through each block

            for (int i = 1; i < numBlocks; i++) {
                // xor current plaintext with previous cipher
                if (i == 1) {
                    lBlocks[i] = getXor(lBlocks[i], lIV);
                    rBlocks[i] = getXor(rBlocks[i], rIV);
                }
                else {
                    lBlocks[i] = getXor(lBlocks[i], lBlocks[i-1]);
                    rBlocks[i] = getXor(rBlocks[i], rBlocks[i-1]);
                }

                // encrypt the xor'ed block
                lBlocks[i] = rBlocks[i-1];
                rBlocks[i] = getXor(lBlocks[i-1], executeF(rBlocks[i-1], key[k]));
            }
        }

        // gather all blocks back into one bitstring
        StringBuilder ciphertextBitString = new StringBuilder();
        for(int i = 0; i < numBlocks; i++) {
            ciphertextBitString.append(lBlocks[i]).append(rBlocks[i]);
        }

        ciphertextBitString.replace(0, padding.length(), "");
        return (getCharacterFromBitString(ciphertextBitString.toString()));
    }


    // Problem 4
    /**
     * Uses Miller-Rabin primality test with no more than 1/256 error probability
     * @param n a large prime number to test
     * @return a String stating whether or not n is prime
     */
    public static String testPrimality (BigInteger n) {
        // Define big integers to work with more easily
        BigInteger one = BigInteger.valueOf(1);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger negativeOne = BigInteger.valueOf(-1);
        BigInteger nMinus1 = n.subtract(one);
        BigInteger m = one;
        BigInteger b;
        BigInteger a;
        int random;

        // Find an m such that n-1 = 2^k * m and m is odd
        int k = 1;              // n-1 should be even, so it will be divisible by at least 2 to the 1
        boolean mFound = false; // to control the while loop, keeping it going until odd m is found

        while (!mFound) {
            // m is (n-1)/2 ^ k
            m = nMinus1.divide(two.pow(k));

            // Check if m is odd
            if (m.remainder(two).equals(one))
                // m is odd, so it has been found
                mFound = true;
            else
                // m is even, (n-1) can be divided by 2 to the power of a higher k
                k++;
        }

//        System.out.printf("Note: Big Integer's primality test suggests that n is%s prime\n\n", n.isProbablePrime((int) 0.00391/256)?"":" not");
//        System.out.printf("n - 1 = (2 to the power of k) * (m) \n-> n = %d\t k = %d\t m = %d\n", n, k, m);;

        // for a 1/256 chance, must be repeated 4 times (1/4)^4 = 1/256
        for (int t = 1; t <= 4; t++) {

            // find a random a between 1 and n-1
            random = (int) (Math.random() * (nMinus1.intValue()) + 1);
            a = BigInteger.valueOf(random);

            // b = (a to m) mod n
            b = a.modPow(m, n);
//            System.out.println("b: " + b);

            // if b is congruent to 1 mod n
            if (b.equals(one.mod(n)))
                // n is prime
                return "The number " + n + " is prime.";

            // only change b value k times
            for (int i = 0; i < k; i++) {
                if (b.equals(negativeOne.mod(n)))
                    // n is prime
                    return "The number " + n + " is prime.";
                else
                    // update b value
                    b = b.modPow(two, n);
            }
        }
        // not able to find values that prove n is prime
        return "The number " + n + " is not a prime";
    }


    // Helpful extractions
    /**
     * Gathers a bitstring from all indices of the array of size n to add to alphabetBits list
     * @param arr an array filled with bits to create a bitstring from
     * @param n the bitstring length
     */
    private static void addBitString(String[] arr, int n) {
        // start with empty bitstring
        StringBuilder nextBitString = new StringBuilder();

        // add each index to the end of the bitstring
        for (int i = 0; i < n; i++)
            nextBitString.append(arr[i]);

        // add the final bitstring to the list
        alphabetBits.add(nextBitString.toString());
    }

    /**
     * find all possible bitstrings of length n to send to addBitString
     * @param n the length of the bitstring
     * @param bitArr where to store the bits of each bitstring
     * @param i iterator
     */
    static void findBitStrings(int n, String[] bitArr, int i) {
        // at the proper length
        if (i == n) {
            // add bitstring to the list
            addBitString(bitArr, n);
            // return back to last running cycle
            return;
        }

        // start with bit at index i being 0
        bitArr[i] = "0";
        // work through the next index (i + 1)
        findBitStrings(n, bitArr, i + 1);

        // return from using 0 at the index i, run again with bit being 1
        bitArr[i] = "1";
        // find all possibilities for the bits following this index
        findBitStrings(n, bitArr, i + 1);
    }

    /**
     * XOR two bitstrings of equal size
     * @param bitString1 the first bitstring to XOR
     * @param bitString2 the second bitstring to XOR
     * @return the XORed string of equal length as both strings
     */
    private static String getXor(String bitString1, String bitString2) {
        // XOR expanded bitString1 with round key bitString2
        String xor = "";
        for (int i = 0; i< bitString1.length(); i++) {
            // characters are equivalent, add 0
            if (bitString1.charAt(i) == bitString2.charAt(i))
                xor = xor.concat("0");
                // characters aren't equivalent, add 1
            else
                xor = xor.concat("1");
        }

        return xor;
    }

    /**
     * Adds 0 to the beginning of a binary string to pad to proper length
     * @param outputLength the length of the binary string to pad
     * @return the padding to add to the front of output
     */
    private static String addBinaryPadding(int outputLength, int n) {
        // how many 0s to pad with
        int difference = (outputLength)% n;

        // empty string so if difference = 0, there will be no padding added
        String padding = "";

        // add padding if there is a difference
        if (difference > 0)
            for (int i = 0; i < n - difference; i++)
                padding = padding.concat("0");
        return padding;
    }


}
