/**
 * 
 */
package GUI;

import java.awt.Dimension;
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

import Logika.CandyCrush;

/**
 * @author Muhammed Talić
 * Klasa sljedeći level
 */
public class Nextlvl extends JFrame {
	CandyCrush candy;
	private JLabel tekst = new JLabel("Izgubili ste, više sreće drugi put.");

	private JButton ok = new JButton("Igraj ponovo");
	private JButton exit = new JButton("Izađi iz igrice");

	/**
	 * Akcija na dugmad i postavljanje objekata
	 */
	private void postavi() {
		postaviAkcijeNaDugmad();
		ubaciObjekte();
	}

	/**
	 * Postavlja akcije na klik
	 */
	private void postaviAkcijeNaDugmad() {
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	/**
	 * Raspoređuje i dodaje objekte u okvir
	 */
	private void ubaciObjekte() {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 2;

		c.insets = new Insets(0, 50, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		add(tekst, c);

		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(ok, c);

		c.gridx = 1;
		c.gridy = 1;
		add(exit, c);

		setIconImage(new ImageIcon("icon.png").getImage());
		setTitle("Candy Crush");
		setSize(500, 100);
		setLocationRelativeTo(null);

		setVisible(true);

	}

	/**
	 * Konstruktor poziva metodu postavi();
	 */
	public Nextlvl() {
		postavi();
	}

	/**
	 * Metoda koja postavlja informacije okvir
	 * @param s1 brojPodudaranja
	 * @param s2 Dugme ok
	 * @param s3 Dugme cancel
	 * @param brojPodudaranja broj bodova
	 * @param cilj cilj
	 */
	public void ispisiInformacije(String s1, String s2, String s3, int brojPodudaranja, int cilj) {
		tekst.setText(s1 + "imali ste " + brojPodudaranja + ", a trebalo vam je minimalno " + cilj + " bodova.");
		ok.setText(s2);
		exit.setText(s3);

	}

	/**
	 * Metoda da li je kliknuto polje
	 * @return True ako jest kliknuto polje
	 */
	public boolean jeKlikno(int i) {
		if (i == 1)
			return true;
		else {
			return false;
		}
	}
}
