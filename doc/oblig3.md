# Oblig 3

## Project report

### Team roles

We will continue with the same roles as before, which is:
- Team lead: Benjamin Godø Mulelid
- Secretary: Fredrik Limi Ballestad
- Developers: Everyone

The team is happy with the current roles, and we find that our roles enable us to develop efficiently.
Role descriptions are the same as before, with the team lead setting up meetings as well as functioning as customer contact,
while the secretary records important information from meetings.

### Team dynamics and communication

We do no longer strictly follow the rule of having two people work on each task.
Some smaller tasks or tasks that required a lot of research were impractical to cooperate on,
such as configuring Gradle settings or setting up the initial battle system.
In cases where only one developer has implemented a feature, that developer has been asked
to present their work on the following meeting, keeping the team up to date.

We have started tagging bugs with the "bug" label in GitLab, and we try to handle them first.
Discord works fine for coordinating meetings and discussing implementation details.

### Retrospective

We have implemented many new features, and we are now closing in on the MVP.
Our new features are listed under "changes" below.

We have tried to fix the unevenness in commits by letting the people with fewer total commits be
the ones that commit when pair programming. This will hopefully result in an even distribution of commits for oblig 4.

Areas that we will try to improve are:
- Distributing commits more evenly.
- Prioritize fixing bugs before developing new features.
- Adding documentation for all public methods, interfaces and classes.
- Keeping the documentation updated when modifying the code.

## Requirements and specification

### User stories

See milestones on GitLab for thorough descriptions of core game features:
https://git.app.uib.no/malicious-malware/kaijumon/-/milestones

### Changes

Since oblig 2, we have made the following changes:
- Added a battle system, with the ability to attack, use items, switch Kaijumon or flee.
- Added an inventory system (currently without GUI).
- Added a proper player sprite with animations.
- Added areas inside buildings.
- Added a "how to play" screen.
- Added NPCs and a dialogue system.
- Cleaned up gradle build files.

Features that are under development are:
- Befriending Kaijumons, and making them join your crew.
- Leveling up Kaijumons, such that their stats increase.
- GUI for managing inventory.

Our priority for the next sprint is to write tests to ensure that the already implemented functionality is working
according to the specifications. We have encountered some bugs that are mentioned under "known bugs" below.

See https://git.app.uib.no/malicious-malware/kaijumon/-/boards for an overview of all features in development.

### Prioritizing tasks

We have tried to prioritize tasks that meet the requirements to our project, such as:
- Having a help screen which tells the user how to play the game.
- Having at least one power-up.
- Compiling the project with Java SE 19 on Windows, Mac and Linux.

We have done this while implementing new features to meet the MVP. Our goal now is to prioritize bugs
so that we can present a more polished product at the end of the semester.

### Current state and MVP

The MVP was described as follows:
- An area where the player can battle and befriend Kaijumons.
- NPCs that the player can battle.
- One more challenging NPC that functions like a boss battle.

The base game is almost complete, and we can soon add progression to our game.
What's missing is the ability to befriend Kaijumons.

We find the MVP to be a good basis for further expansion, but we think that beyond the scope of this project.

## Product and code

### Known bugs
- The opposing Kaijumon sometimes appears with lower health during wild encounters.
- Resizing the game window crashes the game on macOS.
- Saving inside the Kaijucenter and then loading spawns the player in the ocean.

### Fixes

- Fixed JUnit tests not working with newer versions of Java.
- Fixed camera inconsistencies when walking along the edges of the map.

### Class diagram

See the separate file "diagram3.pdf" in the "doc" folder. This diagram broadly illustrates how the MVC architecture
makes up both the battle and world sections of the game.

### Manual tests

- Save game test:
    - start the game and click new game.
    - move away from the spawn location.
    - Press the "s" key to save the game.
    - exit the game, and then start it again.
    - click "load game" and verify that the spawn is where you were when you saved the game.

- New game test:
    - after the save game test, make sure that starting a new game will spawn you at the original spawn (by the beach) and not the saved game spawn.

- Test that you can walk behind objects.
    - walk to any building or tree and see if you can walk behind it such that the graphics appear in front of the player sprite.

- Test that the "quit game" button in the main menu works.

- Verify that pressing "how to play" shows the help screen.

- Camera test:
    - check that the camera will not move beyond the game map.
    - first move towards the middle of the map such that the camera only sees the map, then walk towards any edge and check that the camera stops. (this test does not include the case where the camera spawned with some of it outside the map)

- Battle tests:
    - attack the opposing Kaijumon and verify that its health declines.
    - use an item and verify that it has been used (use health potion to see the effect).
    - switch Kaijumon and verify that your active Kaijumon has been switched.
    - flee and verify that the battle ends immediately.
