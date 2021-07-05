/**
 * Package koji se odnosi na igricu CandCrush
 */
package Konzola;

import java.util.Scanner;
import java.util.Vector;

import Logika.CandyCrush;

/**
 * @author Muhammed Talić
 * Projekat iz predmeta Razvoj softvera, rađen u školskoj 2020/21 godini, asistent Vedad Letić
 */
public class IgrajCandy {
	/**
	 * @param podaci[] podaci niz koji sarži broj redova, broj kolona i broj poteza
	 */
	public static void main(String[] args) {
	    int podaci[] = odaberiMatricu();
		CandyCrush candy = new CandyCrush(podaci[0], podaci[1], podaci[2]); // unosi tri ponudene opcije a,b,c
		candy.postaviMatricu(candy.uzmiMatricu());

		/**
		 * Igra
		 */
		while (true) {
			IgrajCandy.ispisiMatricu(candy.uzmiMatricu());
			odaberiPolje(candy);
			candy.formatirajMatricu(candy.uzmiMatricu());
			IgrajCandy.ispisiRezultat(candy.brojPodudaranja, candy.brojPoteza, candy.cilj);
			if (candy.kraj()) {
				if (candy.daLijePobjedio == 1) {
					if (idemoNextLevel(podaci[0], podaci[1], podaci[2]))
						;
					else
						break;

				} else if (candy.daLijePobjedio == 0) {
					if (idemoPonovo(podaci[0], podaci[1], podaci[2]))
						;
					else
						break;
				}
			}
		}
		ispisiPorukuZakrajIgre();
	}

	/**
	 * Metoda koja se aktivira ako korisnik izgubi
	 * 
	 * @return True ako korisnik želi igrati ponovo
	 */
	private static boolean idemoPonovo(int red, int kol, int bp) {
		System.out.println("Izgubili ste, više sreće drugi put.");
		System.out.println("Da li želite igrati ponovo? (y/n) ");
		if (hocePonovo() == 1) {
			CandyCrush candy = new CandyCrush(red, kol, bp);
			candy.postaviMatricu(candy.uzmiMatricu());
			candy.formatirajMatricu(candy.uzmiMatricu());
			candy.cilj = 20;
			return true;
		}
		return false;

	}

	/**
	 * Metoda koja se aktivira ako korisnik pobijedi
	 * 
	 * @return True ako korisnik želi igrati sljedeći level
	 */
	private static boolean idemoNextLevel(int red, int kol, int bp) {
		System.out.println("Čestitamo, uspjeli ste.");
		System.out.println("Da li želite igrati sljedeći level? (y/n) ");
		if (hocePonovo() == 1) {
			CandyCrush candy = new CandyCrush(red, kol, bp);
			candy.cilj += 5;
			return true;
		}
		return false;

	}

	/**
	 * Korisnik bira dvije ponuđene opcije (y/n)
	 * 
	 * @return 1 ako korisnik unese 'y', 0 ako unese 'n';
	 */
	private static int hocePonovo() {
		char x;
		Scanner ulaz = new Scanner(System.in);
		x = ulaz.next().charAt(0);
		if (x == 'y') {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * ispis za kraj igre
	 */
	private static void ispisiPorukuZakrajIgre() {
		System.out.println("Kraj igre.");
	}

	/**
	 * ispis rezultata
	 */
	private static void ispisiRezultat(int a, int b, int c) {
		System.out.println("Broj bodova: " + a + ",  cilj -> " + (c) + " ili više.");
		System.out.println("Broj prostalih poteza: " + b);
		System.out.println();
	}

	/**
	 * Korisnik odabire jednu od ponuđenih matrica
	 * 
	 * @return niz koji sarži broj redova, broj kolona i broj poteza
	 */
	private static int[] odaberiMatricu() {
		char izbor;
		System.out.println("Cilj Igre je odigrati više 'pametnih' poteza, a 'pemetni' potez je potez") ;
		System.out.println("u kojem se desi više podudaranja od tri polja.");
		System.out.println("Odaberi matricu");
		System.out.println(" a) 8x8");
		System.out.println(" b) 8x6");
		System.out.println(" c) 6x9");
		Scanner ulaz = new Scanner(System.in);
		boolean unos = false;
		int brojRedova = 8, brojKolona = 8, brojPoteza = 5;
		izbor = ulaz.next().charAt(0);

		while (!unos) {
			if (izbor == 'a') {
				brojRedova = 8;
				brojKolona = 8;
				brojPoteza = 5;
			} else if (izbor == 'b') {
				brojRedova = 8;
				brojKolona = 6;
				brojPoteza = 5;
			}

			else if (izbor == 'c') {
				brojRedova = 6;
				brojKolona = 9;
				brojPoteza = 5;
			}

			else {
				System.out.println("Unos nije validan, odaberite ponovo ponuđene opcije (a, b, c). ");
				izbor = ulaz.next().charAt(0);
			}

			if (izbor == 'a' || izbor == 'b' || izbor == 'c') {
				// brojPoteza = unesiBrojPoteza();
				unos = true;
			}

		}
		return new int[] { brojRedova, brojKolona, brojPoteza };

	}

	/**
	 * Metoda koja ispisuje matricu
	 */
	private static void ispisiMatricu(int[][] matrica) {
		System.out.print("  ");
		for (int j = 0; j < matrica[0].length; j++) {
			System.out.print(" " + j);
		}
		System.out.println();
		System.out.print("  ");

		for (int j = 0; j < matrica[0].length; j++) {
			System.out.print(" -");
		}
		System.out.println();

		for (int i = 0; i < matrica.length; i++) {
			System.out.print(i + "| ");
			for (int j = 0; j < matrica[i].length; j++) {
				System.out.print(matrica[i][j] + " ");
			}
			System.out.println();
		}
		for (int j = 0; j < matrica[0].length + 2; j++) {
			System.out.print("__");
		}
		System.out.println();
	}

	/**
	 * Korisnik bira kordinate za izmjenu u matrici
	 */
	private static void odaberiPolje(CandyCrush candy) {
		int x1, y1, x2, y2;
		Scanner ulaz = new Scanner(System.in);
		System.out.println("Odaberi kordinate ");
		x1 = ulaz.nextInt();
		y1 = ulaz.nextInt();
		x2 = ulaz.nextInt();
		y2 = ulaz.nextInt();

		if (!candy.provjeriPolja(x1, y1, x2, y2)) {
			System.out.println("Odabir kordinata nije ispravan, unesite ponovo");
			odaberiPolje(candy);
		}

		else {
			System.out.println("Odabrali ste kordinate : " + "(" + x1 + "," + y1 + ") " + "(" + x2 + "," + y2 + ")");
			candy.zamijeniPolja(x1, y1, x2, y2);
		}

	}

	/*
	 * private static int unesiBrojPoteza() {
	 * System.out.println("Unosite broj poteza"); Scanner ulaz = new
	 * Scanner(System.in); int brojPoteza = ulaz.nextInt(); return brojPoteza; }
	 */

}
