package com.example.nba.dao;


import com.example.nba.model.NbaTeam;
import com.example.nba.model.Player;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeedingDB {

    private File playerFile = new File("salary.txt");

    private File teamFile = new File("team.txt");

    private List<Player> players = new ArrayList<Player>();
    private List<NbaTeam> teams = new ArrayList<>();


    public void add_player(Player player) {
        players.add(player);
    }

    private JdbcTemplate jdbcTemplate;

    public SeedingDB(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void read_players_from_file() {

        try (Scanner scanner = new Scanner(playerFile)) {

            int counter = 0;

            while (scanner.hasNext() & counter < 488) {

                String line = scanner.nextLine();

                String[] arr = line.split(",");

                String nameWithIdToken = arr[1];
                String delim = "\\\\";
                String[] nameArr = nameWithIdToken.split(delim);

                String name = nameArr[0];


                String teamCode = arr[2];
                String salaryAsString = arr[3];
                String salary = salaryAsString.substring(1);
                BigDecimal finalSalary = new BigDecimal(salary).divide(new BigDecimal(1000000));

                try {
                    Player player = new Player(name, finalSalary);
                    player.setNbaTeamCode(teamCode);
                    add_player(player);
                } catch (Exception e) {

                }

                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Whoops can't find that file!");
        }
    }

    public void read_teams_from_file(){

        try(Scanner scanner = new Scanner(teamFile)){
            while(scanner.hasNext()){

                String line = scanner.nextLine();
                String[] arr = line.split(",");

                String teamName = arr[1];
                String salaryAsString = arr[2];
                String salary = salaryAsString.substring(1);
                BigDecimal finalSalary = new BigDecimal(salary).divide(new BigDecimal(1000000));

                try{
                    NbaTeam team = new NbaTeam();
                    team.setTeamSalary(finalSalary);
                    team.setName(teamName);
                    teams.add(team);
                }
                catch(Exception e){

                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Whoops can't find that file!");
        }


    }

    public void fill_players_db(){
        for(Player player: players){
            try{
                String sql = "INSERT into nba_player_salary (player_name, player_salary, team_code) VALUES (?, ?, ?)";
                jdbcTemplate.update(sql, player.getName(), player.getSalary(), player.getNbaTeamCode());
            }
            catch(Exception e){

            }

        }


    }

    public void fill_team_db(){

        for(NbaTeam team: teams){
            try{
                String sql = "INSERT into nba_team_salary (team_name, team_salary) VALUES (?, ?)";
                jdbcTemplate.update(sql, team.getName(), team.getTeamSalary());
            }
            catch(Exception e){

            }

        }

    }

    public Boolean needSeeded(){

        String sql = "SELECT * FROM nba_player_salary";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        if(!results.isBeforeFirst()){
            return true;
        }

        return false;
    }
    }



