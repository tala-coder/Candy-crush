/**
 * 
 */
package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Muhammed Talić
 * Klasa pocetna
 */
public class Pocetna extends JFrame {
	Podloga podloga;
	public JButton a;
	public JButton b;
	public JButton c;
	private JLabel p1,p2;
	public int brojRedova, brojKolona, brojPoteza;
	
	/**
	 * Konstruktor koji poziva metodu postavi
	 */
	public Pocetna() {
		super();
		postavi();
	}


	/**
	 * Metoda koja postavlja akcije, ovjekte i raspoređuje ih u okviru 
	 */
	private void postavi() {

		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("pozadina.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pack();
		setVisible(true);
		
		a = new JButton(" 8 x 8 ");
		b = new JButton(" 8 x 6 ");
		c = new JButton(" 6 x 9 ");
		
		p1 = new JLabel();
		p2 = new JLabel();

		setLayout(new GridBagLayout());
		GridBagConstraints cr = new GridBagConstraints();
		cr.fill = GridBagConstraints.BOTH;
		
		
		a.setBackground(Color.YELLOW);
		b.setBackground(Color.red);
		c.setBackground(Color.green);
		
		
		a.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			brojRedova = 8;
			brojKolona = 8;
			brojPoteza = 5;
			dispose();
			podloga = new Podloga(8, 8, 5);
	
			}
		});
	
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brojRedova = 8;
				brojKolona = 6;
				brojPoteza = 5;
				podloga = new Podloga(8, 6, 5);
				dispose();
			}
		});
	
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brojRedova = 6;
				brojKolona = 9;
				brojPoteza = 5;
				podloga = new Podloga(6, 9, 5);
				dispose();
			}
		});
	
		
		cr.insets = new Insets(15, 0, 2, 0);
		cr.weightx = 1.0;
		cr.weighty = 1.0;
		cr.gridx = 0;
		cr.gridy = 0;
		add(p1, cr);
		

		cr.weightx = 1.0;
		cr.weighty = 0.2;
		cr.gridx = 1;
		cr.gridy = 1;
		add(a, cr);

		cr.weightx = 1.0;
		cr.weighty = 0.2;
		cr.gridx = 1;
		cr.gridy = 2;
		add(b, cr);

		cr.weightx = 1.0;
		cr.weighty = 0.2;
		cr.gridx = 1;
		cr.gridy = 3;
		add(c, cr);

	
		cr.weightx = 1.0;
		cr.weighty = 0.80;
		cr.gridx = 2;
		cr.gridy = 4;
		add(p2, cr);
		setIconImage(new ImageIcon("icon.png").getImage());
		setTitle("Candy Crush home");
		setSize(750, 785);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	
}
