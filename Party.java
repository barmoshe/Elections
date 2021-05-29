package id314022914_id206921777;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Party implements Serializable {
	private String name;
	private PoliticalOpinion PoliticalOpinion;
	private LocalDate dateOfEstablishment;
	private ArrayList<Candidate> candidates;
	private int candidateCounter;
	private int numOfVotes;

	public Party(Party p) throws Exception {
		setName(p.getName());
		this.PoliticalOpinion = p.PoliticalOpinion;
		this.dateOfEstablishment = p.dateOfEstablishment;
		this.candidates = new ArrayList<Candidate>();
		for (int i = 0; i < candidateCounter; i++) {
			this.candidates.add(new Candidate(p.candidates.get(i)));
		}
		this.candidateCounter = p.candidateCounter;
		this.numOfVotes = p.numOfVotes;
	}

	public Party(String name, PoliticalOpinion politicalOpinion) throws Exception {
		setName(name);
		PoliticalOpinion = politicalOpinion;
		this.dateOfEstablishment = LocalDate.now();
		this.candidates = new ArrayList<Candidate>();
		this.candidateCounter = 0;
		this.numOfVotes = 0;
	}

	public Party() {
	}

	public String getName() {
		return name;

	}

	public boolean setName(String name) {

		this.name = name;
		return true;
	}

	public boolean cheakIfCandidateExist(Candidate c) {
		for (int i = 0; i < this.candidateCounter; i++) {
			if (this.candidates.get(i).equals(c)) {
				return true;
			}
		}
		return false;
	}

	public void addCandidate(Candidate c) {
		if (cheakIfCandidateExist(c))
			return;
		this.candidates.add(c);
		this.candidateCounter = this.candidateCounter + 1;
		this.primeElectionForParty();
	}

	public void primeElectionForParty() {

		int numberOfVotesForCand = 0;
		for (int i = 0; i < candidateCounter; i++) {
			numberOfVotesForCand = (int) (Math.random() * (candidateCounter - numberOfVotesForCand));
			candidates.get(i).setNumOfVotes(numberOfVotesForCand);
		}
		Collections.sort(this.candidates);
		
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
		StringBuffer str = new StringBuffer(" " + this.name + " info:" + "\n");
		str.append("the date of Establishment: " + this.dateOfEstablishment.toString() + "\n"
				+ "the party Political Opinion: " + this.PoliticalOpinion + "\n" + "the party num of votes: "
				+ this.numOfVotes + "\n");
		str.append("the candidates in the party: \n");
		for (int i = 0; i < this.candidateCounter; i++) {
			str.append((i + 1) + ") " + this.candidates.get(i).getName() + ", " + this.candidates.get(i).getNumOfVotes()
					+ " candidates voted for him/her\n");
		}

		return str.toString();

	}

	public PoliticalOpinion getPoliticalOpinion() {
		return PoliticalOpinion;
	}

	public LocalDate getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

	public int getCandidateCounter() {
		return candidateCounter;
	}

	public int getNumOfVotes() {
		return numOfVotes;
	}

}