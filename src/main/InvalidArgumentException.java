package main;

public class InvalidArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6733472392914472141L;
	private String msg;
	public InvalidArgumentException(String message){
		msg = message;
	}
	
	public String toString(){
		return "Erreur : "+this.msg;
	}

}
