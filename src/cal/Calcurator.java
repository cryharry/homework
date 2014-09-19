package cal;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.im.InputContext;
import java.awt.im.InputMethodRequests;
import java.awt.image.BufferStrategy;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ComponentPeer;
import java.beans.PropertyChangeListener;
import java.beans.Transient;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.EventListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class Calcurator extends JFrame implements ActionListener {
	JPanel pNorth, pTextField, pClear, pCenter;
	JTextField jText;
	JButton bsBt, clearBt, numBt[] = new JButton[10];
	JButton addBt, MinusBt, multiBt, subBt, dotBt, equalBt;
	int num[], result = 0; 
	public Calcurator() {
		super("계산기");
		pNorth = new JPanel(new GridLayout(2,1));
		// 텍스트 필드 판넬
		pTextField = new JPanel();
		pTextField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED),"계산기값"));
		pTextField.add(jText = new JTextField(20));
		jText.setHorizontalAlignment(jText.RIGHT);
		pNorth.add(pTextField);
		// 백스페이스,클리어 버튼 판넬
		pClear = new JPanel();
		pClear.add(clearBt = new JButton("clear"));
		clearBt.addActionListener(this);
		pClear.add(bsBt = new JButton("backspace"));
		bsBt.addActionListener(this);
		pNorth.add(pClear);
		// 넘버버튼+계산버튼 판넬
		pCenter = new JPanel(new GridLayout(1,2));
		// 넘버버튼 판넬
		pCenter = new JPanel(new GridLayout(4,2,10,10));
		pCenter.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		for(int i=numBt.length-1; i >= 0; i--) {
			switch(i){
			case 6:
				pCenter.add(addBt = new JButton("+"));
				addBt.addActionListener(this);
				break;
			case 3:
				pCenter.add(MinusBt = new JButton("-"));
				MinusBt.addActionListener(this);
				break;
			case 0:
				pCenter.add(subBt = new JButton("/"));
				subBt.addActionListener(this);
				break;
			default:break;
			}
			if(i ==0 )pCenter.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			pCenter.add(numBt[i] = new JButton(""+i));
			numBt[i].addActionListener(this);
		} 
		pCenter.add(dotBt = new JButton("."));
		dotBt.addActionListener(this);
		pCenter.add(equalBt = new JButton("="));
		equalBt.addActionListener(this);
		pCenter.add(multiBt = new JButton("*"));
		multiBt.addActionListener(this);
		add(pNorth,"North");
		add(pCenter,"Center");
		
		// 계산기Frame
		setSize(400, 400);
		setVisible(true);
		// 계산기 닫기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		int calNum = 0;
		try {
			if(str.matches("[0-9]")) {
				jText.setText(str);
				num = new int[calNum+1];
				num[calNum] = Integer.parseInt(str);
				calNum++;
			} else {
				result = cal(str, num[calNum]);
				jText.setText(String.valueOf(result));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private int cal(String str, int x) {
		switch(str) {
			case "+":
				result += x;
				break;
			case "-":
				result -= x;
				break;
			case "*":
				result *= x;
				break;
			case "/":
				result /= x;
				break;
			case "=":
				break;
			default: System.out.println("이게아닌데");break;
		}
		return result;
	}

	public static void main(String[] args) {
		new Calcurator();
	}
}
