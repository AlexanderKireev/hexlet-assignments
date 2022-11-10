package exercise;

//import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String abc;

    public ReversedSequence(String abc) {
        this.abc = abc.chars()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .reverse()
                .toString();
    }

    @Override
    public int length() {
        return abc.length();
    }

    @Override
    public char charAt(int index) {
        return abc.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return abc.substring(start, end);
    }

    public String toString() {
        return abc;
    }
}
// END
