## LDTS<13><03> - <BOMBERMAN>

> Our game consists in a Hero, known as, Bomberman who has the ability to drop BOMBS which are able to destroy some types of blocks and also to KILL the enemies. By destroying the blocks you will be able to create a path leading to the door (EXIT) and then win the game. You can also win the game by killing every robot, be CAREFUL!
This game was developed by *Diogo Babo* (up202004407@fe.up.pt), *João Oliveira* (*up202004407@fe.up.pt) and *Ricardo Cavalheiro* (up202005103@fe.up.pt) for LDTS 2021/2022.

### IMPLEMENTED FEATURES

- **Movement** - The game character (hero) will move depending on which arrow key is pressed.
- **Droping a Bomb** - The game character (hero) will drop a bomb where is he currently standing when the space bar is pressed. This bomb is able to destroy the non-concrete blocks but not the others. It also capable of killing the robots. (The bomb has a time of 4s to explode, and a range of 4 blocks, be careful, don't too close to it!).
- **Robots** - There will be many robots which move randomly (implemented already) or accordingly to the hero position (to develop further on!). These robots will try to kill you, so you better either avoid them or kill them!
- **End Game Message** - There will be a message displayed on the screen depending on if you win/lose. It will also show the time of survival.
  

### PLANNED FEATURES

- **Menu** - The menu with all the features to be implemented, is yet to be developed.
- **LeaderBoard** - You will be able to check the list of winners, based on their time for winning the game.
- **Shop** - Will you will be able to buy new characters and maybe powerups (not defined yet).

### DESIGN

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:

- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

**Example of one of such subsections**:

------

#### Methods to Draw each class

- **Problem in Context :** One problem we knew from the start we had to deal with was the different methods we had to create to draw each class of the game(Ex: Hero, Robot, Blocks, Bomb).


- **Factory Method :** So we have applied the **Factory Method** pattern. **Element** is an abstract class that has an abstract **draw** method which is overridden by the Hero and Robot classes; that way when you are drawing the board it knows how to draw each class. We also did the same thing for the Concrete Blocks, Destructable Blocks and Bomb.


- **Implementation :** Element is an abstract class which is responsible for creating the moving elements of the board but only the subclasses know how to draw themselves.

![img](docs/images/UML/Element.png)

These classes can be found in the following files:

- [Element](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1303/blob/109d3ca525284eabda8cbbab17f4a8623753fc18/src/main/java/com/aor/Element/Element.java)
- [Hero](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1303/blob/109d3ca525284eabda8cbbab17f4a8623753fc18/src/main/java/com/aor/Element/Hero.java)
- [Robot](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g1303/blob/109d3ca525284eabda8cbbab17f4a8623753fc18/src/main/java/com/aor/Element/Robot.java)


- **Consequences :**

The use of the Factory Method Pattern allows some benefits to the design:

- Removes the need to bind application-specific classes into your code. ///FIX
- You can construct objects step-by-step, defer construction steps or run steps recursively. ////FIX
- You can introduce new types of Elements into the program without changing existing code.

#### Diferents Strategies of a Robot

- **Problem in Context :** We wanted the Robots to have different ways of deciding where they wanna move in each step to make it harder for the user to win. 

- **Strategy Method :** We will use the **Strategy Pattern** to have different ways to move the Robot.Currently we have a strategy where Robots move randomly across the board and we are planning on adding another strategy where Robots move each time closer to the Hero making it harder for the user to win the game.

- **Implementation :** *Not implemented yet*

These classes can be found in the following files:

- [___]()
- [___]()
- [___]()

**Consequences**

The advantages of using the Strategy Pattern are the following:

- You can switch algorithms used inside an object at runtime.
- Clean code because you avoid conditional-infested code.
- Open/Closed Principle. You can add new strategies to the Robot without having to change the context.

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

**Example of such a subsection**

------

#### **DATA CLASS**

The `Position` class is a **Data Class**, because it contains fields and not a lot of behavior. However, it is not a bad code smell as it a consequence of the design we choose. It allows you to understand and organize the code and the operations are now gathered in a single place, instead of throughout the code.


### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.


- Diogo Babo: 33.3%
- João Oliveira: 33.3%
- Ricardo Cavalheiro: 33.3%
- Bruno Lima: 0.1%
