/**
 * 
 */
package br.com.amil.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.amil.model.Gun;
import br.com.amil.model.Player;
import br.com.amil.model.Ranking;

/**
 * @author LEANDRO
 *
 */
public class ParseRow {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MatchesReader.class);
	
	private String match;
	
	public ParseRow() {};
	
	public Ranking createRanking(String row) throws Exception {
		Ranking ranking = new Ranking();
		
		if(validateRow(row)) {
			try {
				ranking.setMatch(match);
				ranking.setDate(parseDate(row));
				ranking.setGun(parseGun(row));
				ranking.setKiller(parseKiller(row));
				ranking.setDead(parseDead(row));
			} catch (Exception e) {
				LOGGER.error("Error create object Status: ", e);
				throw new Exception("Error create object Status: ", e);
			}
			
			return ranking;
		} else {
			return null;
		}
	};
	
	private Date parseDate(String row) throws Exception {
		Date date;
		String dateStr = row.substring(0, 19);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
		try {
			date = (Date) sdf.parse(dateStr);
		} catch (ParseException e) {
			LOGGER.error("Error parse Date: ", e);
			throw new Exception("Error parse Date: ", e);
		}
		
		return date;
	}
	
	private Gun parseGun(String row) throws Exception {
		Gun gun = new Gun();
		try {
			gun.setName(row.substring(row.lastIndexOf(" "), row.length()));	
		} catch (Exception e) {
			LOGGER.error("Error parse Gun: ", e);
			throw new Exception("Error parse Gun: ", e);
		}
		
		return gun;
	}
	
	private Player parseKiller(String row) throws Exception {
		Player player = new Player();
		
		try {
			player.setName(row.substring(row.indexOf("- ") +2, row.lastIndexOf(" killed ")));
		} catch (Exception e) {
			LOGGER.error("Error parse Player Killer: ", e);
			throw new Exception("Error parse Player Killer: ", e);
		}	
		
		return player;
	}
	
	private Player parseDead(String row) throws Exception {
		Player player = new Player();
		
		try {
			String name = row.substring(row.indexOf(" killed ") +8, row.lastIndexOf(" "));
			String[] dead = name.split(" ");
			player.setName(dead[0]);
		} catch (Exception e) {
			LOGGER.error("Error parse Player Killer: ", e);
			throw new Exception("Error parse Player Killer: ", e);
		}	
		
		return player;
	}
	
	private boolean validateRow(String row) {
		if (row == null || row.isEmpty()) {
			return false;
		}
		
		if (row.toUpperCase().contains(" HAS STARTED") || row.toUpperCase().contains(" HAS ENDED")) {
			match = row.substring((row.toUpperCase().indexOf(" MATCH ") +7), row.toUpperCase().lastIndexOf(" HAS ")).trim();
			return false;
		}
		
		return true;
	}

}
