package com.example.nba.dao;

import com.example.nba.model.CreatedTeam;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcCreatedTeamDAO implements CreatedTeamDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcCreatedTeamDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void makeNewTeam(CreatedTeam createdTeam) {

        String sql = "INSERT into created_teams (team_name, team_location, team_salary) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, createdTeam.getNickname(), createdTeam.getLocation(), createdTeam.getTeamSalary());


    }

    @Override
    public int getCreatedTeamIdWithName(String name) {

        String sql = "SELECT * FROM created_teams WHERE team_name ILIKE ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

        if(results.next()){

            return results.getInt("created_team_id");

        }


        return 0;
    }


}
