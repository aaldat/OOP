package mountainhuts;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

//import sun.awt.www.content.audio.x_aiff;

/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {

	private String name;
	private List<AltitudeRange> altitudeRanges = new LinkedList<>();
	//private List<Municipality> municipalities = new LinkedList<>();
	private Map<String, Municipality> municipalities = new HashMap<>();
	private List<MountainHut> mountainHuts = new LinkedList<>();
	
	
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name = name;
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		for(String range : ranges) {
			AltitudeRange r = new AltitudeRange(range);
			altitudeRanges.add(r);
		}
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		Optional<AltitudeRange> validRange = altitudeRanges.stream()
		.filter(x -> x.includes(altitude))
		.findFirst();
		if(validRange.isEmpty()) {
			return "0-INF";
		} else {
			return validRange.get().toString();
		}
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province,
												Integer altitude) {
//		return municipalities.stream()
//			   .filter(x -> x.getName().equals(name))
//			   .findFirst()
//			   .orElseGet(() -> {
//				   Municipality m = new Municipality(name, province, altitude);
//				   municipalities.add(m);
//				   return m;
//			   });
		Municipality m = null;
		if(municipalities.containsKey(name)) {
			m = municipalities.get(name);
		} else {
			m = new Municipality(name, province, altitude);
			municipalities.put(name, m);
		}
		return m;
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipalities.values();
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name,
			String category, Integer bedsNumber,
			Municipality municipality) {
		return createOrGetMountainHut(name, null, category, bedsNumber, municipality);
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude,
			String category, Integer bedsNumber,
			Municipality municipality) {
		return mountainHuts.stream()
			   .filter(x -> x.getName().equals(name))
			   .findFirst()
			   .orElseGet(() -> {
				   MountainHut h = new MountainHut(name, altitude, 
						   category, bedsNumber, municipality);
				   mountainHuts.add(h);
				   return h;
			   });
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return mountainHuts;
	}

	/**
	 * Factory methods that creates a new region by loadomg its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region r = new Region(name);
		List<String> lines = readData(file);
		
		String[] headers = lines.remove(0).split(";"); //devo rimuovere la prima linea
		Map<String, Integer> h2i = new HashMap<>();
		
		for(int i=0; i < headers.length; ++i) {
			h2i.put(headers[i], i);
		}
		
		lines.forEach(row -> {
			String[] cells = row.split(";");
			
			String province = cells[h2i.get("Province")];
			String municipalityName = cells[h2i.get("Municipality")];
			Integer municipalityAltitude = 
					Integer.parseInt(cells[h2i.get("MunicipalityAltitude")]);
			
			Municipality m = r.createOrGetMunicipality(municipalityName, 
					province, municipalityAltitude);
		
			String hutName = cells[h2i.get("Name")];
			String altitude = cells[h2i.get("Altitude")];
			String category = cells[h2i.get("Category")];
			Integer bedsNumber = Integer.parseInt(cells[h2i.get("BedsNumber")]);
			
			if(altitude.equals("")) {
				r.createOrGetMountainHut(hutName, category, bedsNumber, m);
			} else {
				r.createOrGetMountainHut(hutName, Integer.parseInt(altitude), 
						category, bedsNumber, m);
			}
		
		});
		
		return r;
	}

	/**
	 * Internal class that can be used to read the lines of
	 * a text file into a list of strings.
	 * 
	 * When reading a CSV file remember that the first line
	 * contains the headers, while the real data is contained
	 * in the following lines.
	 * 
	 * @param file the file name
	 * @return a list containing the lines of the file
	 */
	
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		return municipalities.values().stream()
				.collect(Collectors.groupingBy(
						Municipality::getProvince, 
						//HashMap::new,
						Collectors.counting()
						));
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		return mountainHuts.stream()
				.collect(Collectors.groupingBy(
							x -> x.getMunicipality().getProvince(),
							Collectors.groupingBy(
										x -> x.getMunicipality().getName(),
										Collectors.counting()
									)
						));
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		Map<String, Long> res = mountainHuts.stream()
				.collect(Collectors.groupingBy(
							x -> getAltitudeRange(x),
							Collectors.counting()
						));
		altitudeRanges.stream()
			.map(AltitudeRange::toString)
			.forEach(r -> {
				res.putIfAbsent(r, 0L);
			});
		return res;
	}
	
	private String getAltitudeRange(MountainHut h) {
		if(h.getAltitude().isPresent()) {
			return getAltitudeRange(h.getAltitude().get());
		} else {
			return getAltitudeRange(h.getMunicipality().getAltitude());
		}
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		return mountainHuts.stream()
				.collect(Collectors.groupingBy(
							x -> x.getMunicipality().getProvince(),
							Collectors.summingInt(MountainHut::getBedsNumber)
						));
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		Map<String, Optional<Integer>> res = mountainHuts.stream()
				.collect(Collectors.groupingBy(
							x -> getAltitudeRange(x),
							Collectors.mapping(
									MountainHut::getBedsNumber, 
									Collectors.maxBy(Comparator.naturalOrder())
							)
						));
		altitudeRanges.stream()
			.map(AltitudeRange::toString)
			.forEach(r -> res.putIfAbsent(r, Optional.of(0)));
		return res;
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		return mountainHuts.stream()
				.map(x -> x.getMunicipality().getName())
				.collect(Collectors.groupingBy(
							x -> x,
							TreeMap::new,
							Collectors.counting()
						))
				.entrySet().stream()
				.collect(Collectors.groupingBy(
							Map.Entry::getValue,
							Collectors.mapping(
									Map.Entry::getKey, 
									Collectors.toList()
								)
						));
	}

}
