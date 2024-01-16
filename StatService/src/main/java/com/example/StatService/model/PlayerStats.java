package com.example.StatService.model;

public class PlayerStats {

    private Long playerId;
    private String playerName;
    private int goalsScored;
    private int assists;
    private int yellowCards;
    // Ajoutez d'autres statistiques au besoin

    public PlayerStats(Long playerId, String playerName, int goalsScored, int assists, int yellowCards) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.goalsScored = goalsScored;
        this.assists = assists;
        this.yellowCards = yellowCards;
        // Initialisez d'autres statistiques au besoin
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    // Ajoutez des getters et setters pour d'autres statistiques si nécessaire
}
