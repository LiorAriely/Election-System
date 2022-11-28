package Model;

import java.io.Serializable;

public class IdException extends Exception implements Serializable {

	public IdException(String msg) {
		super(msg);
	}

	public IdException() {
		super("wrong");
	}

}