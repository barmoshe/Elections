package id314022914_id206921777;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import id314022914_id206921777.exceptions.oneOrZeroCheakException;
import id314022914_id206921777.exceptions.outOfRangeInputException;
import id314022914_id206921777.exceptions.MaskException;
import id314022914_id206921777.exceptions.OutOfBoundException;
import id314022914_id206921777.exceptions.TooYoungToBeCandidateException;
import id314022914_id206921777.exceptions.noPartiesException;

public class MainElections implements ElectionsUI {
	public static void main(String[] args) throws Exception {
		MainElections electionsM = new MainElections();
		ElectionsSystem systemE = electionsM.checkReadFromFile(electionsM.oldOrNew());
		Election currentElection = electionsM.setElectionRound(systemE);
		boolean bool = true;
		do {
			bool = electionsM.mainProgramRun(currentElection);
			if (bool) {
				currentElection = electionsM.newElections(systemE);
				electionsM.addHardCoded(currentElection);
			} else
				bool = false;
		} while (bool);
		System.out.println("Terminating program.");
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Elections.dat"));
		outFile.writeObject(systemE);
		outFile.close();
		System.out.println("Terminated.");

	}

	@Override
	public Election setElectionRound(ElectionsSystem systemE) {
		Scanner sc = new Scanner(System.in);
		Election currentElections = null;
		if (systemE.getElections().size() == 0) {
			currentElections = newElections(systemE);
			addHardCoded(currentElections);
			return currentElections;
		}
		System.out.println("Would you like to run a previouse election round? (true \\ false)");
		boolean answer2 = readBool();
		if (answer2) {
			System.out.println("The avaible elections rounds are:");
			int index = 1;
			int answer1 = 0;
			for (Election el1 : systemE.getElections()) {
				System.out.println((index++) + ") " + el1.getMonth() + "\\" + el1.getYear());
			}
			System.out.println("Please enter the desired index of the elections round:");
			boolean inputCheck = true;
			while (inputCheck) {
				try {
					answer1 = sc.nextInt();
					if (answer1 > 0 && answer1 <= systemE.getElections().size())
						systemE.printElectionsRound((answer1 - 1));
					else
						throw new outOfRangeInputException();
					inputCheck = false;
					currentElections = systemE.getElections().get(answer1 - 1);
					inputCheck = false;
				} catch (InputMismatchException e) {
					System.out.println("Wrong input.\nPlease enter a number from the list.");
					sc.nextLine(); // cleans the buffer.
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Wrong input.\nPlease enter an index from the list.");
				} catch (outOfRangeInputException e) {
					System.out.println("Wrong input.\nPlease enter an index from the list.");
				}
			}
			return currentElections;
		} else

		{
			currentElections = newElections(systemE);
			addHardCoded(currentElections);
			return currentElections;
		}
	}

	@Override
	public void readArchivedElections(ElectionsSystem systemE) {
		Scanner sc = new Scanner(System.in);
		int answer1 = 1;
		boolean answer2, print = true;
		while (print) {
			System.out.println("Would you like to print archived elections results? (true \\ false)");
			answer2 = readBool();
			if (answer2) {
				System.out.println("The avaible elections rounds are:");
				int index = 1;
				for (Election el1 : systemE.getElections()) {
					System.out.println((index++) + ") " + el1.getMonth() + "\\" + el1.getYear());
				}
				System.out.println("Please enter the desired index of the elections round:");
				boolean inputCheck = true;
				while (inputCheck) {
					try {
						answer1 = sc.nextInt();
						if (answer1 > 0 && answer1 <= systemE.getElections().size())
							systemE.printElectionsRound((answer1 - 1));
						else
							throw new outOfRangeInputException();
						inputCheck = false;
					} catch (InputMismatchException e) {
						System.out.println("Wrong input.\nPlease enter a number from the list.");
						sc.nextLine(); // cleans the buffer.
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Wrong input.\nPlease enter an index from the list.");
					} catch (outOfRangeInputException e) {
						System.out.println("Wrong input.\nPlease enter an index from the list.");
					}
				}
			} else
				print = false;
		}
	}

