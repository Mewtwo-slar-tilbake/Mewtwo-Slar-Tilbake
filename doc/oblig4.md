# Oblig 4

## Project report

### Team roles

The team roles are still the same as in oblig 2 and 3:
- Team lead: Benjamin Godø Mulelid
- Secretary: Fredrik Limi Ballestad
- Developers: Everyone

We have had no issues with these roles, and it would be too late to change anything now.
The role descriptions are still the same:
- Team lead: Sets up meetings, and functions as customer contact.
- Secretary: Records important information from meetings.
- Developers: Develops and tests the features that are agreed upon in meetings.

### Development methodology

Our initial attempt at doing test driven development failed upon realizing that LibGDX is notoriously difficult to test.
We were recently able to create a test application that makes it possible to run headless tests with an OpenGL context.
Until now, every developer has had to test their code manually, and this has not always been done thoroughly.

We found the issues board to be lacking in that it was impossible to add a small description to each issue.
This was solved by creating a separate channel in Discord with explanations for every feature or bug.

### Meetings

See "doc/minutes" for the minutes from our meetings.

### Team dynamics and communication

The communication has been good, and all team members have known what to do.
We have done some pair programming, but we still found that some features are best to work on alone.
Examples of this are setting up the test application and adding interior to the houses.

### Retrospective

We should have figured out how to test LibGDX properly during the earlier stages of the project,
but this was hard as none of the team members had any experience with the library.

Gradle turned out to be a powerful build tool, but Maven would have been easier to set up,
as Gradle was new for all of us. We took a long time to set up gradle tasks properly.

Most of us have about the same number of commits, with some outliers.
The team agrees that everyone was involved in the project, and no members were left behind during the development process.
All members got the help they needed.

Things that we would have done differently:
- We personally do not like LibGDX, since it mixes rendering and update logic. The difficulties with testing it properly is also a drawback.
- Acceptance criteria should have been translated into tests before implementing features.
- Making a Pokémon clone was unnecessarily ambitious, as we could have gotten the same grade with a simple platformer game.
- GitLab requires us to set up runners on our local computers, and this is an enormous drawback. We chose to avoid pipelines entirely because of this restriction.

Things that worked well and that we would do again:
- Gradle works really well for multi-project builds, and we now know how to set it up properly.
- The MVC architecture worked well.
- Our team structure yielded a good process and result.

## Requirements and specification

### User stories

See milestones on GitLab for thorough descriptions of core game features:
https://git.app.uib.no/malicious-malware/kaijumon/-/milestones

### Changes

Since oblig 3, we have made the following changes:
- Added different messages to the battle screen.
- Fixed visual bugs in the battle screen, such as Kaijumons not updating upon switching.
- Added a custom test backend and more tests.
- Added interiors to all houses.
- Updated the help screen.
- Added licenses for all assets.

We found that the game was almost done after oblig 3, so the last few sprints have been dedicated to
polishing the game and meeting the formal requirements. All base mechanics are now implemented.

See https://git.app.uib.no/malicious-malware/kaijumon/-/boards for an overview of all completed features.

### Prioritizing tasks

As mentioned above, we have prioritized meeting the formal requirements, as well as polishing the game a little.
Most requirements were met after oblig 3, but we had not yet figured out how to write tests for LibGDX.
We have also written documentation for all public methods, and cleaned up our abstractions such that all
model, view and controller classes implement their respective interfaces.

### Current state and MVP

The MVP was described as follows:
- An area where the player can battle and befriend Kaijumons.
- NPCs that the player can battle.
- One more challenging NPC that functions like a boss battle.

We chose to abandon the idea of befriending Kaijumons, and we resorted to just trapping them by using a
Kaijuball item. We also have only one NPC that the player can battle. All the base functionality for adding more fights
is in place, but we focused on ensuring that what we had was working properly rather than adding more content.

## Product and code

### SpotBugs
- Some of the spotbugs that remain are false positives where it says that a variable never gets a value due to the fact that we load the value in from a json file via Gson.
- Some of the spotbugs that remain are due to the way libgdx is implemented.

### Known bugs
- Resizing the game window crashes the game on macOS. This issue has persisted throughout the whole process,
and we think it might be an issue with LibGDX or LWJGL.
- Tests may fail if they are executed in a wrong order. This might be the cause of one test initializing the Dialogue System,
and the other tests being stuck waiting for the dialogue to end.

### Fixes

- Fixed a bug where every battle action was performed twice.
- Fixed inconsistent asset paths that caused tests to fail.
- Fixed a bug where saving inside the Kaijucenter would make the player spawn in the ocean when loading.

### Class diagram

See the separate file "diagram4.pdf" in the "doc" folder. This diagram broadly illustrates how the MVC architecture
makes up both the battle and world sections of the game.

### Formal requirements

All formal requirements have been met, except for the abstract factory pattern.
We found that we had no related groups of items which made it necessary to implement this pattern.
Implementing the abstract factory pattern introduces a lot of extra abstraction, and it would be awkward to try
to adjust our project to fit this pattern rather than implementing the correct patterns for our problems.

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
