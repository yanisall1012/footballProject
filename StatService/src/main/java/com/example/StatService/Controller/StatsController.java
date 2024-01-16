package com.example.StatService.Controller;

import com.example.StatService.model.TeamStats;
import com.example.StatService.model.PlayerStats;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Stats", description = "REST Apis related to stats Entity!!!!")@RestController
@RequestMapping("/stats")
public class StatsController {

    // Initialisation des statistiques
    private Map<Long, TeamStats> teamStatsMap = new HashMap<>();
    private Map<Long, PlayerStats> playerStatsMap = new HashMap<>();

    {
        // Initialiser les statistiques avec des données d'exemple
        TeamStats teamStats1 = new TeamStats(1L, "Équipe1", 10, 5, 3);
        TeamStats teamStats2 = new TeamStats(2L, "Équipe2", 8, 4, 2);

        PlayerStats playerStats1 = new PlayerStats(1L, "Joueur1", 20, 10, 5);
        PlayerStats playerStats2 = new PlayerStats(2L, "Joueur2", 15, 8, 4);

        teamStatsMap.put(teamStats1.getTeamId(), teamStats1);
        teamStatsMap.put(teamStats2.getTeamId(), teamStats2);

        playerStatsMap.put(playerStats1.getPlayerId(), playerStats1);
        playerStatsMap.put(playerStats2.getPlayerId(), playerStats2);
    }
    @ApiOperation(value = "Get stats-teams by id ", response = Iterable.class, tags = "GetStatsTeamById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @GetMapping("/team-stats/{teamId}")
    public ResponseEntity<TeamStats> getTeamStats(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamStatsMap.get(teamId));
    }
    @ApiOperation(value = "Get stats-players by id ", response = Iterable.class, tags = "GetStatsPlayerById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @GetMapping("/player-stats/{playerId}")
    public ResponseEntity<PlayerStats> getPlayerStats(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerStatsMap.get(playerId));
    }



}
