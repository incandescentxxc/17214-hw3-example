import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Santorini from './santorini';

import './index.css';
import StartPage from './startPage';

ReactDOM.render(
	<BrowserRouter>
		<Routes>
			<Route path="/" element={<StartPage />} />
			<Route path="game" element={<Santorini />} />
		</Routes>
	</BrowserRouter>,
	document.getElementById('root'),
);
