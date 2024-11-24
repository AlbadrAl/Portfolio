package Formatter;

public class BaseFormatter implements NumberFormatter{

    @Override
    public String format(int n) {
        return String.valueOf(Integer.toBinaryString(n)); // Java documentation 
    }
}
