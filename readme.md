# MonsterWars
## Description
There are a set of towns and monsters are wandering in the cities. If or more monsters are meeting in a city they destroy each other and the town. Who will survive?
## Additional information
### How to compile
    mvn clean install
### How to run
    mvn exec:java -Dexec.mainClass=monsterwars.main.Main -Dexec.args="{numberOfMonsters}"
    Where {numberOfMonsters} is a valid positive integer.
