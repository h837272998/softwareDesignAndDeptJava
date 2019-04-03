import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;

//定义画图的基本图形单元
public class MiniDrawPad extends JFrame // 主类，扩展了JFrame类，用来生成主界面
{

	private ObjectInputStream input;
	private ObjectOutputStream output; // 定义输入输出流，用来调用和保存图像文件
	private JButton choices[]; // 按钮数组，存放以下名称的功能按钮
	private String names[] = { "New", "Open", "Save", // 这三个是基本操作按钮，包括"新建"、"打开"、"保存"
			"Option",
			/* 接下来是我们的画图板上面有的基本的几个绘图单元按钮 */
			"Pencil", // 铅笔画，也就是用鼠标拖动着随意绘图
			"Line", // 绘制直线
			"Rect", // 绘制空心矩形
			"fRect", // 绘制以指定颜色填充的实心矩形
			"Oval", // 绘制空心椭圆
			"fOval", // 绘制以指定颜色填充的实心椭圆
			"Circle", // 绘制圆形
			"fCircle", // 绘制以指定颜色填充的实心圆形
			"RoundRect", // 绘制空心圆角矩形
			"frRect", // 绘制以指定颜色填充的实心圆角矩形
			"triangle", "ftriangle", "Rubber", // 橡皮擦，可用来擦去已经绘制好的图案
			"Color", // 选择颜色按钮，可用来选择需要的颜色
			"Stroke", // 选择线条粗细的按钮，输入需要的数值可以实现绘图线条粗细的变化
			"Word" // 输入文字按钮，可以在绘图板上实现文字输入
	};
	private int word_number = 17;
	private String styleNames[] = { " 宋体 ", " 隶书 ", " 华文彩云 ", " 仿宋_GB2312 ", " 华文行楷 ", " 方正舒体 ", " Times New Roman ",
			" Serif ", " Monospaced ", " SonsSerif ", " Garamond " }; // 可供选择的字体项
	// 当然这里的灵活的结构可以让读者自己随意添加系统支持的字体
	private Icon items[];

	private String tipText[] = {
			// 这里是鼠标移动到相应按钮上面上停留时给出的提示说明条
			// 读者可以参照上面的按钮定义对照着理解
			"Draw a new picture", "Open a saved picture", "Save current drawing", "select some graph", "Draw at will",
			"Draw a straight line", "Draw a rectangle", "Fill a ractangle", "Draw an oval", "Fill an oval",
			"Draw a circle", "Fill a circle", "Draw a round rectangle", "Fill a round rectangle", "Draw a triangle",
			"Draw a Filltriangle", "Erase at will", "Choose current drawing color", "Set current drawing stroke",
			"Write down what u want", "arc...." };
	JToolBar buttonPanel; // 定义按钮面板
	private JLabel statusBar; // 显示鼠标状态的提示条
	private DrawPanel drawingArea; // 画图区域
	private int width = 800, height = 550; // 定义画图区域初始大小
	GraphicsObject[] itemList = new GraphicsObject[5000]; // 用来存放基本图形的数组
	private int choiceNumber = -1; // 当前选择项目
	private Point beginPoint; // 开始移动点
	private Point endPoint; // 移动结束点
	int oR, oG, oB;
	int ct = -1;
	int ct1 = 0;
	int f = 2;
	private int currentChoice = 4; // 设置默认画图状态为随笔画
	int index = 0; // 当前已经绘制的图形数目
	private Color color = Color.black; // 当前画笔颜色
	int R, G, B; // 用来存放当前色彩值
	int f1, f2; // 用来存放当前字体风格
	String style1; // 用来存放当前字体
	private float stroke = 1.0f; // 设置画笔粗细，默认值为1.0f
	JCheckBox bold, italic; // 定义字体风格选择框
	// bold为粗体，italic为斜体，二者可以同时使用
	JComboBox styles;

