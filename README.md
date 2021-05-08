# NBA Roster Builder v2 Server




### Description
A new iteration of the previous NBA Roster Builder. This version uses Spring Boot to host a server that the frontend will consume.

### Motivation
After learning more technologies, I wanted to build a more thourough application implementing the model-view-controller design pattern.

### Methods
A postgreSQL database contains tables for player salaries, team salaries, created teams, and tables to link them. An API endpoint is hit which seeds the database if it is empty. JdbcDAO classes were made to interact with the database and deserialize info into model classes.
API endpoints have been created to retrieve information on players and teams, and store/edit created teams. 

### Next Steps
More API endpoints need to be built. I want to add a table that connects NBA teams to their specific players, rather than only having the teams total cap spent, so that I can return info on a player/team basis. (For example: How much money do the Knicks spend on their starting 5? How does that compare to the rest of the league? How does your created team stack up?)
<br />
<br />

Once finished this will be the entire backend of the Roster Builder. I plan on practing a frontend framework, building a site to consume these APIs, and hosting both on Heroku.
