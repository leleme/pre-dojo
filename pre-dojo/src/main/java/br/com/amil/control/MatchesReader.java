package br.com.amil.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.amil.model.Ranking;

/**
 * @author LEANDRO
 *
 */
public class MatchesReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchesReader.class);
	
	private Map<String, List<Ranking>> matches = new HashMap<>(); 

	public MatchesReader() {};

	public MatchesReader(String file) throws Exception {

		try (BufferedReader br = new BufferedReader(new FileReader(file));) {

			String row = br.readLine();
			ParseRow parse = new ParseRow();

			while (row != null) {
				List<Ranking> rankings = new ArrayList<Ranking>();
				Ranking ranking = new Ranking();
				ranking = parse.createRanking(row);
				
				if(ranking != null) {
					if(matches.containsKey(ranking.getMatch())) {
						rankings = matches.get(ranking.getMatch());
					}
					
					rankings.add(ranking);
					matches.put(ranking.getMatch(), rankings);	
				}
				
				row = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			LOGGER.error("Error reading file: ", e);
			throw new Exception("Error reading file: ", e);
		}

	}

	// GETTERS AND SETTERS
	public Map<String, List<Ranking>> getMatches() {
		return matches;
	}

	public void setMatches(Map<String, List<Ranking>> matches) {
		this.matches = matches;
	}
}
