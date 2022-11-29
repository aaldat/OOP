package jobOffers; 
import java.util.*;
import java.util.stream.Collectors;


public class JobOffers  {
	
	Map<String, Skill> skillsList = new HashMap<>();
	Map<String, Position> positionsList = new HashMap<>();
	Map<String, Candidate> candidates = new HashMap<>();
	Map<String, Consulent> consulents = new HashMap<>();

//R1
	public int addSkills (String... skills) {
		for(String s : skills) {
			if(!skillsList.containsKey(s)) {
				Skill sk = new Skill(s);
				skillsList.put(sk.getName(), sk);
			} else {
				
			}
		}
		return skillsList.size();
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		if(positionsList.get(position) != null) {
			throw new JOException("Il posto di lavoro e' gia' stato inserito.");
		} else {
			Map<Skill, Integer> skillsLvl = new HashMap<>();
			for(String s : skillLevels) {
				String[] skillsLevels = s.split(":");
				if(!skillsList.containsKey(skillsLevels[0])) throw new JOException("Skill non esistente.");
				if(Integer.parseInt(skillsLevels[1]) < 4 || Integer.parseInt(skillsLevels[1]) > 8) throw new JOException("Range invalido");
				skillsLvl.put(skillsList.get(skillsLevels[0]), Integer.parseInt(skillsLevels[1]));
			}
			Position p = new Position(position, skillsLvl);
			positionsList.put(position, p);
			return (int)Math.round(p.LevelAvg());
		}	
	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		if(candidates.containsKey(name)) throw new JOException("Candidato gia' inserito.");
		Map<String, Skill> candidateSkills = new HashMap<>();
		for(String s : skills) {
			if(!skillsList.containsKey(s)) throw new JOException("Skill non esistente.");
			candidateSkills.put(s, skillsList.get(s));
		}
		Candidate c = new Candidate(name, candidateSkills);
		candidates.put(name, c);
		return candidateSkills.size();
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		if(!candidates.containsKey(candidate)) throw new JOException("Candidato non inserito.");
		List<String> key = new ArrayList<>(); List<String> val = new ArrayList<>();
		Candidate c = candidates.get(candidate);
		for(String s : positions) {
			if(!positionsList.containsKey(s)) 	throw new JOException("Il posto di lavoro non esistente.");
			Position p = positionsList.get(s);
			if(!p.getSkillsList().stream().allMatch(string -> c.getSkillsList().contains(string))) throw new JOException("Il candidato non ha tutti i requisiti.");
			p.addCandidate(c); c.addPosition(p);
			key.add(candidate); val.add(s);
		}
		List<String> newVal = val.stream().sorted().collect(Collectors.toList()); val.clear();
		for(int i = 0; i<newVal.size(); i++) val.add(key.get(i)+":"+newVal.get(i));
		return val;
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
		Map<String, List<String>> map = positionsList.values().stream().sorted(Comparator.comparing(Position::getName)).filter(p -> p.getCandidatesList().size() > 0).collect(Collectors.toMap((Position p) -> p.getName(), (Position p) -> p.getCandidatesNameList()));
		TreeMap<String, List<String>> newRes = new TreeMap<>(); newRes.putAll(map);
		return newRes;
	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		if(consulents.containsKey(name)) throw new JOException("Consulente gia' inserito.");
		Map<String, Skill> consulentSkills = new HashMap<>();
		for(String s : skills) {
			if(!skillsList.containsKey(s)) throw new JOException("Skill non esistente.");
			consulentSkills.put(s, skillsList.get(s));
		}
		Consulent c = new Consulent(name, consulentSkills);
		consulents.put(name, c);
		return consulentSkills.size();
	}
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {
		Consulent cs = consulents.get(consultant);
		Candidate cd = candidates.get(candidate);
		if(!consulents.containsKey(cs.getName())) throw new JOException("Consulente non esistente.");
		if(!candidates.containsKey(cd.getName())) throw new JOException("Candidato non esistente.");
		int notIn = 0;
		for(String s : cd.getSkillsList()) {
			if(!cs.getSkillsList().contains(s)) notIn++;
		}
		if(notIn > 0) throw new JOException("Il consulente non ha tutti i requisiti.");
		for(String s : skillRatings) {
			String[] skillsLevels = s.split(":");
			if(Integer.parseInt(skillsLevels[1]) < 4 || Integer.parseInt(skillsLevels[1]) > 10) throw new JOException("Range invalido");
		}
		for(String s : skillRatings) {
			String[] skillsLevels = s.split(":");
			cd.addRating(skillsList.get(skillsLevels[0]), Integer.parseInt(skillsLevels[1]));
		}
		return (cd.RatingsAvg());
	}
	
//R4
	public List<String> discardApplications() {
		return positionsList.values().stream().flatMap(p -> p.getDiscardCandidates().stream()).sorted().collect(Collectors.toList());
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		return positionsList.get(position).getEligibleCandidates().stream().sorted().collect(Collectors.toList());
	}
	

	
}

		
