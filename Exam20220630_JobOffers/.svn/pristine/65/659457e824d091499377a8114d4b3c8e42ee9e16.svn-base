package jobOffers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Consulent {
	
	String name;
	Map<String, Skill> skills = new HashMap<>();

	public Consulent(String name, Map<String, Skill> candidateSkills) {
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

}
