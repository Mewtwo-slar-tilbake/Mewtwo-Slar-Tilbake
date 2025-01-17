# INF112 Project – *Kaijumon*

* Team: *Malicious Malware 2023* (Group 5): *Benjamin Godø Mulelid, Felix Mariendal Kaasa, Fredrik Limi Ballestad, Kaja Wollebæk Skråmo, Maja Jersin Walde, Tobias Meyer Innleggen*
* https://git.app.uib.no/malicious-malware/kaijumon/

## About the game
*«You live in a word filled with Kaijumons, monsters with peculiar powers. Can you catch them and become the best Kaijumon trainer in history?»*

The controls are as follows:
- Walk around: Arrow keys
- Pause game: ESC
- Save game: S
- Interact with NPC: X
- Progress dialogue: Enter
- Change selection in battle: Arrow keys
- Confirm selection in battle: X or Enter
- Cancel selection in battle: C

## Compile, run and test

Compile and run this project with Java 17 through 19.
Run the provided jar by cd-ing into the project directory and running `java -jar artifacts/kaijumon.jar`.

### Publish as JAR and run
* Compile with `./gradlew desktop:dist`
* Run JAR with `java -jar desktop/build/libs/desktop-1.0.jar`

### Run without compiling separately
* Run with `./gradlew desktop:run`

### Run tests
* Run tests with `./gradlew test`

## Known issues
The game crashes when resizing the game window to a different aspect ratio.
The battle screen does not indicate the effectiveness of attacks, which creates the illusion
that some Kaijumons are invincible.

## Credits
TODO: Add credits when using assets.
* [tileset.png (outdoor tileset)](https://www.deviantart.com/chaoticcherrycake/art/Pokemon-Tileset-From-Public-Tiles-32x32-623246343) with [list of credits](https://www.deviantart.com/chaoticcherrycake/journal/Credits-for-Tiles-367931482)
* [interior.png (indoor tileset)](https://www.deviantart.com/akizakura16/art/Hi-res-Interior-General-Tileset-588725678) made by [Akizakura16](https://www.deviantart.com/akizakura16) and [shiney570](https://www.deviantart.com/shiney570) 
* [Dream Raid Part I.mp3](https://opengameart.org/content/dream-raid-cinematic-action-soundtrack) made by [Matthew Pablo](http://www.matthewpablo.com), (CC BY 3.0)
* [awesomeness.wav](https://opengameart.org/content/menu-music) made by [mrpoly](https://opengameart.org/users/mrpoly), (CC0 1.0)
* [September (Master).mp3](https://opengameart.org/content/peaceful-music) made by [el-corleo](https://opengameart.org/users/el-corleo), (CC BY 3.0)
* [stepwood_2.wav](https://opengameart.org/content/foot-walking-step-sounds-on-stone-water-snow-wood-and-dirt) made by [Jute](https://opengameart.org/content/foot-walking-step-sounds-on-stone-water-snow-wood-and-dirt) for DungeonHack, (GNU General Public License)
* [TV No Video Example.png](https://opengameart.org/content/giant-2d-tv) made by [Burning Pants](https://opengameart.org/users/burningpants), (CC0 1.0)
* [landscape.png](https://opengameart.org/content/landscape) made by [PepperRacoon](https://opengameart.org/users/pepperracoon), (CC BY 3.0) 
* [player character (edited by us (CC0 1.0)) ](https://opengameart.org/content/the-revolution-sprites) made by [Fabzy](https://opengameart.org/users/fabzy), (CC BY-SA 3.0)
* gamepaused.png is made by us (CC0 1.0)
* All kaijumon sprites are made by us with the help of AI (CC0 1.0)
