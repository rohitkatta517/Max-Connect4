# Max-Connect4
The game is played on a 6x7 grid, with six rows and seven columns. There are two players, player A (red) and player B (green). The two players take turns placing pieces on the board: the red player can only place red pieces, and the green player can only place green pieces.

It is best to think of the board as standing upright. We will assign a number to every row and column, as follows: columns are numbered from left to right, with numbers 1, 2, ..., 7. Rows are numbered from bottom to top, with numbers 1, 2, ..., 6. When a player makes a move, the move is completely determined by specifying the COLUMN where the piece will be placed. If all six positions in that column are occupied, then the move is invalid, and the program should reject it and force the player to make a valid move. In a valid move, once the column is specified, the piece is placed on that column and "falls down", until it reaches the lowest unoccupied position in that column.

The game is over when all positions are occupied. Obviously, every complete game consists of 42 moves, and each player makes 21 moves. The score, at the end of the game is determined as follows: consider each quadruple of four consecutive positions on board, either in the horizontal, vertical, or each of the two diagonal directions (from bottom left to top right and from bottom right to top left). The red player gets a point for each such quadruple where all four positions are occupied by red pieces. Similarly, the green player gets a point for each such quadruple where all four positions are occupied by green pieces. The player with the most points wins the game.

# Code Structure:
maxconnect contains main function.

GameBoard is all functions relating to the game like 
getting score, printing board, creaing board etc.

Algorithm used is Minimax with Alpha Beta pruning with depth limit

AiPlayer makes descision on which branch to take. Takes depth
as input to perform Depth limited Minimax.

Evaluation Function:
Assuming Player 2  is maximizing, Player1 minimizing.
If Board is full, if Player 2 wins it returns a large value.
if player1 wins it return negaive of large value.
0 if it is a draw.

If depth limit is reached before board is full:
we give weighted values as follows.
Four 2's: Count*1000
Three 2's: Count*50
Two 2's: Count*10

Four 1's: -Count*1000
Three 1's: -Count*50
Two 1's: -Count*10

Threes and twos considered above are only those values which may 
result in a 4.
Eg: 1110 will be considered, as 1111 is possible.
    1112 will not be considered, as 1111 can never be formed.

Evaluation function is a modification to 
http://isites.harvard.edu/fs/docs/icb.topic788088.files/Assignment%203/asst3c.pdf

# How to Run the code:
javac maxconnect4.java GameBoard.java AiPlayer.java

Interactive:
java maxconnect4 interactive input1.txt computer-next 9

One-Move:
java maxconnect4 one-move input1.txt out.txt 12
