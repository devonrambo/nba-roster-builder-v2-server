package com.example.nba.controller;

import com.example.nba.dao.CreatedTeamDAO;
import com.example.nba.dao.NbaTeamDAO;
import com.example.nba.dao.PlayerDAO;
import com.example.nba.dao.SeedingDB;
import com.example.nba.model.CreatedTeam;
import com.example.nba.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.dbcp.BasicDataSource;


import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    NbaTeamDAO nbaTeamDAO;

    @Autowired
    CreatedTeamDAO createdTeamDAO;

    @Autowired
    PlayerDAO playerDAO;


    @RequestMapping(path = "/seed", method = RequestMethod.GET)
    public boolean seedDataBase(){

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/nba_v2");
        ds.setUsername("postgres");
        ds.setPassword("postgres1");

        SeedingDB seed = new SeedingDB(ds);

        if(seed.needSeeded()){
            seed.read_teams_from_file();
            seed.read_players_from_file();
            seed.fill_team_db();
            seed.fill_players_db();
            return true;

        }
        return false;
    }

    @RequestMapping (path = "/new-team", method = RequestMethod.POST)
    public void createNewTeam(@RequestBody CreatedTeam createdTeam){

        createdTeamDAO.makeNewTeam(createdTeam);
    }

    @RequestMapping(path = "/player-name/{player_name}", method = RequestMethod.GET)
    public Player getPlayerByPlayerName(@PathVariable String player_name){

        return playerDAO.getPlayerBasedOnName(player_name);

    }

    @RequestMapping(path = "/player/{player_id}", method = RequestMethod.GET)
    public Player getPlayerByPlayerName(@PathVariable int player_id){

        return playerDAO.getPlayerBasedOnPlayerId(player_id);

    }

    @RequestMapping(path = "/players-under-salary/{salary}", method = RequestMethod.GET)
    public List<Player> getPlayersUnderSalary(@PathVariable BigDecimal salary){

        return playerDAO.getTenPlayersUnderSalaryAmount(salary);
    }

    @RequestMapping(path = "/players/cheapest", method = RequestMethod.GET)
    public List<Player> getTenCheapestPlayers(){

        return playerDAO.getTenCheapestPlayers();
    }

    @RequestMapping(path = "/players/most-expensive", method = RequestMethod.GET)
    public List<Player> getTenMostExpensivePlayers(){

        return playerDAO.getTenMostExpensivePlayers();
    }


    @RequestMapping (path = "/add-player-to-team/{player_id}", method = RequestMethod.POST)
    public void addPlayerToCreatedTeam(@PathVariable int player_id, @RequestBody CreatedTeam createdTeam){

        playerDAO.addPlayerToCreatedTeam(player_id, createdTeam);

    }

    @RequestMapping (path = "/remove-player-from-team/{player_id}", method = RequestMethod.DELETE)
    public void removePlayerFromCreatedTeam(@PathVariable int player_id, @RequestBody CreatedTeam createdTeam){

        playerDAO.removePlayerFromCreatedTeam(player_id, createdTeam);

    }

}
