## Operation: new Game(int startPlayer, String name1, String name2, GodPower power1, GodPower power2)

- pre-condtion:

  No pre-condtion for this operation.

- post-conditions:

  1. Players are initialized based on corresponding subtype indicated by the power parameter.
  2. Players are initialized correctly with name, opponent, and workers information.
  3. Board, Worker are initialized correctly with built-in keys.

## Operation: placeWorker(int playerId, int positionX, int positionY)

- pre-conditions

  1. The game-related instances (board, grid, player, worker) have been created.
  2. PlayerId is valid and this player is in the right turn of this round.
  3. Player still has worker who has not been placed yet. If all the workers of some player have been placed, this operation cannot be done.
  4. positionX and positionY both are valid in the sense of that the position(x,y) is neither occupied nor out of boundary.

- post-conditions

  1. The worker is assigned a grid.
  2. The grid with the position will record the info of the worker.
  3. If all the workers of a player have been successfully placed, we changed the turn for the other player by updating the active player; if all players finish placing workers, change the Game Status to SELECT.
  4. Update the operable grids

## Operation: select(int workerId, int playerId, int posX, int posY)

- pre-conditions

  1. The workerId and playerId are valid.
  2. Player does own this worker.

- post-conditions
  1. Update the worker with an active status
  2. Update the Game Status to MOVE
  3. Update the operable grids

## Operation: move(int playerId, int posX, int posY)

- pre-conditions

  1. PlayerId is valid.
  2. All the workers of all players have been successfully placed.
  3. It is the turn of the this player, and the worker has not been moved yet in this round
  4. The worker of other player allows this move.
  5. positionX and positionY both are valid in the sense of the following,

     - position is not ouf of boundary of the board
     - position is adjacent to the position of worker
     - position is occupied but satisfies the following:
       - the position is not occupied by a worker belong to the same player
       - the worker on this occupied position can be pushed one step backward to an unoccuipied grid without going beyond the board.
     - position is not occupied but satisfies the following:
       - the difference of level between the position and the position of current worker is not greater than 1.
       - there is no tower with a dome located in the position

- post-conditions
  1. The worker's origin grid no longer stores the info of the worker. The new grid corresponding to the position now has the record of the worker. If there is a worker who was forced backwards, update its info as well.
  2. The worker records the new grid corresponding to the position, and eliminates the old one.
  3. This worker's isMoved is set true, which is expected to do a build operation, and cannot be move again.
  4. Check possible win, and possible lost of the opponent. If win, update the hasWon status.
  5. Update Game Status to BUILD.
  6. Update the operable grids.

## Operation: build(int playerId, int posX, int posY)

- pre-conditions

  1. The playerId is valid.
  2. The worker has just been moved.
  3. It is the turn of the player.
  4. The other player allows this build.
  5. positionX and positionY both are valid in the sense of the following,
     - position is not ouf of boundary of the board
     - position is adjacent to the position of worker
     - position is not occupied by another worker
     - there is no tower on that position or the tower on that position is not capped with a dome.

- post-conditions

  1. The tower information is recorded to the grid of that position.
  2. The "active" status and "isMoved" of the worker are cancelled off.
  3. The opponent has active status, indicating current player's turn is over.
  4. Check possible win, if won, update the hasWon status.
  5. update Game Status to SELECT.
  6. Update the operable grids.

## Operation: skip(int playerId, int posX, int posY)

- pre-conditions

  1. The playerId is valid.
  2. The Game Status is currently BUILD_OR_SKIP.

- post-conditions
  1. The "active" status and "isMoved" of the worker are cancelled off.
  2. The opponent has active status, indicating current player's turn is over.
  3. Check possible win, if won, update the hasWon status.
  4. update Game Status to SELECT.
  5. Update the operable grids.
