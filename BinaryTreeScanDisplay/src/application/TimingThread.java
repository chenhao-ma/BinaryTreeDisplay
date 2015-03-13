package application;

import javafx.application.Platform;

public class TimingThread extends Thread {
	int time;
	int cirNum;
	DisplayUIController dis;

	public TimingThread(int time, int cirNum, DisplayUIController dis) {
		this.time = time;
		this.cirNum = cirNum;
		this.dis = dis;
		start();
	}

	public void run() {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				dis.setColor(cirNum);
			}
		});
	}
}
