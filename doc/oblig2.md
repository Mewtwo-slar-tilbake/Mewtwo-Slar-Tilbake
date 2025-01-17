# Oblig 2

## Project report

### Team roles

All team members currently are developers, with Benjamin Godø Mulelid as team lead, and Fredrik Limi Ballestad as secretary.
We think that adding more roles at this point adds needless complexity to the project,
and we do not have enough capacity to have different departments such as "development", "QA" and "customer service".
The team chose to not have a designated customer contact role. The team lead will function as customer contact if that becomes relevant.

We thought about having separate QA people, but this is difficult as the course requires all team members to write code.
Instead, every developer is responsible for testing their own code, and the code is also reviewed before being merged into the main branch.

Our current roles are the absolute minimum required to make the team work.
The team lead is responsible for setting up meetings, and the secretary records important information from them.
Other than that, all members are developers, meaning that every team member writes, tests, and commits code.

We will not switch team lead or secretary yet, but we may switch at a later stage if the roles are not properly fulfilled,
or if we want to switch things up out of curiosity.

In summary, we like being flexible rather than having fixed roles for everyone. For the moment, we do not intend to change anything about this.

### Team dynamics and communication

We have had good experiences with creating groups of two people that work together on solving a task.
Some groups have gotten tasks that were noticeably larger than those of other groups,
and we will try to break those larger tasks down going forward.
There are no disagreements in the team yet. Discussions regarding both team coordination and development have been friendly and productive.

Communication via Discord works well. We have sometimes forgotten to update the "Issues" board in GitLab,
which has left some confusion regarding who solved which task.
User stories have not been sufficiently descriptive, which makes requested features ambiguous and hard to implement.

### Retrospective

The team has implemented new features consistently, and everyone has had something to work on at all times.
We want to shorten our sprints to one week rather than the two weeks we have been used to.
This is because the release candidate will have to be ready in about a month.

Some features were harder to implement, which is why some features (like saving and drawing the UI) have fewer commits in a longer timespan.
This explains some unevenness in the commits. Team members also have different approaches to committing,
as some only commits after finishing a feature while others commit more frequently during development.
Lastly, pair programming leads to commits being unbalanced, as the work of two people are committed by one of them.
We have included a list of participants in affected merge requests to mitigate this issue.

Areas that we will try to improve are:
- Making more and smaller issues on the "Issues" board. 
- Keep the "Issues" board updated and create more descriptive user stories.
- Announce clearly who is working on what for every sprint.

## Requirements and specification

### Updated user stories

See milestones on GitLab for thorough descriptions of core game features:
https://git.app.uib.no/malicious-malware/kaijumon/-/milestones

### Changes

Since oblig 1, we have made the following changes:
- Added a proper map using a tile set.
- Added collisions and boundaries to the map.

Other features that are under development are
- Creating a battle system with a separate battle screen.
- Creating NPCs that walk around the map.
- Switching between world view and battle view.

Our sprints start and end on Tuesdays, which is why most features are still in development.
We have also had great difficulty with implementing the Kaijumon model and battle system in a simple manner,
which is why this feature is still under development.

See https://git.app.uib.no/malicious-malware/kaijumon/-/boards for an overview of all features in development.

### Prioritizing tasks

We have prioritized the core game mechanics and visuals:
- Rendering a tile map and a player.
- NPCs (no interaction for now)
- Player movement and interaction with the world.
- Battle system.
- Mechanics for switching between world view and battle view.
- Start and pause menu.

Combining these features results in a simple game without any progression, but we think that adding progression is easy after the core features are done.
Features are implemented in a logical order. Rendering the player and tile map is implemented before implementing player movement, for instance.

After finishing the battle system and world view, we will start adding NPCs which can be battled.
These battles will have different difficulties, and they will force the player to become better.
All of these mechanics depend on the core mechanics, which is why we will wait until the base game is completed.

### Current state and MVP

The MVP was described as follows:
- An area where the player can battle and befriend Kaijumons.
- NPCs that the player can battle.
- One more challenging NPC that functions like a boss battle.

The game now consists of an area where the player can encounter Kaijumons and NPCs.
It is not yet possible to befriend Kaijumons. The player does not have an inventory to track its Kaijumons.

A boss battle has not yet been added, but that is easy to add after implementing the base game mechanics.

We will try to keep the MVP as it is.

## Product and code

### Known bugs
- the camera can be partially outside the map if a player spawns close enough to the border of the map.
- the movement of the player sprite can somtimes be a bit stuttery.
- some directional input keys are prioritized over others.
- there is a tile in front of the stairs north of the spawn which blocks the player from entering even though it should be clear. it is not blocked in the tiled program.

### Fixes

- fixed a camera bug where the camera could move outside the map.
    - still need to fix the bug where the spawns partially outside the map
- fixed a bug where the player could walk outside the map 

### Class diagram

See the separate file in the "doc" folder.

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

- Camera test:
    - check that the camera will not move beyond the game map.
    - first move towards the middle of the map such that the camera only sees the map, then walk towards any edge and check that the camera stops. (this test does not include the case where the camera spawned with some of it outside the map)
