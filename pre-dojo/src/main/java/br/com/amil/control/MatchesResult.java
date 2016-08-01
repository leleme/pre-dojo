/**
 * 
 */
package br.com.amil.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.amil.model.Player;
import br.com.amil.model.Ranking;

/**
 * @author LEANDRO
 *
 */
public class MatchesResult {
	
	private static final String IGNORED_PLAYER = "<WORLD>";
	private static Map<String, List<Ranking>> matches;
	private static Map<String, Player> result = new HashMap<String, Player>();
	
	public static List<Player> topPlayer(String match) {
		result = new HashMap<String, Player>();
		List<Player> players = new ArrayList<Player>();
		List<Ranking> rankings = matches.get(match);
		
		for(int i = 0; i < rankings.size(); i++) {
			addKill(rankings.get(i).getKiller());
			addDead(rankings.get(i).getDead());
		}
		
		for(String key : result.keySet()) {
			players.add(result.get(key));
		}
		
		return players;
		
	}
	
	private static void addKill(Player player) {
		Player killer = result.get(player.getName());
		if(!IGNORED_PLAYER.equals(player.getName())) {
			if(killer == null) {
				player.setKills(1);
				result.put(player.getName(), player);
			} else {
				killer.setKills(killer.getKills() +1);
				result.put(killer.getName(), killer);
			}	
		}
	}
	
	private static void addDead(Player player) {
		Player dead = result.get(player.getName());
		
		if(dead == null) {
			player.setDeaths(1);
			result.put(player.getName(), player);
		} else {
			dead.setDeaths(dead.getDeaths() +1);
			result.put(dead.getName(), dead);
		}
	}

	// GETTERS AND SETTERS
	public static Map<String, List<Ranking>> getMatches() {
		return matches;
	}

	public static void setMatches(Map<String, List<Ranking>> matches) {
		MatchesResult.matches = matches;
	}

}
