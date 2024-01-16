package com.example.PlayerService.controller;
import com.example.PlayerService.model.Player;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Api(value = "players", description = "REST Apis related to players Entity!!!!")
@RestController
@RequestMapping("/players")
public class PlayerController {

    //initializing players
    private Map<Long, Player> players = new HashMap<>();

    {
        // Initialize players map with sample data
        Player player1 = new Player(1L, "Joueur1", "Équipe1");
        Player player2 = new Player(2L, "Joueur2", "Équipe2");
        Player player3 = new Player(3L, "Joueur3", "Équipe3");


        players.put(player1.getId(), player1);
        players.put(player2.getId(), player2);
        players.put(player3.getId(), player3);

    }
    @ApiOperation(value = "Get player by id ", response = Iterable.class, tags = "getteambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(players.get(id));
    }

    @ApiOperation(value = "post player ", response = Iterable.class, tags = "postplayer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping()
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        players.put(player.getId(), player);
        return ResponseEntity.ok("Player created successfully");
    }
    @ApiOperation(value = "delete player by id ", response = Iterable.class, tags = "deleteplayerbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayerById(@PathVariable("id") Long id) {
        players.remove(id);
        return ResponseEntity.ok("Player deleted successfully");

    }
    @ApiOperation(value = "put player ", response = Iterable.class, tags = "put player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        Player player = players.get(id);
        if (player != null) {
            player.setName(updatedPlayer.getName());
            player.setTeamName(updatedPlayer.getTeamName());
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
