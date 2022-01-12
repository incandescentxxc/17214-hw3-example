import React, { FormEvent, SyntheticEvent, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './nameForm.css';

type Props = {};

const NameForm = (props: Props) => {
	// useState Hooks
	let [player1Name, setPlayer1Name] = useState('player1');
	let [player2Name, setPlayer2Name] = useState('player2');
	let [player1Power, setPlayer1Power] = useState('');
	let [player2Power, setPlayer2Power] = useState('');

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

	let handleSubmit = (event: SyntheticEvent) => {
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

export default NameForm;
