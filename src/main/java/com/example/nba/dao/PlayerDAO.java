package com.example.nba.dao;

import com.example.nba.model.CreatedTeam;
import com.example.nba.model.Player;

import java.math.BigDecimal;
import java.util.List;

public interface PlayerDAO {

    public void addPlayerToCreatedTeam(int player_id, CreatedTeam createdTeam);

    public void removePlayerFromCreatedTeam(int player_id, CreatedTeam createdTeam);

    public Player getPlayerBasedOnName(String name);

    public Player getPlayerBasedOnPlayerId(int id);

    public List<Player> getTenPlayersUnderSalaryAmount(BigDecimal salaryAmt);

    public List<Player> getTenCheapestPlayers();

    public List<Player> getTenMostExpensivePlayers();
}
