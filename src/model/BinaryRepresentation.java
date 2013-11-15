package model;

import static java.util.Arrays.copyOfRange;

public class BinaryRepresentation {

    private int current;

    public void add(byte[] anotherArray) {
        current += byteArrayToInt(anotherArray);
    }

    public void add(int value) {
        add(intToByteArray(value));
    }

    public byte[] value() {
        while (!containedInSixteenBits(current)) {
            current = lowerWord() + higherWord();
        }
        return intToByteArray(current);
    }

    private int higherWord() {
        return byteArrayToInt(copyOfRange(intToByteArray(current), 2, 4));
    }

    private int lowerWord() {
        return byteArrayToInt(copyOfRange(intToByteArray(current), 0, 2));
    }

    private int byteArrayToInt(byte[] anotherArray) {
        int result = 0;
        for (byte aByte : anotherArray) {
            result += aByte;
        }
        return result;
    }

    private byte[] intToByteArray(int value) {
        byte[] result = new byte[4];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (value & 0xFF);
            value >>>= 16;
        }
        return result;
    }

    private boolean containedInSixteenBits(int current) {
        return (current & 0xFFFFFF00) == 0;
    }
}