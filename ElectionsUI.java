package id314022914_id206921777;

import id314022914_id206921777.exceptions.oneOrZeroCheakException;

public interface ElectionsUI {
	void addBallotBoxUI(Election e);

	void addCitizenUI(Election e);

	void addPartyUI(Election e);

	void addCandidateUI(Election e) throws Exception;

	void showAllBallotBoxes(Election e);

	void showAllCitizens(Election e);

	void showAllParties(Election e);

	void startElections(Election e);

	void showElectionsResults(Election currentElection);

	boolean mainProgramRun(Election currentElection) throws Exception;

	ElectionsSystem checkReadFromFile(int x);

	Election setElectionRound(ElectionsSystem elections);

	void readArchivedElections(ElectionsSystem elecArch);
	int oldOrNew() throws oneOrZeroCheakException;
}
