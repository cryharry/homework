
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Calcurator extends JFrame implements ActionListener {
	JPanel pNorth, pTextField, pLabel, pClear, pCenter;
	JTextField jText;
	JTextArea jTextArea;
	JLabel jl;
	JButton bsBt, clearBt, operBt[], numBt[] = new JButton[10];
	JButton addBt, minusBt, multiBt, divBt, dotBt, equalBt;
	String oper = "";
	int result = 0, eCount, num = 0; 
	String str = "";
	Calcurator() {
		super("계산기");
		pNorth = new JPanel(new GridLayout(2,1));
		// 텍스트 필드 판넬
		pTextField = new JPanel(new GridLayout(2,1));
		pTextField.add(pLabel = new JPanel());
		pTextField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED),"계산기값"));
		pTextField.add(jText = new JTextField(20));
		jText.setBorder(null);
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
		pCenter.add(addBt = new JButton("+"));
		for(int i=numBt.length-1; i >= 0; i--) {
			switch(i){
			case 6:
				pCenter.add(minusBt = new JButton("-"));
				break;
			case 3:
				pCenter.add(divBt = new JButton("/"));
				break;
			case 0:
				pCenter.add(multiBt = new JButton("*"));
				pCenter.add(equalBt = new JButton("="));
				pCenter.add(dotBt = new JButton("."));
				dotBt.addActionListener(this);
				break;
			default:break;
			}			
			pCenter.add(numBt[i] = new JButton(String.valueOf(i)));
			// 숫자버튼 이벤트
			numBt[i].addActionListener(this);
		}
		add(pNorth,"North");
		add(pCenter,"Center");
		// 연산자버튼 이벤트
		setOperListener(addBt, minusBt, divBt, multiBt);
		equalListener(equalBt);
		// 계산기Frame
		setSize(300, 300);
		setVisible(true);
		setResizable(false);
		// 계산기 닫기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void equalListener(JButton equalBt2) {
		equalBt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cal(oper,Integer.parseInt(str));
				jText.setText(""+result);
				pLabel.removeAll();
				setVisible(true);
			}
		});
	}
	public void setOperListener(JButton ...operBtArr) {
		operBt = operBtArr;
		for(JButton num: operBt) {
			num.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inputOper(e.getActionCommand());
				}
			});
		}
	}
	public void inputOper(String btn) {
		oper = btn;
		if(str != ""){
			pLabel.add(jl = new JLabel(oper));
			jText.setText(str);
			num = Integer.parseInt(str);
		} else {
			result = 0;
			pLabel.add(jl = new JLabel("0"));
			pLabel.add(jl = new JLabel(oper));
		}
		if(result == 0){
			result = num;
		} else{
			cal(oper, num);
		}
		str = "";
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			str += e.getActionCommand();
			pLabel.add(jl = new JLabel(e.getActionCommand()));
			setVisible(true);
	}
	private int cal(String oper, int y) {
		switch(oper) {
			case "+":
				result += y;
				jText.setText(""+result);
				break;
			case "-":
				result -= y;
				jText.setText(""+result);
				break;
			case "/":
				result /= y;
				jText.setText(""+result);
				break;
			case "*":
				result *= y;
				jText.setText(""+result);
				break;
			case "clear":
				result = 0;
				jTextArea.removeAll();
				break;
			case "backspace":
				System.out.println(jTextArea.getLineCount());
				//jTextArea.remove();
				break;
			default: System.out.println("이게아닌데");
				break;
		}
		return result;
	}

	public static void main(String[] args) {
		new Calcurator();
	}
}
