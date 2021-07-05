/**
 * Package Package logika
 */
package Logika;

import java.util.Random;
import java.util.Vector;

/**
 * @author Muhammed Talić
 *
 */
public class CandyCrush {
	private int matrica[][];
	public int brojRedova, brojKolona, daLijePobjedio, brojPodudaranja, brojPoteza, cilj, level;
	Random r = new Random();

	/**
	 * Konstruktor prima tri parametra, postavlja vrijednosti i popunjava matricu random brojevima
	 * @param brojRedova broj redova matrice
	 * @param brojKolona broj kolona matrice
	 * @param brojPoteza broj peteza 
	 */
	public CandyCrush(int brojRedova, int brojKolona, int brojPoteza) {
		super();
		this.brojRedova = brojRedova;
		this.brojKolona = brojKolona;
		this.brojPoteza = brojPoteza;
		this.cilj = brojPoteza * 3 + brojPoteza;
		level = 1;
		matrica = new int[brojRedova][brojKolona];

		popuniMatricu();
		
	}

	/**
	 * Doadavanje random brojeve(1-6) u matricu 
	 */
	private void popuniMatricu() {
		for (int i = 0; i < brojRedova; i++) { 
			for (int j = 0; j < brojKolona; j++) {
				matrica[i][j] = r.nextInt(6) + 1;
			}
		}
	}

	/**
	 * Metoda koja vraća da li je korisnik došao do cilja ili nije
	 * @return True ako je korisnik uspio preći u drugi level
	 */
	public boolean kraj() {
		if (brojPoteza == 0) {
			if (brojPodudaranja >= cilj) {
				daLijePobjedio = 1;
				brojPoteza = 5 + level; // brojPoteza = 5;
				level += 1;
				cilj += 5;
				brojPodudaranja = 0;
				return true;
			}

			else if (brojPodudaranja <= cilj) {
				daLijePobjedio = 0;
				cilj = 20;
				level = 1;
				brojPoteza = 5;
				brojPodudaranja = 0;
				return true;
			}
		}
		return false;
	}

	/**
	 * Metoda koja vraća broj bodova
	 * @return brojPodudaranja
	 */
	public int uzmiBrojBodova() {
		return brojPodudaranja;

	}

	/**
	 * Metoda koja rotira polja
	 * @param x1 X kordinata prvog polja
	 * @param y1 Y kordinata prvog polja
	 * @param x2 X kordinata drugog polja
	 * @param y2 Y kordinata drugog polja
	 */
	public void zamijeniPolja(int x1, int y1, int x2, int y2) {
		int temp = matrica[x1][y1];
		matrica[x1][y1] = matrica[x2][y2];
		matrica[x2][y2] = temp;
		brojPoteza--;

	}

	/**
	 * Metoda koja provjerava ima li podudaranja ako ima postavi negativne vrijednosti(horizontalno pa vertikalno),
	 *  nakon toga prolazi vertikalno kroz matricu i spušta elemente te postavlja nule, onda se poziva druga funkcija 
	 *  koja umjesto nula postavlja radnom brojeve
	 * @return matrica
	 */

