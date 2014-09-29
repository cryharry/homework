
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Calcurator extends JFrame implements ActionListener {
	JPanel pNorth, pTextField, pLabel, pClear, pCenter;
	JTextField jText;
	JLabel jl;
	JButton bsBt, clearBt, operBt, numBt[] = new JButton[10];
	JButton addBt, minusBt, multiBt, divBt, dotBt, equalBt;
	String oper, str = "", strOper = "";
	int result = 0, eCount, num = 0;
	Calcurator() {
		super("계산기");
		pNorth = new JPanel(new GridLayout(2,1));
		// 텍스트 필드 패널
		pTextField = new JPanel(new GridLayout(2,1));
		// 계산기 값 패널 보더셋팅
		pTextField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED),"계산기값"));
		// 텍스트필드 위 입력을 더할 레이블패널 추가
		pTextField.add(pLabel = new JPanel());
		// 텍스트 필드 패널에 텍스트 필드 추가
		pTextField.add(jText = new JTextField(20));
		jText.setBorder(null); // 텍스트 필드 보더X
		jText.setHorizontalAlignment(jText.RIGHT); // 텍스트 필드값 오른쪽 정렬
		pNorth.add(pTextField);
		// 백스페이스,클리어 버튼 패널
		pClear = new JPanel();
		pClear.add(clearBt = new JButton("C"));
		pClear.add(bsBt = new JButton("backspace"));
		pNorth.add(pClear);
		clearListener(clearBt, bsBt);
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
		// 모니터사이즈에 따른 위치셋팅
		Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		setLocation((int)(dim.getWidth()/2)-getWidth(), (int)(dim.getHeight()/2)-getHeight());
		setVisible(true);
		setResizable(false);
		// 계산기 닫기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// 클리어버튼, 백스페이스버튼 내부무명이벤트
	private void clearListener(JButton clearBt2, JButton bsBt2) {
		// 클리어버튼 이벤트 오버라이드
		clearBt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				result = 0;
				jText.setText("0");
				pLabel.removeAll();
				str = "";
				setVisible(true);
			}
		});
		// 백스페이스버튼 이벤트 오버라이드
		bsBt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(str.length()>=1){
					str = str.substring(0,str.length()-1);
					pLabel.removeAll();
					setVisible(true);
					pLabel.add(new JLabel(str));
					setVisible(true);
				}
				if(oper!="" && strOper.length()>=1){
					strOper = strOper.substring(0,strOper.length()-1);
					pLabel.removeAll();
					setVisible(true);
					pLabel.add(new JLabel(strOper));
					setVisible(true);
				}
				
			}
		});
	}
	// "=" 버튼 내부무명이벤트
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
	// +,-,/,* 버튼 내부무명이벤트
	public void setOperListener(JButton ...operBtArr) {
		for(JButton num: operBtArr) {
			num.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inputOper(e.getActionCommand());
				}
			});
		}
	}
	// 연산자버튼 클릭시 메서드
	public void inputOper(String btn) {
		oper = btn;
		strOper += str+oper;
		if(str != ""){
			pLabel.add(new JLabel(oper));
			jText.setText(str);
			num = Integer.parseInt(str);
		// 숫자 입력없이 연산자 버튼만 클릭했을경우
		} else {
			result = 0;
			pLabel.add(new JLabel("0"));
			pLabel.add(new JLabel(oper));
		}
		// 계산기 초기나 clear버튼으로 연산값 reset후 사용시
		if(result == 0){
			result = num;
		} else{
			cal(oper, num);
		}
		str = "";
		setVisible(true);
	}
	// 숫자버튼 이벤트 오버라이드
	@Override
	public void actionPerformed(ActionEvent e) {
			str += e.getActionCommand();
			pLabel.add(new JLabel(e.getActionCommand()));
			setVisible(true);
	}
	// 연산 메서드
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
			default: 
				break;
		}
		setVisible(true);
		return result;
	}

	public static void main(String[] args) {
		new Calcurator();
	}
}
