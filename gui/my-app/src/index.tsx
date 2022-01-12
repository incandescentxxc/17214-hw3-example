import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import App from './santorini';

import './index.css';
import NameForm from './nameForm';

ReactDOM.render(
	<BrowserRouter>
		<Routes>
			<Route path="/" element={<NameForm />} />
			<Route path="game" element={<App />} />
		</Routes>
	</BrowserRouter>,
	document.getElementById('root'),
);
