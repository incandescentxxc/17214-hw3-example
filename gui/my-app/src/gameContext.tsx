import React from "react";

const GameContext = React.createContext({
    gameId: "",
    setGameId: (gameId: String) => {},
});

export default GameContext;