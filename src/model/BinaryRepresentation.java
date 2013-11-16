package model;

import static java.util.Arrays.copyOfRange;

/**
 * Enables performing operations like add on a set of bytes
 */
public class BinaryRepresentation {

    /**
     * The current value represented as an integer
     */
    private int current;

    /**
     * Add a set of bytes to the current value
     *
     * @param anotherArray The list of bytes to be added to the current value
     */
    public void add(byte[] anotherArray) {
        current += byteArrayToInt(anotherArray);
    }

    /**
     * Add an integer value value to the current value
     *
     * @param value The integer value to add to the current value
     */
    public void add(int value) {
        add(intToByteArray(value));
    }

    /**
     * Adds higher word to lower word until a sixteen bit representation of the current value is attained
     *
     * @return Current value within sixteen bits
     */
    public byte[] value() {
        while (!containedInSixteenBits(current)) {
            current = lowerWord() + higherWord();
        }
        return intToByteArray(current);
    }

    /**
     * The byte array has higher words at higher indexes
     *
     * @return The higher word from the set of bytes
     */
    private int higherWord() {
        return byteArrayToInt(copyOfRange(intToByteArray(current), 2, 4));
    }

    /**
     * The byte array has lower words at lower indexes
     *
     * @return The lower word from the set of bytes
     */
    private int lowerWord() {
        return byteArrayToInt(copyOfRange(intToByteArray(current), 0, 2));
    }

    /**
     * Java implicitly casts bytes to integer whe performing arithmetic  operations. This is utilized to convert a set of bytes to integer
     *
     * @param anotherArray The set of bytes to be converted to int
     * @return The integer value
     */
    private int byteArrayToInt(byte[] anotherArray) {
        int result = 0;
        for (byte aByte : anotherArray) {
            result += aByte;
        }
        return result;
    }

    /**
     * Take 8 bits at a time and append to the byte array
     *
     * @param value The int value to be converted
     * @return Byte representation of the int value
     */
    private byte[] intToByteArray(int value) {
        byte[] result = new byte[4];
        for (int i = 0; i < result.length; i++) {
            // Extract lower 8 bits
            result[i] = (byte) (value & 0xFF);
            // Shift right and insert 0's into the higher 8 bits
            value >>>= 8;
        }
        return result;
    }

    /**
     * @param current current value
     * @return true - when all bits higher than 16 are 0
     *         false - when any bit higher than 16 is 1
     */
    private boolean containedInSixteenBits(int current) {
        return (current & 0xFFFFFF00) == 0;
    }
}