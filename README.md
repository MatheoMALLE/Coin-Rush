# Coin-Rush
Coin-Rush est un donjon-crawler développé en Java où le joueur doit explorer des donjons pour récupérer des pièces, tout en combattant des monstres et en évitant les pièges disséminés à travers les niveaux.

## 🛠️ Roadmap
- creation of the game engine and the main loop
- management of the playable character (movement, collisions)
- level design (“worlds”) and integration of collectibles
- addition of monsters and the combat system
- addition of traps
- packaging the game into an executable `.jar` file

## 🧰 Skills required
- Java
- JavaFX

---
## 📖 Documentation
### Start the game
From the project root directory, run:
```bash
java -jar monde_2.jar
```

### Compiling from Source
If you want to recompile the project from the `src/` folder:
```bash
javac -d bin src/*.java
```

## 📁 Project Structure
```
Coin-Rush/
├── bin/          # Compiled files (.class)
├── doc/          # Project documentation
├── niveaux/      # Files for the game's various levels/worlds
├── src/          # Java source code
├── monde_2.jar   # Game executable (world 2)
└── README.md
```
