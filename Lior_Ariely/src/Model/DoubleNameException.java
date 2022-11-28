package Model;

import java.io.Serializable;

public class DoubleNameException extends Exception implements Serializable {
	public DoubleNameException(String msg) {
		super(msg);
	}

	public DoubleNameException() {
		super("There has been an error");
	}
}
