package com.palvair;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculatrice extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstPart;
	
	private String operateur;
	
	private boolean clickOperator = false;
	
	private String[] operators = new String[] {"+","-","/","*","="};
	
	private StringBuilder buffer = new StringBuilder();

	private JTextField result;
	
	public Calculatrice() {
		super();
		this.setSize(240, 260);
	    this.setTitle("Calculette");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
		init();
		
		this.setVisible(true);
	}
	
	private void init() {
		Container container = this.getContentPane();
		
		
		JPanel resultPanel = new JPanel();
		this.result = new JTextField();
		result.setPreferredSize(new Dimension(220, 20));
		resultPanel.add(result);
		
		JPanel numberPanel = new JPanel();
		numberPanel.setPreferredSize(new Dimension(165, 225));
		for(int i =0; i < 10; i++) {
			JButton button = new JButton(String.valueOf(i));
			button.addActionListener(new ChiffreListener());
			numberPanel.add(button);
		}
		
		JPanel operatorPanel = new JPanel();
		operatorPanel.setPreferredSize(new Dimension(55, 225));
		for(String operator : operators) {
			JButton button = new JButton(operator);
			if(operator.equals("+")) {
				button.addActionListener(new PlusListener());
			} else if(operator.equals("-")) {
				button.addActionListener(new MinusListener());
			}else if(operator.equals("%")) {
				button.addActionListener(new DivListener());
			}else if(operator.equals("*")) {
				button.addActionListener(new MultiListener());
			}
			else if(operator.equals("=")) {
				button.addActionListener(new EqualListener());
			}
			operatorPanel.add(button);
		}
		container.add(resultPanel, BorderLayout.NORTH);
		container.add(numberPanel, BorderLayout.WEST);
		container.add(operatorPanel, BorderLayout.EAST);
	}
	
	  private void calcul(){
		  int result = 0;
		    if(operateur.equals("+")){
		    	result = Integer.valueOf(firstPart) + 
		    			Integer.valueOf(buffer.toString());
		    }
		    if(operateur.equals("-")){
		    	result = Integer.valueOf(firstPart) - 
		    			Integer.valueOf(buffer.toString());
		    }          
		    if(operateur.equals("*")){
		    	result = Integer.valueOf(firstPart) * 
		    			Integer.valueOf(buffer.toString());
		    }     
		    if(operateur.equals("/")){
		      try{
		    	  result = Integer.valueOf(firstPart) / 
		    			  Integer.valueOf(buffer.toString());
		      } catch(ArithmeticException e) {
		    	  this.result.setText("0");
		      }
		    }
		    this.result.setText(String.valueOf(result));
		  }
	
	class PlusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(clickOperator){
		        calcul();
			} else {
			    firstPart = buffer.toString();
			    clickOperator = true;
			    result.setText(String.valueOf(firstPart));
			    buffer = new StringBuilder();
			}
			operateur = "+";
		}
		
	}
	
	class MinusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(clickOperator){
		        calcul();
			} else {
			    firstPart = buffer.toString();
			    clickOperator = true;
			    result.setText(String.valueOf(firstPart));
			    buffer = new StringBuilder();
			}
			operateur = "-";
			
		}
		
	}
	
	class DivListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(clickOperator){
		        calcul();
			} else {
			    firstPart = buffer.toString();
			    clickOperator = true;
			    result.setText(String.valueOf(firstPart));
			    buffer = new StringBuilder();
			}
			operateur = "/";
			
		}
		
	}
	
	class MultiListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(clickOperator){
		        calcul();
			} else {
			    firstPart = buffer.toString();
			    clickOperator = true;
			    result.setText(String.valueOf(firstPart));
			    buffer = new StringBuilder();
			}
			operateur = "*";
		}
		
	}
	class EqualListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calcul();
			clickOperator = false;
		}
		
	}
	
	class ChiffreListener implements ActionListener {
	    public void actionPerformed(ActionEvent e){
	      String str = ((JButton)e.getSource()).getText();
	      buffer.append(str);
	      result.setText(String.valueOf(buffer.toString()));
	    }
	  }

}
