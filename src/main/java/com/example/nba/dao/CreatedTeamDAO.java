package com.example.nba.dao;

import com.example.nba.model.CreatedTeam;

public interface CreatedTeamDAO {

    public void makeNewTeam(CreatedTeam createdTeam);

    public int getCreatedTeamIdWithName(String name);
}
