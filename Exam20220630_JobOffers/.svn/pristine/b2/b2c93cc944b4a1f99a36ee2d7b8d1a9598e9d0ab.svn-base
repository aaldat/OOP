package jobOffers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Candidate {
	
	String name;
	Map<String, Skill> skills = new HashMap<>();
	Map<Skill, Integer> skillRatings = new HashMap<>();
	List<Position> positions = new ArrayList<>();

	public Candidate(String name, Map<String, Skill> candidateSkills) {
		this.name = name;
		this.skills = candidateSkills;
	}

	public String getName() {
		return name;
	}

	public Map<String, Skill> getSkills() {
		return skills;
	}
	
	public List<String> getSkillsList() {
		return skills.keySet().stream()
				.sorted()
				.collect(Collectors.toList());
	}
	
	public void addPosition(Position p) {
		positions.add(p);
	}
	
	public void addRating(Skill s, Integer i) {
		skillRatings.put(s, i);
	}
	
	public int RatingsAvg() {
		int sum = 0;
		int c = 0;
		for(Integer i : skillRatings.values()) {
			sum += i;
			c++;
		}
		return (int)(sum/c);
	}
	
	public boolean isDiscard(Position p) {
		boolean res = false;
		for(Position pos : positions) {
			if(skillRatings.isEmpty()) return true;
			for(int i = 0; i < skillRatings.size(); i++) {
				for(int j = 0; j < pos.getSkills().size(); j++) {
					if(skillRatings.keySet().stream().collect(Collectors.toList()).get(i).getName().equals(p.getSkills().keySet().stream().collect(Collectors.toList()).get(j).getName())) {
						if(skillRatings.values().stream().collect(Collectors.toList()).get(i) < pos.getSkills().values().stream().collect(Collectors.toList()).get(j)) {
							res = true;
		}}}}}		
		return res;
	}
}
