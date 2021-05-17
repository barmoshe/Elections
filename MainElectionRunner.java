package id314022914_id206921777;

import java.util.Scanner;

import id314022914_id206921777.exceptions.BoolCheakException;
import id314022914_id206921777.exceptions.MaskException;
import id314022914_id206921777.exceptions.OutOfBoundException;

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
		System.out.println("10- end current election and start new one");
		System.out.println("11- end program");

		return sc.nextInt();

	}

	public static void menuForAddBallotBox() {

		System.out.println("enter the ballot box that you want : ");
		System.out.println("1- normal ballot box (for soliders and non quarentined)");
		System.out.println("2- army ballot box (for soliders only)");
		System.out.println("3- covid ballot box (querantined pepole only )");
		System.out.println("if you want to cancel press any other key. ");

	}

	public static void addHardCoded(Election e) {
		try {
			e.addPartyHardCoded("Likud", PoliticalOpinion.RIGHT);
			e.addPartyHardCoded("Yesh Atid", PoliticalOpinion.CENTER);
			e.addPartyHardCoded("Yamina", PoliticalOpinion.LEFT);
			e.addBallotBox("Rishon Le Zion", BallotType.FOR_SOLIDERS);
			e.addBallotBox("jaffa", BallotType.REGULAR);
			e.addBallotBox("Kiryat Ono", BallotType.FOR_SICK);
			e.addBallotBox("Tel Aviv", BallotType.FOR_SICK_SOLIDERS);
			e.addCitizenHadCoded("Adi Himembloi", "332233333", 1993);
			e.addSickCitizenHadCoded("Shlomo Artzi", "342233333", 1996, 3);
			e.addSickSoliderHadCoded("Shlomi Shabat", "344443333", 2002, 7);
			e.addSoliderHadCoded("Morgan Shabat", "277723332", 2002);
			e.addCandidateHardCoded("Bar Refaeli", "111111111", 1980, "Likud");
			e.addCandidateHardCoded("Gal Gadot", "111113111", 1945, "Likud");
			e.addCandidateHardCoded("Galya Micheli", "111111121", 1980, "Yesh Atid");
			e.addCandidateHardCoded("Noa Kirel", "121113111", 1984, "Yesh Atid");
			e.addCandidateHardCoded("Benjamin Netanyaho", "111113331", 1980, "Yamina");
			e.addSickCandidateHardCoded("yossi Levi", "121999921", 1984, "Yamina", 5);
			e.addSickCandidateHardCoded("Yossi Benayoun", "111111911", 1980, "Likud", 3);
			e.addSickCandidateHardCoded("Yotam Keren", "121993111", 1984, "Yesh Atid", 0);
			e.addSickCandidateHardCoded("Elor Koren", "111993331", 1980, "Yamina", 8);
			e.addSickCandidateHardCoded("Ofri Maane", "199132212", 1984, "Yamina", 7);
		} catch (Exception x) {
			System.out.println(x.getMessage());

		}

	}

	public static Citizen createCitizen(int yearOfElections) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter name ");
		String name2 = sc.nextLine();
		System.out.println("enter id ");
		String id2 = sc.next();
		System.out.println("enter year of birth ");
		int year2 = sc.nextInt();
		if (checkAge(year2, yearOfElections) == -1) {
			Solider s = new Solider(name2, id2, year2);
			return s;
		} else {
			Citizen c = new Citizen(name2, id2, year2);
			return c;
		}

	}

	public static int checkAge(int YearOfBirth, int yearOfElections) {
		int age = yearOfElections - YearOfBirth;
		if (age >= 18 && age <= 21) {
			return -1;
		}
		return age;
	}

	public static Citizen createSickCitizen(int yearOfElections) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter name ");
		String name2 = sc.nextLine();
		System.out.println("enter id ");
		String id2 = sc.next();
		System.out.println("enter year of birth ");
		int year2 = sc.nextInt();
		System.out.println("enter days of sickness ");
		int days = sc.nextInt();
		if (checkAge(year2, yearOfElections) == -1) {
			SickSolider s = new SickSolider(name2, id2, year2, days);
			return s;
		} else {
			SickCitizen c = new SickCitizen(name2, id2, year2, days);
			return c;
		}

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
			boolean isValid = false;
			int boolchoose2 = 0;
			int select = 0;
			while (!isValid) {
				try {
					if (e.getCitizens().get(i) instanceof Sickable) {
						System.out.println(
								"Dear " + e.getCitizens().get(i).getName() + " enter 1 if you have mask else 0 ");
						boolchoose2 = sc.nextInt();
						if (boolchoose2 != 1 && boolchoose2 != 0)
							throw new BoolCheakException();
					} else
						boolchoose2 = 1;
				} catch (BoolCheakException x) {
					System.out.println(x.getMessage());
				}
				try {
					System.out.println("Dear " + e.getCitizens().get(i).getName()
							+ " choose your vote number.\nIf you do not want to vote please enter 0");
					e.showPartiesNames();
					select = sc.nextInt() - 1;
					if ((select <= -1) || (select >= e.getPartyCounter()))
						throw new OutOfBoundException();

					try {
						if (boolchoose2 == 1) {
							if (e.getCitizens().get(i) instanceof Sickable)
								((Sickable) e.getCitizens().get(i)).setHasMask(true);
							e.getCitizens().get(i).setPartyChosen(e.getParties().get(select).getName());
						} else if (select != -1 || boolchoose2 == 0)
							throw new MaskException();

						isValid = true;
					} catch (MaskException x) {
						System.out.println(x.getMessage());
					}
				} catch (OutOfBoundException x) {
					System.out.println(x.getMessage());
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
			throw new OutOfBoundException();
		p = switchOpinion(choise);

		Party p1 = new Party(name3, p);

		return p1;

	}

	public static Candidate createCandidate(Party p, int yearOfElection) throws Exception {
		Candidate c = new Candidate(createCitizen(yearOfElection));
		c.setPartyBelong(p);
		return c;
	}

	public static SickCandidate createSickCandidate(Party p, int yearOfElection) throws Exception {
		SickCandidate c = new SickCandidate((SickCitizen) createSickCitizen(yearOfElection));
		c.setPartyBelong(p);

		return c;
	}

	public static void main(String[] args) {
		int year, month;
		ElectionsSystem systemE = new ElectionsSystem();
		Scanner sc = new Scanner(System.in);
		int currentElectionIndex = 0;
		boolean isValid1 = false;
		while (!isValid1) {
			System.out.println("enter year for elections");
			year = sc.nextInt();
			System.out.println("enter month for elections");
			month = sc.nextInt();
			try {
				systemE.createElection(year, month);
				isValid1 = true;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		isValid1 = true;
		addHardCoded(systemE.getElections()[currentElectionIndex]);
		int choise = menu();
		while (choise != 11) {
			switch (choise) {
			case 1:
				System.out.println("enter address");
				sc.nextLine();
				String address = sc.nextLine();
				BallotType[] types = BallotType.values();
				for (int i = 0; i < types.length; i++) {
					System.out.println((types[i].ordinal() + 1) + ") " + types[i].name());
				}
				System.out.println("enter number of type from the menu above:");

				try {
					if (systemE.getElections()[currentElectionIndex].addBallotBox(address, types[sc.nextInt() - 1]))
						System.out.println("added succsessfully");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				isValid1 = false;
				while (!isValid1) {
					try {
						System.out.println("enter 1 if you are sick else 0 ");
						int boolchoose1 = sc.nextInt();
						if (boolchoose1 != 1 && boolchoose1 != 0)
							throw new BoolCheakException();
						if (boolchoose1 == 0) {
							if (systemE.getElections()[currentElectionIndex].addCitizens(
									createCitizen(systemE.getElections()[currentElectionIndex].getYearOfElections())))
								System.out.println("added succsessfully");
							isValid1 = true;
						} else if (systemE.getElections()[currentElectionIndex].addCitizens(
								createSickCitizen(systemE.getElections()[currentElectionIndex].getYearOfElections())))
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
						if (systemE.getElections()[currentElectionIndex].addParty(createParty()))
							System.out.println("added succsessfully");
						isValid1 = true;
					} catch (Exception x) {
						System.out.println(x.getMessage());
					}
				}
				break;
			case 4:
				if (systemE.getElections()[currentElectionIndex].getPartyCounter() > 0) {
					isValid1 = false;
					while (!isValid1) {
						try {
							systemE.getElections()[currentElectionIndex].showPartiesNames();
							System.out.println("choose your party number");
							int Chosen4 = sc.nextInt() - 1;

							System.out.println("enter 1 if you are sick else 0 ");
							int boolchoose1 = sc.nextInt();
							if (boolchoose1 != 1 && boolchoose1 != 0)
								throw new BoolCheakException();
							if (boolchoose1 == 0) {
								if (systemE.getElections()[currentElectionIndex].addCandidate(createCandidate(
										systemE.getElections()[currentElectionIndex].getParties().get(Chosen4),
										systemE.getElections()[currentElectionIndex].getYearOfElections())))
									System.out.println("added succsessfully");
							} else {
								if (systemE.getElections()[currentElectionIndex].addCandidate(createSickCandidate(
										systemE.getElections()[currentElectionIndex].getParties().get(Chosen4),
										systemE.getElections()[currentElectionIndex].getYearOfElections())))
									System.out.println("added succsessfully");
							}
							isValid1 = true;
						} catch (Exception x) {
							System.out.println(x.getMessage());
						}
					}
				} else
					System.out.println("there is no parties");
				break;
			case 5:
				systemE.getElections()[currentElectionIndex].showBallotBoxes();
				break;
			case 6:
				systemE.getElections()[currentElectionIndex].showCitizens();
				break;
			case 7:
				systemE.getElections()[currentElectionIndex].showParties();
				break;
			case 8:
				citizensChoose(systemE.getElections()[currentElectionIndex]);
				systemE.getElections()[currentElectionIndex].electionStart();
				break;
			case 9:
				systemE.getElections()[currentElectionIndex].showResult();
				break;
			case 10:
				currentElectionIndex = systemE.getElectionCounter();
				isValid1 = false;
				while (!isValid1) {
					System.out.println("enter year for elections");
					year = sc.nextInt();
					System.out.println("enter month for elections");
					month = sc.nextInt();
					try {
						systemE.createElection(year, month);
						isValid1 = true;
					} catch (Exception ex) {
						ex.getMessage();
					}
				}
				addHardCoded(systemE.getElections()[currentElectionIndex]);

				break;
			case 11:
				System.out.println("end program");
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