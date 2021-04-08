package id314022914_id206921777;

import java.util.Scanner;

public class MainElectionRunner {
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "________-menu-________");
		System.out.println("chooes the option:");
		System.out.println("-1- add ballot box");
		System.out.println("-2- add citizen");
		System.out.println("-3- add party");
		System.out.println("-4- add candidate");
		System.out.println("-5- show all ballot boxes");
		System.out.println("-6- show all citizens");
		System.out.println("-7- show all parties");
		System.out.println("-8- election start");
		System.out.println("-9- show results");
		System.out.println("10- exit");
		return sc.nextInt();

	}

	public static void menuForAddBallotBox() {

		System.out.println("enter the ballot box that you want : ");
		System.out.println("1- normal ballot box (for soliders and non quarentined)");
		System.out.println("2- army ballot box (for soliders only)");
		System.out.println("3- covid ballot box (querantined pepole only )");
		System.out.println("if you want to cancel press any other key. ");

	}

	public static Citizen createCitizen() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter name ");
		String name2 = sc.nextLine();
		System.out.println("enter id ");
		String id2 = sc.next();
		System.out.println("enter 1 if you qurentined else 0 ");
		int boolchoose1 = sc.nextInt();
		if (boolchoose1 != 1 && boolchoose1 != 0)
			throw new Exception("must be 1 or 0 ");
		boolean isQuarentined = false;
		if (boolchoose1 == 1)
			isQuarentined = true;
		System.out.println("enter 1 if you have mask else 0 ");
		int boolchoose2 = sc.nextInt();
		if (boolchoose2 != 1 && boolchoose2 != 0)
			throw new Exception("must be 1 or 0 ");
		boolean hasMask = false;
		if (boolchoose2 == 1)
			hasMask = true;
		System.out.println("enter year of birth ");
		int year2 = sc.nextInt();
		Citizen c = new Citizen(name2, id2, isQuarentined, hasMask, year2);

		return c;
	}

	private static PoliticalOpinion switchOpinion(int choise) {
		switch (choise) {
		case 1:
			return PoliticalOpinion.RIGHT;
		case 2:
			return PoliticalOpinion.LEFT;
		case 3:
			return PoliticalOpinion.CENTER;
		default:
			return null;

		}
	}

	private static void citizensChoose(Election e) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < e.getCitizenCounter(); i++) {
			System.out.println("Dear " + e.getCitizens()[i].getName()
					+ " choose your vote number.\nIf you do not want to vote please enter 0");
			e.showPartiesNames();
			int select = sc.nextInt() - 1;
			if (select != -1) {
				boolean isValid = false;
				while (!isValid) {
					try {

						if (!(e.getCitizens()[i].getBallotbox() instanceof BallotBoxForCovid))
							e.getCitizens()[i].setPartyChosen(e.getParties()[select].getName());
						else if (e.getCitizens()[i].getHasMask()) {
							e.getCitizens()[i].setPartyChosen(e.getParties()[select].getName());
							System.out.println("not mask");

						}
						isValid = true;
					} catch (Exception y) {
						System.out.println("enter choise again! ");
						System.out.println("Dear " + e.getCitizens()[i].getName()
								+ " choose your vote number.\nIf you do not want to vote please enter 0");

						select = sc.nextInt() - 1;
					}
				}
			}
		}
	}

	public static Party createParty() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter name ");
		String name3 = sc.nextLine();
		PoliticalOpinion p;
		System.out.println(" enter 1 for RIGHT ,2 for LEFT,3 for CENTER");

		int choise = sc.nextInt();
		if (choise != 1 && choise != 2 && choise != 3)
			throw new Exception("political opinion must be 1 ,2 or 3 ");
		p = switchOpinion(choise);

		Party p1 = new Party(name3, p);

		return p1;

	}

	public static Candidate createCandidate(Party p) throws Exception {
		Candidate c = new Candidate(createCitizen());
		c.setPartyBelong(p);
		return c;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Election e = new Election();

		boolean isValid1 = false;
		while (!isValid1) {
			try {
				System.out.println("enter year for elections");
				int year = sc.nextInt();
				System.out.println("enter month for elections");
				int month = sc.nextInt();
				e = new Election(year, month);
				isValid1 = true;

			} catch (Exception x) {
				System.out.println(x.getMessage());

			}
		}
		try {
			e.addPartyHardCoded("Likud", PoliticalOpinion.RIGHT);
			e.addPartyHardCoded("Yesh Atid", PoliticalOpinion.CENTER);
			e.addPartyHardCoded("Yamina", PoliticalOpinion.LEFT);
			e.addBallotBoxHardCoded("Rishon Le Zion", 2);
			e.addBallotBoxHardCoded("Kiryat Ono", 1);
			e.addBallotBoxHardCoded("Tel Aviv", 3);
			e.addCitizensHadCoded("Adi Himembloi", "332233333", true, true, 1993);
			e.addCitizensHadCoded("Shlomo Artzi", "342233333", false, true, 2002);
			e.addCandidateHardCoded("Bar Refaeli", "111111111", true, false, 1980, "Likud");
			e.addCandidateHardCoded("Gal Gadot", "111113111", false, false, 1945, "Likud");
			e.addCandidateHardCoded("Galya Micheli", "111111121", true, true, 1980, "Yesh Atid");
			e.addCandidateHardCoded("Noa Kirel", "121113111", false, false, 1984, "Yesh Atid");
			e.addCandidateHardCoded("Benjamin Netanyaho", "111113331", false, false, 1980, "Yamina");
			e.addCandidateHardCoded("Ella Levi ", "121999921", false, false, 1984, "Yamina");
			e.addCandidateHardCoded("Yossi Benayoun", "111111911", true, false, 1980, "Likud");
			e.addCandidateHardCoded("Mor Silver", "111113191", false, false, 1945, "Likud");
			e.addCandidateHardCoded("Itay Hason", "111111199", true, true, 1980, "Yesh Atid");
			e.addCandidateHardCoded("Yotam Keren", "121993111", false, false, 1984, "Yesh Atid");
			e.addCandidateHardCoded("Elor Koren", "111993331", false, false, 1980, "Yamina");
			e.addCandidateHardCoded("Ofri Maane", "199132212", false, false, 1984, "Yamina");
		} catch (Exception x) {
			System.out.println(x.getMessage());

		}

		int choise = menu();
		while (choise != 10) {
			switch (choise) {
			case 1:
				menuForAddBallotBox();
				int choise1 = sc.nextInt();
				System.out.println("enter address");
				sc.nextLine();
				String address = sc.nextLine();

				if (e.addBallotBoxHardCoded(address, choise1))
					System.out.println("added succsessfully");
				break;
			case 2:
				isValid1 = false;
				while (!isValid1) {
					try {
						if (e.addCitizens(createCitizen()))
							System.out.println("added succsessfully");
						isValid1 = true;
					} catch (Exception x) {
						System.out.println(x.getMessage());
					}
				}
				break;
			case 3:
				isValid1 = false;
				while (!isValid1) {
					try {
						if (e.addParty(createParty()))
							System.out.println("added succsessfully");
						isValid1 = true;
					} catch (Exception x) {
						System.out.println(x.getMessage());
					}
				}
				break;
			case 4:
				isValid1 = false;
				while (!isValid1) {
					try {
						System.out.println("choose your party number");// *
						e.showPartiesNames();
						if (e.addCandidate(createCandidate(e.getParties()[sc.nextInt() - 1])))
							System.out.println("added succsessfully");
						isValid1 = true;
					} catch (Exception x) {
						System.out.println(x.getMessage());
					}
				}
				break;
			case 5:
				e.showBallotBoxes();
				break;
			case 6:
				e.showCitizens();
				break;
			case 7:
				e.showParties();
				break;
			case 8:
				citizensChoose(e);
				e.electionStart();
				break;
			case 9:
				e.showResult();
				break;
			case 10:
				System.out.println("thank you bye.");
				break;
			default:
				System.out.println("wrong input ,again");
				break;
			}

			choise = menu();
		}
		sc.close();
	}

}