	@Override
	public ElectionsSystem checkReadFromFile(int choice) {

		if (choice == 1) {
			try {
				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("Elections.dat"));
				ElectionsSystem fileArchive = (ElectionsSystem) inFile.readObject();
				readArchivedElections(fileArchive);
				inFile.close();
				return fileArchive;
			} catch (FileNotFoundException e) {
				System.out.println("File not found. Please start a new election round.");
				ElectionsSystem elections = new ElectionsSystem();
				return elections;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		ElectionsSystem elections = new ElectionsSystem();
		return elections;
	}

	public int oldOrNew() throws oneOrZeroCheakException {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.println("if you want to load previous election from file enter 1 else 0 :");
			try {
				choice = sc.nextInt();
				if (choice != 0 && choice != 1)
					throw new oneOrZeroCheakException();
				isValid = true;
			} catch (oneOrZeroCheakException e) {
				System.out.println(e.getMessage());
			}
		}
		return choice;

	}

	public Election newElections(ElectionsSystem elections) {
		int year, month;
		Scanner sc = new Scanner(System.in);
		boolean isValid1 = false;
		while (!isValid1) {
			System.out.println("enter year for elections");
			year = sc.nextInt();
			System.out.println("enter month for elections");
			month = sc.nextInt();
			try {
				elections.createElection(year, month);

				isValid1 = true;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return elections.getElections().get(elections.getElections().size() - 1);
	}

	public int menu() {
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
		return sc.nextInt();

	}

	public void menuForAddBallotBox() {

		System.out.println("enter the ballot box that you want : ");
		System.out.println("1- normal ballot box (for soliders and non quarentined)");
		System.out.println("2- army ballot box (for soliders only)");
		System.out.println("3- covid ballot box (querantined pepole only )");
		System.out.println("if you want to cancel press any other key. ");

	}

	public void addHardCoded(Election e) {
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

	@Override
	public void addBallotBoxUI(Election current) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter address");
		sc.nextLine();
		String address = sc.nextLine();
		BallotType[] types = BallotType.values();
		for (int i = 0; i < types.length; i++) {
			System.out.println((types[i].ordinal() + 1) + ") " + types[i].name());
		}
		System.out.println("enter number of type from the menu above:");

		try {
			if (current.addBallotBox(address, types[sc.nextInt() - 1]))
				System.out.println("added succsessfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Citizen createCitizen(int yearOfElections) throws Exception {
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

	public int checkAge(int YearOfBirth, int yearOfElections) {
		int age = yearOfElections - YearOfBirth;
		if (age >= 18 && age <= 21) {
			return -1;
		}
		return age;
	}

	public Citizen createSickCitizen(int yearOfElections) throws Exception {
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

	@Override
	public void addCitizenUI(Election current) {
		Scanner sc = new Scanner(System.in);

		boolean isValid1 = false;
		while (!isValid1) {
			try {
				System.out.println("enter 1 if you are sick else 0 ");
				int boolchoose1 = sc.nextInt();
				if (boolchoose1 != 1 && boolchoose1 != 0)
					throw new oneOrZeroCheakException();
				if (boolchoose1 == 0) {
					if (current.addCitizens(createCitizen(current.getYearOfElections())))
						System.out.println("added succsessfully");
					isValid1 = true;
				} else if (current.addCitizens(createSickCitizen(current.getYearOfElections())))
					System.out.println("added succsessfully");
				isValid1 = true;

			} catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}

	private PoliticalOpinion switchOpinion(int choise) {
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

	public Party createParty() throws Exception {
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

	@Override
	public void addPartyUI(Election current) {
		boolean isValid1 = false;
		while (!isValid1) {
			try {
				if (current.addParty(createParty()))
					System.out.println("added succsessfully");
				isValid1 = true;
			} catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}

	public Candidate createCandidate(Party p, int yearOfElection) throws Exception {

		Candidate c = new Candidate(createCitizen(yearOfElection));
		System.out.println("999");
		c.setPartyBelong(p);
		return c;
	}

	public SickCandidate createSickCandidate(Party p, int yearOfElection) throws Exception {
		SickCandidate c = new SickCandidate((SickCitizen) createSickCitizen(yearOfElection));
		c.setPartyBelong(p);

		return c;
	}

	@Override
	public void addCandidateUI(Election current) throws Exception {
		Scanner sc = new Scanner(System.in);

		if (current.getPartyCounter() > 0) {
			boolean isValid1 = false;
			while (!isValid1) {
				try {
					System.out.println("enter your year of birth");
					int age1 = checkAge(sc.nextInt(), current.getYear());
					if (age1 < 22)
						throw new TooYoungToBeCandidateException();
					current.showPartiesNames();
					System.out.println("choose your party number");
					int Chosen4 = sc.nextInt() - 1;

					System.out.println("enter 1 if you are sick else 0 ");
					int boolchoose1 = sc.nextInt();
					if (boolchoose1 != 1 && boolchoose1 != 0)
						throw new oneOrZeroCheakException();
					if (boolchoose1 == 0) {
						if (current.addCandidate(
								createCandidate(current.getParties().get(Chosen4), current.getYearOfElections())))
							System.out.println("added succsessfully");
					} else {
						if (current.addCandidate(
								createSickCandidate(current.getParties().get(Chosen4), current.getYearOfElections())))
							System.out.println("added succsessfully");
					}
					isValid1 = true;
				} catch (TooYoungToBeCandidateException x) {
					System.out.println(x.getMessage());
					return;
				} catch (Exception x) {
					System.out.println(x.getMessage());
				}
			}
		} else
			throw new noPartiesException();
	}

	@Override
	public void showAllBallotBoxes(Election current) {
		current.showBallotBoxes();

	}

	@Override
	public void showAllCitizens(Election current) {
		current.showCitizens();
	}

	@Override
	public void showAllParties(Election current) {
		current.showParties();
	}

	private void citizensChoose(Election e) {
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
							throw new oneOrZeroCheakException();
					} else
						boolchoose2 = 1;
				} catch (oneOrZeroCheakException x) {
					System.out.println(x.getMessage());
				}
				try {
					System.out.println("Dear " + e.getCitizens().get(i).getName()
							+ " choose your vote number.\nIf you do not want to vote please enter 0");
					e.showPartiesNames();
					select = sc.nextInt() - 1;
					if ((select < -1) || (select >= e.getPartyCounter()))
						throw new OutOfBoundException();
					else if (select != -1) {

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
							isValid = true;

						}
					} else if (select + 1 == 0) {
						isValid = true;
						e.getCitizens().get(i).setPartyChosen(null);
					}
				} catch (OutOfBoundException x) {
					System.out.println(x.getMessage());
				}
			}
		}

	}

	@Override
	public void startElections(Election current) {
		citizensChoose(current);
		current.electionStart();
	}

	@Override
	public void showElectionsResults(Election current) {
		current.showResult();
	}

	@Override
	public boolean mainProgramRun(Election current) throws Exception {
		boolean bool = true;
		do {
			int select = menu();

			switch (select) {
			case 1:
				addBallotBoxUI(current);
				break;
			case 2:
				addCitizenUI(current);
				break;
			case 3:
				addPartyUI(current);
				break;
			case 4:
				addCandidateUI(current);
				break;
			case 5:
				showAllBallotBoxes(current);
				break;
			case 6:
				showAllCitizens(current);
				break;
			case 7:
				showAllParties(current);
				break;
			case 8:
				startElections(current);
				break;
			case 9:
				showElectionsResults(current);
				break;
			case 10:
				System.out.println("Would you like to start a new elections round? (true \\ false)");
				return readBool();

			default:
				System.out.println("wrong input ,again");
				break;

			}

		} while (bool);
		return false;

	}

	private static boolean readBool() {
		Scanner sc = new Scanner(System.in);
		System.out.println("if true 1 else if false 0");
		int choise = sc.nextInt();
		if (choise == 1)
			return true;
		else if (choise == 0)
			return false;
		else
			System.out.println("wrong input");
		return readBool();
	}

}
