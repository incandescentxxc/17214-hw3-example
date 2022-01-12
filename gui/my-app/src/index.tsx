import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './index.css';

import Santorini from './santorini';
import StartPage from './startPage';

ReactDOM.render(
	<BrowserRouter>
		<Routes>
			{/* if you need to add new pages, add a Route component below, specifying the path and its corresponding element */}
			<Route path="/" element={<StartPage />} />
			<Route path="game" element={<Santorini />} />
		</Routes>
	</BrowserRouter>,
	document.getElementById('root'),
);
