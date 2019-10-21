package main;

public class Report {

	public void writeCustomMessageInReport(String message) {
		writeInReport(">>> " + message);
	}

	public void writeInReport(String message) {
		System.out.println(message);
	}

	public void writeInReport(String message, String ... var) {
		writeInReport(String.format(message, var));
	}

}
