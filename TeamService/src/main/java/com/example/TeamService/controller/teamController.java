package com.example.TeamService.controller;
import com.example.TeamService.model.team;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Api(value = "teams", description = "REST Apis related to team Entity!!!!")

@RestController

public class teamController {

    private static List<team> teamList = new ArrayList<>();

    static {
        teamList.add(new team(1L, "TeamA"));
        teamList.add(new team(2L, "TeamB"));
        teamList.add(new team(3L, "TeamC"));
    }
    @ApiOperation(value = "Get team by id ", response = Iterable.class, tags = "getteambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
    public ResponseEntity<team> getTeam(@PathVariable Long id) {
        Optional<team> optionalTeam = teamList.stream()
                .filter(team -> team.getId().equals(id))
                .findFirst();

        if (optionalTeam.isPresent()) {
            team foundTeam = optionalTeam.get();
            return ResponseEntity.ok(foundTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "post team ", response = Iterable.class, tags = "postteam")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping("/teams/{clubName}")
    public ResponseEntity<team> addTeam(@PathVariable String clubName, @RequestBody team equipe) {
        equipe.setName(clubName); // Si vous souhaitez toujours stocker le nom du club

        // Assurez-vous d'attribuer un ID unique à chaque équipe
        equipe.setId(generateUniqueId());

        teamList.add(equipe);
        return ResponseEntity.ok(equipe);
    }

    // Méthode utilitaire pour générer un ID unique
    private Long generateUniqueId() {
        // Implémentation simplifiée, vous devrez mettre en œuvre une logique plus robuste dans un environnement de production
        return System.currentTimeMillis();
    }
    @ApiOperation(value = "put team  ", response = Iterable.class, tags = "putteambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping("/teams/{clubName}/{id}")
    public ResponseEntity<team> updateTeam(@PathVariable String clubName, @PathVariable Long id, @RequestBody team equipe) {
        Optional<team> optionalTeam = teamList.stream()
                .filter(existingTeam -> existingTeam.getId().equals(id) && existingTeam.getName().equals(clubName))
                .findFirst();

        if (optionalTeam.isPresent()) {
            team existingTeam = optionalTeam.get();
            existingTeam.setName(equipe.getName()); // Mettez à jour les propriétés nécessaires
            return ResponseEntity.ok(existingTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiOperation(value = "delete team by id ", response = Iterable.class, tags = "deleteteambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("/teams/{clubName}/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String clubName, @PathVariable Long id) {
        boolean removed = teamList.removeIf(team -> team.getId().equals(id) && team.getName().equals(clubName));
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }}
}