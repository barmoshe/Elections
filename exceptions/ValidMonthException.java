package id314022914_id206921777.exceptions;

public class ValidMonthException extends Exception {
	public ValidMonthException() {
		super("Month must be between 1-12");
	}
}
