package com.example.MatchService.Controller;

import com.example.MatchService.model.Match;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/matches")
@Api(value = "match", description = "REST Apis related to match Entity!!!!")
public class MatchController {

    private Map<Long, Match> matches = new HashMap<>();
    {
        Match match1 = new Match(1L, "Équipe 1 vs Équipe 2", 1L, 2L);
        Match match2 = new Match(2L, "Équipe 3 vs Équipe 4", 3L, 4L);

        matches.put(match1.getId(), match1);
        matches.put(match2.getId(), match2);

    }
    @ApiOperation(value = "Get Match by id ", response = Iterable.class, tags = "GetMatchById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @HystrixCommand(fallbackMethod = "fallbackGetMatchById")
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(matches.get(id));
    }

    private ResponseEntity<Match> fallbackGetMatchById(Long id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }

    @ApiOperation(value = "delete match by id ", response = Iterable.class, tags = "DeleteMatchById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @HystrixCommand(fallbackMethod = "fallbackDeleteMatchById")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatchById(@PathVariable("id") Long id) {
        matches.remove(id);
        return ResponseEntity.ok("Match deleted successfully");
    }

    private ResponseEntity<String> fallbackDeleteMatchById(Long id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Fallback: Unable to delete match");
    }

    @ApiOperation(value = "post match  ", response = Iterable.class, tags = "PostMatch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @HystrixCommand(fallbackMethod = "fallbackCreateMatch")
    @PostMapping
    public ResponseEntity<String> createMatch(@RequestBody Match match) {
        matches.put(match.getId(), match);
        return ResponseEntity.ok("Match created successfully");
    }

    private ResponseEntity<String> fallbackCreateMatch(Match match) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Fallback: Unable to create match");
    }

    @ApiOperation(value = "put match by id", response = Iterable.class, tags = "PutMatchById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match updatedMatch) {
        Match match = matches.get(id);
        if (match != null) {
            match.setName(updatedMatch.getName());
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Match> fallbackUpdateMatch(Long id, Match updatedMatch) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}
