package exercise;

import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String turned;

    public ReversedSequence(String value) {
        StringBuilder builder = new StringBuilder();
        this.turned = builder.append(value).reverse().toString();
    }

    @Override
    public String toString() {
        return this.turned;
    }

    @Override
    public int length() {
        return this.turned.length();
    }

    @Override
    public char charAt(int index) {
        return this.turned.charAt(index);
    }

    @Override
    public boolean isEmpty() {
        return this.turned.isEmpty();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.turned.subSequence(start, end);
    }

    @Override
    public IntStream chars() {
        return this.turned.chars();
    }

    @Override
    public IntStream codePoints() {
        return this.turned.codePoints();
    }
}
// END
