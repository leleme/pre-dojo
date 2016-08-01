/**
 * 
 */
package br.com.amil.test;

import java.util.List;

import org.junit.Test;

import br.com.amil.control.MatchesReader;
import br.com.amil.control.MatchesResult;
import br.com.amil.model.Player;

/**
 * @author LEANDRO
 *
 */
public class TestGameApp {
	
	private static final String file = "C:/matches.log";

	@Test
	public void test() {
		try {
			MatchesReader reader = new MatchesReader(file);
			MatchesResult.setMatches(reader.getMatches());

			for (String key : reader.getMatches().keySet()) {
				System.out.println("=========== MATCH " + key + " ===============");
				List<Player> players = MatchesResult.topPlayer(key);

				System.out.println("PLAYER         | Kill | Dead ");

				for (int i = 0; i < players.size(); i++) {
					String row = String.format("%-15s", players.get(i).getName()) + "|"
							+ String.format("%-6s", players.get(i).getKills()) + "|"
							+ String.format("%-6s", players.get(i).getDeaths());
					System.out.println(row);
				}

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
