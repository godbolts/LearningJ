import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class Ilmaandmed {

	// Kolm massiivi, mis täidetakse failist loetud andmetega
	static String[] kuupäev;
	static String[] kellaaeg;
	static double[] temperatuur;

	public static void loeAndmed(String failitee) {
		//loeb andmed failist nimega "failitee"
		try {
			// Loeme failist kõik read, eeldame et faili kodeering on UTF-8
			List<String> read = Files.readAllLines(Path.of(failitee), StandardCharsets.UTF_8);

			// Määrame massiivide pikkuse
			kuupäev = new String[read.size()];
			kellaaeg = new String[read.size()];
			temperatuur = new double[read.size()];
			for (int i = 0; i < read.size(); i++) {
				// Hakime read tühikute põhjal
				String[] jupid = read.get(i).split(" ");
				// Määrame hakitud jupid vastavatesse massiividesse
				kuupäev[i] = jupid[0];
				kellaaeg[i] = jupid[1];
				temperatuur[i] = Double.parseDouble(jupid[2]);
			}
		} catch (IOException e) {
			// Faili ei leitud või lugemisel esines viga - viskame erindi ja lõpetame töö
			throw new RuntimeException("Faili " + failitee + " lugemisel tekkis viga", e);
		}
	}//loeAndmed

	/**
	 * Näidismeetod (mitte kasutamiseks): leiab antud ajal mõõdetud temperatuuri.
	 *
	 * @param päev Kuupäev kujul aaaa-kk-pp.
	 * @param kell Kellaaeg kujul tt:mm:ss.
	 * @return Temperatuur kuupäeval <b>päev</b> ajal <b>kell</b>, NaN kui andmetest puudu.
	 */
	static double temperatuurValitudPäevalJaKellal(String päev, String kell) {
		for (int i = 0; i < temperatuur.length; i++) {
			if (kuupäev[i].equals(päev) && kellaaeg[i].equals(kell))
				return temperatuur[i];
		}
		return Double.NaN;
	}

	// Õpilase poolt teostatavad meetodid:

	public static double aastaKesk() {

		ArrayList<Double> keskmine = new ArrayList<>();
		for (int i = 0; i < temperatuur.length; i++) {
				keskmine.add(temperatuur[i]);
			}
		double sum = 0;
		for (double temp : keskmine) {
			sum += temp;
		}
		double vastus = sum / keskmine.size();

		return vastus;
	}

	public static double[] aastaMinMax() {
		double vaikseim = Double.MAX_VALUE;
		double suurim = Double.MIN_VALUE;

		for (int i = 0; i < temperatuur.length; i++) {
			if (temperatuur[i] < vaikseim) {
				vaikseim = temperatuur[i];
			}
			if (temperatuur[i] > suurim) {
				suurim = temperatuur[i];
			}
		}

		return new double[]{vaikseim, suurim};
	}

	public static String[] pikimKasvavKahanev() {
		String alg = kuupäev[0] + " " + kellaaeg[0];
		String kaiv = kuupäev[0] + " " + kellaaeg[0];
		int count = 0;
		String[] neg = {"", "", String.valueOf(0)};
		String[] pos = {"", "", String.valueOf(0)};

		for (int i = 0; i < temperatuur.length - 1; i++) {
			if (temperatuur[i] < temperatuur[i + 1]) {
				kaiv = kuupäev[i + 1] + " " + kellaaeg[i + 1];
				count++;
			} else {
				if (count > Double.parseDouble(pos[2])) {
					pos = new String[]{alg, kaiv, String.valueOf(count)};
				}
				alg = kuupäev[i + 1] + " " + kellaaeg[i + 1];
				count = 0;
			}
		}

		for (int i = 0; i < temperatuur.length - 1; i++) {
			if (temperatuur[i] < temperatuur[i + 1]) {
				kaiv = kuupäev[i + 1] + " " + kellaaeg[i + 1];
				count++;
			} else {
				if (count > Double.parseDouble(pos[2])) {
					pos = new String[]{alg, kaiv, String.valueOf(count)};
				}
				alg = kuupäev[i + 1] + " " + kellaaeg[i + 1];
				count = 0;
			}
		}

		if (Double.parseDouble(pos[2]) > Double.parseDouble(neg[2])) {
			return new String[]{pos[0], pos[1]};
		} else {
			return new String[]{neg[0], neg[1]};
		}

	}

	public static double[] kuudeKeskmised() {
		int kuu = 1;
		ArrayList<Double> keskmine = new ArrayList<>();
		ArrayList<Double> keskmised = new ArrayList<>();

		for (int i = 0; i < temperatuur.length; i++) {

			if (Integer.parseInt(kuupäev[i].substring(5, 7)) == kuu && i < (temperatuur.length - 1)) {
				keskmine.add(temperatuur[i]);
			} else {
				double sum = 0;
				for (double temp : keskmine) {
					sum += temp;
				}
				double vastus = sum / keskmine.size();
				keskmised.add(vastus);
				keskmine = new ArrayList<>();
				kuu++;
			}
		}
		double[] result = new double[keskmised.size()];
		for (int i = 0; i < keskmised.size(); i++) {
			result[i] = keskmised.get(i);
		}
		return result;
	}

	public static String vaheMinMaxVähimPäevas() {
		String päev = kuupäev[0].substring(8, 10);
		ArrayList<Double> järjend = new ArrayList<>();
		String[] tähtpäev = {"", String.valueOf(100)};

		for (int i = 0; i < temperatuur.length; i++) {
			if ((kuupäev[i].substring(8, 10)).equals(päev) && i < (temperatuur.length - 1)) {
				järjend.add(temperatuur[i]);
			} else if (i == temperatuur.length - 1) {
				järjend.add(temperatuur[i]);
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe < Double.parseDouble(tähtpäev[1])) {
					tähtpäev = new String[]{kuupäev[i], String.valueOf(vahe)};
				}
			} else {
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe < Double.parseDouble(tähtpäev[1])) {
					tähtpäev = new String[]{kuupäev[i], String.valueOf(vahe)};
				}
				järjend.clear();
				päev = kuupäev[i].substring(8, 10);
				järjend.add(temperatuur[i]);
			}
		}
		String vastus = tähtpäev[0];
		return vastus;
	}

	public static String vaheMinMaxSuurimPäevas() {

		String päev = kuupäev[0].substring(8, 10);
		ArrayList<Double> järjend = new ArrayList<>();
		String[] tähtpäev = {"", String.valueOf(0)};

		for (int i = 0; i < temperatuur.length; i++) {
			if ((kuupäev[i].substring(8, 10)).equals(päev) && i < (temperatuur.length - 1)) {
				järjend.add(temperatuur[i]);
			} else if (i == temperatuur.length - 1) {
				järjend.add(temperatuur[i]);
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe > Double.parseDouble(tähtpäev[1])) {
					tähtpäev = new String[]{kuupäev[i], String.valueOf(vahe)};
				}
			} else {
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe > Double.parseDouble(tähtpäev[1])) {
					tähtpäev = new String[]{kuupäev[i], String.valueOf(vahe)};
				}
				järjend.clear();
				päev = kuupäev[i].substring(8, 10);
				järjend.add(temperatuur[i]);
			}
		}
		String vastus = tähtpäev[0];
		return vastus;
	}

	public static int vaheMinMaxVähimKuus() {

		String kuu = kuupäev[0].substring(5, 7);
		ArrayList<Double> järjend = new ArrayList<>();
		Double[] õige = {0.0, 1000.0};

		for (int i = 0; i < temperatuur.length; i++) {
			if ((kuupäev[i].substring(5, 7)).equals(kuu) && i < (temperatuur.length - 1)) {
				järjend.add(temperatuur[i]);
			} else if (i == temperatuur.length - 1) {
				järjend.add(temperatuur[i]);
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe < õige[1]) {
					õige = new Double[]{Double.parseDouble(kuupäev[i].substring(5, 7)), vahe};
				}
			} else {
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe < õige[1]) {
					õige = new Double[]{Double.parseDouble(kuupäev[i].substring(5, 7)), vahe};
				}
				järjend.clear();
				kuu = kuupäev[i].substring(8, 10);
				järjend.add(temperatuur[i]);
			}
		}
		double doubleValue = õige[0];
		int intValue = (int) doubleValue;
		return intValue;

	}

	public static int vaheMinMaxSuurimKuus() {

		String kuu = kuupäev[0].substring(5, 7);
		ArrayList<Double> järjend = new ArrayList<>();
		Double[] õige = {0.0, 0.0};

		for (int i = 0; i < temperatuur.length; i++) {
			if ((kuupäev[i].substring(5, 7)).equals(kuu) && i < (temperatuur.length - 1)) {
				järjend.add(temperatuur[i]);
			} else if (i == temperatuur.length - 1) {
				järjend.add(temperatuur[i]);
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe > õige[1]) {
					õige = new Double[]{Double.parseDouble(kuupäev[i].substring(5, 7)), vahe};
				}
			} else {
				double vahe = Math.abs(Collections.max(järjend) - (Collections.min(järjend)));
				if (vahe > õige[1]) {
					õige = new Double[]{Double.parseDouble(kuupäev[i].substring(5, 7)), vahe};
				}
				järjend.clear();
				kuu = kuupäev[i].substring(8, 10);
				järjend.add(temperatuur[i]);
			}
		}
		double doubleValue = õige[0];
		int intValue = (int) doubleValue;
		return intValue;

	}

	public static String suurimMiinimum() {
		String päev = kuupäev[0].substring(8, 10);
		ArrayList<Double> järjend = new ArrayList<>();
		String[] tähtpäev = {"", String.valueOf(100)};

		for (int i = 0; i < temperatuur.length; i++) {

			if ((kuupäev[i].substring(8, 10)).equals(päev) && i < (temperatuur.length - 1)) {
				järjend.add(temperatuur[i]);
			} else if (i == temperatuur.length - 1) {
				järjend.add(temperatuur[i]);
				if (Collections.min(järjend) < Double.parseDouble(tähtpäev[1])) {
					tähtpäev = new String[] {kuupäev[i], String.valueOf(Collections.min(järjend))};
				}
			} else {
				if (Collections.min(järjend) < Double.parseDouble(tähtpäev[1])) {
					tähtpäev = new String[] {kuupäev[i], String.valueOf(Collections.min(järjend))};
				}
				järjend.clear();
				päev = kuupäev[i].substring(8, 10);
				järjend.add(temperatuur[i]);
			}
		}
		return tähtpäev[0];

	}

}



