package interfaces;

import java.io.IOException;

public interface Observer {

	void update(int index, int alg)throws NumberFormatException, IOException;
	
}
