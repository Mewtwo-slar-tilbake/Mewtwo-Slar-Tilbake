# INF112 Project – *Kaijumon*

* Team: *Malicious Malware 2023* (Group 5): *Benjamin Godø Mulelid, Felix Mariendal Kaasa, Fredrik Limi Ballestad, Kaja Wollebæk Skråmo, Maja Jersin Walde, Tobias Meyer Innleggen*
* https://git.app.uib.no/malicious-malware/kaijumon/

## About the game
*«You live in a word filled with Kaijumons, monsters with peculiar powers. Can you catch them and become the best Kaijumon trainer in history?»*

## Compile and run

This project requires Java 17 to compile and run.

### Publish as JAR and run
* Compile with `./gradlew desktop:dist`
* Run JAR with `java -jar desktop/build/libs/desktop-1.0.jar`
* On macOS, run with `java -jar -XstartOnFirstThread desktop/build/libs/desktop-1.0.jar`

### Run without compiling separately
* Run with `./gradlew desktop:run`

## Known issues
The game crashes when resizing the game window to a different aspect ratio.

## Credits
TODO: Add credits when using assets.
