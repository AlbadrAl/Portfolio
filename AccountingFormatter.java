package Formatter;

public class AccountingFormatter implements NumberFormatter{
	// 2.10 book 
    @Override
    public String format(int n) {
        return String.valueOf("("+Math.abs(n)+")"); 
    }
}
