package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class DisplayUIController {
	@FXML
	private Button generateTreeBtn;
	@FXML
	private Button preorderBtn;
	@FXML
	private Button inorderBtn;
	@FXML
	private Button postorderBtn;
	@FXML
	private AnchorPane displayPane;
	private Circle[] circles;
	private Line[] lines;
	private int[] left;
	private int[] right;
	private int num;
	private int time;

	@FXML
	protected void generateTree(ActionEvent event) throws FileNotFoundException {
		Scanner in = new Scanner(
				new FileReader(
						"E:\\workspace\\BinaryTreeScanDisplay\\bin\\application\\input.txt"));
		num = in.nextInt();
		lines = new Line[num + 1];
		circles = new Circle[num + 1];
		left = new int[num + 1];
		right = new int[num + 1];

		for (int i = 1; i <= num; i++) {
			in.nextInt();
			left[i] = in.nextInt();
			right[i] = in.nextInt();
			circles[i] = new Circle();
			lines[i] = new Line();
			circles[i].setRadius(10);
			circles[i].setFill(Color.BLUE);
		}

		// set circle position
		circles[1].setLayoutX(200);
		circles[1].setLayoutY(60);
		setPosition(1, 0);
		for (int i = 2; i <= num; i++) {
			displayPane.getChildren().add(lines[i]);
		}
		for (int i = 1; i <= num; i++) {
			displayPane.getChildren().add(circles[i]);
		}
	}

	protected void setColor(int i) {
		circles[i].setFill(Color.RED);
	}

	private void setPosition(int i, int h) {
		if (left[i] != 0) {
			circles[left[i]]
					.setLayoutX(circles[i].getLayoutX() - (60 - h * 20));
			circles[left[i]].setLayoutY(circles[i].getLayoutY() + 70);
			lines[left[i]].setStartX(circles[i].getLayoutX());
			lines[left[i]].setStartY(circles[i].getLayoutY());
			lines[left[i]].setEndX(circles[i].getLayoutX() - (60 - h * 20));
			lines[left[i]].setEndY(circles[i].getLayoutY() + 70);
			setPosition(left[i], h + 1);
		}
		if (right[i] != 0) {
			circles[right[i]].setLayoutX(circles[i].getLayoutX()
					+ (60 - h * 20));
			circles[right[i]].setLayoutY(circles[i].getLayoutY() + 70);
			lines[right[i]].setStartX(circles[i].getLayoutX());
			lines[right[i]].setStartY(circles[i].getLayoutY());
			lines[right[i]].setEndX(circles[i].getLayoutX() + (60 - h * 20));
			lines[right[i]].setEndY(circles[i].getLayoutY() + 70);
			setPosition(right[i], h + 1);
		}
	}

	@FXML
	protected void preorder(ActionEvent event) {
		for (int i = 1; i <= num; i++) {
			circles[i].setFill(Color.BLUE);
		}
		time = 1;
		preorderDFS(1);
	}

	private void preorderDFS(int i) {
		new TimingThread(time++, i, this);
		if (left[i] != 0)
			preorderDFS(left[i]);
		if (right[i] != 0)
			preorderDFS(right[i]);

	}

	@FXML
	protected void inorder(ActionEvent event) {
		for (int i = 1; i <= num; i++) {
			circles[i].setFill(Color.BLUE);
		}
		time = 1;
		inorderDFS(1);
	}

	private void inorderDFS(int i) {

		if (left[i] != 0)
			inorderDFS(left[i]);
		new TimingThread(time++, i, this);
		if (right[i] != 0)
			inorderDFS(right[i]);
	}

	@FXML
	protected void postorder(ActionEvent event) {
		for (int i = 1; i <= num; i++) {
			circles[i].setFill(Color.BLUE);
		}
		time = 1;
		postorderDFS(1);
	}

	private void postorderDFS(int i) {
		if (left[i] != 0)
			postorderDFS(left[i]);
		if (right[i] != 0)
			postorderDFS(right[i]);
		new TimingThread(time++, i, this);

	}

	@FXML
	protected void levelorder(ActionEvent event) {
		for (int i = 1; i <= num; i++) {
			circles[i].setFill(Color.BLUE);
		}
		time = 1;

		int head = 0;
		int tail = 1;
		int[] que = new int[num + 1];
		que[0] = 1;
		while (head < tail) {
			new TimingThread(time++, que[head], this);
			if (left[que[head]] != 0)
				que[tail++] = left[que[head]];
			if (right[que[head]] != 0)
				que[tail++] = right[que[head]];
			head++;
		}
	}
}
