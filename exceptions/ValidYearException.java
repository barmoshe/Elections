package id314022914_id206921777.exceptions;

public class ValidYearException extends Exception {
	public ValidYearException() {
		super("year must be between 1900-2100");
	}
}
