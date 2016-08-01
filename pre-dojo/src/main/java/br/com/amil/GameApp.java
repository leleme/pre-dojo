package br.com.amil;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.amil.control.MatchesReader;
import br.com.amil.control.MatchesResult;
import br.com.amil.model.Player;

/**
 * @author LEANDRO
 *
 */
public class GameApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameApp.class);

	private static final String file = "C:/matches.log";

	public static void main(String[] args) {
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
			LOGGER.error("Error reading file: ", e);
			System.out.println(e);
		}

	}

}
