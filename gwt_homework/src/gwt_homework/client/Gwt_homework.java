package gwt_homework.client;

import gwt_homework.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_homework implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox nameFielda = new TextBox();
		final TextBox nameFieldb = new TextBox();
		final TextBox nameFieldc = new TextBox();
		final Label para =new Label();
		final Label parb =new Label();
		final Label parc =new Label();
		nameFielda.setText("Adda ");
		nameFieldb.setText("Addb ");
		nameFieldc.setText("Addc ");
		final Label errorLabel = new Label();
        para.setText("Add a :");
        parb.setText("Add b :");
        parc.setText("Add c :");
		// We can add style names to widgets 
		
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("Adda").add(nameFielda);
		RootPanel.get("Addb").add(nameFieldb);
		RootPanel.get("Addc").add(nameFieldc);
		RootPanel.get("label1").add(para);
		RootPanel.get("label2").add(parb);
		RootPanel.get("label3").add(parc);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameFielda.setFocus(true);
		nameFielda.selectAll();
		nameFieldb.setFocus(true);
		nameFieldb.selectAll();
		nameFieldc.setFocus(true);
		nameFieldc.selectAll();
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Result");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>your Equation is :</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>and solution(s) :</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServera = nameFielda.getText();
				String textToServerb = nameFieldb.getText();
				String textToServerc = nameFieldc.getText();
				
				if (!FieldVerifier.isValidName(textToServera)) {
					errorLabel.setText("Please enter number for a");
					return;
				}
				if (!FieldVerifier.isValidName(textToServerb)) {
					errorLabel.setText("Please enter number for b");
					return;
				}
				if (!FieldVerifier.isValidName(textToServerc)) {
					errorLabel.setText("Please enter number for c");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				if (Double.parseDouble(textToServera) == 1)
				{ 
					textToServerLabel.setText(" X^2 + "+textToServerb+" X + "+textToServerc+" = 0");
						
				}else
				{
				textToServerLabel.setText(textToServera + " X^2 + "+textToServerb+" X + "+textToServerc+" = 0");}
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServera,textToServerb,textToServerc,
						new AsyncCallback<String>() {
						
								public void onFailure(Throwable caught) {
									// Show the RPC error message to the user
									dialogBox
											.setText("Result - Failure");
									serverResponseLabel
											.addStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(SERVER_ERROR);
									dialogBox.center();
									closeButton.setFocus(true);
								}

								public void onSuccess(String result) {
									dialogBox.setText("Result");
									serverResponseLabel
											.removeStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(result);
									dialogBox.center();
									closeButton.setFocus(true);
								}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
	
	}
}
