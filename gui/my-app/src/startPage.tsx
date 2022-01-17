import React, { FormEvent, SyntheticEvent, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import GameContext from './gameContext';
import './styles/startPage.css';

type Props = {};

const StartPage = (props: Props) => {
	// useState Hooks
	let [player1Name, setPlayer1Name] = useState('player1');
	let [player2Name, setPlayer2Name] = useState('player2');
	let [player1Power, setPlayer1Power] = useState('ATHENA');
	let [player2Power, setPlayer2Power] = useState('DEMETER');

	// useNavigate Hook, used for navigating to the other page
	let navigate = useNavigate();

	let handleChange = (event: FormEvent<HTMLInputElement>, playerId: string) => {
		if (playerId === 'player1') {
			setPlayer1Name(event.currentTarget.value);
		} else {
			setPlayer2Name(event.currentTarget.value);
		}
	};

	let handleSelect = (event: FormEvent<HTMLSelectElement>, playerId: string) => {
		if (playerId === 'player1') {
			setPlayer1Power(event.currentTarget.value);
		} else {
			setPlayer2Power(event.currentTarget.value);
		}
	};

	const { setGameId } = useContext(GameContext);

	let handleSubmit = (event: SyntheticEvent) => {
		let info = {
			name1: player1Name,
			name2: player2Name,
			power1: player1Power,
			power2: player2Power,
			startPlayer: 1,
		};
		// generate HTTP request to the backend
		fetch('/test', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(info),
		})
			.then((response) => {
				return response.json();
			})
			.then((res) => {
				setGameId(res.id);
			})
			.catch((err) => {
				console.log('frontend init the game failed', err);
			});
		// navigate to the game page
		navigate('/game');
	};

	return (
		<div id="form-area">
			<form onSubmit={handleSubmit} id="form">
				<h2>Welcome to Santorini</h2>
				<div className="form-row">
					<label>
						Player1 Name:
						<input
							className="form-input"
							type="text"
							value={player1Name}
							onChange={(e) => handleChange(e, 'player1')}
						/>
						Power:
						<select value={player1Power} onChange={(e) => handleSelect(e, 'player1')}>
							<option value="ATHENA">Athena</option>
							<option value="DEMETER">Demeter</option>
							<option value="PAN">Pan</option>
							<option value="MINOTAUR">Minotaur</option>
						</select>
					</label>
				</div>
				<div className="form-row">
					<label>
						Player2 Name:
						<input
							className="form-input"
							type="text"
							value={player2Name}
							onChange={(e) => handleChange(e, 'player2')}
						/>
						Power:
						<select value={player2Power} onChange={(e) => handleSelect(e, 'player2')}>
							<option value="ATHENA">Athena</option>
							<option value="DEMETER">Demeter</option>
							<option value="PAN">Pan</option>
							<option value="MINOTAUR">Minotaur</option>
						</select>
					</label>
				</div>
				<input type="submit" value="Start the Game!" />
			</form>
		</div>
	);
};
// explictly export the component
export default StartPage;
