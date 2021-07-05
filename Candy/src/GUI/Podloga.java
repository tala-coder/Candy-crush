/**
 * 
 */
package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Logika.*;

/**
 * @author Muhammed 
 * Klasa podloga
 */
public class Podloga extends JFrame {
	Rezultat rezultat;
	CandyCrush candy;
	MojPanel mojPanel;

	/**
	 * Konstruktor podloga, postavlja ikonu, naziv prozora, velicinu prozora
	 * @param i broj redova matrice
	 * @param j broj redova matrice
	 * @param k broj poteza
	 */
	public Podloga(int i, int j, int k) {
		super();
		setTitle("Candy Crush");
		postavi(i, j, k);
		setSize(800, 600);	// pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("icon.png").getImage());
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Metoda koja postavlja i raspoređuje objekte
	 * @param i broj redova matrice
	 * @param j broj redova matrice
	 * @param k broj poteza
	 */
	private void postavi(int i, int j, int k) {
		rezultat = new Rezultat();
		mojPanel = new MojPanel(i, j, k, rezultat);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.insets = new Insets(6, 0, 0, 0);
		c.weightx = 1.0;
		c.weighty = 0.02;
		c.gridx = 0;
		c.gridy = 0;
		add(rezultat, c);

		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		add(mojPanel, c);

	}

}
