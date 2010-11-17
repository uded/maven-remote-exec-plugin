package pl.radical.mojos.data;

import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

/**
 * Class containing information on an SSH user.
 */
public class SSHUserInfo implements UserInfo, UIKeyboardInteractive {

	private String name;
	private String password = null;
	private String keyfile;
	private String passphrase = null;
	private boolean trustAllCertificates;

	/**
	 * Constructor for SSHUserInfo.
	 */
	public SSHUserInfo() {
		super();
		trustAllCertificates = false;
	}

	/**
	 * Constructor for SSHUserInfo.
	 * 
	 * @param password
	 *            the user's password
	 * @param trustAllCertificates
	 *            if true trust hosts whose identity is unknown
	 */
	public SSHUserInfo(final String password, final boolean trustAllCertificates) {
		super();
		this.password = password;
		this.trustAllCertificates = trustAllCertificates;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Gets the pass phrase of the user.
	 * 
	 * @param message
	 *            a message
	 * @return the passphrase
	 */
	public final String getPassphrase(final String message) {
		return passphrase;
	}

	/**
	 * Gets the user's password.
	 * 
	 * @return the user's password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Prompts a string.
	 * 
	 * @param str
	 *            the string
	 * @return whether the string was prompted
	 */
	public final boolean prompt(final String str) {
		return false;
	}

	/**
	 * Indicates whether a retry was done.
	 * 
	 * @return whether a retry was done
	 */
	public final boolean retry() {
		return false;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            The name to set
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * Sets the passphrase.
	 * 
	 * @param passphrase
	 *            The passphrase to set
	 */
	public final void setPassphrase(final String passphrase) {
		this.passphrase = passphrase;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            The password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Sets the trust.
	 * 
	 * @param trust
	 *            whether to trust or not.
	 */
	public final void setTrust(final boolean trust) {
		trustAllCertificates = trust;
	}

	/**
	 * @return whether to trust or not.
	 */
	public final boolean getTrust() {
		return trustAllCertificates;
	}

	/**
	 * Returns the passphrase.
	 * 
	 * @return String
	 */
	public final String getPassphrase() {
		return passphrase;
	}

	/**
	 * Returns the keyfile.
	 * 
	 * @return String
	 */
	public final String getKeyfile() {
		return keyfile;
	}

	/**
	 * Sets the keyfile.
	 * 
	 * @param keyfile
	 *            The keyfile to set
	 */
	public final void setKeyfile(final String keyfile) {
		this.keyfile = keyfile;
	}

	/**
	 * Implement the UserInfo interface.
	 * 
	 * @param message
	 *            ignored
	 * @return true always
	 */
	public final boolean promptPassphrase(final String message) {
		return true;
	}

	/**
	 * Implement the UserInfo interface.
	 * 
	 * @param passwordPrompt
	 *            ignored
	 * @return true the first time this is called, false otherwise
	 */
	public final boolean promptPassword(final String passwordPrompt) {
		return true;
	}

	/**
	 * Implement the UserInfo interface.
	 * 
	 * @param message
	 *            ignored
	 * @return the value of trustAllCertificates
	 */
	public final boolean promptYesNo(final String message) {
		return trustAllCertificates;
	}

	// why do we do nothing?
	/**
	 * Implement the UserInfo interface (noop).
	 * 
	 * @param message
	 *            ignored
	 */
	public void showMessage(final String message) {
		// log(message, Project.MSG_DEBUG);
	}

	/**
	 * Implementation of UIKeyboardInteractive#promptKeyboardInteractive.
	 * 
	 * @param destination
	 *            not used.
	 * @param name
	 *            not used.
	 * @param instruction
	 *            not used.
	 * @param prompt
	 *            the method checks if this is one in length.
	 * @param echo
	 *            the method checks if the first element is false.
	 * @return the password in an size one array if there is a password and if
	 *         the prompt and echo checks pass.
	 */
	public String[] promptKeyboardInteractive(final String destination, final String name, final String instruction,
			final String[] prompt, final boolean[] echo) {
		if (prompt.length != 1 || echo[0] || password == null) {
			return null;
		}
		final String[] response = new String[1];
		response[0] = password;
		return response;
	}

}
