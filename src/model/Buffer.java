package model;

import protocol.Packet;

public class Buffer {

    private byte[] data = new byte[10000];
    private ReadOperation readHead = new ReadOperation(data.length);
    private WriteOperation writeHead = new WriteOperation(data.length);

    public Buffer() {
        /*TODO: Temporary - to simulate initial set of data in node*/
        readHead.increment();
        writeHead.increment();
    }

    public Packet read(int bytes) {
        if (readHead.readOperationHasCommenced()) {
            byte[] result = new byte[bytes];
            int remainingDataToReadInBuffer = new RemainingBuffer(readHead.getIndex(), writeHead.getIndex(), data.length).bytesToRead();
            int totalDataThatCouldBeRead = (bytes > remainingDataToReadInBuffer) ? remainingDataToReadInBuffer : bytes;
            for (int i = 0; i < totalDataThatCouldBeRead; i++, readHead.increment()) {
                result[i] = data[readHead.getIndex()];
            }
            return new Packet(result, new byte[0]);
        } else {
            throw new RuntimeException("No data written yet");
        }
    }

    public void append(Packet packet) {
        readHead.canRead();
        if (!possibleDataOverwrite(packet.size())) {
            for (byte aByte : packet.data()) {
                data[writeHead.getIndex()] = aByte;
                writeHead.increment();
            }
        } else {
            throw new RuntimeException("Buffer overflow");
        }

    }

    private boolean possibleDataOverwrite(int length) {
        return readHead.readOperationHasCommenced() && (length > new RemainingBuffer(readHead.getIndex(), writeHead.getIndex(), data.length).bytesToWrite());
    }

    private static class ReadOperation {

        private int index = -1;
        private int maximum;

        private ReadOperation(int maximum) {
            this.maximum = maximum;
        }

        public void canRead() {
            if (-1 == index) {
                index = 0;
            }
        }

        public int getIndex() {
            return index;
        }

        public void increment() {
            index = (index + 1) % maximum;
        }

        public boolean readOperationHasCommenced() {
            return index != -1;
        }
    }

    private static class WriteOperation {

        private int index = 0;
        private int maximum;

        private WriteOperation(int maximum) {
            this.maximum = maximum;
        }

        public int getIndex() {
            return index;
        }

        public void increment() {
            index = (index + 1) % maximum;
        }

    }

    private static class RemainingBuffer {
        private int readHead;
        private int writeHead;
        private int maximum;
        private boolean readHeadLessOrEqualsThanWriteHead;

        private RemainingBuffer(int readHead, int writeHead, int maximum) {
            this.readHead = readHead;
            this.writeHead = writeHead;
            this.maximum = maximum;
            this.readHeadLessOrEqualsThanWriteHead = readHead <= writeHead;
        }

        public int bytesToRead() {
            if (readHeadLessOrEqualsThanWriteHead) {
                return writeHead - readHead;
            } else {
                return ((maximum + 1) - readHead) + writeHead;
            }
        }

        public int bytesToWrite() {
            if (readHeadLessOrEqualsThanWriteHead) {
                return ((maximum + 1) - writeHead) + readHead;
            } else {
                return (readHead - writeHead) + 1;
            }
        }
    }
}
