/**
 * Package GUI
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Logika.CandyCrush;

/**
 * @author Muhammed Talić
 *
 */
public class MojPanel extends JPanel {
	private int potez = 1, iTemp = 0, jTemp = 0, level = 1;
	CandyCrush candy;
	Nextlvl lvl;
	Rezultat rezultat; 
	JPanel prikazTabele;
	private JButton[][] tabelaDugmadi;
	
	
	/**
	 * 
	 * Konstruktor prima tri parametra, postavlja vrijednosti i popunjava matricu random brojevima
	 * @param brojRedova broj redova matrice
	 * @param brojKolona broj kolona matrice
	 * @param brojPoteza broj peteza 
	 * @param rez1 rezultat traka
	 */
	public MojPanel(int brojRedova, int brojKolona, int brojPoteza, Rezultat rez1) {
		super();
		rezultat = rez1;
		candy = new CandyCrush(brojRedova, brojKolona, brojPoteza);
		setLayout(new BorderLayout());
		prikazTabele = new JPanel();
		add(prikazTabele);
		prikazTabele.setLayout(new GridLayout(brojRedova, brojKolona));
		tabelaDugmadi = new JButton[brojRedova][brojKolona];
		candy.postaviMatricu(candy.uzmiMatricu());


		for (int i = 0; i < brojRedova; i++) {
			for (int j = 0; j < brojKolona; j++) {
//				tabelaDugmadi[i][j] = new JButton("" + candy.uzmiMatricu()[i][j]);
				tabelaDugmadi[i][j] = new JButton();
				tabelaDugmadi[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				prikazTabele.add(tabelaDugmadi[i][j]);
				tabelaDugmadi[i][j].setBackground(uzmiBoju(candy.uzmiMatricu()[i][j]));
				tabelaDugmadi[i][j].addMouseListener(new akcija());

			}
		}
	}
	
	/**
	 * 
	 * @author Muhammed
	 * Klasa koja vrši promjene nakon klika
	 */
	class akcija implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			
			for (int i = 0; i < tabelaDugmadi.length; i++) {
				for (int j = 0; j < tabelaDugmadi[0].length; j++) {
					if (tabelaDugmadi[i][j] == e.getSource()) {
						System.out.println(i + "," + j); //samo radi testiranja
						
						if (potez == 1) {
							iTemp = i;
							jTemp = j;
							potez++;

						}

						else if (potez == 2) {
							if (candy.provjeriPolja(iTemp, jTemp, i, j)) {
								candy.zamijeniPolja(iTemp, jTemp, i, j);
								candy.formatirajMatricu(candy.uzmiMatricu());
								ispisiRezultat(candy.brojPodudaranja, candy.brojPoteza, candy.cilj); //samo radi testiranja
								rezultat.ispisRezultata(candy.brojPodudaranja, candy.brojPoteza, candy.cilj, candy.level); // dodat lvl
								potez--;
								
								int tempBrojPodudaranja = candy.brojPodudaranja;
								int tempCilj = candy.cilj;
								

								if (candy.kraj()) {
									ispisiInformacije(tempBrojPodudaranja,  tempCilj);
									rezultat.ispisRezultata(candy.brojPodudaranja, candy.brojPoteza, candy.cilj, candy.level); // dodat lvl
									if (candy.daLijePobjedio == 0) {
										candy.cilj = 20;
										candy.brojPoteza = 5;
									}
//									else if(candy.daLijePobjedio == 1) {
//										candy.brojPoteza ++;
//										rezultat.ispisRezultata(candy.brojPodudaranja, candy.brojPoteza, candy.cilj, candy.level);
//									}
									
								}

								
							} else if (!candy.provjeriPolja(iTemp, jTemp, i, j)) {
								potez = 1;
							}

						}  							
					}						
				}
			}
			osvjeziMatricu();
		}

		/**
		 * Metoda koja u novi okvir ispisuje informacije 
		 * @param brojPodudaranja bodovi
		 * @param cilj cilj
		 */
		private void ispisiInformacije(int brojPodudaranja, int cilj) {
			if (candy.daLijePobjedio == 1) {
				lvl = new Nextlvl();
				lvl.ispisiInformacije("Čestitamo, uspjeli ste, ", "Igraj sljedeći level", "Izađi iz igrice", brojPodudaranja, cilj );
				
			}
			else {
				lvl = new Nextlvl();
				lvl.ispisiInformacije("Izgubili ste, ", "Igraj ponovo", "Izađi iz igrice", brojPodudaranja, cilj );
				
			}
			
		}

		/**
		 * Pomocna metoda koju sam koristio radi testiranja igre
		 * @param a Broj bodova
		 * @param b Cilj
		 * @param c Broj prostalih poteza
		 */
		private void ispisiRezultat(int a, int b, int c) {
			System.out.println("Broj bodova: " + a + ",  cilj -> " + (c) + " ili više.");
			System.out.println("Broj prostalih poteza: " + b);
			System.out.println();
		}

		@Override 
		public void mouseEntered(MouseEvent e) {
			for (int i = 0; i < tabelaDugmadi.length; i++) {
				for (int j = 0; j < tabelaDugmadi[0].length; j++) {

					if (tabelaDugmadi[i][j] == e.getSource()) {
						tabelaDugmadi[i][j].setBorderPainted(false);
					}

				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override

		public void mouseExited(MouseEvent e) {

			for (int i = 0; i < tabelaDugmadi.length; i++) {
				for (int j = 0; j < tabelaDugmadi[0].length; j++) {

					if (tabelaDugmadi[i][j] == e.getSource()) {
						tabelaDugmadi[i][j].setBorderPainted(true);

					}

				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			for (int i = 0; i < tabelaDugmadi.length; i++) {
				for (int j = 0; j < tabelaDugmadi[0].length; j++) {

					if (tabelaDugmadi[i][j] == e.getSource()) {
						tabelaDugmadi[i][j].setFocusable(true);
						
					}

				}
			}

		}

	}
	
	
	/**
	 * Metoda koja osvjezava matricu
	 */
	public void osvjeziMatricu() {
		for (int i = 0; i < tabelaDugmadi.length; i++) {
			for (int j = 0; j < tabelaDugmadi[0].length; j++) {
				tabelaDugmadi[i][j].setBackground(uzmiBoju(candy.uzmiMatricu()[i][j]));
			}
		}

	}

	/**
	 * Metoda koja vraća boju
	 * @param boja boja
	 * @return Color
	 */
	private Color uzmiBoju(int boja) {
		if (boja == 7) {
			return Color.WHITE;
		} else if (boja == 1) {
			return Color.BLUE;
		} else if (boja == 2) {
			return Color.RED;
		} else if (boja == 3) {
			return Color.PINK;
		} else if (boja == 4) {
			return Color.YELLOW;
		} else if (boja == 5) {
			return Color.MAGENTA;
		} else if (boja == 6) {
			return Color.CYAN;
		} else {
			return Color.BLACK;
		}
	}

}
