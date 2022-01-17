import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './styles/index.css';

import Santorini from './santorini';
import StartPage from './startPage';
import GameContext from './gameContext';

const App = () => {
	const [gameId, setGameId] = useState('');
	const gameCtx: any = { gameId, setGameId };
	return (
		<GameContext.Provider value={gameCtx}>
			<BrowserRouter>
				<Routes>
					{/* if you need to add new pages, add a Route component below, specifying the path and its corresponding element */}
					<Route path="/" element={<StartPage />} />
					<Route path="game" element={<Santorini />} />
				</Routes>
			</BrowserRouter>
		</GameContext.Provider>
	);
};

ReactDOM.render(
	<App />,
	document.getElementById('root'),
);