	public int[][] formatirajMatricu(int[][] matrica) {
		int m = brojRedova;
		int n = brojKolona;
		boolean uslov = false;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n - 2; j++) {
				int v = Math.abs(matrica[i][j]);
				if (v > 0 && v == Math.abs(matrica[i][j + 1]) && v == Math.abs(matrica[i][j + 2])) {
					matrica[i][j] = matrica[i][j + 1] = matrica[i][j + 2] = -v;
					uslov = true;
				}
			}
		}

		for (int i = 0; i < m - 2; i++) {
			for (int j = 0; j < n; j++) {
				int v = Math.abs(matrica[i][j]);
				if (v > 0 && v == Math.abs(matrica[i + 1][j]) && v == Math.abs(matrica[i + 2][j])) {
					matrica[i][j] = matrica[i + 1][j] = matrica[i + 2][j] = -v;
					uslov = true;
				}
			}
		}

		for (int j = 0; j < n; j++) {
			int r = m - 1;
			for (int i = m - 1; i >= 0; i--) {
				if (matrica[i][j] >= 0) {
					matrica[r--][j] = matrica[i][j];
				}
			}
			for (int i = r; i >= 0; i--) {
				matrica[i][j] = 0;
			}
		}

		brojPodudaranja();
		formatirajMatricu2();
		return uslov ? formatirajMatricu(matrica) : matrica;

	}

	/**
	 * Metoda koja prolazi kroz matricu i postavlja random vrijednost(1-6) na mjestima gdje se nalazi nula
	 */
	public void formatirajMatricu2() {
		for (int k = 0; k < matrica.length; k++) { // generise random brojeve na mjesta gdje je bio drop
			for (int m = 0; m < matrica[k].length; m++) {
				if (matrica[k][m] == 0) {
					matrica[k][m] = r.nextInt(6) + 1;
				}
			}
		}
	}

	/**
	 * Metoda koja broji podudaranja u matrici
	 */
	public void brojPodudaranja() {
		for (int k = 0; k < matrica.length; k++) {
			for (int m = 0; m < matrica[k].length; m++) {
				if (matrica[k][m] == 0) {
					brojPodudaranja += 1;
				}
			}
		}
	}

	/**
	 * Metoda koja vraća matricu
	 * @return matrica
	 */
	public int[][] uzmiMatricu() {
		return matrica;
	}

	/**
	 * Metoda koja provjerava da li je validan potez
	 * @param x1 X kordinata prvog polja
	 * @param y1 Y kordinata prvog polja
	 * @param x2 X kordinata drugog polja
	 * @param y2 Y kordinata drugog polja
	 * @return True ako je potez vallidan
	 */
	public boolean provjeriPolja(int x1, int y1, int x2, int y2) {
		if ((x1 == x2 && y1 == y2) || (x1 < 0 || x1 > brojRedova - 1) || (x2 < 0 || x2 > brojRedova - 1)
				|| (y1 < 0 || y1 > brojKolona - 1) || (y2 < 0 || y2 > brojKolona - 1)
				|| (Math.abs((x1 + y1) - (x2 + y2)) == 2 || Math.abs((x1 + y1) - (x2 + y2)) == 0)) {
			return false;
		} else if (Math.abs(x1 - x2) > 1) {
			return false;
		} else if (Math.abs(y1 - y2) > 1) {
			return false;
		}

		return true;
	}

	/**
	 * Metoda koja se pozivamo prije pocetka igre, kako bi se moguća podudaranja eliminisala
	 * @return matrica
	 */
	public int[][] postaviMatricu(int[][] matrica) {

		int m = brojRedova;
		int n = brojKolona;
		boolean uslov = false;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n - 2; j++) {
				int v = Math.abs(matrica[i][j]);
				if (v > 0 && v == Math.abs(matrica[i][j + 1]) && v == Math.abs(matrica[i][j + 2])) {
					matrica[i][j] = matrica[i][j + 1] = matrica[i][j + 2] = -v;
					uslov = true;
				}
			}
		}

		for (int i = 0; i < m - 2; i++) {
			for (int j = 0; j < n; j++) {
				int v = Math.abs(matrica[i][j]);
				if (v > 0 && v == Math.abs(matrica[i + 1][j]) && v == Math.abs(matrica[i + 2][j])) {
					matrica[i][j] = matrica[i + 1][j] = matrica[i + 2][j] = -v;
					uslov = true;
				}
			}
		}

		for (int j = 0; j < n; j++) {
			int r = m - 1;
			for (int i = m - 1; i >= 0; i--) {
				if (matrica[i][j] >= 0) {
					matrica[r--][j] = matrica[i][j];
				}
			}
			for (int i = r; i >= 0; i--) {
				matrica[i][j] = 0;
			}
		}
		formatirajMatricu2();
		return uslov ? postaviMatricu(matrica) : matrica;

	}

}
