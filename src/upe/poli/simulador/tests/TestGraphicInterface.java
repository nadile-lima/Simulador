package upe.poli.simulador.tests;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;

import upe.poli.simulador.graphicinterface.MainFrame;

public class TestGraphicInterface {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try{
					MainFrame window = new MainFrame(100000000L);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					window.setSize(new Dimension(1100, 740));
					window.setExtendedState(Frame.MAXIMIZED_BOTH);
					window.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
}
