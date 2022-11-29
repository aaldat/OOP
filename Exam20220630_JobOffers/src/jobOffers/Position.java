package jobOffers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {
	
	String name;
	Map<Skill, Integer> skills = new HashMap<>();
	List<Candidate> candidates = new ArrayList<>();

	public Position(String position, Map<Skill, Integer> skillsLvl) {
		this.name = position;
		this.skills = skillsLvl;
	}

	public String getName() {
		return name;
	}

	public Map<Skill, Integer> getSkills() {
		return skills;
	}
	
	public double LevelAvg() {
		int sum = 0;
		int c = 0;
		for(Integer i : skills.values()) {
			sum += i;
			c++;
		}
		return (sum/c);
	}
	
	public void addCandidate(Candidate c) {
		candidates.add(c);
	}
	
	public List<String> getSkillsList() {
		return skills.keySet().stream()
				.map(s -> s.getName())
				.sorted()
				.collect(Collectors.toList());
	}
	
	public List<Candidate> getCandidatesList() {
		return candidates;
	}
	
	public List<String> getCandidatesNameList() {
		return candidates.stream().map((Candidate c) -> c.getName()).sorted().collect(Collectors.toList());
	}
	
	public List<String> getDiscardCandidates() {
		List<String> key = new ArrayList<>();
		for(Candidate c : candidates) {
			if(c.isDiscard(this)) key.add(c.getName()+":"+this.getName());
		}
		return key.stream().sorted().collect(Collectors.toList());
	}
	
	public List<String> getEligibleCandidates() {
		List<String> res = new ArrayList<>();
		for(Candidate c : candidates) {
			if(!c.isDiscard(this)) res.add(c.getName());
		}
		return res;
	}

}
