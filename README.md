# AI-Search-Project

We have a board of dimensions m * n.
The object of the game is to reach the goal element starting from start element.
In the start and the goal elements, a value is set, and in the rest of the houses, a value is set with an operator.
(For example, in the house there is a starting value of 1 and in the house next to it which is not the goal '* 5'. In this case, if we move towards this house, our path will be worth 1 * 5 up to here.)
We start from the start element so that the result of the whole route is more than or equal to the amount written in the goal element.

Specific table symbols:
1. s: start element
2. g: goal element
3. w: walls (We can't pass these elements.)
4. a: passing this element means the goal value is increased by the amount written next to 'a'.
5. b: passing this element means the goal value is decreased by the amount written next to 'b'.
6. mathematics operators
