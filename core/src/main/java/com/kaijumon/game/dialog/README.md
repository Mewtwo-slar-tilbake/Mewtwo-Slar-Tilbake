# Dialogue system explanation
    This dialog system consists of 3 main parts:

        - DialogSystem
            - This is the main class that is used to create and manage dialogs.
            - It is responsible for creating and managing the dialog nodes.
                - It creates the dialog nodes by loading in data from a json file. and validates that the data is legal
            - It is also responsible for sending key input to the dialog traverser, as well as calling the render method of the dialog traverser.
        
        - DialogNode
            - A dialog is made up of multiple dialog nodes. each dialog node representing a character saying something.
            - Dialog nodes are made up of some text, and a list of options (can be empty) that the player can choose from to continue the dialog to the next dialogNode.

        - DialogNodeTraverser
            - This class represents a single conversation. a new instance of this class is created every time a dialog is started.
            - This class is responsible for keeping track of the current dialog node, and rendering the dialog nodes.
            - It is also responsible for handling the input from the player, and moving to the next dialog node.

# How to create new dialog node
    - in the assets/dialogue.json file, add a new entry to the json array.
    - the entry has to have a title, some text and a unique id.
    - the entry can also have a list of options (can be empty, or left out entirely), each option has to have a title and a id that references another entry in the json array.

    - the format of an entry is as follows:
            {
                "title": "title of the dialog node",
                "text": "text of the dialog node",
                "id": 10,
                "options": [
                    {
                        "title": "title of the option",
                        "id": 11
                    }
                ]
            },

# How to use
    - The dialogueSystem is a singleton, so you can get an instance of it by calling DialogueSystem.getInstance(). but it has to be initialized once first, by calling DialogueSystem.init();
    - To start a dialog, just create a new instance of the DialogTraverser class, and pass in the id of the dialog node you want to start the dialog from.
        - you should not save any references to this traverser. just call create it and the DialogueSystem will take care of the rest.