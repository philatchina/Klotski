
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class HUARONGDAO// 游戏开始结束界面
{
	public static void main(String args[]) {
		new Game();
	}
}

class Person extends Button implements FocusListener // 创建人物棋子
{
	int number;
	Color c = new Color(135, 206, 250);

	Person(int number, String s) {
		super(s);
		setBackground(c);
		this.number = number;
		c = getBackground();
		addFocusListener(this);
	}

	public void FocusGained(FocusEvent a) {
		setBackground(Color.orange);

	}

	public void FocusLost(FocusEvent a) {
		setBackground(c);
	}
}

//主游戏
class Game extends Frame implements MouseListener, KeyListener, ActionListener {
	Person person[] = new Person[10];
	Button left, right, above, below;
	Button helpsolve = new Button("help");

	public Game() {
		init();
		setBounds(100, 100, 450, 360);
		setVisible(true);
		validate();
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent a) {

				System.exit(0);

			}
		});
	}

	public void init() {
		setLayout(null);
		String name[] = { "曹操", "关羽", "张飞", "刘备", "赵云", "黄忠", "兵", "兵", "兵", "兵" };
		for (int i = 0; i < name.length; i++) {
			person[i] = new Person(i, name[i]);
			person[i].addMouseListener(this);
			person[i].addKeyListener(this);
			add(person[i]);
		}
		person[0].setBounds(104, 54, 100, 100);
		person[1].setBounds(104, 154, 100, 50);
		person[2].setBounds(54, 154, 50, 100);
		person[3].setBounds(204, 154, 50, 100);
		person[4].setBounds(54, 54, 50, 100);
		person[5].setBounds(204, 54, 50, 100);
		person[6].setBounds(54, 254, 50, 50);
		person[7].setBounds(204, 254, 50, 50);
		person[8].setBounds(104, 204, 50, 50);
		person[9].setBounds(154, 204, 50, 50);
		person[9].requestFocus();
		left = new Button();
		right = new Button();
		above = new Button();
		below = new Button();
		add(left);
		add(right);
		add(above);
		add(below);
		left.setBounds(49, 49, 5, 260);
		right.setBounds(254, 49, 5, 260);
		above.setBounds(49, 49, 210, 5);
		below.setBounds(49, 304, 210, 5);
		validate();

		add(helpsolve);
		helpsolve.setBounds(320, 54, 60, 40);
		helpsolve.addMouseListener(this);
		helpsolve.addKeyListener(this);
		helpsolve.requestFocus();

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		/*
		 * Person b = (Person)e.getSource(); if(e.getKeyCode()==KeyEvent.VK_DOWN) {
		 * go(b,below); } if(e.getKeyCode()==KeyEvent.VK_UP) { go(b,above); }
		 * if(e.getKeyCode()==KeyEvent.VK_LEFT) { go(b,left); }
		 * if(e.getKeyCode()==KeyEvent.VK_RIGHT) { go(b,right); }
		 */
	}

	public void mousePressed(MouseEvent e) {

		if (e.getSource().equals(helpsolve)) {

			help();

		} else {
			Person b = (Person) e.getSource();
			int x = -1, y = -1;
			x = e.getX();
			y = e.getY();
			int w = b.getBounds().width;
			int h = b.getBounds().height;
			if (y > h / 2) {
				go(b, below);
			}
			if (y < h / 2) {
				go(b, above);
			}
			if (x < w / 2) {
				go(b, left);
			}
			if (x > w / 2) {
				go(b, right);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void go(Person b, Button direction) {

		boolean move = true;
		Rectangle bRect = b.getBounds();
		int x = b.getBounds().x;
		int y = b.getBounds().y;
		if (direction == below) {
			y = y + 50;
		} else if (direction == above) {
			y = y - 50;
		} else if (direction == left) {
			x = x - 50;
		} else if (direction == right) {
			x = x + 50;
		}
		bRect.setLocation(x, y);
		Rectangle directionRect = direction.getBounds();
		for (int k = 0; k < 10; k++) {
			Rectangle personRect = person[k].getBounds();
			if ((bRect.intersects(personRect)) && (b.number != k)) {
				move = false;
			}
		}
		if (bRect.intersects(directionRect)) {
			move = false;
		}
		if (move == true) {
			b.setLocation(x, y);
		}

	}

	public void actionPerformed(ActionEvent e) {

		dispose();

		new Game();

	}

	public void help() {

		JFrame frame = new JFrame("通关提示");
		// 指定图片路径
		String picPath = "src\\GIF\\01.横道立马.gif";
		String picName = "01.横道立马.gif";
		// 实例化ImageIcon
		Icon icon = new ImageIcon(picPath);
		// 实例化标签对象
		JLabel lab = new JLabel(picName, icon, JLabel.CENTER);
		// 初始化字体
		Font fnt = new Font("Serief", Font.ITALIC + Font.BOLD, 16);
		// 标签设置字体
		lab.setFont(fnt);
		// 这种标签字体颜色
		lab.setForeground(Color.RED);
		// 设置标签背景颜色
		lab.setOpaque(true);
		lab.setBackground(Color.PINK);

		// 把标签放到面板中
		frame.add(lab);
		Dimension dim = new Dimension();
		dim.setSize(500, 300);
		frame.setSize(dim);
		Point point = new Point(100, 100); // 设置坐标
		frame.setLocation(point);
		frame.setVisible(true);

	}

}
