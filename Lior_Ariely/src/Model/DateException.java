package Model;

import java.io.Serializable;

public class DateException extends Exception implements Serializable {
	public DateException(String msg) {
		super(msg);
	}

	public DateException() {
		super("There has been a problem");
	}
}
