package com.example.StatService.model;

public class TeamStats {

    private Long teamId;
    private String teamName;
    private int matchesPlayed;
    private int wins;
    private int draws;
    // Ajoutez d'autres statistiques au besoin

    public TeamStats(Long teamId, String teamName, int matchesPlayed, int wins, int draws) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.draws = draws;
        // Initialisez d'autres statistiques au besoin
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    // Ajoutez des getters et setters pour d'autres statistiques si nécessaire
}
