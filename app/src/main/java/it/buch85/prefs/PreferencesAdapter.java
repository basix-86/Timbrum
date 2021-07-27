package it.buch85.prefs;

public interface PreferencesAdapter {
	String getUsername();
	String getPassword();
	String getHost();
	
	void saveUsername(String username);
	void savePassword(String password);
	void saveHost(String host);
	
}
