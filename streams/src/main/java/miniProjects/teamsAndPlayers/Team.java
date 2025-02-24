package miniProjects.teamsAndPlayers;

import java.util.List;

public record Team(String teamName, List<Player> playerList, int ranking, int wins, int losses) {
}