	public MiniDrawPad() // 构造函数
	{

		super("Drawing Pad");
		JMenuBar bar = new JMenuBar(); // 定义菜单条
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		// 新建文件菜单条
		JMenuItem newItem = new JMenuItem("New");
		newItem.setMnemonic('N');
		newItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				newFile(); // 如果被触发，则调用新建文件函数段
			}
		});
		fileMenu.add(newItem);

		// 保存文件菜单项
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setMnemonic('S');
		saveItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				saveFile(); // 如果被触发，则调用保存文件函数段
			}
		});
		fileMenu.add(saveItem);

		// 打开文件菜单项
		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.setMnemonic('L');
		loadItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadFile(); // 如果被触发，则调用打开文件函数段
			}
		});
		fileMenu.add(loadItem);

		fileMenu.addSeparator(); //

		// 退出菜单项
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('X');
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 如果被触发，则退出画图板程序
			}
		});
		fileMenu.add(exitItem);
		bar.add(fileMenu);

		// 设置颜色菜单条
		JMenu colorMenu = new JMenu("Color");
		colorMenu.setMnemonic('C');

		// 选择颜色菜单项
		JMenuItem colorItem = new JMenuItem("Choose Color");
		colorItem.setMnemonic('O');
		colorItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				chooseColor(); // 如果被触发，则调用选择颜色函数段
			}
		});
		colorMenu.add(colorItem);
		bar.add(colorMenu);

		// 设置线条粗细菜单条
		JMenu strokeMenu = new JMenu("Stroke");
		strokeMenu.setMnemonic('S');

		// 设置线条粗细菜单项
		JMenuItem strokeItem = new JMenuItem("Set Stroke");
		strokeItem.setMnemonic('K');
		strokeItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setStroke();
			}
		});
		strokeMenu.add(strokeItem);
		bar.add(strokeMenu);

		// 设置提示菜单条
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		// 设置提示菜单项
		JMenuItem aboutItem = new JMenuItem("About this Drawing Pad!");
		aboutItem.setMnemonic('A');
		aboutItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This is a mini drawing pad!/nCopyright (c) 2002 Tsinghua University ", " 画图板程序说明 ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(aboutItem);
		bar.add(helpMenu);

		items = new ImageIcon[names.length];

		// 创建各种基本图形的按钮
		drawingArea = new DrawPanel();
		choices = new JButton[names.length];
		buttonPanel = new JToolBar(JToolBar.VERTICAL);
		buttonPanel = new JToolBar(JToolBar.HORIZONTAL);
		ButtonHandler handler = new ButtonHandler();
		ButtonHandler1 handler1 = new ButtonHandler1();

		// 导入我们需要的图形图标，这些图标都存放在与源文件相同的目录下面
		for (int i = 0; i < choices.length; i++) {// items[i]=new ImageIcon( MiniDrawPad.class.getResource(names[i]
													// +".gif"));
			// 如果在jbuilder下运行本程序，则应该用这条语句导入图片
			items[i] = new ImageIcon(names[i] + ".gif");
			// 默认的在jdk或者jcreator下运行，用此语句导入图片
			choices[i] = new JButton("", items[i]);
			choices[i].setToolTipText(tipText[i]);
			buttonPanel.add(choices[i]);
		}

		// 将动作侦听器加入按钮里面
		for (int i = 3; i < choices.length - 3; i++) {
			choices[i].addActionListener(handler);
		}

		choices[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				newFile();
			}
		});

		choices[1].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});

		choices[2].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});

		choices[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentChoice = 3;
			}
		});
		choices[choices.length - 3].addActionListener(handler1);
		choices[choices.length - 2].addActionListener(handler1);
		choices[choices.length - 1].addActionListener(handler1);

		// 字体风格选择
		styles = new JComboBox(styleNames);
		styles.setMaximumRowCount(8);
		styles.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				style1 = styleNames[styles.getSelectedIndex()];
			}
		});
		// 字体选择
		bold = new JCheckBox("BOLD");
		italic = new JCheckBox("ITALIC");

		checkBoxHandler cHandler = new checkBoxHandler();
		bold.addItemListener(cHandler);
		italic.addItemListener(cHandler);

		JPanel wordPanel = new JPanel();
		buttonPanel.add(bold);
		buttonPanel.add(italic);
		buttonPanel.add(styles);
		styles.setMinimumSize(new Dimension(50, 20));
		styles.setMaximumSize(new Dimension(100, 20));

		Container c = getContentPane();
		super.setJMenuBar(bar);
		c.add(buttonPanel, BorderLayout.NORTH);
		c.add(drawingArea, BorderLayout.CENTER);

		statusBar = new JLabel();
		c.add(statusBar, BorderLayout.SOUTH);
		statusBar.setText("     Welcome To The Little Drawing Pad!!!  :)");

		createNewItem();
		setSize(width, height);

		show();
	}

	// 按钮侦听器ButtonHanler类，内部类，用来侦听基本按钮的操作
	public class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			for (int j = 4; j < choices.length - 3; j++) {
				if (e.getSource() == choices[j]) {
					currentChoice = j;
					createNewItem();
					repaint();
				}
			}
		}
	}

	// 按钮侦听器ButtonHanler1类，用来侦听颜色选择、画笔粗细设置、文字输入按钮的操作
	public class ButtonHandler1 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == choices[choices.length - 3]) {
				chooseColor();
			}
			if (e.getSource() == choices[choices.length - 2]) {
				setStroke();
			}
			if (e.getSource() == choices[choices.length - 1]) {
				JOptionPane.showMessageDialog(null, "Please hit the drawing pad to choose the word input position",
						"Hint", JOptionPane.INFORMATION_MESSAGE);
				currentChoice = word_number; // 14
				createNewItem();
				repaint();
			}
		}
	}

	// 鼠标事件mouseA类，继承了MouseAdapter，用来完成鼠标相应事件操作
	class mouseA extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			statusBar.setText("     Mouse Pressed @:[" + e.getX() + ", " + e.getY() + "]");// 设置状态提示
			// drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if (names[currentChoice].equals("Option") && choiceNumber != -1) {
				if (itemList[choiceNumber].isHit(e.getX(), e.getY())) {
					if (ct == -1) {
						oB = itemList[choiceNumber].B;
						oR = itemList[choiceNumber].R;
						oG = itemList[choiceNumber].G;
						ct = 1;
						ct1 = choiceNumber;
					} else {
						itemList[ct1].R = oR;
						itemList[ct1].G = oG;
						itemList[ct1].B = oB;
					}
					oB = itemList[choiceNumber].B;
					oR = itemList[choiceNumber].R;
					oG = itemList[choiceNumber].G;
					itemList[choiceNumber].R = 255;
					itemList[choiceNumber].G = 69;
					itemList[choiceNumber].B = 0;
					itemList[choiceNumber].drawD((Graphics2D) drawingArea.getGraphics());
					beginPoint = e.getPoint();
					drawingArea.repaint();
				} else {
					itemList[choiceNumber].drawR((Graphics2D) drawingArea.getGraphics());
					itemList[choiceNumber].B = oB;
					itemList[choiceNumber].R = oR;
					itemList[choiceNumber].G = oG;
					ct = -1;
					drawingArea.repaint();
					statusBar.setText("     Mouse Pressed @:[" + e.getX() + ", " + e.getY() + "] no pitch on anyone");// 设置状态提示
					choiceNumber = -1;
				}
			}

			if (!names[currentChoice].equals("Option")) {
				itemList[index].x1 = itemList[index].x2 = e.getX();
				itemList[index].y1 = itemList[index].y2 = e.getY();
			}

			// 如果当前选择的图形是随笔画或者橡皮擦，则进行下面的操作
			// if (currentChoice == 3 + 4 || currentChoice == 13 + 4) {
			if (names[currentChoice].equals("Pencil") || names[currentChoice].equals("Rubber")) {
				itemList[index].x1 = itemList[index].x2 = e.getX();
				itemList[index].y1 = itemList[index].y2 = e.getY();
				index++;
				createNewItem();
			}

			// 如果当前选择的图形式文字输入，则进行下面操作
			if (currentChoice == word_number) {
				itemList[index].x1 = e.getX();
				itemList[index].y1 = e.getY();

				String input;
				input = JOptionPane.showInputDialog("Please input the text you want!");
				itemList[index].s1 = input;
				itemList[index].x2 = f1;
				itemList[index].y2 = f2;
				itemList[index].s2 = style1;

				index++;
				currentChoice = word_number;
				createNewItem();
				drawingArea.repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {
			statusBar.setText("     Mouse Released @:[" + e.getX() + ", " + e.getY() + "]");

			if (names[currentChoice].equals("Pencil") || names[currentChoice].equals("Rubber")) {
				itemList[index].x1 = e.getX();
				itemList[index].y1 = e.getY();
			}

			// endPoint = e.getPoint();
			// if
			// (names[currentChoice].equals("Option")&&choiceNumber!=-1&&!endPoint.equals(beginPoint))
			// {
			// int Lx = endPoint.x - beginPoint.x;
			// int Ly = endPoint.y - beginPoint.y;
			// itemList[choiceNumber].x1 = Lx + itemList[choiceNumber].x1;
			// itemList[choiceNumber].y1 = Ly + itemList[choiceNumber].y1;
			// itemList[choiceNumber].x2 = Lx + itemList[choiceNumber].x2;
			// itemList[choiceNumber].y2 = Ly + itemList[choiceNumber].y2;
			// drawingArea.repaint();
			// }
			if (!names[currentChoice].equals("Option")) {
				itemList[index].x2 = e.getX();
				itemList[index].y2 = e.getY();
				repaint();
				index++;
				createNewItem();
			}
		}

		public void mouseEntered(MouseEvent e) {
			statusBar.setText("     Mouse Entered @:[" + e.getX() + ", " + e.getY() + "]");
		}

		public void mouseExited(MouseEvent e) {

			statusBar.setText("     Mouse Exited @:[" + e.getX() + ", " + e.getY() + "]");
		}
	}

	// 鼠标事件mouseB类继承了MouseMotionAdapter，用来完成鼠标拖动和鼠标移动时的相应操作
	class mouseB extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {
			statusBar.setText("     Mouse Dragged @:[" + e.getX() + ", " + e.getY() + "]");

			if (names[currentChoice].equals("Pencil") || names[currentChoice].equals("Rubber")) {
				itemList[index - 1].x1 = itemList[index].x2 = itemList[index].x1 = e.getX();
				itemList[index - 1].y1 = itemList[index].y2 = itemList[index].y1 = e.getY();
				index++;
				createNewItem();
			} else {
				if (names[currentChoice].equals("Option")) {

				} else {
					itemList[index].x2 = e.getX();
					itemList[index].y2 = e.getY();
				}
			}
			endPoint = e.getPoint();
			if (names[currentChoice].equals("Option") && choiceNumber != -1 && !endPoint.equals(beginPoint)) {
				int Lx = endPoint.x - beginPoint.x;
				int Ly = endPoint.y - beginPoint.y;
				itemList[choiceNumber].x1 = Lx + itemList[choiceNumber].x1;
				itemList[choiceNumber].y1 = Ly + itemList[choiceNumber].y1;
				itemList[choiceNumber].x2 = Lx + itemList[choiceNumber].x2;
				itemList[choiceNumber].y2 = Ly + itemList[choiceNumber].y2;
				drawingArea.repaint();
			}
			beginPoint = endPoint; // 实现移动
			// itemList[choiceNumber].
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			if (names[currentChoice].equals("Option")) {
				int j = 0;
				while (j < index) {
					if (itemList[j].isHit(e.getX(), e.getY())) {
						drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
						choiceNumber = j;
						break;
					} else if (!itemList[j].isHit(e.getX(), e.getY())) {
						drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					j++;
				}
			}
			statusBar.setText("     Mouse Moved @:[" + e.getX() + ", " + e.getY() + "]");
		}
	}

	// 选择字体风格时候用到的事件侦听器类，加入到字体风格的选择框中
	private class checkBoxHandler implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == bold) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					f1 = Font.BOLD;
				} else {
					f1 = Font.PLAIN;
				}
			}
			if (e.getSource() == italic) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					f2 = Font.ITALIC;
				} else {
					f2 = Font.PLAIN;
				}
			}
		}
	}

	// 画图面板类，用来画图

	class DrawPanel extends JPanel {

		public DrawPanel() {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			setBackground(Color.white);
			addMouseListener(new mouseA());
			addMouseMotionListener(new mouseB());
		}

		Graphics2D g2d;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g2d = (Graphics2D) g; // 定义画笔

			int j = 0;
			while (j <= index) {
				draw(g2d, itemList[j]);
				j++;
			}
		}

		void draw(Graphics2D g2d, GraphicsObject i) {
			i.draw(g2d);// 将画笔传入到各个子类中，用来完成各自的绘图
		}

	}

	// 新建一个画图基本单元对象的程序段
	void createNewItem() {
		if (currentChoice == word_number) {// 进行相应的游标设置
			drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		} else {
			drawingArea.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		switch (currentChoice) {
		case 4:
			itemList[index] = new Pencil();
			break;
		case 5:
			itemList[index] = new Line();
			break;
		case 6:
			itemList[index] = new Rectangle();
			break;
		case 7:
			itemList[index] = new fillRect();
			break;
		case 8:
			itemList[index] = new Oval();
			break;
		case 9:
			itemList[index] = new fillOval();
			break;
		case 10:
			itemList[index] = new Circle();
			break;
		case 11:
			itemList[index] = new fillCircle();
			break;
		case 12:
			itemList[index] = new RoundRect();
			break;
		case 13:
			itemList[index] = new triangle();
			break;
		case 14:
			itemList[index] = new ftriangle();
			break;
		case 15:
			itemList[index] = new fillRoundRect();
			break;
		case 16:
			itemList[index] = new Rubber();
			break;
		case 17:
			itemList[index] = new Word();
			break;
		}
		itemList[index].type = currentChoice;
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
		itemList[index].stroke = stroke;
	}

	public void pitch_on() {
		// TODO Auto-generated method stub

	}

	// 选择当前颜色程序段
	public void chooseColor() {
		color = JColorChooser.showDialog(MiniDrawPad.this, "Choose a color", color);
		R = color.getRed();
		G = color.getGreen();
		B = color.getBlue();
		itemList[index].R = R;
		itemList[index].G = G;
		itemList[index].B = B;
	}

	// 选择当前线条粗细程序段
	public void setStroke() {
		String input;
		input = JOptionPane.showInputDialog("Please input a float stroke value! ( >0 )");
		stroke = Float.parseFloat(input);
		itemList[index].stroke = stroke;
	}

	// 保存图形文件程序段
	public void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showSaveDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		File fileName = fileChooser.getSelectedFile();
		fileName.canWrite();

		if (fileName == null || fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(fileChooser, "Invalid File Name", "Invalid File Name",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				fileName.delete();
				FileOutputStream fos = new FileOutputStream(fileName);

				output = new ObjectOutputStream(fos);
				GraphicsObject record;

				output.writeInt(index);

				for (int i = 0; i < index; i++) {
					GraphicsObject p = itemList[i];
					output.writeObject(p);
					output.flush(); // 将所有图形信息强制转换成父类线性化存储到文件中
				}
				output.close();
				fos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	// 打开一个图形文件程序段
	public void loadFile() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		File fileName = fileChooser.getSelectedFile();
		fileName.canRead();
		if (fileName == null || fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(fileChooser, "Invalid File Name", "Invalid File Name",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {

				FileInputStream fis = new FileInputStream(fileName);

				input = new ObjectInputStream(fis);
				GraphicsObject inputRecord;

				int countNumber = 0;
				countNumber = input.readInt();

				for (index = 0; index < countNumber; index++) {
					inputRecord = (GraphicsObject) input.readObject();
					itemList[index] = inputRecord;

				}

				createNewItem();
				input.close();

				repaint();
			} catch (EOFException endofFileException) {
				JOptionPane.showMessageDialog(this, "no more record in file", "class not found",
						JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException classNotFoundException) {
				JOptionPane.showMessageDialog(this, "Unable to Create Object", "end of file",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException ioException) {
				JOptionPane.showMessageDialog(this, "error during read from file", "read Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// 新建一个文件程序段
	public void newFile() {
		index = 0;
		currentChoice = 3;
		color = Color.black;
		stroke = 1.0f;
		createNewItem();
		repaint();// 将有关值设置为初始状态，并且重画
	}

	// 主函数段
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		} // 将界面设置为当前windows风格

		MiniDrawPad newPad = new MiniDrawPad();
		newPad.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/*******************************************************************************
	 * 下面是各种基本图形单元的子类，都继承自父类drawings，请仔细理解继承的概念
	 ********************************************************************************/
	class Line extends GraphicsObject // 直线类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
			// shape = new Line2D.Double(x1, y1, x2, y2);
			// g2d.draw(shape);
			g2d.draw(new Line2D.Double(x1, y1, x2, y2));
			int[] x = { Math.abs(x1 - 10), x1 + 10, x2 + 10, Math.abs(x2 - 10) };
			int[] y = { Math.abs(y1 - 10), y1 + 10, y2 + 10, Math.abs(y2 - 10) };
			shape = new Polygon(x, y, 4); // 定义直线选择框
			removable = new Shape[2];
			removable[0] = new Rectangle2D.Double(x1 - 2, y1 - 2, 5, 5);
			removable[1] = new Rectangle2D.Double(x2 - 2, y2 - 2, 5, 5);
		}

		void drawD(Graphics2D g2d) {
			g2d.draw(removable[0]);
			g2d.draw(removable[1]);
		}

		void drawR(Graphics2D g2d) {
			java.awt.Rectangle t1 = removable[0].getBounds();
			java.awt.Rectangle t2 = removable[1].getBounds();
			g2d.clearRect(t1.x, t1.y, t1.width, t1.height);
			g2d.clearRect(t2.x, t1.y, t2.width, t2.height);
		}

		@Override
		public boolean isHit(int x, int y) {
			return shape.contains(x, y);
		}

	}

	class Rectangle extends GraphicsObject// 矩形类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
			// Math.abs(y1 - y2));
			shape = new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			g2d.draw(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			// points = getRectSelectAre(new Point(x1, y1), new Point(x2, y2));
			// return IsPoinInMatrix(points, new Point(x, y));
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

	}

	class fillRect extends GraphicsObject// 实心矩形类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
			// Math.abs(y1 - y2));
			shape = new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			g2d.fill(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class triangle extends GraphicsObject //
	{
		@Override
		void draw(Graphics2D g2d) {
			// TODO Auto-generated method stub
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			int[] x = { x1, Math.abs((x1 + x2) / 2), x2 };
			int[] y = { y2, y1, y2 };
			int npoints = 3;
			shape = new Polygon(x, y, npoints);
			g2d.draw(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class ftriangle extends GraphicsObject //
	{
		@Override
		void draw(Graphics2D g2d) {
			// TODO Auto-generated method stub
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.drawArc(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2), 0, 90);\
			int[] x = { x1, Math.abs((x1 + x2) / 2), x2 };
			int[] y = { y2, y1, y2 };
			int npoints = 3;
			shape = new Polygon(x, y, npoints);
			g2d.fill(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class Oval extends GraphicsObject// 椭圆类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			shape = new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			// g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
			// Math.abs(y1 - y2));
			g2d.draw(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class fillOval extends GraphicsObject// 实心椭圆
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
			// Math.abs(y1 - y2));
			shape = new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			g2d.fill(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class Circle extends GraphicsObject// 圆类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.max(Math.abs(x1 - x2),
			// Math.abs(y1 - y2)),
			// Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));

			shape = new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2),
					Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)), Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
			g2d.draw(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class fillCircle extends GraphicsObject// 实心圆
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.max(Math.abs(x1 - x2),
			// Math.abs(y1 - y2)),
			// Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
			shape = new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2),
					Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)), Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
			g2d.fill(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class RoundRect extends GraphicsObject// 圆角矩形类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
			// Math.abs(y1 - y2), 50, 35);
			shape = new RoundRectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
					Math.abs(y1 - y2), 50, 35);
			g2d.draw(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

	}

	class fillRoundRect extends GraphicsObject// 实心圆角矩形类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke));
			// g2d.fillRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
			// Math.abs(y1 - y2), 50, 35);
			shape = new RoundRectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
					Math.abs(y1 - y2), 50, 35);
			g2d.fill(shape);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return shape.contains(x, y);
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class Pencil extends GraphicsObject// 随笔画类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2d.drawLine(x1, y1, x2, y2);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class Rubber extends GraphicsObject// 橡皮擦类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(255, 255, 255));
			g2d.setStroke(new BasicStroke(stroke + 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2d.drawLine(x1, y1, x2, y2);
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}
	}

	class Word extends GraphicsObject// 输入文字类
	{

		void draw(Graphics2D g2d) {
			g2d.setPaint(new Color(R, G, B));
			g2d.setFont(new Font(s2, x2 + y2, ((int) stroke) * 18));
			if (s1 != null) {
				g2d.drawString(s1, x1, y1);
			}
		}

		@Override
		public boolean isHit(int x, int y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		void drawD(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

		@Override
		void drawR(Graphics2D g2d) {
			// TODO Auto-generated method stub

		}

	}

}

abstract class GraphicsObject implements Serializable, IHitTest// 父类，基本图形单元，用到串行化接口，保存时所用
{

	int x1, y1, x2, y2; // 定义坐标属性
	int R, G, B; // 定义色彩属性
	float stroke; // 定义线条粗细属性
	int type; // 定义字体属性
	String s1;
	String s2; // 定义字体风格属性
	Point[] points;
	Shape shape;
	Shape[] removable;

	abstract void draw(Graphics2D g2d);

	abstract void drawD(Graphics2D g2d);

	abstract void drawR(Graphics2D g2d);
}

interface IHitTest {
	boolean isHit(int x, int y);// 击中点的坐标x，y
}
