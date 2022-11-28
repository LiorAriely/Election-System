package Model;

import java.io.Serializable;

public interface Changable extends Serializable {
	boolean isChangable();

	void setChangable(boolean c);

}
