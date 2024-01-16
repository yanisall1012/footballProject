package com.example.MatchService.Controller;

import com.example.MatchService.model.Match;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Api(value = "matchs", description = "REST Apis related to match Entity!!!!")
@RestController
@RequestMapping("/matches")
public class MatchController {

    private Map<Long, Match> matches = new HashMap<>();
    {
        Match match1 = new Match(1L, "Équipe 1 vs Équipe 2", 1L, 2L);
        Match match2 = new Match(2L, "Équipe 3 vs Équipe 4", 3L, 4L);

        matches.put(match1.getId(), match1);
        matches.put(match2.getId(), match2);

    }
    @ApiOperation(value = "Get Match by id ", response = Iterable.class, tags = "getmatchbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })


    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(matches.get(id));
    }

    @ApiOperation(value = "delete match by id ", response = Iterable.class, tags = "deletematchbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatchById(@PathVariable("id") Long id) {
        matches.remove(id);
        return ResponseEntity.ok("Match deleted successfully");

    }
    @ApiOperation(value = "post match  ", response = Iterable.class, tags = "post match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping
    public ResponseEntity<String> createMatch(@RequestBody Match match) {
        matches.put(match.getId(), match);
        return ResponseEntity.ok("Match created successfully");
    }
    @ApiOperation(value = "put match ", response = Iterable.class, tags = "putmatch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match updatedMatch) {
        Match match = matches.get(id);
        if (match != null) {
            match.setName(updatedMatch.getName());
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
