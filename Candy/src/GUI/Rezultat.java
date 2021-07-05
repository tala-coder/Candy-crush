/**
 * 
 */
package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logika.CandyCrush;

/**
 * @author Muhammed
 * Klasa rezultat
 */
public class Rezultat extends JPanel {
	CandyCrush candy;
	Pocetna pocetna;
	private JLabel bpp = new JLabel("Broj prostalih poteza");
	private JLabel bp = new JLabel("Broj bodova");;
	private JLabel cilj = new JLabel("Cilj");
	private JLabel level = new JLabel("Level");

	private JTextField tbpp;
	private JTextField tbp;
	private JTextField tcilj;
	private JTextField tlevel;

	/**
	 * Metoda koja ispisuje rezultat u traku
	 * @param a broj preostalih poteza
	 * @param b broj bodova
	 * @param c cilj
	 * @param d level
	 */
	public void ispisRezultata(int a, int b, int c, int d) {
		tbpp.setText("" + b);
		tbp.setText("" + a);
		tcilj.setText("" + c);
		tlevel.setText("" + d);
	}

	/**
	 * Metoda koja dodatno uređuje objekte
	 */
	private void postavi() {
		candy = new CandyCrush(8, 8, 5);
		tbpp = new JTextField("" + candy.brojPoteza);
		tbp = new JTextField(2);
		tbp.setText("" + candy.brojPodudaranja);
		tcilj = new JTextField("" + candy.cilj);
		tlevel = new JTextField("1");
		tbpp.setEditable(false);
		tbp.setEditable(false);
		tcilj.setEditable(false);
		tlevel.setEditable(false);
		ubaciObjekte();

	}

	/**
	 * Metoda koja postavlja i raspoređuje objekte
	 */
	private void ubaciObjekte() {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(0, 0, 0, 2);
		c.gridx = 1;
		c.gridy = 0;
		add(bpp, c);

		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 9);
		add(tbpp, c);
		c.insets = new Insets(0, 0, 0, 2);

		c.gridx = 3;
		c.gridy = 0;
		add(bp, c);

		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 9);
		add(tbp, c);
		c.insets = new Insets(0, 0, 0, 2);

		c.gridx = 5;
		c.gridy = 0;
		add(cilj, c);

		c.gridx = 6;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 9);
		add(tcilj, c);
		c.insets = new Insets(0, 0, 0, 2);

		c.gridx = 7;
		c.gridy = 0;
		add(level, c);

		c.gridx = 8;
		c.gridy = 0;
		add(tlevel, c);

	}

	/**
	 * Konstruktor koji poziva metodu postavi
	 */
	public Rezultat() {
		postavi();
	}
}
