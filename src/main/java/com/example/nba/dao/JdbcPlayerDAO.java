package com.example.nba.dao;

import com.example.nba.model.CreatedTeam;
import com.example.nba.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlayerDAO implements PlayerDAO{

    @Autowired
    CreatedTeamDAO createdTeamDAO;

    private JdbcTemplate jdbcTemplate;

    public JdbcPlayerDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void addPlayerToCreatedTeam(int player_id, CreatedTeam createdTeam) {

        int createdTeamId = createdTeamDAO.getCreatedTeamIdWithName(createdTeam.getNickname());



        String sql = "INSERT INTO created_team_players (player_id, created_team_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, player_id, createdTeamId);

    }

    @Override
    public void removePlayerFromCreatedTeam(int player_id, CreatedTeam createdTeam) {
        int createdTeamId = createdTeamDAO.getCreatedTeamIdWithName(createdTeam.getNickname());

        String sql = "DELETE FROM created_team_players WHERE player_id = ? AND created_team_id = ?";

        jdbcTemplate.update(sql, player_id, createdTeamId);

    }

    @Override
    public Player getPlayerBasedOnName(String name) {

        String sql = "SELECT * FROM nba_player_salary WHERE player_name ILIKE ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

        Player playerFound;

        if(results.next()){

            String playerName = results.getString("player_name");
            BigDecimal salary = results.getBigDecimal("player_salary");
            int playerId = results.getInt("player_id");

            playerFound = new Player(playerName, salary);
            playerFound.setPlayerId(playerId);
            return playerFound;

        }

        return null;
    }

    @Override
    public Player getPlayerBasedOnPlayerId(int id) {
        String sql = "SELECT * FROM nba_player_salary WHERE player_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        Player playerFound;

        if(results.next()){

            String playerName = results.getString("player_name");
            BigDecimal salary = results.getBigDecimal("player_salary");


            playerFound = new Player(playerName, salary);
            playerFound.setPlayerId(id);
            return playerFound;

        }

        return null;
    }


    @Override
    public List<Player> getTenPlayersUnderSalaryAmount(BigDecimal salaryAmt) {

        List<Player> returnList = new ArrayList<Player>();

        String sql = "SELECT * FROM nba_player_salary WHERE player_salary < ? LIMIT 10";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, salaryAmt);

        while(results.next()){

            Player playerFound;
            String playerName = results.getString("player_name");
            BigDecimal salary = results.getBigDecimal("player_salary");
            int playerId = results.getInt("player_id");

            playerFound = new Player(playerName, salary);
            playerFound.setPlayerId(playerId);
            returnList.add(playerFound);



        }


        return returnList;
    }

    @Override
    public List<Player> getTenCheapestPlayers() {
        List<Player> returnList = new ArrayList<Player>();

        String sql = "SELECT * FROM nba_player_salary ORDER BY player_salary ASC LIMIT 10";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){

            Player playerFound;
            String playerName = results.getString("player_name");
            BigDecimal salary = results.getBigDecimal("player_salary");
            int playerId = results.getInt("player_id");

            playerFound = new Player(playerName, salary);
            playerFound.setPlayerId(playerId);
            returnList.add(playerFound);



        }


        return returnList;
    }

    @Override
    public List<Player> getTenMostExpensivePlayers() {
        List<Player> returnList = new ArrayList<Player>();

        String sql = "SELECT * FROM nba_player_salary ORDER BY player_salary DESC LIMIT 10";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){

            Player playerFound;
            String playerName = results.getString("player_name");
            BigDecimal salary = results.getBigDecimal("player_salary");
            int playerId = results.getInt("player_id");

            playerFound = new Player(playerName, salary);
            playerFound.setPlayerId(playerId);
            returnList.add(playerFound);



        }


        return returnList;
    }
}
