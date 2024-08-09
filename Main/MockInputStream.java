package Main;

import java.io.InputStream;

public class MockInputStream extends InputStream {
    private final String input;
    private int position;

    public MockInputStream(String input) {
        this.input = input;
        this.position = 0;
    }

    @Override
    public int read() {
        if (position >= input.length()) {
            return -1; // End of stream
        }
        int nextChar = input.charAt(position);
        position++;
        return nextChar;
    }
}