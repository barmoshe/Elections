package Elections;

import java.time.LocalDate;
import java.util.Arrays;

public class Party {
	protected String name;
	protected PoliticalOpinion PoliticalOpinion;
	protected LocalDate dateOfEstablishment;
	protected Candidate[] candidates;
	protected int candidateCounter;
	protected int numOfVotes;

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

	public void setName(String name) throws Exception {
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')))
				throw new Exception(" name must be chars between a-z or A-Z");
			break;
		}
		this.name = name;
	}

	public void copyAndMultiplyCandidate() {
		if (candidates[candidates.length - 1] != null) {
			Candidate[] temp = new Candidate[this.candidates.length * 2];
			for (int i = 0; i < this.candidates.length; i++) {
				temp[i] = this.candidates[i];
			}
			this.candidates = temp;
			System.out.println("the arry is doubled");
		}
	}

	public boolean cheakIfCandidateExist(Candidate c) {
		for (int i = 0; i < this.candidateCounter; i++) {
			if (this.candidates[i].equals(c)) {
				System.out.println("exist");// *
				return true;
			}
		}
		return false;
	}

	public void addCandidate(Candidate c) {
		copyAndMultiplyCandidate();
		if (cheakIfCandidateExist(c))
			return;
		if (c.ballotbox == null)
			System.out.println("666");
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
				if (candidates[j].numOfVotes > candidates[j + 1].numOfVotes) {
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

	public void setNumOfVotes(int temp) {
		this.numOfVotes = temp;
	}

	@Override
	public String toString() {
		return "Party [name=" + name + ", PoliticalOpinion=" + PoliticalOpinion + ", dateOfEstablishment="
				+ dateOfEstablishment + ", candidates=" + Arrays.toString(candidates) + ", candidateCounter="
				+ candidateCounter + ", numOfVotes=" + numOfVotes + "]";
	}

}