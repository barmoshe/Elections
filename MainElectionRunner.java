package Elections;

import java.util.Scanner;

public class MainElectionRunner {
	public static void menu() {
		System.out.println("-menu-");
		System.out.println("chooes the option:");
		System.out.println("1- add ballot box");
		System.out.println("2- add citizen");
		System.out.println("3- add party");
		System.out.println("4- add candidate");
		System.out.println("5- show all ballot boxes");
		System.out.println("6- show all citizens");
		System.out.println("7- show all parties");
		System.out.println("8- election start");
		System.out.println("9- show results");
		System.out.println("10- exit");
	}

	public static void menuForAddBallotBox() {

		System.out.println("enter the ballot box that you want : ");
		System.out.println("1- normal ballot box (for soliders and non quarentined)");
		System.out.println("2- army ballot box (for soliders only)");
		System.out.println("3- covid ballot box (querantined pepole only )");
		System.out.println("if you want to cancel press any other key. ");

	}

	public static Citizen createCitizen() {

		Scanner sc = new Scanner(System.in);
		System.out.println("enter name ");
		String name2 = sc.nextLine();
		System.out.println("enter id ");
		String id2 = sc.nextLine();
		System.out.println("enter 1 if you qurentined else 0 ");
		int boolchoose1 = sc.nextInt();
		boolean isQuarentined = false;
		if (boolchoose1 == 1)
			isQuarentined = true;
		System.out.println("enter 1 if you have mask else 0 ");
		int boolchoose2 = sc.nextInt();
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

	public static Party createParty() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter name ");
		String name3 = sc.nextLine();
		PoliticalOpinion p;

		System.out.println(" enter 1 for RIGHT ,2 for LEFT,3 for CENTER");
		int choise = sc.nextInt();
		p = switchOpinion(choise);
		Party p1 = new Party(name3, p);

		return p1;
	}

	public static Candidate createCandidate(Party p) {

		Candidate c = new Candidate(createCitizen());
		c.setPartyBelong(p);
		return c;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * System.out.println("enter year for elections"); int year = sc.nextInt();
		 * System.out.println("enter month for elections"); int month = sc.nextInt();
		 */
		Election e = new Election(2020, 2);
		e.addPartyHardCoded("likud", PoliticalOpinion.RIGHT);
		e.addPartyHardCoded("yeshatid", PoliticalOpinion.CENTER);
		e.addPartyHardCoded("benet", PoliticalOpinion.LEFT);
		e.addBallotBoxHardCoded("kiryatono", 1);
		e.addBallotBoxHardCoded("rishon", 2);
		e.addBallotBoxHardCoded("telaviv", 3);
		e.addCitizensHadCoded("shlomi", "332233333", true, true, 1993);
		e.addCitizensHadCoded("shlomo", "342233333", false, true, 2002);
		e.addCandidateHardCoded("bar", "111111111", true, false, 1980, "likud");
		e.addCandidateHardCoded("ELKOUBI", "111113111", false, false, 1345, "likud");
		e.addCandidateHardCoded("galya", "111111121", true, true, 1980, "yeshatid");
		e.addCandidateHardCoded("gabi", "121113111", false, false, 1884, "yeshatid");
		e.addCandidateHardCoded("ori", "111113331", false, false, 1980, "benet");
		e.addCandidateHardCoded("ella", "121113221", false, false, 1884, "benet");

		menu();
		int choise = sc.nextInt();
		while (choise != 10) {
			switch (choise) {
			case 1:
				System.out.println("enter address");
				String address = sc.next();
				menuForAddBallotBox();
				int choise1 = sc.nextInt();

				e.addBallotBoxHardCoded(address, choise1 - 1);
				break;
			case 2:
				e.addCitizens(createCitizen());
				break;
			case 3:
				e.addParty(createParty());
				break;
			case 4:
				System.out.println("choose your party number");
				e.showParties();
				e.addCandidate(createCandidate(e.parties[sc.nextInt() - 1]));

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
				for (int i = 0; i < e.citizenCounter; i++) {
					System.out.println("choose your vote number if you do not want to vote please enter 0");
					e.showParties();
					int select = sc.nextInt() - 1;
					if (select != -1)
						e.citizens[i].partyChosen = e.parties[select].getName();
				}
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
			menu();
			choise = sc.nextInt();
		}

	}

}