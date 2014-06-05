package gwt_homework.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String inputa,String inputb,String inputc, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
