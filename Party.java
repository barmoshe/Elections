package id314022914_id206921777;

import java.time.LocalDate;
import java.util.Arrays;

public class Party {
	private String name;
	private PoliticalOpinion PoliticalOpinion;
	private LocalDate dateOfEstablishment;
	private Candidate[] candidates;
	private int candidateCounter;
	private int numOfVotes;

	public Party(Party p) throws Exception {
		setName(p.getName());
		this.PoliticalOpinion = p.PoliticalOpinion;
		this.dateOfEstablishment = p.dateOfEstablishment;
		this.candidates = new Candidate[p.candidates.length];
		for (int i = 0; i < candidateCounter; i++) {
			this.candidates[i] = new Candidate(p.candidates[i]);
		}
		this.candidateCounter = p.candidateCounter;
		this.numOfVotes = p.numOfVotes;
	}

	public Party(String name, PoliticalOpinion politicalOpinion) throws Exception {
		setName(name);
		PoliticalOpinion = politicalOpinion;
		this.dateOfEstablishment = LocalDate.now();
		this.candidates = new Candidate[5];
		this.candidateCounter = 0;
		this.numOfVotes = 0;
	}

	public Party() {
	}

	public String getName() {
		return name;

	}

	public boolean setName(String name) throws Exception {
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')))
				throw new Exception(" name must be chars between a-z or A-Z");
			break;
		}
		this.name = name;
		return true;
	}

	public void copyAndMultiplyCandidate() {
		if (candidates[candidates.length - 1] != null) {
			Candidate[] temp = new Candidate[this.candidates.length * 2];
			for (int i = 0; i < this.candidates.length; i++) {
				temp[i] = this.candidates[i];
			}
			this.candidates = temp;
		}
	}

	public boolean cheakIfCandidateExist(Candidate c) {
		for (int i = 0; i < this.candidateCounter; i++) {
			if (this.candidates[i].equals(c)) {
				return true;
			}
		}
		return false;
	}

	public void addCandidate(Candidate c) {
		copyAndMultiplyCandidate();
		if (cheakIfCandidateExist(c))
			return;
		this.candidates[candidateCounter] = c;
		this.candidateCounter = this.candidateCounter + 1;
	}

	public void primeElectionForParty() {

		int numberOfVotesForCand = 0;
		for (int i = 0; i < candidateCounter; i++) {
			numberOfVotesForCand = (int) (Math.random() * (candidateCounter - numberOfVotesForCand));
			candidates[i].setNumOfVotes(numberOfVotesForCand);
		}
		sortCandidates();
	}

	public void sortCandidates() {
		int n = candidateCounter;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (candidates[j].numOfVotes < candidates[j + 1].numOfVotes) {
					Candidate temp = candidates[j];
					candidates[j] = candidates[j + 1];
					candidates[j + 1] = temp;
				}

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Party))
			return false;
		Party temp = (Party) obj;
		if (temp.getName() == this.name)
			return true;
		else
			return false;
	}

	public boolean setNumOfVotes(int temp) {
		this.numOfVotes = temp;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(this.getClass().getSimpleName() + " " + this.name + " info:" + "\n");
		str.append("the date of Establishment: " + this.dateOfEstablishment.toString() + "\n"
				+ "the party Political Opinion: " + this.PoliticalOpinion + "\n" + "the party num of votes"
				+ this.numOfVotes + "\n");
		str.append("the candidates in the party: \n\n");
		for (int i = 0; i < this.candidateCounter; i++) {
			str.append(this.candidates[i].toString() + "\n");
		}

		return str.toString();

	}

	public PoliticalOpinion getPoliticalOpinion() {
		return PoliticalOpinion;
	}

	public LocalDate getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public Candidate[] getCandidates() {
		return candidates;
	}

	public int getCandidateCounter() {
		return candidateCounter;
	}

	public int getNumOfVotes() {
		return numOfVotes;
	}

}