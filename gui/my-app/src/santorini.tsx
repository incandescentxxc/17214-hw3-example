import React, { useContext, useEffect, useState } from 'react';
import GameContext from './gameContext';
import './styles/santorini.css';
import { GameData } from './types/types';

type santProps = {};

const Santorini = (props: santProps) => {
	let gameData = {};

	// useEffect(() => {
	// 	fetch('/check?id=', {
	//         method: "GET",
	//         headers: {
	//             'Content-Type': 'application/json'
	//         },
	//         body: JSON.stringify(info),
	//     }).then((response) => {
	//         return response.json();
	//     }).catch((err) => {
	//         console.log("frontend init the game failed", err);
	//     });
	// })

	const { gameId } = useContext(GameContext);

	return <div>Your Game Should Appear Here {gameId}</div>;
};

export default Santorini;
