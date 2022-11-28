package Model;

import java.io.Serializable;

public interface Synchronizable extends Serializable {
	boolean isSync();

	void setSync(boolean s);
}
