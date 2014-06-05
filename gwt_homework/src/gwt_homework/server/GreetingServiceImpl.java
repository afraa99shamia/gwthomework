package gwt_homework.server;

import gwt_homework.client.GreetingService;

import gwt_homework.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import gwt_homework.Task;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String inputa,String inputb,String inputc) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(inputa)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					" a isn't number");
		}
		if (!FieldVerifier.isValidName(inputb)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					" b isn't number");
		}
		if (!FieldVerifier.isValidName(inputc)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					" c isn't number");
		}

		
         
		// Escape data from the client to avoid cross-site script vulnerabilities.
		inputa = escapeHtml(inputa);
		inputb= escapeHtml(inputb);
		inputc= escapeHtml(inputc);
		 Task t=new Task();
         t.a=Double.parseDouble(inputa);
         t.b=Double.parseDouble(inputb);
         t.c= Double.parseDouble(inputc);
         Task.create(t);
		return t.res ;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
